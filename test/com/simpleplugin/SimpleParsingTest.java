package com.simpleplugin;


import com.intellij.testFramework.ParsingTestCase;

import java.io.IOException;

public class SimpleParsingTest extends ParsingTestCase {
    public SimpleParsingTest() {
        super("", "simple", true, new SimpleParserDefinition());
    }

    public void testParsingTestData() throws Exception {
        doTest(true);
    }
    public void testTest1() throws IOException {
        //this.doCodeTest("hello : word");
        doTest(true);
    }
    public void testComment() throws IOException{
        this.doCodeTest("comment : hello\ntest : more");

        //doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }
}