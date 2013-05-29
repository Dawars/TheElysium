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
	
	@SideOnly(Side.CLIENT)
    public Vec3 getSkyColorBody(World world, Entity entity, float par2)
    {
        float angle = world.getCelestialAngle(par2);
        float f2 = MathHelper.cos(angle * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posZ);
        BiomeGenBase biomegenbase = this.getBiomeGenForCoords(i, j);
        float f3 = biomegenbase.getFloatTemperature();
        int k = biomegenbase.getSkyColorByTemp(f3);
        float f4 = (float)(k >> 16 & 255) / 255.0F;
        float f5 = (float)(k >> 8 & 255) / 255.0F;
        float f6 = (float)(k & 255) / 255.0F;
        f4 *= f2;
        f5 *= f2;
        f6 *= f2;
        float f7 = world.getRainStrength(par2);
        float f8;
        float f9;

        if (f7 > 0.0F)
        {
            f8 = (f4 * 0.3F + f5 * 0.59F + f6 * 0.11F) * 0.6F;
            f9 = 1.0F - f7 * 0.75F;
            f4 = f4 * f9 + f8 * (1.0F - f9);
            f5 = f5 * f9 + f8 * (1.0F - f9);
            f6 = f6 * f9 + f8 * (1.0F - f9);
        }

        f8 = world.getWeightedThunderStrength(par2);

        if (f8 > 0.0F)
        {
            f9 = (f4 * 0.3F + f5 * 0.59F + f6 * 0.11F) * 0.2F;
            float f10 = 1.0F - f8 * 0.75F;
            f4 = f4 * f10 + f9 * (1.0F - f10);
            f5 = f5 * f10 + f9 * (1.0F - f10);
            f6 = f6 * f10 + f9 * (1.0F - f10);
        }

        if (world.lastLightningBolt > 0)
        {
            f9 = (float)world.lastLightningBolt - par2;

            if (f9 > 1.0F)
            {
                f9 = 1.0F;
            }

            f9 *= 0.45F;
            f4 = f4 * (1.0F - f9) + 0.8F * f9;
            f5 = f5 * (1.0F - f9) + 0.8F * f9;
            f6 = f6 * (1.0F - f9) + 1.0F * f9;
        }

        return world.getWorldVec3Pool().getVecFromPool((double)f4, (double)f5, (double)f6);
    }
	
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
		float f = 5.0F;

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