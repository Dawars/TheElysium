package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysianBlockGastroShell extends ElysianBlock
{
	@SideOnly(Side.CLIENT)
	private Icon shellRightSide;
	@SideOnly(Side.CLIENT)
	private Icon shellLeftSide;
	@SideOnly(Side.CLIENT)
	private Icon shellHole;
	@SideOnly(Side.CLIENT)
	private Icon shellTop2;
	
	public ElysianBlockGastroShell(int id, Material mat)
	{
		super(id, mat);
	}
	
	@Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entity, ItemStack itemstack)
	{
		super.onBlockPlacedBy(world, i, j, k, entity, itemstack);
		int dir = MathHelper.floor_double((double)((entity.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, dir, 0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int meta)
	{
		Icon texture = null;
		if(meta == 0 || meta == 2)
		{
			if(side == 0 || side == 1)
				texture = this.blockIcon;
			
			
			if(meta == 0)//2: front 4: side
			{
				if(side == 3)
					texture = this.blockIcon;
				
				if(side == 2)
					texture = this.shellHole;

				if(side == 5)
					texture = this.shellLeftSide;
				if(side == 4)
					texture = this.shellRightSide;
			}
			else
			{
				if(side == 2)
					texture = this.blockIcon;
				if(side == 3)
					texture = this.shellHole;
				if(side == 4)
					texture = this.shellLeftSide;
				if(side == 5)
					texture = this.shellRightSide;
			}
		}
		
		if(meta == 1 || meta == 3){
			if(side == 0 || side == 1)
				texture = this.shellTop2;
			
			
			if(meta == 1){//2: front 4: side
				if(side == 5)
					texture = this.shellHole;
				
				if(side == 4)
					texture = this.blockIcon;
				
				if(side == 3)
					texture = this.shellLeftSide;
				if(side == 2)
					texture = this.shellRightSide;
			} else {
				if(side == 4)
					texture = this.shellHole;
				
				if(side == 5)
					texture = this.blockIcon;
				
				if(side == 2)
					texture = this.shellLeftSide;
				if(side == 3)
					texture = this.shellRightSide;
				}
			
		}
		return texture;
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister IconRegister)
	{
		this.blockIcon = IconRegister.registerIcon(DefaultProps.modId + ":gastroshellTop");
		this.shellTop2 = IconRegister.registerIcon(DefaultProps.modId + ":gastroshellTop2");
		this.shellRightSide = IconRegister.registerIcon(DefaultProps.modId + ":gastroshellRightSide");
		this.shellLeftSide = IconRegister.registerIcon(DefaultProps.modId + ":gastroshellLeftSide");
		this.shellHole = IconRegister.registerIcon(DefaultProps.modId + ":gastroshellHole");
	}
}
