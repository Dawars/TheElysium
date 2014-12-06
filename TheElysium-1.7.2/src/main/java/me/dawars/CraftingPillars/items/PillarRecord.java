package me.dawars.CraftingPillars.items;

import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PillarRecord extends ItemRecord
{
	public PillarRecord(String recordName)
	{
		super(recordName);
		this.setCreativeTab(CraftingPillars.tabPillar);
		this.maxStackSize = 1;

	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		super.onItemUse(item, player, world, x, y, z, par7, par8, par9, par10);
		if (world.getBlock(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0)
		{
			player.addStat(CraftingPillars.achievementDisc, 1);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * Retrieves the resource location of the sound to play for this record.
     * 
     * @param name The name of the record to play
     * @return The resource location for the audio, null to use default.
     */
    public ResourceLocation getRecordResource(String name)
    {
    	System.out.println("record name: " + CraftingPillars.ID + ":" + name.substring(8));
        return new ResourceLocation(CraftingPillars.ID + ":" + name.substring(8));
    }
}