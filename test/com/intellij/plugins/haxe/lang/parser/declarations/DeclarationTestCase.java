package com.intellij.plugins.haxe.lang.parser.declarations;

import com.intellij.plugins.haxe.lang.parser.HaxeParsingTestCase;

import java.io.File;

public class DeclarationTestCase extends HaxeParsingTestCase {
    @Override
    protected String getBasePath() {
        return super.getBasePath() + "declarations" + File.separator;
    }
}
