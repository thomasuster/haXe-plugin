package com.intellij.plugins.haxe.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

import static com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypes.*;

public interface HaxeTokenTypeSets {

    static final TokenSet WHITESPACES = TokenSet.create(
            wsWS,
            TokenType.WHITE_SPACE
    );

    static final TokenSet COMMENTS = TokenSet.create(
            mSL_COMMENT, mML_COMMENT
    );

    static final TokenSet BLOCK_COMMENTS = TokenSet.create(
            mML_COMMENT
    );

    static final TokenSet NUMBERS = TokenSet.create(
            litINT,
            litHEX,
            litOCT,
            litFLOAT
    );

    static final TokenSet LINE_COMMENTS = TokenSet.create(
            mSL_COMMENT
    );

    static final TokenSet BAD_TOKENS = TokenSet.create(
            mWRONG
    );

    static final TokenSet STRINGS = TokenSet.create(
            litSTRING
    );

    static final TokenSet IDENTIFIERS = TokenSet.create(
            mIDENT
    );

    static final TokenSet BRACES = TokenSet.create(
            pLBRACK,
            pRBRACK,
            pLPAREN,
            pRPAREN,
            pLCURLY,
            pRCURLY
    );

    static final TokenSet LITERALS = TokenSet.create(
            litCHAR,
            litFLOAT,
            litSTRING,
            litINT,
            litHEX,
            litOCT
    );

    public static final TokenSet KEYWORDS = TokenSet.create(
            kPACKAGE,
            kIMPORT,
            kBREAK,
            kCASE,
            kCONST,
            kCONTINUE,
            kDEFAULT,
            kELSE,
            kFOR,
            kDO,
            kWHILE,
            kFUNCTION,
            kIF,
            kIMPORT,
            kINTERFACE,
            kPACKAGE,
            kRETURN,
            kCLASS,
            kSWITCH,
            kVAR,
            kENUM,
            kINTERFACE,
            kTHROW,
            kIMPLEMENTS,
            kEXTENDS
    );

    public static final TokenSet UNARY_OPERATORS = TokenSet.create(
            oPLUS_PLUS, oMINUS_MINUS, oNOT
    );

    public static final TokenSet ASSIGN_OPERATORS = TokenSet.create(
            oASSIGN,
            oPLUS_ASSIGN, oMINUS_ASSIGN, oBIT_OR_ASSIGN, oBIT_XOR_ASSIGN,
            oMUL_ASSIGN, oQUOTIENT_ASSIGN, oREMAINDER_ASSIGN,
            oSHIFT_LEFT_ASSIGN, oSHIFT_RIGHT_ASSIGN,
            oBIT_AND_ASSIGN, oBIT_CLEAR_ASSIGN
    );

    public static final TokenSet BINARY_OPERATORS = TokenSet.create(
            oCOND_OR, oCOND_AND,
            oSEND_CHANNEL,
            oEQ, oNOT_EQ, oLESS, oLESS_OR_EQUAL, oGREATER, oGREATER_OR_EQUAL,
            oPLUS, oMINUS, oBIT_OR, oBIT_XOR,
            oMUL, oQUOTIENT, oREMAINDER, oSHIFT_LEFT, oSHIFT_RIGHT, oBIT_AND, oBIT_CLEAR
    );

    public static final TokenSet INC_DEC_OPERATORS = TokenSet.create(
            oPLUS_PLUS, oMINUS_MINUS
    );

    public static final TokenSet OPERATORS = TokenSet.create(
            oSEMI,
            oTRIPLE_DOT,
            oDOT,
            oCOLON,
            oCOMMA,
            oEQ,
            oASSIGN,
            oNOT_EQ,
            oNOT,
            oPLUS_PLUS,
            oPLUS_ASSIGN,
            oPLUS,
            oMINUS_MINUS,
            oMINUS_ASSIGN,
            oMINUS,
            oCOND_OR,
            oBIT_OR_ASSIGN,
            oBIT_OR,
            oBIT_CLEAR_ASSIGN,
            oBIT_CLEAR,
            oCOND_AND,
            oBIT_AND_ASSIGN,
            oBIT_AND,
            oSHIFT_LEFT_ASSIGN,
            oSHIFT_LEFT,
            oSEND_CHANNEL,
            oLESS_OR_EQUAL,
            oLESS,
            oBIT_XOR_ASSIGN,
            oBIT_XOR,
            oMUL_ASSIGN,
            oMUL,
            oQUOTIENT_ASSIGN,
            oQUOTIENT,
            oREMAINDER_ASSIGN,
            oREMAINDER,
            oSHIFT_RIGHT_ASSIGN,
            oSHIFT_RIGHT,
            oGREATER_OR_EQUAL,
            oGREATER,
            oVAR_ASSIGN,
            oNEW
    );

}

