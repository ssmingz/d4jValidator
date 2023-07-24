package cofix.main;

import cofix.common.run.Runner;
import cofix.common.util.JavaFile;
import cofix.common.util.Pair;
import cofix.common.util.Subject;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Validator {

    private final ASTNode _bugNode;
    private final String originalCode;
    private final String filePath;
    private final Subject _subject;

    private int _startLine = -1;
    private int _endLine = -1;


    private static class MethodFinder extends ASTVisitor {

        private final int _line;
        private final CompilationUnit _cu;
        private ASTNode _target = null;

        private int _startLine = -1;
        private int _endLine = -1;

        public MethodFinder(CompilationUnit cu, Integer line) {
            _line = line;
            _cu = cu;
        }

        public boolean visit(MethodDeclaration node) {
            int startLine = _cu.getLineNumber(node.getStartPosition());
            int endLine = _cu.getLineNumber(node.getStartPosition() + node.getLength());

            if (startLine <= _line && endLine >= _line) {
                _target = node;
                _startLine = startLine;
                _endLine = endLine;
                return false;
            }

            return true;
        }

        public ASTNode getMethod() {
            return _target;
        }

        public int getStartLine() {
            return _startLine;
        }

        public int getEndLine() {
            return _endLine;
        }


    }

    public enum ValidateStatus {
        COMPILE_FAILED,
        TEST_FAILED,
        SUCCESS
    }

    public static String replaceLines(String original, String replacement, int i, int j) {
        String[] lines = original.split("\n");
        StringBuilder result = new StringBuilder();

        for (int k = 0; k < lines.length; k++) {
            if (k >= i - 1 && k <= j - 1) {
                if (k == i - 1) {
                    result.append(replacement);
                }
                continue;
            }
            result.append(lines[k]);
            if (k != lines.length - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public Validator(Subject subject, Pair<String, Integer> loc) {
        filePath = JavaFile.class2Path(subject, loc.getFirst());
        CompilationUnit cu =
                JavaFile.genASTFromFileWithType(filePath);
        MethodFinder finder = new MethodFinder(cu, loc.getSecond());
        cu.accept(finder);
        _bugNode = finder.getMethod();
        originalCode = JavaFile.readFileToString(filePath);
        _subject = subject;
        _startLine = finder.getStartLine();
        _endLine = finder.getEndLine();
    }

    public void validate(PatchProvider method) {
        List<String> patches = method.fix();
        for (String patch : patches) {
            String newCode = replaceLines(originalCode, patch, _startLine, _endLine);
            JavaFile.writeStringToFile(filePath, newCode);
            ValidateStatus status = compileAndTest();
            JavaFile.writeStringToFile(filePath, originalCode);
            //You can print some essential information here
            switch (status) {
                case COMPILE_FAILED:
                    System.out.println("Compile failed");
                    break;
                case TEST_FAILED:
                    System.out.println("Test failed");
                    break;
                case SUCCESS:
                    System.out.println("Success");
            }
        }
    }

    private ValidateStatus compileAndTest() {
        if (!Runner.compileSubject(_subject)) {
            return ValidateStatus.COMPILE_FAILED;
        }

        for (String testcase : _subject.getAbstractFaultlocalization()
                .getFailedTestCases()) {
            String[] testinfo = testcase.split("::");
            if (!Runner.testSingleTest(_subject, testinfo[0], testinfo[1])) {
                return ValidateStatus.TEST_FAILED;
            }
        }

        if (!Runner.runTestSuite(_subject)) {
            return ValidateStatus.TEST_FAILED;
        }

        return ValidateStatus.SUCCESS;
    }

}
