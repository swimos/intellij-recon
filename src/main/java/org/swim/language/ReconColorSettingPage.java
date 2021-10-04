// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.swim.language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class ReconColorSettingPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Attribute", ReconSyntaxHighlighter.ATTR),
            new AttributesDescriptor("Identifier", ReconSyntaxHighlighter.IDENT),
            new AttributesDescriptor("String", ReconSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", ReconSyntaxHighlighter.NUM),
            new AttributesDescriptor("Boolean", ReconSyntaxHighlighter.BOOL),
            new AttributesDescriptor("Operator", ReconSyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Data", ReconSyntaxHighlighter.DATA),
            new AttributesDescriptor("Comment", ReconSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Bad character", ReconSyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return ReconIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new ReconSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "# This is a comment\n" +
                "@config {\n" +
                "   @client {\n" +
                "       buffer_size: 5,\n" +
                "       on_invalid: \"ignore\",\n" +
                "       data: %AA==,\n" +
                "       started: true\n" +
                "   }\n" +
                "}";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Recon";
    }

}