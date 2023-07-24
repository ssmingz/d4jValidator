package cofix.main;

import java.util.ArrayList;
import java.util.List;

public class StaticProvider implements PatchProvider {

    final
    public List<String> fix(String func) {
        List<String> patches = new ArrayList<>();
        String correctPatch =
                "public ValueMarker(double value, Paint paint, Stroke stroke, \n"
                        + "        Paint outlinePaint, Stroke outlineStroke, float alpha) {\n"
                        + "        super(paint, stroke, outlinePaint, outlineStroke, alpha);\n"
                        + "        this.value = value;\n"
                        + " }";
        String compileFailedPath =
                "public ValueMarker(double value, Paint paint, Stroke stroke, \n"
                        + "        Paint outlinePaint, Stroke outlineStroke, float alpha) {\n"
                        + "        super(paint, stroke, outlinePaint, outlineStroke, alpha);\n"
                        + "        this.val\n"
                        + " }";
        patches.add(correctPatch);
        patches.add(compileFailedPath);
        return patches;
    }

    @Override
    public List<String> fix() {
        List<String> patches = new ArrayList<>();
        String correctPatch =
                "public ValueMarker(double value, Paint paint, Stroke stroke, \n"
                        + "        Paint outlinePaint, Stroke outlineStroke, float alpha) {\n"
                        + "        super(paint, stroke, outlinePaint, outlineStroke, alpha);\n"
                        + "        this.value = value;\n"
                        + " }";
        String compileFailedPath =
                "public ValueMarker(double value, Paint paint, Stroke stroke, \n"
                        + "        Paint outlinePaint, Stroke outlineStroke, float alpha) {\n"
                        + "        super(paint, stroke, outlinePaint, outlineStroke, alpha);\n"
                        + "        this.val\n"
                        + " }";
        patches.add(correctPatch);
        patches.add(compileFailedPath);
        return patches;
    }
}
