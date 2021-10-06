package org.swim.settings;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.swim.language.ReconLanguage;

public class ReconCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new ReconCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "Recon";
    }

    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new ReconCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class ReconCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {

        @Override
        protected void initTabs(CodeStyleSettings settings) {
            addSpacesTab(settings);
            addBlankLinesTab(settings);
        }

        public ReconCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(ReconLanguage.INSTANCE, currentSettings, settings);
        }

    }

}