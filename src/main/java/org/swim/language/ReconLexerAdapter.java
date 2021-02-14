package org.swim.language;

import com.intellij.lexer.FlexAdapter;

public class ReconLexerAdapter extends FlexAdapter {

    public ReconLexerAdapter() {
        super(new ReconLexer(null));
    }

}