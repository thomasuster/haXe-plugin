package com.intellij.plugins.haxe.highlight;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class HaxeEditorHighlighter extends LayeredLexerEditorHighlighter {
    public HaxeEditorHighlighter(EditorColorsScheme scheme, Project project, VirtualFile virtualFile) {
        super(new HaxeSyntaxHighlighter(), scheme);
    }
}