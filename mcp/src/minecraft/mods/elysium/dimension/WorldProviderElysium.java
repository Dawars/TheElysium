package mods.elysium.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderElysium extends WorldProvider {
	private float[] colorsSunriseSunset = new float[4];

	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(Elysium.ElysiumPlainBiome,
				this.dimensionId, this.dimensionId);
		this.dimensionId = Elysium.DimensionID;
		this.hasNoSky = false;
		this.setSkyRenderer(new ElysiumSkyRenderer());

	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderElysium(this.worldObj,
				this.worldObj.getSeed(), false);
	}

	public int getAverageGroundLevel() {
		return 64;
	}


	public String getDimensionName() {
		return "The Elysium";
	}

	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return true;
	}

	public boolean canRespawnHere() {
		return false;
	}

	public boolean isSurfaceWorld() {
		return true;
	}
	
	
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(50, 5, 0);
	}

	protected void generateLightBrightnessTable() {
		float f = 5.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage() {
		if ((this instanceof WorldProviderElysium)) {
			return "Entering The Elysium";
		}
		return null;
	}
	 /**
     * A Message to display to the user when they transfer out of this dismension.
     *
     * @return The message to be displayed
     */
    public String getDepartMessage()
    {
    	if ((this instanceof WorldProviderElysium)) {
			return "Leaving The Elysium";
		}
		return null;
    }
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2) {
		int i = 10518688;
		float f2 = MathHelper.cos(par1 * 3.141593F * 2.0F) * 2.0F + 0.5F;
		if (f2 < 0.0F) {
			f2 = 0.0F;
		}
		if (f2 > 1.0F) {
			f2 = 1.0F;
		}
		float f3 = (i >> 16 & 0xFF) / 255.0F;
		float f4 = (i >> 8 & 0xFF) / 255.0F;
		float f5 = (i & 0xFF) / 255.0F;
		f3 *= (f2 * 0.0F + 0.15F);
		f4 *= (f2 * 0.0F + 0.15F);
		f5 *= (f2 * 0.0F + 0.15F);
		return this.worldObj.getWorldVec3Pool().getVecFromPool(f3, f4, f5);
	}
}