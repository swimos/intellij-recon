package org.swim.language;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.swim.language.psi.ReconTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReconFormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {

        SpacingBuilder builder = new SpacingBuilder(settings, ReconLanguage.INSTANCE)
                .after(ReconTypes.ATTRIBUTE)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_BEFORE_CLASS_LBRACE)
                .after(ReconTypes.COL)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_AFTER_COLON);


        int blank_lines_after_opening_brack = settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).BLANK_LINES_AFTER_CLASS_HEADER;
        int blank_lines_before_closing_brack = settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).BLANK_LINES_BEFORE_CLASS_END;
        int blank_lines_between_slots = settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).BLANK_LINES_AROUND_FIELD;

        if (blank_lines_after_opening_brack == 0) {
            builder.after(ReconTypes.OPEN_CRL_BRACK)
                    .lineBreakInCode();
        } else {
            builder.after(ReconTypes.OPEN_CRL_BRACK)
                    .blankLines(blank_lines_after_opening_brack);
        }

        if (blank_lines_before_closing_brack == 0) {
            builder.before(ReconTypes.CLOSE_CRL_BRACK)
                    .lineBreakInCode();
        } else {
            builder.before(ReconTypes.CLOSE_CRL_BRACK)
                    .blankLines(blank_lines_before_closing_brack);
        }

        if (blank_lines_between_slots == 0) {
            builder.after(ReconTypes.SEP)
                    .lineBreakInCode();
        } else {
            builder.after(ReconTypes.SEP)
                    .blankLines(blank_lines_between_slots);
        }

        return builder;
    }

    @NotNull
    @Override
    public FormattingModel createModel(@NotNull FormattingContext formattingContext) {

        CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
        settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).KEEP_BLANK_LINES_IN_CODE = 0;
        settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).KEEP_BLANK_LINES_IN_DECLARATIONS = 0;

        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new ReconBlock(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(settings)),
                        settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

}