package com.intellij.plugins.haxe.lang.parser;

import com.intellij.plugins.haxe.lang.lexer.HaxeElementType;
import com.intellij.plugins.haxe.lang.lexer.HaxeElementTypeImpl;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypes;

public interface HaxeElementTypes extends HaxeTokenTypes {
    HaxeElementType PACKAGE_DECLARATION = new HaxeElementTypeImpl("PackageDeclaration");
    HaxeElementType IMPORT_DECLARATION = new HaxeElementTypeImpl("ImportDeclaration");
    HaxeElementType CLASS_DECLARATION = new HaxeElementTypeImpl("ClassDeclaration");
    HaxeElementType FUNCTION_DECLARATION = new HaxeElementTypeImpl("FunctionDeclaration");
    HaxeElementType TEMPLATE_DECLARATION = new HaxeElementTypeImpl("TemplateDeclaration");
    HaxeElementType PACKAGE_PATH = new HaxeElementTypeImpl("PackagePath");
    HaxeElementType ACCESS_MODIFIER = new HaxeElementTypeImpl("AccessModifier");
    HaxeElementType ATTRIBUTE_MODIFIER = new HaxeElementTypeImpl("AttributeModifier");
    HaxeElementType VAR_FUNCTION_NAME = new HaxeElementTypeImpl("VarFunctionName");
    HaxeElementType VAR_CONST_DECLARATION = new HaxeElementTypeImpl("VarConstDeclaration");
    HaxeElementType TYPE = new HaxeElementTypeImpl("Type");
    HaxeElementType BLOCK_STATEMENT = new HaxeElementTypeImpl("BlockStatement");
    HaxeElementType LITERAL = new HaxeElementTypeImpl("Literal");
    HaxeElementType EXPRESSION = new HaxeElementTypeImpl("Expression");
    HaxeElementType INSTANTIATION = new HaxeElementTypeImpl("Instantiation ");
}
