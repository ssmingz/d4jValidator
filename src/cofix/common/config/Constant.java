/**
 * Copyright (C) SEI, PKU, PRC. - All Rights Reserved. Unauthorized copying of this file via any
 * medium is strictly prohibited Proprietary and Confidential. Written by Jiajun
 * Jiang<jiajun.jiang@pku.edu.cn>.
 */
package cofix.common.config;

/**
 * This class contains all constant variables
 *
 * @author Jiajun
 */
public class Constant {

    public static final String HOME = System.getProperty("user.dir");

    // common info
    public static final String SOURCE_FILE_SUFFIX = ".java";

    public static final String JAR_FILE_SUFFIX = ".jar";

    // build failed flag
    public static final String ANT_BUILD_FAILED = "BUILD FAILED";

    // code search configure
    public static final int MAX_BLOCK_LINE = 10;
    public static final boolean USE_GROUNDTRUTH = true;

    public static final String PATH_TO_JAVA8 = "/Library/Java/JavaVirtualMachines/jdk1.8.0_291.jdk/Contents/Home";

    // useful file path
    public static String PROJECT_HOME = null;
    public static String PROJ_INFO = HOME + "/d4j-info/src_path";
    public static String PROJ_JSON_FILE = HOME + "/d4j-info/project.json";
    public static String PROJ_LOG_BASE_PATH = HOME + "/log";
    public static String PROJ_REALTIME_LOC_BASE = HOME + "/d4j-info/realtime/location";

    public static String PATCH_BASE_PATH = "";

    // command configuration
    public static final String COMMAND_CD = "cd ";
    public static final int COMPILE_TIMEOUT = 120;
    public static final int SBFL_TIMEOUT = 3600;

    public static final String LOCATOR_HOME = HOME + "/sbfl";
    public static final String COMMAND_LOCATOR = LOCATOR_HOME + "/sbfl.sh ";
    public static final String LOCATOR_SUSP_FILE_BASE = LOCATOR_HOME + "/ochiai";
    public static String ENV_D4J = "DEFECTS4J_HOME";
    public static String COMMAND_TIMEOUT = "/usr/local/bin/gtimeout ";

    public static boolean is_server = false;
    public static String COMMAND_D4J = null;
}
