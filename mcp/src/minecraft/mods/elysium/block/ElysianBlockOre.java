package mods.elysium.block;

import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysianBlockOre extends ElysianBlock
{
    public ElysianBlockOre(int par1)
    {
        super(par1, Material.rock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID == Elysium.SulphurOreBlock.blockID ? Elysium.SulphurItem.itemID : (this.blockID == Elysium.TourmalineOreBlock.blockID ? Elysium.TourmalineItem.itemID : (this.blockID == Elysium.JadeOreBlock.blockID ? Elysium.jadeItem.itemID : (this.blockID == Elysium.BerylOreBlock.blockID ? Elysium.BerlyItem.itemID : this.blockID)));
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return this.blockID == Elysium.BerylOreBlock.blockID ? 4 + par1Random.nextInt(5) : 1;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int j = par2Random.nextInt(par1 + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }

            return this.quantityDropped(par2Random) * (j + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int j1 = 0;

            if (this.blockID == Elysium.SulphurOreBlock.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
            }
            else if (this.blockID == Elysium.TourmalineOreBlock.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }
            else if (this.blockID == Elysium.BerylOreBlock.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }
            else if (this.blockID == Elysium.JadeOreBlock.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);
            }

            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }
}
