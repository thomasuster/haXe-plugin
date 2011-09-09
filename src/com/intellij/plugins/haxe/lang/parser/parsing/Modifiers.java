package com.intellij.plugins.haxe.lang.parser.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;

import java.util.HashSet;

public class Modifiers implements HaxeElementTypes {

    private static final HashSet<String> access = new HashSet<String>() {
        {
            add("public");
            add("private");
        }
    };

    private static final HashSet<String> attributes = new HashSet<String>() {
        {
            add("static");
            add("inline");
            add("dynamic");
            add("override");
        }
    };

    public static void parse(PsiBuilder builder, HaxeParser parser) {
        // access attribute or attribute access
        if (tryParseAttribute(builder)) {
            tryParseAccess(builder);
        } else {
            if (tryParseAccess(builder)) {
                tryParseAttribute(builder);
            }
        }

    }

    private static boolean tryParseAccess(PsiBuilder builder) {
        if (builder.getTokenType() == mIDENT && access.contains(builder.getTokenText())) {
            PsiBuilder.Marker marker = builder.mark();
            builder.advanceLexer();
            marker.done(ATTRIBUTE_MODIFIER);
            return true;
        }
        return false;
    }

    private static boolean tryParseAttribute(PsiBuilder builder) {
        if (builder.getTokenType() == mIDENT && attributes.contains(builder.getTokenText())) {
            PsiBuilder.Marker marker = builder.mark();
            builder.advanceLexer();
            marker.done(ACCESS_MODIFIER);
            return true;
        }
        return false;
    }
}
