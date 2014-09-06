package me.dawars.CraftingPillars.api.sentry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

public class FakeSentryPlayer extends EntityPlayer
{


	public FakeSentryPlayer(World world, GameProfile gameProfile) {
		super(world, gameProfile);
	}

	@Override
	public boolean canCommandSenderUseCommand(int i, String s) {
		return false;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		return new ChunkCoordinates(0, 0, 0);
	}

	@Override
	public void addChatMessage(IChatComponent var1) {
		
	}

}
