package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class FunctionDeclaration implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser, boolean classMember) {
        PsiBuilder.Marker marker = builder.mark();

        if (!ParserUtils.getToken(builder, kFUNCTION)) {
            marker.rollbackTo();
            return false;
        }

        boolean isConstructor = false;
        if (classMember) {
            ParserUtils.skipNLS(builder);
            //try constructor
            if (ParserUtils.getToken(builder, oNEW)) {
                isConstructor = true;
            } else if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("identifier.expected"))) {
                return false;
            }
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pLPAREN, HaxeBundle.message("parameters.list.expected"))) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!TypedVariableDeclaration.parseList(builder, parser)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pRPAREN, HaxeBundle.message("parameters.list.expected"))) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!isConstructor && !TypedVariableDeclaration.parseTypeAssign(builder, parser)) {
            return false;
        }

        marker.done(FUNCTION_DECLARATION);

        parser.parseBlock(builder);

        return true;
    }
}
