package com.simpleplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.simpleplugin.psi.SimpleTokenType.*;

public class MyParser implements PsiParser {
//	public static class RecognitionException extends RuntimeException {
//		public RecognitionException(String message) {
//			super(message);
//		}
//	}

	protected PsiBuilder builder;
	protected IElementType token;

	@NotNull
	@Override
	// item*
	public ASTNode parse(IElementType root, PsiBuilder builder) {
		this.builder = builder;
		token = builder.getTokenType();

		PsiBuilder.Marker rootMarker = builder.mark();
		while ( !builder.eof() && token!=null ) {
			item();
		}
		if (root != null) {
			rootMarker.done(root);
		}
		return builder.getTreeBuilt();
	}

	// property|COMMENT|CRLF
	// COMMENT skipped by advanceLexer()
	public void item() {
		if ( token == KEY ) {
			property();
		}
		else if ( token == CRLF ) {
			match(CRLF);
		}
		else {
			consume(); // recover by just consuming offending token
			builder.error("no viable alt at: "+builder.getTokenType());
		}
	}

	// KEY SEPARATOR VALUE | KEY
	public void property() {
		PsiBuilder.Marker marker = builder.mark();
		match(KEY);
		match(SEPARATOR);
		match(VALUE);
		marker.done(PROPERTY);
	}

	public void match(IElementType ttype) {
		if ( builder.getTokenType() != ttype ) {
			builder.error("mismatched token; expecting "+
							  ttype+", found "+builder.getTokenType());
		}
		consume();
	}

	public void consume() {
		builder.advanceLexer();
		token = builder.getTokenType();
	}
}