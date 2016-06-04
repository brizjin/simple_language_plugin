package com.simpleplugin;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;

/** I'm using an adapter between Intellij and a simple lexer of my
 *  own design, in preparation for  using ANTLR instead.
 */
public class MyLexerAdaptor extends LexerBase {
	MyLexer lexer;
	CharSequence buffer;
	int startOffset;
	int endOffset;
	int initialState;

	@Override
	public void advance() {
		IElementType token = lexer.nextToken();
//		System.out.println("advance()");
	}

	/** API doc says that the end offset is the offset but in fact it seems
	 * 	to be the length.  Access buffer[endOffset] to enjoy and exception.
	 */
	@Override
	public void start(CharSequence buffer, int startOffset, int endOffset, int initialState) {
//		System.out.println("start: "+buffer+", "+startOffset+", "+endOffset+", "+initialState);
		this.buffer = buffer;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.initialState = initialState;
		lexer = new MyLexer(buffer.subSequence(startOffset, endOffset).toString());
	}

	@Override
	public int getState() {
		return initialState;
	}

	@Nullable
	@Override
	public IElementType getTokenType() {
		IElementType type = lexer.getTokenType();
//		System.out.println("getTokenType: "+type);
		return type;
	}

	/** Intellij wants token types not token objects and so it must ask for
	 *  the start and stop indexes for each token.
	 */
	@Override
	public int getTokenStart() {
		return startOffset + lexer.start;
	}

	@Override
	public int getTokenEnd() {
		// seems like stop must be one PAST the last char for this token
		return startOffset + lexer.stop + 1;
	}

	@Override
	public CharSequence getBufferSequence() {
		return buffer;
	}

	@Override
	public int getBufferEnd() {
		return endOffset;
	}
}
