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

        SpacingBuilder builder = new SpacingBuilder(settings, ReconLanguage.INSTANCE);

        builder.after(ReconTypes.COMMENT).lineBreakInCode();

        builder.before(ReconTypes.COL)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_BEFORE_COLON);

        builder.after(ReconTypes.COL)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_AFTER_COLON);

        builder.before(ReconTypes.BLOCK_ITEM)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_BEFORE_CLASS_LBRACE);

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

        builder.before(ReconTypes.SEP).spaces(0);
        builder.before(ReconTypes.SLOTS).blankLines(0);

        builder.after(ReconTypes.OPEN_BRACK).lineBreakInCode();
        builder.before(ReconTypes.OPEN_BRACK).spaces(0);
        builder.before(ReconTypes.CLOSE_BRACK).lineBreakInCode();
        builder.between(ReconTypes.ATTRIBUTE, ReconTypes.ATTRIBUTE).lineBreakInCode();
        builder.between(ReconTypes.ATTRIBUTE, ReconTypes.PRIMITIVE).lineBreakInCode();

        return builder;
    }

    @NotNull
    @Override
    public FormattingModel createModel(@NotNull FormattingContext formattingContext) {

        CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
        Integer spaces = settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).BLANK_LINES_BEFORE_METHOD_BODY;

        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new ReconBlock(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(settings), spaces),
                        settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

}