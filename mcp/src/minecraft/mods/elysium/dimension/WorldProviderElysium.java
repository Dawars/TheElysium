package mods.elysium.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderElysium extends WorldProvider
{
	private float[] colorsSunriseSunset = new float[4];

	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(Elysium.ElysiumPlainBiome, this.dimensionId, this.dimensionId);
		this.dimensionId = Elysium.DimensionID;
		this.hasNoSky = false;
		if(worldObj.isRemote)
		this.setSkyRenderer(new ElysiumSkyRenderer());
		
	}
	
//	@SideOnly(Side.CLIENT)
//    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
//    {//86C7FF
//        return getSkyColorBody(worldObj, cameraEntity, partialTicks);
//    }
	
	
	
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderElysium(this.worldObj,
				this.worldObj.getSeed(), false);
	}

	public int getAverageGroundLevel()
	{
		return 64;
	}


	public String getDimensionName()
	{
		return "The Elysium";
	}

	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return true;
	}

	public boolean canRespawnHere()
	{
		return true;
	}

	public boolean isSurfaceWorld()
	{
		return true;
	}
	
	
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		return false;
	}

	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(50, 5, 0);
	}

	protected void generateLightBrightnessTable()
	{
		float f = 3.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering The Elysium";
	}
	
	/**
    * A Message to display to the user when they transfer out of this dismension.
    *
    * @return The message to be displayed
    */
	public String getDepartMessage()
    {
		return "Leaving The Elysium";
    }
	
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float par1, float par2) {
		float f2 = 0.4F;
		float f3 = MathHelper.cos(par1 * 3.141593F * 2.0F) - 0.0F;
		float f4 = -0.0F;
		if ((f3 >= f4 - f2) && (f3 <= f4 + f2)) {
			float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
			float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
			f6 *= f6;
			this.colorsSunriseSunset[0] = (f5 * 0.3F + 0.7F);
			this.colorsSunriseSunset[1] = (f5 * f5 * 0.7F + 0.2F);
			this.colorsSunriseSunset[2] = (f5 * f5 * 0.0F + 0.2F);
			this.colorsSunriseSunset[3] = f6;
			return this.colorsSunriseSunset;
		}
		return null;
	}
    
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2)
	{
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