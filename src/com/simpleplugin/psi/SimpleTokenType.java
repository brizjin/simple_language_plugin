package com.simpleplugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.simpleplugin.SimpleLanguage;
import com.simpleplugin.psi.impl.SimplePropertyImpl;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

// IDEA wants just token types here not token objects
public class SimpleTokenType extends IElementType {
	public static final IElementType PROPERTY = new SimpleElementType("PROPERTY");
	public static final IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
	public static final TokenSet WHITE_SPACES = TokenSet.create(WHITE_SPACE);
	public static final IElementType COMMENT = new SimpleTokenType("COMMENT");
	public static final TokenSet COMMENTS = TokenSet.create(COMMENT);
	public static final IElementType CRLF = new SimpleTokenType("CRLF");
	public static final IElementType KEY = new SimpleTokenType("KEY");
	public static final IElementType SEPARATOR = new SimpleTokenType("SEPARATOR");
	public static final IElementType VALUE = new SimpleTokenType("VALUE");

	public SimpleTokenType(@NotNull @NonNls String debugName) {
		super(debugName, SimpleLanguage.INSTANCE);
	}

	@Override
	public String toString() {
		return "SimpleTokenType." + super.toString();
	}

	public static class Factory {
		public static PsiElement createElement(ASTNode node) {
			System.out.println("createElement from "+node);
			IElementType type = node.getElementType();
			if (type == PROPERTY) {
				return new SimplePropertyImpl(node);
			}
			throw new AssertionError("Unknown element type: " + type);
		}
	}
}
