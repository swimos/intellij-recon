package org.swim.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class ReconFileType extends LanguageFileType {

    public static final ReconFileType INSTANCE = new ReconFileType();

    private ReconFileType() {
        super(ReconLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Recon file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Recon language file";
    }


    @NotNull
    @Override
    public String getDefaultExtension() {
        return "recon";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ReconIcons.FILE;
    }
}