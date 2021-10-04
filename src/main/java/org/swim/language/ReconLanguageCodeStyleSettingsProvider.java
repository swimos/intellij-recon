package org.swim.language;


import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;

public class ReconLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return ReconLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AFTER_COLON");
            consumer.renameStandardOption("SPACE_AFTER_COLON", "After colon");
            consumer.moveStandardOption("SPACE_AFTER_COLON", "Spacing");

            consumer.showStandardOptions("SPACE_BEFORE_CLASS_LBRACE");
            consumer.renameStandardOption("SPACE_BEFORE_CLASS_LBRACE", "Before opening brace");
            consumer.moveStandardOption("SPACE_BEFORE_CLASS_LBRACE", "Spacing");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("BLANK_LINES_AFTER_CLASS_HEADER");
            consumer.renameStandardOption("BLANK_LINES_AFTER_CLASS_HEADER", "After opening brace");

            consumer.showStandardOptions("BLANK_LINES_BEFORE_CLASS_END");
            consumer.renameStandardOption("BLANK_LINES_BEFORE_CLASS_END", "Before closing brace");

            consumer.showStandardOptions("BLANK_LINES_AROUND_FIELD");
            consumer.renameStandardOption("BLANK_LINES_AROUND_FIELD", "Between slots");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "# This is a comment\n" +
                "@config{@client{buffer_size:5,on_invalid:\"ignore\",data:%AA==,started:true}}";
    }

}
