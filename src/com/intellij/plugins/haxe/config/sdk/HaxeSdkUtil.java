package com.intellij.plugins.haxe.config.sdk;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.diagnostic.Logger;

import java.io.File;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HaxeSdkUtil {
    private static final Logger LOG = Logger.getInstance("#com.intellij.plugins.haxe.config.sdk.HaxeSdkUtil");
    private static final Pattern VERSION_MATCHER = Pattern.compile("(\\d+(\\.\\d+)+)");
    public final static String COMPILER_FOLDER = "haxe";
    public final static String COMPILER_NAME = "haxe.exe";
    public final static String NEKO_FOLDER = "neko";
    public final static String NEKO_NAME = "neko.exe";

    /*
        TODO check VM. Not only compiler.
     */
    public static HaxeSdkData testHaxeSdk(String path) {
        if (!checkFolderExists(path)) {
            return null;
        }

        GeneralCommandLine command = new GeneralCommandLine();
        command.setExePath(getCompilerPathByFolderPath(path));
        command.addParameter("-help");
        command.setWorkDirectory(path);

        try {
            ProcessOutput output = new CapturingProcessHandler(
                    command.createProcess(),
                    Charset.defaultCharset(),
                    command.getCommandLineString()).runProcess();

            if (output.getExitCode() != 0) {
                LOG.error("haXe compiler exited with invalid exit code: " + output.getExitCode());
                return null;
            }

            String outputString = output.getStdout();

            Matcher matcher = VERSION_MATCHER.matcher(outputString);
            if (matcher.find()) {
                return new HaxeSdkData(path, matcher.group(1));
            }

            return null;
        } catch (ExecutionException e) {
            LOG.error("Exception while executing the process:", e);
            return null;
        }
    }

    public static String getCompilerPathByFolderPath(String folderPath) {
        //TODO make for other OS
        File compilerFolder = new File(folderPath, COMPILER_FOLDER);
        File compilerFile = new File(compilerFolder, COMPILER_NAME);
        return compilerFile.getPath();
    }

    public static String getVMPathByFolderPath(String folderPath) {
        //TODO make for other OS
        File vmFolder = new File(folderPath, NEKO_FOLDER);
        File vmFile = new File(vmFolder, NEKO_NAME);
        return vmFile.getPath();
    }

    private static boolean checkFolderExists(String path) {
        return checkFolderExists(new File(path));
    }

    private static boolean checkFolderExists(File file) {
        return file.exists() && file.isDirectory();
    }
}
