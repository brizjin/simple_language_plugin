package com.simpleplugin;

/**
 * Created by ivan on 6/4/2016.
 */
import com.intellij.lang.*;
import com.intellij.lang.impl.PsiBuilderFactoryImpl;
import com.intellij.lexer.Lexer;
import com.intellij.mock.MockProjectEx;
import com.intellij.mock.MockPsiManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiFileFactoryImpl;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.testFramework.UsefulTestCase;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParsingTest extends LightPlatformCodeInsightFixtureTestCase {
    public ParsingTest(){
    }

    public void testSimple() throws Exception {
        MyParser parser = new MyParser();
        //parser.parse()

        //PsiFile file = PsiFileFactoryImpl..createFileFromText("Test",SimpleLanguage.INSTANCE,"Hello : wrd");
        //Project project = file.getProject();
        //MockProjectEx myProject = new MockProjectEx(this.getTestRootDisposable());
        //MockPsiManager myPsiManager = new MockPsiManager(myProject);
        //PsiFileFactoryImpl myFileFactory = new PsiFileFactoryImpl(myPsiManager);

        //LightVirtualFile virtualFile = new LightVirtualFile("Test", SimpleLanguage.INSTANCE, "Hello : word");

        //virtualFile.setCharset(CharsetToolkit.UTF8_CHARSET);
        //this.myFileFactory.trySetupPsiForFile(virtualFile, SimpleLanguage.INSTANCE, true, false);

        //Project project = psi.getProject();


        //PsiBuilder builder = PsiBuilderFactoryImpl.getInstance().createBuilder(new SimpleParserDefinition(),new MyLexerAdaptor(),"Hello : word");
        //PsiBuilderFactoryImpl.getInstance().

        //PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, (Lexer)null, languageForParser, chameleon.getChars());
        //PsiFile file = myFileFactory.createFileFromText("Test",SimpleLanguage.INSTANCE,"Hello : wrd");
        //this.setUp();

        PsiFile file = this.createLightFile("Test",SimpleLanguage.INSTANCE,"Hello : wrd");
        //PsiFile file2 = this.createLightFile("simple","hello : word");



        //parser.parse(file,builder);
    }


}
