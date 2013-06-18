package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.entity.EntityDrachma;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.world.World;

public class ElysianBlock extends Block 
{
	public ElysianBlock(int id, Material mat)
	{
		super(id, mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.blockIcon = itemIcon.registerIcon(DefaultProps.modId + ":" + getUnlocalizedName().substring(5));
    }
	
	@Override
	protected void dropXpOnBlockBreak(World world, int x, int y, int z, int amount)
	{
		if(!world.isRemote)
		{
			while(amount > 0)
			{
				int i1 = EntityXPOrb.getXPSplit(amount);
				amount -= i1;
				world.spawnEntityInWorld(new EntityDrachma(world, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, i1));
			}
		}
	}
}
