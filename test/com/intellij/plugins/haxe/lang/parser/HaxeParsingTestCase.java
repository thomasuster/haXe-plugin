package com.intellij.plugins.haxe.lang.parser;

import com.intellij.openapi.application.PluginPathManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.plugins.haxe.lang.parser.util.TestUtils;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.DebugUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class HaxeParsingTestCase extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getBasePath() {
        String base = FileUtil.toSystemIndependentName(PluginPathManager.getPluginHomePathRelative("haXe-plugin")) + File.separator + "testdata" + File.separator;

        return base + File.separator + "parsing" + File.separator + "haxe" + File.separator;
    }

    @Test
    public void doTest() throws IOException {
        doTest(getTestName(true).replace("$", File.separator));
    }

    public void doTest(String testName) throws IOException {
        final String testIn = getTestDataPath() + testName + ".in.test";
        final String testOut = getTestDataPath() + testName + ".out.test";
        final PsiFile psiFile = TestUtils.createPseudoPhysicalHaxeFile(getProject(), TestUtils.readInput(testIn));
        String psiTree = DebugUtil.psiToString(psiFile, false);
        assertEquals(psiTree.trim(), TestUtils.readInput(testOut));
    }
}
