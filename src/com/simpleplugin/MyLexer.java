package com.simpleplugin;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.SimpleTokenType;

/** Manually lex to remove jflex dependency

 CRLF= \n|\r|\r\n
 WHITE_SPACE=[\ \t\f]
 FIRST_VALUE_CHARACTER=[^ \n\r\f\\] | "\\"{CRLF} | "\\".
 VALUE_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".
 END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
 SEPARATOR=[:=]
 KEY_CHARACTER=[^:=\ \n\r\t\f\\] | "\\"{CRLF} | "\\".

 // IDEA wants null for EOF
 */
public class MyLexer {
	String text;
	int p; // ptr into text of next char to match
	int start, stop; // token start/stop indexes
	int c; // current char
	IElementType prevNonWSToken; // track = for value vs keyword detection
	IElementType token; // track for getTokenType (current token)

	boolean eof = false;

	public MyLexer(String text) {
		this.text = text;
		p = -1;
		consume();
	}

	private void consume() {
		p++;
		if ( p<text.length() ) c = text.charAt(p);
	}

	public IElementType getTokenType() {
		if ( token == null && !eof ) {
			nextToken();
		}
		return token;
	}

	public IElementType nextToken() {
//		System.out.println("nextToken at "+p);
		// advance
		int n = text.length();
		if ( p>=n ) {
			token = null;
			eof = true;
			return null;  // IDEA wants null for EOF
		}
		IElementType token;
		start = p;
		if (isWS(c)) {
			while ( p<n && isWS(c) ) { // don't consume end of line
				consume();
			}
			stop = p-1;
			// IDEA wants whitespace char
			token = TokenType.WHITE_SPACE;
		}
		else if ( c=='\n' ) {
			consume();
			token = SimpleTokenType.CRLF;
			stop = p-1;
		}
		else if ( c=='\r' ) {
			consume();
			if ( c=='\n' ) {
				consume();
			}
			token = SimpleTokenType.CRLF;
		}
		else if ( c=='#' || c=='!' ) {
			while ( p<n && c!='\n' ) {
				consume();
			}
			stop = p-1;
			token = SimpleTokenType.COMMENT;
			prevNonWSToken = token;
		}
		else if ( c=='=' || c==':' ) {
			stop = p;
			consume();
			token = SimpleTokenType.SEPARATOR;
			prevNonWSToken = token;
		}
		else {
			// must be a key or value
			while ( p<n && !isWS(c) && !isNL(c) ) {
				if ( c=='\\' ) {
					consume();
				}
				consume();
			}
			stop = p-1;
			if ( prevNonWSToken == SimpleTokenType.SEPARATOR ) {
				token = SimpleTokenType.VALUE;
			}
			else {
				token = SimpleTokenType.KEY;
			}
			prevNonWSToken = token;
		}
		this.token = token;
//		System.out.println("nextToken yields "+token);
		return token;
	}

	private boolean isWS(int c) {
		return c==' ' || c=='\t';
	}

	private boolean isNL(int c) {
		return c=='\r' || c=='\n';
	}
}
