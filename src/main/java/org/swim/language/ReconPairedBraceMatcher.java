package org.swim.language;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.swim.language.psi.ReconTypes;

public class ReconPairedBraceMatcher implements PairedBraceMatcher {
    @Override
    public BracePair @NotNull [] getPairs() {

        BracePair normal = new BracePair(ReconTypes.OPEN_BRACK, ReconTypes.CLOSE_BRACK, false);
        BracePair curly = new BracePair(ReconTypes.OPEN_CRL_BRACK, ReconTypes.CLOSE_CRL_BRACK, false);

        return new BracePair[]{normal, curly};
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
