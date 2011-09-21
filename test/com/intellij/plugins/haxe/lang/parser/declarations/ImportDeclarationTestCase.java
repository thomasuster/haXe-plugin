package com.intellij.plugins.haxe.lang.parser.declarations;

import com.intellij.plugins.haxe.lang.parser.HaxeParsingTestCase;

import java.io.File;

public class ImportDeclarationTestCase extends HaxeParsingTestCase {

    @Override
    protected String getBasePath() {
        return super.getBasePath() + "declarations" + File.separator;
    }

    public void testImport$empty() throws Throwable {
        doTest();
    }

    public void testImport$multi() throws Throwable {
        doTest();
    }

    public void testImport$simple() throws Throwable {
        doTest();
    }
}
