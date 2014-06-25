package hu.hundevelopers.elysium.event;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.api.Plants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ElysiumHandler
{
	public static ElysiumHandler INSTANCE = new ElysiumHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    private ElysiumHandler() {
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
}
