package com.intellij.plugins.haxe.lang.parser.parsing.statements;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.declarations.VarOrConstDeclaration;

public class Statements implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        if (builder.getTokenType() == kVAR || builder.getTokenType() == kCONST) {
            return VarOrConstDeclaration.parse(builder, parser);
        }
        return false;
    }
}
