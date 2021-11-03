package org.swim.formatting;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.swim.language.ReconLanguage;
import org.swim.language.psi.ReconTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReconFormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {

        SpacingBuilder builder = new SpacingBuilder(settings, ReconLanguage.INSTANCE);

        builder.beforeInside(ReconTypes.SLOTS, ReconTypes.EMPTY_SLOT).lineBreakInCode();
        builder.before(ReconTypes.COMMENT).blankLines(0);
        builder.after(ReconTypes.COMMENT).lineBreakInCode();

        builder.before(ReconTypes.OPERATION).spaces(1);
        builder.after(ReconTypes.OPERATION).spaces(1);

        builder.before(ReconTypes.COL)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_BEFORE_COLON);

        builder.after(ReconTypes.COL)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_AFTER_COLON);

        builder.before(ReconTypes.BLOCK_ITEM)
                .spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_BEFORE_CLASS_LBRACE);

        int blank_lines_after_opening_brack = settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).BLANK_LINES_AFTER_CLASS_HEADER;
        int blank_lines_before_closing_brack = settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).BLANK_LINES_BEFORE_CLASS_END;

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

        builder.before(ReconTypes.ATTR_SLOTS).spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_AFTER_COMMA);

        builder.before(ReconTypes.SEP).spaces(0);
        builder.before(ReconTypes.SLOTS).blankLines(0);
        builder.between(ReconTypes.SLOTS, ReconTypes.RECORD).lineBreakInCode();

        builder.after(ReconTypes.OPEN_BRACK).spaces(0);
        builder.before(ReconTypes.OPEN_BRACK).spaces(0);
        builder.before(ReconTypes.CLOSE_BRACK).spaces(0);
        builder.between(ReconTypes.ATTRIBUTE, ReconTypes.ATTRIBUTE).lineBreakInCode();
        builder.between(ReconTypes.ATTRIBUTE, ReconTypes.PRIMITIVE).lineBreakInCode();
        builder.between(ReconTypes.LITERAL, ReconTypes.ATTRIBUTE).lineBreakInCode();
        builder.between(ReconTypes.ATTRIBUTE, ReconTypes.RECORD).spaceIf(settings.getCommonSettings(ReconLanguage.INSTANCE.getID()).SPACE_BEFORE_CLASS_LBRACE);

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