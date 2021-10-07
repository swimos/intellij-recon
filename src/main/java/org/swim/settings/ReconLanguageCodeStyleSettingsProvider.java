package org.swim.settings;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.swim.language.ReconLanguage;

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
            consumer.renameStandardOption("SPACE_BEFORE_CLASS_LBRACE", "After attribute");
            consumer.moveStandardOption("SPACE_BEFORE_CLASS_LBRACE", "Spacing");

            consumer.showStandardOptions("SPACE_BEFORE_COLON");
            consumer.renameStandardOption("SPACE_BEFORE_COLON", "Before colon");
            consumer.moveStandardOption("SPACE_BEFORE_COLON", "Spacing");

            consumer.showStandardOptions("SPACE_AFTER_COMMA");
            consumer.renameStandardOption("SPACE_AFTER_COMMA", "After comma");
            consumer.moveStandardOption("SPACE_AFTER_COMMA", "Spacing");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("BLANK_LINES_BEFORE_METHOD_BODY");
            consumer.renameStandardOption("BLANK_LINES_BEFORE_METHOD_BODY", "Number of spaces");

            consumer.showStandardOptions("BLANK_LINES_AFTER_CLASS_HEADER");
            consumer.renameStandardOption("BLANK_LINES_AFTER_CLASS_HEADER", "After opening brace");

            consumer.showStandardOptions("BLANK_LINES_BEFORE_CLASS_END");
            consumer.renameStandardOption("BLANK_LINES_BEFORE_CLASS_END", "Before closing brace");
        }
    }

    @Override
    protected void customizeDefaults(@NotNull CommonCodeStyleSettings commonSettings, @NotNull CommonCodeStyleSettings.IndentOptions indentOptions) {
        commonSettings.BLANK_LINES_BEFORE_METHOD_BODY = 4;
        commonSettings.KEEP_BLANK_LINES_IN_CODE = 0;
        commonSettings.KEEP_BLANK_LINES_IN_DECLARATIONS = 0;
        commonSettings.KEEP_LINE_BREAKS = false;
        commonSettings.SPACE_BEFORE_COLON = false;
        commonSettings.SPACE_AFTER_COMMA = true;
        commonSettings.SPACE_AFTER_COLON = true;
        commonSettings.SPACE_BEFORE_CLASS_LBRACE = true;
        indentOptions.CONTINUATION_INDENT_SIZE = 4;
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "# This is a comment\n" +
                "@config(type:\"client\",version:1){@client{buffer_size:5,on_invalid:\"ignore\",hex:0xB3CEF912D48,data:%AA==,started:true}}";
    }

}
