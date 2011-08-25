package com.intellij.plugins.haxe.lang.lexer;

import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

import static com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypes.*;

public class HaxeLexer extends LookAheadLexer {

    private static final TokenSet tokensToMerge = TokenSet.create(
            mSL_COMMENT,
            mML_COMMENT,
            wsWS
    );

    public HaxeLexer() {
        super(new MergingLexerAdapter(new HaxeFlexLexer(), tokensToMerge));
    }
}