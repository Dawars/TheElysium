package mods.elysium.dimension.portal;

import net.minecraft.util.ChunkCoordinates;

public class ElysiumPortalPosition extends ChunkCoordinates {
	public long lastUpdateTime;
	final ElysiumTeleporter teleporter;

	public ElysiumPortalPosition(ElysiumTeleporter teleporter, int i, int j, int k, long time) {
		super(i, j, k);
		this.teleporter = teleporter;
		this.lastUpdateTime = time;
	}
}