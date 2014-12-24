package me.dawars.CraftingPillars.api.sentry;

import com.mojang.authlib.GameProfile;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class FakeSentryPlayer extends FakePlayer {

	public FakeSentryPlayer(World world) {
		super((WorldServer) world, (GameProfile) GameProfileCompatibility.get("", "Sentry Pillar"));
	}

}
