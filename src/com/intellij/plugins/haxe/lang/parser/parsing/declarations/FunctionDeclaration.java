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

        if (classMember) {
            ParserUtils.skipNLS(builder);
            if (!ParserUtils.getToken(builder, oNEW) && !ParserUtils.getToken(builder, mIDENT)) {
                builder.error(HaxeBundle.message("identifier.expected"));
                return false;
            }
        }

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, pLPAREN, HaxeBundle.message("parameters.list.expected"));

        ParserUtils.skipNLS(builder);
        TypedVariableDeclaration.parseList(builder, parser);

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, pRPAREN, HaxeBundle.message("parameters.list.expected"));

        TypedVariableDeclaration.parseTypeAssign(builder, parser);

        marker.done(FUNCTION_DECLARATION);

        parser.parseBlock(builder);

        return true;
    }
}
