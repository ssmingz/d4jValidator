package cofix.main;

import java.util.List;

public interface PatchProvider {

    public List<String> fix(String func);

    public List<String> fix();
}
