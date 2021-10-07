package org.swim.settings;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.swim.color.ReconSyntaxHighlighter;
import org.swim.misc.ReconIcons;

import javax.swing.*;
import java.util.Map;

public class ReconColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Attribute", ReconSyntaxHighlighter.ATTR),
            new AttributesDescriptor("Identifier", ReconSyntaxHighlighter.IDENT),
            new AttributesDescriptor("String", ReconSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", ReconSyntaxHighlighter.NUM),
            new AttributesDescriptor("Boolean", ReconSyntaxHighlighter.BOOL),
            new AttributesDescriptor("Operator", ReconSyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Hex", ReconSyntaxHighlighter.HEX),
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
                "@config(type: \"client\", version: 1) {\n" +
                "   @client {\n" +
                "       buffer_size: 5,\n" +
                "       on_invalid: \"ignore\",\n" +
                "       hex: 0xB3CEF912D48,\n" +
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