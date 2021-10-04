package org.swim.language;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.swim.language.psi.ReconTypes;

import java.util.ArrayList;
import java.util.List;

public class ReconBlock extends AbstractBlock {

    private final SpacingBuilder spacingBuilder;

    protected ReconBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                         SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();

        while (child != null) {
            if (child.getElementType() != ReconTypes.WHITE_SPACE && child.getElementType() != ReconTypes.NL && child.getElementType() != ReconTypes.SP) {
                Block block = new ReconBlock(child, Wrap.createWrap(WrapType.NONE, false), null,
                        spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        if (myNode.getElementType() == ReconTypes.BLOCK) {
            return Indent.getSpaceIndent(4);
        } else {
            return Indent.getSpaceIndent(0);
        }
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

}