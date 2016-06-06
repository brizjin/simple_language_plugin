package com.simpleplugin;

import com.intellij.openapi.Disposable;
import com.intellij.testFramework.HighlightTestInfo;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by ivan on 6/4/2016.
 */
public class SimpleHighlighTest extends HighlightTestInfo {
    public SimpleHighlighTest(@NotNull Disposable parentDisposable, @NonNls @NotNull String... filePaths) {
        super(parentDisposable, filePaths);
    }

    @Override
    protected HighlightTestInfo doTest() {
        return null;
    }
}
