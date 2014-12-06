package me.dawars.CraftingPillars.handlers;

import java.util.Random;

import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.api.baubles.Baubles;
import me.dawars.CraftingPillars.blocks.BaseBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class PillarEventHandler
{
	@SubscribeEvent
	public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		if(event.crafting != null && event.crafting.isItemEqual(new ItemStack(CraftingPillars.blockBasePillar)))
		{
			event.player.addStat(CraftingPillars.achievementGettingStarted, 1);
		}

		if(event.crafting != null && event.crafting.isItemEqual(new ItemStack(CraftingPillars.blockTankPillar)))
		{
			event.player.addStat(CraftingPillars.achievementCompressingLiquids, 1);
		}
		
		if(event.crafting != null && event.crafting.isItemEqual(new ItemStack(CraftingPillars.blockChristmasTreeSapling)))
		{
			event.player.addStat(CraftingPillars.achievementChristmas, 1);
		}
	}
	
	@SubscribeEvent
	public void onPlayerInterract(PlayerInteractEvent event)
	{
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && event.entity.isSneaking() && event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if(event.entity.worldObj.getBlock(event.x, event.y, event.z) instanceof BaseBlockContainer && event.face == 1)
			{
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onBreakBlock(BlockEvent.BreakEvent event)
	{
		if(event.block instanceof BaseBlockContainer && event.getPlayer().isSneaking())
		{
			event.setCanceled(true);
			event.block.onBlockClicked(event.world, event.x, event.y, event.z, event.getPlayer());
		}
	}
	
	private static final Random rand = new Random();

	@SubscribeEvent
	public void onPlayerGetHurt(LivingHurtEvent event)
	{
		if(event.entity.worldObj.isRemote) return;
		if(event.entityLiving instanceof EntityPlayer && event.source != DamageSource.fall)
		{
			if(BaublesApi.getBaubles((EntityPlayer) event.entityLiving) != null)
			{
				ItemStack stack1 = BaublesApi.getBaubles((EntityPlayer) event.entityLiving).getStackInSlot(1);
				ItemStack stack2 = BaublesApi.getBaubles((EntityPlayer) event.entityLiving).getStackInSlot(2);
				
				boolean isRing1 = false;
				boolean isRing2 = false;
				
				if (stack1 != null)
					isRing1 = Baubles.isGemInRing(Items.ender_pearl, stack1);
				if (stack2 != null)
					isRing2 = Baubles.isGemInRing(Items.ender_pearl, stack2);
				
				if(isRing1 || isRing2)
				{
					double d0 = event.entityLiving.posX + (PillarEventHandler.rand.nextDouble() - 0.5D) * 64.0D;
			        double d1 = event.entityLiving.posY + (double)(PillarEventHandler.rand.nextInt(64) - 32);
			        double d2 = event.entityLiving.posZ + (PillarEventHandler.rand.nextDouble() - 0.5D) * 64.0D;
			        teleportTo(event.entity, d0, d1, d2);
				}
			}
		}
	}
	
	/**
     * Teleport the enderman
     */
    protected boolean teleportTo(Entity entity, double targetX, double targetY, double targetZ)
    {
        double d3 = entity.posX;
        double d4 = entity.posY;
        double d5 = entity.posZ;
        entity.posX = targetX;
        entity.posY = targetY;
        entity.posZ = targetZ;
        boolean flag = false;
        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);

        if (entity.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = entity.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --entity.posY;
                    --j;
                }
            }

            if (flag1)
            {
            	setPositionEntity(entity, entity.posX, entity.posY, entity.posZ);

                if (entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
        	setPositionEntity(entity, d3, d4, d5);
            return false;
        }
        else
        {
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (PillarEventHandler.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (PillarEventHandler.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (PillarEventHandler.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (entity.posX - d3) * d6 + (PillarEventHandler.rand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
                double d8 = d4 + (entity.posY - d4) * d6 + PillarEventHandler.rand.nextDouble() * (double)entity.height;
                double d9 = d5 + (entity.posZ - d5) * d6 + (PillarEventHandler.rand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
                entity.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }

            entity.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            entity.playSound("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

	private void setPositionEntity(Entity entity, double posX, double posY, double posZ)
	{
//	System.out.println("tp");
    	
    	EntityPlayerMP entityplayermp = (EntityPlayerMP) entity;
		if (entityplayermp.playerNetServerHandler.func_147362_b().isChannelOpen())
        {
			EnderTeleportEvent event2 = new EnderTeleportEvent(entityplayermp, posX, posY, posZ, 5.0F);
            if (!MinecraftForge.EVENT_BUS.post(event2))
            { // Don't indent to lower patch size
            if (entityplayermp.isRiding())
            {
                entityplayermp.mountEntity((Entity)null);
            }

            ((EntityLivingBase) entity).setPositionAndUpdate(event2.targetX, event2.targetY, event2.targetZ);
            entity.fallDistance = 0.0F;
//            entity.attackEntityFrom(DamageSource.fall, event2.attackDamage);
            }
        }
	}
}
