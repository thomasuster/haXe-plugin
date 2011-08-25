package com.intellij.plugins.haxe.lang.lexer;

import com.intellij.psi.tree.IElementType;

public interface HaxeTokenTypes {
    IElementType wsWS = new HaxeElementTypeImpl("WHITESPACE");
    IElementType wsNLS = new HaxeElementTypeImpl("WS_NEW_LINES");

    IElementType litCHAR = new HaxeElementTypeImpl("LITERAL_CHAR");
    IElementType litSTRING = new HaxeElementTypeImpl("LITERAL_STRING");

    IElementType litOCT = new HaxeElementTypeImpl("LITERAL_OCT");
    IElementType litINT = new HaxeElementTypeImpl("LITERAL_INT");
    IElementType litHEX = new HaxeElementTypeImpl("LITERAL_HEX");
    IElementType litFLOAT = new HaxeElementTypeImpl("LITERAL_FLOAT");
    IElementType litFLOAT_I = new HaxeElementTypeImpl("LITERAL_IMAGINARY_FLOAT");
    IElementType litDECIMAL_I = new HaxeElementTypeImpl("LITERAL_IMAGINARY_INTEGER");

    IElementType kBREAK = new HaxeElementTypeImpl("KEYWORD_BREAK");
    IElementType kDEFAULT = new HaxeElementTypeImpl("KEYWORD_DEFAULT");
    IElementType kPACKAGE = new HaxeElementTypeImpl("KEYWORD_PACKAGE");
    IElementType kFUNCTION = new HaxeElementTypeImpl("KEYWORD_FUNCTION");
    IElementType kINTERFACE = new HaxeElementTypeImpl("KEYWORD_INTERFACE");

    IElementType kCASE = new HaxeElementTypeImpl("KEYWORD_CASE");

    IElementType kSTRUCT = new HaxeElementTypeImpl("KEYWORD_STRUCT");
    IElementType kELSE = new HaxeElementTypeImpl("KEYWORD_ELSE");
    IElementType kSWITCH = new HaxeElementTypeImpl("KEYWORD_SWITCH");
    IElementType kCONST = new HaxeElementTypeImpl("KEYWORD_CONST");

    IElementType kIF = new HaxeElementTypeImpl("KEYWORD_IF");
    IElementType kFOR = new HaxeElementTypeImpl("KEYWORD_FOR");
    IElementType kRETURN = new HaxeElementTypeImpl("KEYWORD_RETURN");
    IElementType kIMPORT = new HaxeElementTypeImpl("KEYWORD_IMPORT");
    IElementType kCONTINUE = new HaxeElementTypeImpl("KEYWORD_CONTINUE");

    IElementType kRANGE = new HaxeElementTypeImpl("KEYWORD_RANGE");
    IElementType kTYPE = new HaxeElementTypeImpl("KEYWORD_TYPE");
    IElementType kVAR = new HaxeElementTypeImpl("KEYWORD_VAR");

    IElementType mWRONG = new HaxeElementTypeImpl("WRONG");

    IElementType mSL_COMMENT = new HaxeElementTypeImpl("SL_COMMENT");
    IElementType mML_COMMENT = new HaxeElementTypeImpl("ML_COMMENT");

    IElementType mIDENT = new HaxeElementTypeImpl("IDENTIFIER");

    IElementType pLCURCLY = new HaxeElementTypeImpl("{");
    IElementType pRCURLY = new HaxeElementTypeImpl("}");
    IElementType pLBRACK = new HaxeElementTypeImpl("[");
    IElementType pRBRACK = new HaxeElementTypeImpl("]");
    IElementType pLPAREN = new HaxeElementTypeImpl("(");
    IElementType pRPAREN = new HaxeElementTypeImpl(")");

    IElementType oSEMI = new HaxeElementTypeImpl(";");

    IElementType oTRIPLE_DOT = new HaxeElementTypeImpl("...");
    IElementType oDOT = new HaxeElementTypeImpl(".");
    IElementType oCOLON = new HaxeElementTypeImpl(":");
    IElementType oCOMMA = new HaxeElementTypeImpl(",");

    IElementType oEQ = new HaxeElementTypeImpl("==");
    IElementType oASSIGN = new HaxeElementTypeImpl("=");

    IElementType oNOT_EQ = new HaxeElementTypeImpl("!=");
    IElementType oNOT = new HaxeElementTypeImpl("!");

    IElementType oPLUS_PLUS = new HaxeElementTypeImpl("++");
    IElementType oPLUS_ASSIGN = new HaxeElementTypeImpl("+=");
    IElementType oPLUS = new HaxeElementTypeImpl("+");

    IElementType oMINUS_MINUS = new HaxeElementTypeImpl("--");
    IElementType oMINUS_ASSIGN = new HaxeElementTypeImpl("-=");
    IElementType oMINUS = new HaxeElementTypeImpl("-");

    IElementType oCOND_OR = new HaxeElementTypeImpl("||");
    IElementType oBIT_OR_ASSIGN = new HaxeElementTypeImpl("|=");
    IElementType oBIT_OR = new HaxeElementTypeImpl("|");

    IElementType oBIT_CLEAR_ASSIGN = new HaxeElementTypeImpl("&^=");
    IElementType oBIT_CLEAR = new HaxeElementTypeImpl("&^");
    IElementType oCOND_AND = new HaxeElementTypeImpl("&&");

    IElementType oBIT_AND_ASSIGN = new HaxeElementTypeImpl("&=");
    IElementType oBIT_AND = new HaxeElementTypeImpl("&");

    IElementType oBIT_XOR_ASSIGN = new HaxeElementTypeImpl("^=");
    IElementType oBIT_XOR = new HaxeElementTypeImpl("^");

    IElementType oMUL_ASSIGN = new HaxeElementTypeImpl("*=");
    IElementType oMUL = new HaxeElementTypeImpl("*");

    IElementType oQUOTIENT_ASSIGN = new HaxeElementTypeImpl("/=");
    IElementType oQUOTIENT = new HaxeElementTypeImpl("/");

    IElementType oREMAINDER_ASSIGN = new HaxeElementTypeImpl("%=");
    IElementType oREMAINDER = new HaxeElementTypeImpl("%");

    IElementType oSEND_CHANNEL = new HaxeElementTypeImpl("<-");
    IElementType oSHIFT_LEFT_ASSIGN = new HaxeElementTypeImpl("<<=");
    IElementType oSHIFT_LEFT = new HaxeElementTypeImpl("<<");

    IElementType oSHIFT_RIGHT_ASSIGN = new HaxeElementTypeImpl(">>=");
    IElementType oSHIFT_RIGHT = new HaxeElementTypeImpl(">>");

    IElementType oLESS_OR_EQUAL = new HaxeElementTypeImpl("<=");
    IElementType oLESS = new HaxeElementTypeImpl("<");

    IElementType oGREATER_OR_EQUAL = new HaxeElementTypeImpl(">=");
    IElementType oGREATER = new HaxeElementTypeImpl(">");

    IElementType oVAR_ASSIGN = new HaxeElementTypeImpl(":=");
}
