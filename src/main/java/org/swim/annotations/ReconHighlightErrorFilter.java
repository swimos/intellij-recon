package org.swim.annotations;

import com.intellij.codeInsight.highlighting.HighlightErrorFilter;
import com.intellij.psi.PsiErrorElement;
import org.jetbrains.annotations.NotNull;

public class ReconHighlightErrorFilter extends HighlightErrorFilter {
    @Override
    public boolean shouldHighlightErrorElement(@NotNull PsiErrorElement element) {
        return false;
    }
}
