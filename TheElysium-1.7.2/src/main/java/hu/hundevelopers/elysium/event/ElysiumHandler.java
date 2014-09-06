package hu.hundevelopers.elysium.event;

import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.ElysiumWorldProvider;

import java.util.HashMap;
import java.util.Map;

import me.dawars.CraftingPillars.blocks.BaseBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ElysiumHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == Elysium.itemSulphur)
		{
			return 1600;
		}
		
		return 0;
	}

	public static ElysiumHandler INSTANCE = new ElysiumHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    private ElysiumHandler() {
    }

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event)
    {
    	if(event.world.provider instanceof ElysiumWorldProvider)
    	{
			event.world.setThunderStrength(1F);
			event.world.setRainStrength(0.5F);
    	}
    }
    
    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        ItemStack result = fillCustomBucket(event.world, event.target);

        if (result == null)
                return;

        event.result = result;
        event.setResult(Result.ALLOW);
    }

    private ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
    {
        Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

        Item bucket = buckets.get(block);
        if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
                world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
                return new ItemStack(bucket);
        } else
                return null;
    }
    
//    @SubscribeEvent
//	public void onEntityConstructing(EntityConstructing event)
//	{
//		if (event.entity instanceof EntityPlayer && ElysiumExtendedPlayer.get((EntityPlayer) event.entity) == null)
//			ElysiumExtendedPlayer.register((EntityPlayer) event.entity);
//	}
    
    @SubscribeEvent
	public void onBreakBlock(BlockEvent.BreakEvent event)
	{
		if(!event.getPlayer().capabilities.isCreativeMode &&  event.world.provider instanceof ElysiumWorldProvider && event.y <= Configs.labyrinthTop && event.y >= Configs.labyrinthBottom)
		{
			if(event.block == Blocks.quartz_block || event.block == Elysium.blockEnergyCrystal || event.block == Blocks.trapdoor)
				event.setCanceled(true);
		}
	}
}