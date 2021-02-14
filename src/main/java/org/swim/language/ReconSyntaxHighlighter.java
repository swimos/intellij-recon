package org.swim.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.swim.language.psi.ReconTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ReconSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey OPERATOR = createTextAttributesKey("RECON_OPERATOR", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey BOOL =
            createTextAttributesKey("RECON_BOOL", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("RECON_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey IDENT =
            createTextAttributesKey("RECON_IDENT", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUM =
            createTextAttributesKey("RECON_NUM", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("RECON_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey DATA =
            createTextAttributesKey("RECON_DATA", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey ATTR =
            createTextAttributesKey("RECON_ATTRIBUTE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("RECON_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] BOOL_KEYS = new TextAttributesKey[]{BOOL};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] IDENT_KEYS = new TextAttributesKey[]{IDENT};
    private static final TextAttributesKey[] NUM_KEYS = new TextAttributesKey[]{NUM};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] DATA_KEYS = new TextAttributesKey[]{DATA};
    private static final TextAttributesKey[] ATTR_KEYS = new TextAttributesKey[]{ATTR};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new ReconLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(ReconTypes.OPEN_BRACK)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(ReconTypes.CLOSE_BRACK)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(ReconTypes.OPEN_CRL_BRACK)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(ReconTypes.CLOSE_CRL_BRACK)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(ReconTypes.COL)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(ReconTypes.SEP)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(ReconTypes.BOOL)) {
            return BOOL_KEYS;
        } else if (tokenType.equals(ReconTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(ReconTypes.IDENT)) {
            return IDENT_KEYS;
        } else if (tokenType.equals(ReconTypes.NUM)) {
            return NUM_KEYS;
        } else if (tokenType.equals(ReconTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(ReconTypes.DATA)) {
            return DATA_KEYS;
        } else if (tokenType.equals(ReconTypes.ATTR)) {
            return ATTR_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }

}