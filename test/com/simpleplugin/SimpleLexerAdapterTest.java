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
    public void testComment(){
        doTest("#hello word","SimpleTokenType.COMMENT ('#hello word')");
        doTest("!hello word","SimpleTokenType.COMMENT ('!hello word')");
    }
    public void testMultiLineComment(){
        doTest( "#hello word\n" +
                "#second line\n" +
                "!line3"
                ,"SimpleTokenType.COMMENT ('#hello word')\n" +
                "SimpleTokenType.CRLF ('\\n')\n" +
                "SimpleTokenType.COMMENT ('#second line')\n" +
                "SimpleTokenType.CRLF ('\\n')\n" +
                "SimpleTokenType.COMMENT ('!line3')");
    }
    public void testPropertyWithComment(){
        doTest( "#hello word\n" +
                "#second line\n" +
                "hello : word"
                ,"SimpleTokenType.COMMENT ('#hello word')\n" +
                "SimpleTokenType.CRLF ('\\n')\n" +
                "SimpleTokenType.COMMENT ('#second line')\n" +
                "SimpleTokenType.CRLF ('\\n')\n" +
                "SimpleTokenType.KEY ('hello')\n" +
                "WHITE_SPACE (' ')\n" +
                "SimpleTokenType.SEPARATOR (':')\n" +
                "WHITE_SPACE (' ')\n" +
                "SimpleTokenType.VALUE ('word')");
    }
    public void testPropertyWithMixComment(){
        doTest( "#hello word\n" +
                        "!second line\n" +
                        "hello : word"
                ,"SimpleTokenType.COMMENT ('#hello word')\n" +
                        "SimpleTokenType.CRLF ('\\n')\n" +
                        "SimpleTokenType.COMMENT ('!second line')\n" +
                        "SimpleTokenType.CRLF ('\\n')\n" +
                        "SimpleTokenType.KEY ('hello')\n" +
                        "WHITE_SPACE (' ')\n" +
                        "SimpleTokenType.SEPARATOR (':')\n" +
                        "WHITE_SPACE (' ')\n" +
                        "SimpleTokenType.VALUE ('word')");
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
