package com.intellij.plugins.haxe.lang.parser.parsing.statements;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class BlockStatement implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {

        PsiBuilder.Marker block = builder.mark();
        if (!ParserUtils.getToken(builder, pLCURLY)) {
            block.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);
        do {
            if (!parser.parseStatement(builder)) {
                break;
            }
            ParserUtils.skipNLS(builder);
        } while (!builder.eof() && builder.getTokenType() != pRCURLY);

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, pRCURLY, "right.curly.expected");

        block.done(BLOCK_STATEMENT);

        return true;

    }
}
