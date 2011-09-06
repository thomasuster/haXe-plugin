package com.intellij.plugins.haxe.lang.parser;

import com.intellij.plugins.haxe.lang.lexer.HaxeElementType;
import com.intellij.plugins.haxe.lang.lexer.HaxeElementTypeImpl;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypes;

public interface HaxeElementTypes extends HaxeTokenTypes {
    HaxeElementType PACKAGE_DECLARATION = new HaxeElementTypeImpl("PackageDeclaration");
    HaxeElementType IMPORT_DECLARATION = new HaxeElementTypeImpl("ImportDeclaration");
    HaxeElementType IMPORT_PATH = new HaxeElementTypeImpl("ImportPath");
}
