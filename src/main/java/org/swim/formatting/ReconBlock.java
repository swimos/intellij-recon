package org.swim.formatting;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.swim.language.psi.ReconTypes;

import java.util.ArrayList;
import java.util.List;

public class ReconBlock extends AbstractBlock {

    private final SpacingBuilder spacingBuilder;
    private final Integer spaces;

    protected ReconBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                         SpacingBuilder spacingBuilder, Integer spaces) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
        this.spaces = spaces;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();

        while (child != null) {
            if (child.getPsi() instanceof PsiErrorElement) {
                return new ArrayList<>();
            }

            if (child.getElementType() != ReconTypes.WHITE_SPACE && child.getElementType() != ReconTypes.NL && child.getElementType() != ReconTypes.SP) {


                if (child.getTreeParent() != null) {
                    if (child.getTreeParent().getTreeParent() == null) {
                        if (foo(child)) {
                            Block block = new ReconBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(),
                                    spacingBuilder, this.spaces);
                            blocks.add(block);
                        } else {
                            Block block = new ReconBlock(child, Wrap.createWrap(WrapType.NONE, false), createReconAlignment(child, myNode.getTreeNext()),
                                    spacingBuilder, this.spaces);
                            blocks.add(block);
                        }
                    } else {
                        Block block = new ReconBlock(child, Wrap.createWrap(WrapType.NONE, false), createReconAlignment(child, myNode.getTreeNext()),
                                spacingBuilder, this.spaces);
                        blocks.add(block);
                    }
                } else {
                    Block block = new ReconBlock(child, Wrap.createWrap(WrapType.NONE, false), createReconAlignment(child, myNode.getTreeNext()),
                            spacingBuilder, this.spaces);
                    blocks.add(block);
                }
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        if (myNode.getElementType() == ReconTypes.BLOCK) {
            return Indent.getSpaceIndent(this.spaces);
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

    private boolean foo(ASTNode child) {
        if (child.getElementType() == ReconTypes.RECORD) {
            ASTNode prev = child.getTreePrev();

            while (child.getTreePrev() != null) {
                if (prev.getElementType() == ReconTypes.ATTRIBUTE) {
                    return false;
                }
                if (prev.getElementType() == ReconTypes.WHITE_SPACE) {
                    prev = prev.getTreePrev();
                } else {
                    return true;
                }

            }
        }

        return true;

    }

    private Alignment createReconAlignment(ASTNode child, ASTNode next) {
        if (child.getElementType() == ReconTypes.SLOT) {
            return Alignment.createAlignment();
        }

        if (child.getElementType() == ReconTypes.EMPTY_SLOT) {
            return Alignment.createAlignment();
        }

        return null;
    }
}