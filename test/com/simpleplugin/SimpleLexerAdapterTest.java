package com.simpleplugin;

import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LexerTestCase;

public class SimpleLexerAdapterTest extends LexerTestCase {
    public void testSimple() {
        doTest("abc\ndef",
                "SimpleTokenType.KEY ('abc')\n" +
                "SimpleTokenType.CRLF ('\\n')\n" +
                "SimpleTokenType.KEY ('def')");
    }
    public void testSimpleWithNewLine() {
        doTest("hello : word\n",
                "SimpleTokenType.KEY ('hello')\n" +
                "WHITE_SPACE (' ')\n" +
                "SimpleTokenType.SEPARATOR (':')\n" +
                "WHITE_SPACE (' ')\n" +
                "SimpleTokenType.VALUE ('word')\n" +
                "SimpleTokenType.CRLF ('\\n')"
        );
    }

    @Override
    protected Lexer createLexer() {
        return new MyLexerAdaptor();
    }

    @Override
    protected String getDirPath() {
        return null;
    }
}
