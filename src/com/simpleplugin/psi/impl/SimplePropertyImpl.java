// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.simpleplugin.psi.SimpleProperty;
import com.simpleplugin.psi.SimpleVisitor;
import org.jetbrains.annotations.NotNull;

public class SimplePropertyImpl extends ASTWrapperPsiElement implements SimpleProperty {

  public SimplePropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitProperty(this);
    else super.accept(visitor);
  }

}
