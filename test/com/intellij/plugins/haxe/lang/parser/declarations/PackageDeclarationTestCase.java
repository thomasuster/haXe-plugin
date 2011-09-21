package com.intellij.plugins.haxe.lang.parser.declarations;

import com.intellij.plugins.haxe.lang.parser.HaxeParsingTestCase;

import java.io.File;

public class PackageDeclarationTestCase extends HaxeParsingTestCase {

    @Override
    protected String getBasePath() {
        return super.getBasePath() + "declarations" + File.separator;
    }

    public void testPackage$empty() throws Throwable {
        doTest();
    }

    public void testPackage$error() throws Throwable {
        doTest();
    }

    public void testPackage$simple() throws Throwable {
        doTest();
    }
}
