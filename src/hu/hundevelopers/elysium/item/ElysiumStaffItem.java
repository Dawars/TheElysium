package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.entity.EntityBlockProjectile;
import hu.hundevelopers.elysium.entity.EntityFallingProjectile;
import hu.hundevelopers.elysium.entity.EntityIceProjectile;

import java.util.List;

import com.mojang.authlib.GameProfile;

import me.dawars.CraftingPillars.api.sentry.FakeSentryPlayer;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumStaffItem extends ElysiumItem
{
	public ElysiumStaffItem()
	{
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
    public static final String[] names = new String[] { "earth", "ice", "ender", "fire" };
    
    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        itemStack.stackTagCompound = new NBTTagCompound();
    }
    
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + names[i];
    }

    /**
     * This is called when the item is used, before the block is activated.
     * @param stack The Item Stack
     * @param player The Player that used the item
     * @param world The Current World
     * @param x Target X Position
     * @param y Target Y Position
     * @param z Target Z Position
     * @param side The side of the target hit
     * @return Return true to prevent any further processing.
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	if(stack.getItemDamage() != 0)
    		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    	    	
    	if(Staff.getDamageForBlock(world.getBlock(x, y, z)) != 0 && getBlockHolding(stack) == null)
    	{
    		System.out.println("Picking up block");
    		Block block = world.getBlock(x, y, z);
    		setBlockHolding(stack, block);
    		if(!world.isRemote)
    			FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(x, y, z, block, world.getBlockMetadata(x, y, z));
    		world.setBlock(x, y, z, Block.getBlockById(0));
    		
    	}
		return true;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	if(stack.getItemDamage() == 0)
    	{
	    	Block block = getBlockHolding(stack);
	
	    	if(block != null)
			{
//				if(!world.isRemote)
//		    	{
				EntityBlockProjectile entityprojectile = new EntityBlockProjectile(world, player, block);
		            world.spawnEntityInWorld(entityprojectile);
//		    	}
				
				setBlockHolding(stack, null);
			}
    	} else if(stack.getItemDamage() == 1)
    	{
    		if(!world.isRemote)
    		{
				EntityIceProjectile entityprojectile = new EntityIceProjectile(world, player);
			    world.spawnEntityInWorld(entityprojectile);
    		}
    	}
        return stack;
    }
    
    public static Block getBlockHolding(ItemStack staff)
    {
    	if(staff.stackTagCompound == null) staff.stackTagCompound = new NBTTagCompound();
    	
    	NBTTagList nbtlist = staff.stackTagCompound.getTagList("Items", 10);
		NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(0);
		
		ItemStack holding = ItemStack.loadItemStackFromNBT(nbtslot);
		
		if(holding == null) return null;
		
		Block block = Block.getBlockFromItem(holding.getItem());
		
		return block;
    }
    
    public static void setBlockHolding(ItemStack staff, Block block)
    {
    	NBTTagCompound nbt = staff.stackTagCompound;
		NBTTagList nbtlist = new NBTTagList();
		
		ItemStack projectile = new ItemStack(block);
		
		if(projectile != null)
		{
			NBTTagCompound nbtslot = new NBTTagCompound();
			nbtslot.setByte("Slot", (byte) 0);
			projectile.writeToNBT(nbtslot);
			nbtlist.appendTag(nbtslot);
			
			nbt.setTag("Items", nbtlist);
		}
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int x = 0; x < names.length; x++)
        {
            list.add(new ItemStack(this, 1, x));
        }
    }
}
