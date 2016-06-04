package com.simpleplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.simpleplugin.psi.SimpleFile;
import com.simpleplugin.psi.SimpleTokenType;
import org.jetbrains.annotations.NotNull;

public class SimpleParserDefinition implements ParserDefinition{
	public static final IFileElementType FILE = new IFileElementType(Language.<SimpleLanguage>findInstance(SimpleLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new MyLexerAdaptor();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return SimpleTokenType.WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return SimpleTokenType.COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new MyParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SimpleFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return SimpleTokenType.Factory.createElement(node);
    }
}
