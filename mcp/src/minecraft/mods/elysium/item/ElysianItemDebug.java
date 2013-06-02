package mods.elysium.item;

import org.lwjgl.input.Keyboard;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.dimension.portal.ElysianTeleporter;
import mods.elysium.entity.ElysianEntityDrachma2;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysianItemDebug extends ElysianItem
{
	public ElysianItemDebug(int id)
	{
		super(id);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		world.spawnEntityInWorld(new ElysianEntityDrachma2(world, x+hitX, y+hitY, z+hitZ, 10));
		
		if(!world.isRemote)
		{
			player.sendChatToPlayer("Id: "+world.getBlockId(x, y, z));
			player.sendChatToPlayer("Metadata: "+world.getBlockMetadata(x, y, z));
			player.sendChatToPlayer("TileEntity: "+world.getBlockTileEntity(x, y, z));
		}
		
        return false;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(/*player.isSneaking()*/ Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && (player instanceof EntityPlayerMP))
		{
			EntityPlayerMP playermp = (EntityPlayerMP)player;
			if(playermp.dimension == Elysium.DimensionID)
			{
				playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, 0, new ElysianTeleporter(playermp.mcServer.worldServerForDimension(0)));
			}
			else
			{
				playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, Elysium.DimensionID, new ElysianTeleporter(playermp.mcServer.worldServerForDimension(Elysium.DimensionID)));
			}
		}
		return stack;
    }
}
