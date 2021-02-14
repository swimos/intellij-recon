package org.swim.language;

import com.intellij.lang.Language;

public class ReconLanguage extends Language {
    public static final ReconLanguage INSTANCE = new ReconLanguage();

    private ReconLanguage() {
        super("Recon");
    }
}