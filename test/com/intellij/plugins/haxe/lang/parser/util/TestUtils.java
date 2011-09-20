package com.intellij.plugins.haxe.lang.parser.util;

import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.LocalTimeCounter;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;

public class TestUtils {
    public static final String TEMP_FILE = "temp.hx";

    public static PsiFile createPseudoPhysicalHaxeFile(final Project project, final String text) throws IncorrectOperationException {
        return createPseudoPhysicalFile(project, TEMP_FILE, text);
    }


    public static PsiFile createPseudoPhysicalFile(final Project project, final String fileName, final String text) throws IncorrectOperationException {
        return PsiFileFactory.getInstance(project).createFileFromText(
                fileName,
                FileTypeManager.getInstance().getFileTypeByFileName(fileName),
                text,
                LocalTimeCounter.currentTime(),
                true);
    }

    public static String readInput(String filePath) throws IOException {
        String content = new String(FileUtil.loadFileText(new File(filePath)));

        Assert.assertTrue("No data found in source file: " + filePath, content.length() > 0);

        return content;
    }
}
