package org.swim.misc;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.swim.language.psi.impl.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReconFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        Collection<ReconRecordImpl> reconRecords =
                PsiTreeUtil.findChildrenOfType(root, ReconRecordImpl.class);

        for (final ReconRecordImpl reconRecord : reconRecords) {
            descriptors.add(new FoldingDescriptor(reconRecord.getNode(),
                    new TextRange(reconRecord.getTextRange().getStartOffset() + 1,
                            reconRecord.getTextRange().getEndOffset() - 1),
                    null));
        }

        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        String retTxt = "...";
        return retTxt;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }

}