package com.intellij.plugins.haxe.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import java.util.*;
import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;

%%

%unicode
%class _HaxeLexer
%implements FlexLexer, HaxeTokenTypes
%unicode
%public

%function advance
%type IElementType

%eof{ return;
%eof}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////// User code //////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

%{

  private Stack <IElementType> gStringStack = new Stack<IElementType>();
  private Stack <IElementType> blockStack = new Stack<IElementType>();

  private int afterComment = YYINITIAL;
  private int afterNls = YYINITIAL;
  private int afterBrace = YYINITIAL;

  private void clearStacks(){
    gStringStack.clear();
    blockStack.clear();
  }

  private Stack<IElementType> braceCount = new Stack <IElementType>();

%}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////// NewLines and spaces /////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

mNL = [\r\n] | \r\n      // NewLinE
mWS = [ \t\f]    // Whitespaces

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////// Comments ////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

mSL_COMMENT = "//" [^\r\n]*

mLETTER = [:letter:] | "_"
mDIGIT = [:digit:]

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////      integers and floats     /////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

mHEX_DIGIT = [0-9A-Fa-f]
mINT_DIGIT = [0-9]
mOCT_DIGIT = [0-7]

mNUM_INT = "0" | ([1-9] {mINT_DIGIT}*)
mNUM_HEX = ("0x" | "0X") {mHEX_DIGIT}+
mNUM_OCT = "0" {mOCT_DIGIT}+

mFLOAT_EXPONENT = [eE] [+-]? {mDIGIT}+
mNUM_FLOAT = ( ( ({mDIGIT}+ "." {mDIGIT}*) | ({mDIGIT}* "." {mDIGIT}+) ) {mFLOAT_EXPONENT}?) | ({mDIGIT}+ {mFLOAT_EXPONENT})

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////      identifiers      ////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

mIDENT = {mLETTER} ({mLETTER} | {mDIGIT} )*

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////// String & regexprs ///////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

%state IN_CHAR_LITERAL
%state IN_STRING_LITERAL

%%

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////// Reserved shorthands //////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

<YYINITIAL> {
"|"                                       { return oBIT_OR; }

{mWS}                                     { return wsWS; }
{mNL}+                                    { return wsNLS; }

{mSL_COMMENT}                             { return( mSL_COMMENT ); }
"/*" ( ([^"*"]|[\r\n])* ("*"+ [^"*""/"] )? )* ("*" | "*"+"/")? { return( mML_COMMENT ); }

"..."                                     { return oTRIPLE_DOT; }
"."                                       { return oDOT; }

"'" . "'"                                               { return litCHAR; }
"'" \n "'"                                              { return litCHAR; }
"'\\" [abfnrtv\\\'] "'"                                 { return litCHAR; }
"'\\" {mOCT_DIGIT} {mOCT_DIGIT} {mOCT_DIGIT} "'"        { return litCHAR; }
"'\\x" {mHEX_DIGIT} {mHEX_DIGIT} "'"                    { return litCHAR; }
"'\\u" {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} "'"
                                                        { return litCHAR; }
"'\\U" {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} {mHEX_DIGIT} "'"
                                                        { return litCHAR; }

"`" [^`]* "`"                             { return litSTRING; }
"\"" ("\\\"" | [^\"])* "\""               { return litSTRING; }
"{"                                       { return pLCURCLY; }
"}"                                       { return pRCURLY; }

"["                                       { return pLBRACK; }
"]"                                       { return pRBRACK; }

"("                                       { return pLPAREN; }
")"                                       { return pRPAREN; }

":"                                       { return oCOLON; }
";"                                       { return oSEMI; }
","                                       { return oCOMMA; }

"=="                                      { return oEQ; }
"="                                       { return oASSIGN; }

"!="                                      { return oNOT_EQ; }
"!"                                       { return oNOT; }

"++"                                      { return oPLUS_PLUS; }
"+="                                      { return oPLUS_ASSIGN; }
"+"                                       { return oPLUS; }

"--"                                      { return oMINUS_MINUS; }
"-="                                      { return oMINUS_ASSIGN; }
"-"                                       { return oMINUS; }

"||"                                      { return oCOND_OR; }
"|="                                      { return oBIT_OR_ASSIGN; }


"&^="                                     { return oBIT_CLEAR_ASSIGN; }
"&^"                                      { return oBIT_CLEAR; }
"&&"                                      { return oCOND_AND; }

"&="                                      { return oBIT_AND_ASSIGN; }
"&"                                       { return oBIT_AND; }

"<<="                                     { return oSHIFT_LEFT_ASSIGN; }
"<<"                                      { return oSHIFT_LEFT; }
"<-"                                      { return oSEND_CHANNEL; }
"<="                                      { return oLESS_OR_EQUAL; }
"<"                                       { return oLESS; }

"^="                                      { return oBIT_XOR_ASSIGN; }
"^"                                       { return oBIT_XOR; }

"*="                                      { return oMUL_ASSIGN; }
"*"                                       { return oMUL; }

"/="                                      { return oQUOTIENT_ASSIGN; }
"/"                                       { return oQUOTIENT; }

"%="                                      { return oREMAINDER_ASSIGN; }
"%"                                       { return oREMAINDER; }

">>="                                     { return oSHIFT_RIGHT_ASSIGN; }
">>"                                      { return oSHIFT_RIGHT; }
">="                                      { return oGREATER_OR_EQUAL; }
">"                                       { return oGREATER; }

":="                                      { return oVAR_ASSIGN; }


"break"                                   { return( kBREAK );  }
"default"                                 { return( kDEFAULT );  }
"package"                                 { return( kPACKAGE );  }
"function"                                { return( kFUNCTION );  }
"interface"                               { return( kINTERFACE );  }

"case"                                    { return( kCASE );  }

"struct"                                  {  return( kSTRUCT );  }
"else"                                    {  return( kELSE );  }
"switch"                                  {  return( kSWITCH );  }
"const"                                   {  return( kCONST ); }

"if"                                      {  return kIF ;  }
"for"                                     {  return kFOR ;  }
"return"                                  {  return kRETURN ;  }
"import"                                  {  return kIMPORT ;  }
"continue"                                {  return kCONTINUE ;  }

"var"                                     {  return kVAR;  }

{mIDENT}                                  {  return mIDENT; }

{mNUM_FLOAT}"i"                           {  return litFLOAT_I; }
{mNUM_FLOAT}                              {  return litFLOAT; }
{mDIGIT}+"i"                              {  return litDECIMAL_I; }
{mNUM_OCT}                                {  return litOCT; }
{mNUM_HEX}                                {  return litHEX; }
{mNUM_INT}                                {  return litINT; }

.                                         {  return mWRONG; }
}