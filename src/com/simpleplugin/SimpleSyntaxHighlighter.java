package com.simpleplugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.SimpleTokenType;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SimpleSyntaxHighlighter extends SyntaxHighlighterBase {
	public static final TextAttributesKey SEPARATOR = createTextAttributesKey("SIMPLE_SEPARATOR", SyntaxHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey KEY = createTextAttributesKey("SIMPLE_KEY", SyntaxHighlighterColors.KEYWORD);
	public static final TextAttributesKey VALUE = createTextAttributesKey("SIMPLE_VALUE", SyntaxHighlighterColors.STRING);
	public static final TextAttributesKey COMMENT = createTextAttributesKey("SIMPLE_COMMENT", SyntaxHighlighterColors.LINE_COMMENT);

	static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("SIMPLE_BAD_CHARACTER",
																		   new TextAttributes(Color.RED, null, null, null, Font.BOLD));

	private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
	private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
	private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
	private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
	private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
	private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		return new MyLexerAdaptor();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		System.out.println("Highlight tokenType: "+tokenType);
		if (tokenType.equals(SimpleTokenType.SEPARATOR)) {
			return SEPARATOR_KEYS;
		} else if (tokenType.equals(SimpleTokenType.KEY)) {
			return KEY_KEYS;
		} else if (tokenType.equals(SimpleTokenType.VALUE)) {
			return VALUE_KEYS;
		} else if (tokenType.equals(SimpleTokenType.COMMENT)) {
			return COMMENT_KEYS;
		} else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
			return BAD_CHAR_KEYS;
		} else {
			System.out.println("Highlight tokenType: EMPTY_KEYS");
			return EMPTY_KEYS;
		}
	}
}