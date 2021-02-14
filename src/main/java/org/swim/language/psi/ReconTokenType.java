package org.swim.language.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.swim.language.ReconLanguage;

public class ReconTokenType extends IElementType {

    public ReconTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ReconLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ReconTokenType." + super.toString();
    }

}