package mods.elysium.dimension;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;

public class ElysiumSkyRenderer extends IRenderHandler {

	@Override
	@SideOnly(Side.CLIENT)
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
        if (mc.theWorld.provider.dimensionId == Elysium.DimensionID)
        {
            Tessellator tessellator = Tessellator.instance;

            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glPushMatrix();
            float f4 = 1.0F - world.getRainStrength(partialTicks);
            float f7 = 0.0F;
            float f8 = 0.0F;
            float f9 = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f4);
            GL11.glTranslatef(f7, f8, f9);
//            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            float f10 = 30.0F;
            FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + DefaultProps.modId + "/textures/blocks/elysiansun.png");

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)(-f10), 100.0D, (double)(-f10), 0.0D, 0.0D);
            tessellator.addVertexWithUV((double)f10, 100.0D, (double)(-f10), 1.0D, 0.0D);
            tessellator.addVertexWithUV((double)f10, 100.0D, (double)f10, 1.0D, 1.0D);
            tessellator.addVertexWithUV((double)(-f10), 100.0D, (double)f10, 0.0D, 1.0D);
            tessellator.draw();
            

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
        	
        	
        	//night
//        	GL11.glDisable(GL11.GL_FOG);
//            GL11.glDisable(GL11.GL_ALPHA_TEST);
//            GL11.glEnable(GL11.GL_BLEND);
//            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//            RenderHelper.disableStandardItemLighting();
//            GL11.glDepthMask(false);
//            FMLClientHandler.instance().getClient().renderEngine.bindTexture("/misc/tunnel.png");
//            Tessellator tessellator = Tessellator.instance;
//
//            for (int i = 0; i < 6; ++i)
//            {
//                GL11.glPushMatrix();
//
//                if (i == 1)
//                {
//                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//                }
//
//                if (i == 2)
//                {
//                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//                }
//
//                if (i == 3)
//                {
//                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
//                }
//
//                if (i == 4)
//                {
//                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
//                }
//
//                if (i == 5)
//                {
//                    GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
//                }
//
//                tessellator.startDrawingQuads();
//                tessellator.setColorOpaque_I(2631720);
//                tessellator.addVertexWithUV(-100.0D, -100.0D, -100.0D, 0.0D, 0.0D);
//                tessellator.addVertexWithUV(-100.0D, -100.0D, 100.0D, 0.0D, 16.0D);
//                tessellator.addVertexWithUV(100.0D, -100.0D, 100.0D, 16.0D, 16.0D);
//                tessellator.addVertexWithUV(100.0D, -100.0D, -100.0D, 16.0D, 0.0D);
//                tessellator.draw();
//                GL11.glPopMatrix();
//            }
//
//            GL11.glDepthMask(true);
//            GL11.glEnable(GL11.GL_TEXTURE_2D);
//            GL11.glEnable(GL11.GL_ALPHA_TEST);
        }
	}
}
