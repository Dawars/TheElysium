package me.dawars.CraftingPillars.api.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IBehaviorSentryItem
{
	/**
	 * Dispenses the specified ItemStack from a Sentry.
	 * @param iblocksource - info about the Sentry block
	 * @param target - the target the Sentry is shooting
	 * @param entityPlayer - the owner - use it only if the weapon needs to consume "mana" from the player - use new FakeSentryPlayer(world, "Sentry") otherwise
	 * @param itemstack - weapon or projectile placed in the Sentry
	 * @return - returns the ammo remaining in the Sentry
	 */
	ItemStack dispense(IBlockSource iblocksource, EntityLiving target, EntityPlayer entityPlayer, ItemStack itemstack);
	
	/**
	 * Returns the reload speed of the projectile
	 * @param itemstack - current weapon or projectile
	 * @return - time to reload in ticks - default: 20
	 */
	int reloadSpeed(ItemStack itemstack);
}
