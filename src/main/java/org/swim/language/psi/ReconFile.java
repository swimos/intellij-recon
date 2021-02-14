package org.swim.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.swim.language.ReconFileType;
import org.swim.language.ReconLanguage;
import org.jetbrains.annotations.NotNull;

public class ReconFile extends PsiFileBase {

    public ReconFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ReconLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ReconFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Recon File";
    }

}