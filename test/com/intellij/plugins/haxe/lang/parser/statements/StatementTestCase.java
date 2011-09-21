package com.intellij.plugins.haxe.lang.parser.statements;

import com.intellij.plugins.haxe.lang.parser.HaxeParsingTestCase;

import java.io.File;

public class StatementTestCase extends HaxeParsingTestCase {
    @Override
    protected String getBasePath() {
        return super.getBasePath() + "statements" + File.separator;
    }
}
