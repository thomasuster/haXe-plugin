package com.intellij.plugins.haxe.lang.parser.declarations;

import com.intellij.plugins.haxe.lang.parser.HaxeParsingTestCase;

import java.io.File;

public class ClassDeclarationTestCase extends HaxeParsingTestCase {

    @Override
    protected String getBasePath() {
        return super.getBasePath() + "declarations" + File.separator;
    }

    public void testClass$extends() throws Throwable {
        doTest();
    }

    public void testClass$extendsimplements() throws Throwable {
        doTest();
    }

    public void testClass$implements() throws Throwable {
        doTest();
    }

    public void testClass$multiextends() throws Throwable {
        doTest();
    }

    public void testClass$simple() throws Throwable {
        doTest();
    }
}
