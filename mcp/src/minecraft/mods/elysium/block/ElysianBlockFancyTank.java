package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.entity.tileentity.TileEntityFancyTank;
import mods.elysium.entity.tileentity.TileEntityFancyWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ElysianBlockFancyTank extends ElysianBlockContainer
{
	public ElysianBlockFancyTank(int id)
	{
		super(id, Material.rock);
	}
	
	@Override
	public int getRenderType()
	{
		return Elysium.fancyTankRenderID;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
		ItemStack current = entityplayer.inventory.getCurrentItem();
		if (current != null) {

			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);

			TileEntityFancyTank tank = (TileEntityFancyTank) world.getBlockTileEntity(i, j, k);

			// Handle filled containers
			if (fluid != null) {
				int qty = tank.fill(ForgeDirection.UNKNOWN, fluid, true);

				if (qty != 0 && !entityplayer.capabilities.isCreativeMode) {
					//entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, Utils.consumeItem(current));
				}

				return true;

				// Handle empty containers
			} else {

				FluidStack available = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
				if (available != null) {
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);

					fluid = FluidContainerRegistry.getFluidForFilledItem(filled);

					if (fluid != null) {
						if (!entityplayer.capabilities.isCreativeMode) {
							if (current.stackSize > 1) {
								if (!entityplayer.inventory.addItemStackToInventory(filled))
									return false;
								else {
									//entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, Utils.consumeItem(current));
								}
							} else {
								//entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, Utils.consumeItem(current));
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, filled);
							}
						}
						tank.drain(ForgeDirection.UNKNOWN, fluid.amount, true);
						return true;
					}
				}
			}
		}

		return false;
	}
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntityFancyTank tile = new TileEntityFancyTank();
		return tile;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.blockIcon = itemIcon.registerIcon(DefaultProps.modId + ":palestone_pillar_side");
    }
	
	@Override
    @SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
