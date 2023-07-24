package cofix.main;

import cofix.common.config.Constant;
import cofix.common.util.JavaFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileProvider implements PatchProvider {
    String project;
    int bugId;

    public FileProvider(String proj, int bid) {
        project = proj;
        bugId = bid;
    }

    public List<String> fix() {
        File patches = new File(Constant.PATCH_BASE_PATH + "/" + project + "/" + bugId);
        if (!patches.isDirectory())
            return null;
        List<String> result = new ArrayList<>();
        for (File patch : patches.listFiles(f -> f.getName().endsWith(".java"))) {
            result.add(JavaFile.readFileToString(patch));
        }
        return result;
    }

    @Override
    public List<String> fix(String patchDir) {
        File patches = new File(patchDir);
        if (!patches.isDirectory())
            return null;
        List<String> result = new ArrayList<>();
        for (File patch : patches.listFiles(f -> f.getName().endsWith(".java"))) {
            result.add(JavaFile.readFileToString(patch));
        }
        return result;
    }
}
