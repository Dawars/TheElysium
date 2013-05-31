package mods.elysium.items;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;

public class ElysiumShovel extends ElysiumItemTool
{
    /** an array of the blocks this spade is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {Elysium.grassBlock, Elysium.soilBlock, Elysium.LeucosandBlock, Elysium.RiltBlock};

    public ElysiumShovel(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, 1, par2EnumToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }
}
