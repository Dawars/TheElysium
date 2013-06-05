package mods.elysium.client.particle;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class ElysianEntityFX extends EntityFX
{
	public ElysianEntityFX(int index, World world, double x, double y, double z, double mx, double my, double mz)
	{
		super(world, x, y, z, mx, my, mz);
		this.particleMaxAge = (int)(4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
		this.motionX = mx;
		this.motionY = my;
		this.motionZ = mz;
		this.setParticleTextureIndex(index);
	}
	
	@Override
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		//FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/elysium/textures/misc/particles.png");
		super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
	}
}