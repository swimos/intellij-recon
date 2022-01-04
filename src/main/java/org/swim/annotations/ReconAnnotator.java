package org.swim.annotations;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import org.jetbrains.annotations.NotNull;

public class ReconAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        if (element instanceof PsiErrorElement) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unexpected character")
                    .range(element.getTextRange())
                    .highlightType(ProblemHighlightType.GENERIC_ERROR)
                    .create();
        }
    }
}
