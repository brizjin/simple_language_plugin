package com.simpleplugin;


import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.PlatformTestUtil;

import java.io.IOException;

public class SimpleParsingTest extends ParsingTestCase {
    public SimpleParsingTest() {
        super("", "simple", true, new SimpleParserDefinition());
    }

    public void testParsingTestData() throws Exception {
        doTest(true);
    }
    public void testFromText() throws IOException {
        this.doCodeTest("hello : word");
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    /*@Override
    protected String getTestDataPath() {
        return PlatformTestUtil.getCommunityPath() + "/testData";
    }*/

    //@Override
    //protected boolean skipSpaces() {
    //    return false;
    //}

    //@Override
    //protected boolean includeRanges() {
    //    return true;
    //}
}