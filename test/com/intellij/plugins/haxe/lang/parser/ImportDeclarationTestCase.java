package com.intellij.plugins.haxe.lang.parser;

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
