package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityEnderMage;
import hu.hundevelopers.elysium.model.ModelEnderMage;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderEnderMage extends RenderBiped
{
	private ModelEnderMage model;

    public RenderEnderMage()
    {
        super(new ModelEnderMage(), 1F);
        this.model = (ModelEnderMage)super.mainModel;
        this.setRenderPassModel(this.model);
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation(Elysium.MODID + ":textures/mobs/EnderMage.png");
	}

	protected void renderEquippedItems(EntityEnderMage entity, float par2)
    {
        super.renderEquippedItems(entity, par2);
        ItemStack item = entity.getEquipmentInSlot(0);
        if (item != null)
        {
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glPushMatrix();
            GL11.glRotatef(5.0F + 180.0F * this.model.rightarm1.rotateAngleX / (float)Math.PI, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.6875F, 1.25F, -0.9375F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            float f1 = 0.8F;
            GL11.glScalef(f1, -f1, f1);
            int i = entity.getBrightnessForRender(par2);
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindTexture(TextureMap.locationBlocksTexture);
            this.field_147909_c.renderBlockAsItem(Block.getBlockFromItem(item.getItem()), item.getItemDamage(), 1.0F);
            GL11.glPopMatrix();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
    }


    protected void renderEquippedItems(EntityLivingBase entity, float par2)
    {
        this.renderEquippedItems((EntityEnderMage)entity, par2);
    }
}
