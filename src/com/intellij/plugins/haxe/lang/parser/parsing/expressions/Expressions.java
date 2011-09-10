package com.intellij.plugins.haxe.lang.parser.parsing.expressions;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;

public class Expressions implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        return BinaryExpression.parse(builder, parser);
    }
}
