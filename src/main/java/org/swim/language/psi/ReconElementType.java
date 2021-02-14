package org.swim.language.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.swim.language.ReconLanguage;

public class ReconElementType extends IElementType {

    public ReconElementType(@NotNull @NonNls String debugName) {
        super(debugName, ReconLanguage.INSTANCE);
    }
}
