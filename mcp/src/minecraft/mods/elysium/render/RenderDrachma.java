package mods.elysium.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import mods.elysium.DefaultProps;
import mods.elysium.entity.ElysianEntityDrachma;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class RenderDrachma extends Render
{
	public static ModelBase model = new ModelBase()
	{
		
	};
	private ModelRenderer Part1;
	private ModelRenderer Part2;
	private ModelRenderer Part3;
	private ModelRenderer Part4;
	private ModelRenderer Part5;
	private ModelRenderer Part6;
	private ModelRenderer Part7;
	private ModelRenderer Part8;
	private ModelRenderer Part9;
	private ModelRenderer Part10;
	private ModelRenderer Part11;
	private ModelRenderer Part12;
	private ModelRenderer Part13;
	private ModelRenderer Part14;
	private ModelRenderer Part15;
	private ModelRenderer Part16;
	private ModelRenderer Part17;
	private ModelRenderer Part18;
	private ModelRenderer Part19;
	private ModelRenderer Part20;
	private ModelRenderer Part21;
	private ModelRenderer Part22;
	private ModelRenderer Part23;
	private ModelRenderer Part24;
	private ModelRenderer Part25;
	private ModelRenderer Part26;
	private ModelRenderer Part27;
	private ModelRenderer Part28;
	private ModelRenderer Part29;
	private ModelRenderer Part30;
	
	public RenderDrachma()
	{
		model.textureWidth = 64;
		model.textureHeight = 64;
		
		Part1 = new ModelRenderer(model, 32, 52);
		Part1.addBox(0F, 0F, 0F, 12, 8, 4);
		Part1.setRotationPoint(0F, 0F, 0F);
		Part1.setTextureSize(64, 64);
		Part1.mirror = true;
		setRotation(Part1, 0F, 0F, 0F);
		Part2 = new ModelRenderer(model, 0, 16);
		Part2.addBox(-2F, -2F, 1F, 16, 2, 2);
		Part2.setRotationPoint(0F, 0F, 0F);
		Part2.setTextureSize(64, 64);
		Part2.mirror = true;
		setRotation(Part2, 0F, 0F, 0F);
		Part3 = new ModelRenderer(model, 0, 20);
		Part3.addBox(-4F, 2F, 0F, 2, 2, 4);
		Part3.setRotationPoint(0F, 0F, 0F);
		Part3.setTextureSize(64, 64);
		Part3.mirror = true;
		setRotation(Part3, 0F, 0F, 0F);
		Part4 = new ModelRenderer(model, 0, 26);
		Part4.addBox(-2F, 0F, 0F, 2, 6, 4);
		Part4.setRotationPoint(0F, 0F, 0F);
		Part4.setTextureSize(64, 64);
		Part4.mirror = true;
		setRotation(Part4, 0F, 0F, 0F);
		Part5 = new ModelRenderer(model, 0, 48);
		Part5.addBox(-6F, -10F, 0F, 4, 12, 4);
		Part5.setRotationPoint(0F, 0F, 0F);
		Part5.setTextureSize(64, 64);
		Part5.mirror = true;
		setRotation(Part5, 0F, 0F, 0F);
		Part6 = new ModelRenderer(model, 52, 26);
		Part6.addBox(12F, 0F, 0F, 2, 6, 4);
		Part6.setRotationPoint(0F, 0F, 0F);
		Part6.setTextureSize(64, 64);
		Part6.mirror = true;
		setRotation(Part6, 0F, 0F, 0F);
		Part7 = new ModelRenderer(model, 0, 10);
		Part7.addBox(14F, 2F, 0F, 2, 2, 4);
		Part7.setRotationPoint(0F, 0F, 0F);
		Part7.setTextureSize(64, 64);
		Part7.mirror = true;
		setRotation(Part7, 0F, 0F, 0F);
		Part8 = new ModelRenderer(model, 16, 48);
		Part8.addBox(14F, -10F, 0F, 4, 12, 4);
		Part8.setRotationPoint(0F, 0F, 0F);
		Part8.setTextureSize(64, 64);
		Part8.mirror = true;
		setRotation(Part8, 0F, 0F, 0F);
		Part9 = new ModelRenderer(model, 40, 0);
		Part9.addBox(-2F, -4F, 1F, 2, 2, 2);
		Part9.setRotationPoint(0F, 0F, 0F);
		Part9.setTextureSize(64, 64);
		Part9.mirror = true;
		setRotation(Part9, 0F, 0F, 0F);
		Part10 = new ModelRenderer(model, 40, 4);
		Part10.addBox(12F, -4F, 1F, 2, 2, 2);
		Part10.setRotationPoint(0F, 0F, 0F);
		Part10.setTextureSize(64, 64);
		Part10.mirror = true;
		setRotation(Part10, 0F, 0F, 0F);
		Part11 = new ModelRenderer(model, 12, 30);
		Part11.addBox(0F, -4F, 0F, 4, 2, 4);
		Part11.setRotationPoint(0F, 0F, 0F);
		Part11.setTextureSize(64, 64);
		Part11.mirror = true;
		setRotation(Part11, 0F, 0F, 0F);
		Part12 = new ModelRenderer(model, 12, 24);
		Part12.addBox(8F, -4F, 0F, 4, 2, 4);
		Part12.setRotationPoint(0F, 0F, 0F);
		Part12.setTextureSize(64, 64);
		Part12.mirror = true;
		setRotation(Part12, 0F, 0F, 0F);
		Part13 = new ModelRenderer(model, 48, 36);
		Part13.addBox(4F, -8F, 0F, 4, 6, 4);
		Part13.setRotationPoint(0F, 0F, 0F);
		Part13.setTextureSize(64, 64);
		Part13.mirror = true;
		setRotation(Part13, 0F, 0F, 0F);
		Part14 = new ModelRenderer(model, 28, 30);
		Part14.addBox(2F, -6F, 0F, 2, 2, 4);
		Part14.setRotationPoint(0F, 0F, 0F);
		Part14.setTextureSize(64, 64);
		Part14.mirror = true;
		setRotation(Part14, 0F, 0F, 0F);
		Part15 = new ModelRenderer(model, 28, 24);
		Part15.addBox(8F, -6F, 0F, 2, 2, 4);
		Part15.setRotationPoint(0F, 0F, 0F);
		Part15.setTextureSize(64, 64);
		Part15.mirror = true;
		setRotation(Part15, 0F, 0F, 0.0174533F);
		Part16 = new ModelRenderer(model, 48, 0);
		Part16.addBox(0F, -6F, 1F, 2, 2, 2);
		Part16.setRotationPoint(0F, 0F, 0F);
		Part16.setTextureSize(64, 64);
		Part16.mirror = true;
		setRotation(Part16, 0F, 0F, 0F);
		Part17 = new ModelRenderer(model, 48, 4);
		Part17.addBox(2F, -8F, 1F, 2, 2, 2);
		Part17.setRotationPoint(0F, 0F, 0F);
		Part17.setTextureSize(64, 64);
		Part17.mirror = true;
		setRotation(Part17, 0F, 0F, 0F);
		Part18 = new ModelRenderer(model, 28, 7);
		Part18.addBox(4F, -10F, 1F, 4, 2, 2);
		Part18.setRotationPoint(0F, 0F, 0F);
		Part18.setTextureSize(64, 64);
		Part18.mirror = true;
		setRotation(Part18, 0F, 0F, 0F);
		Part19 = new ModelRenderer(model, 56, 0);
		Part19.addBox(8F, -8F, 1F, 2, 2, 2);
		Part19.setRotationPoint(0F, 0F, 0F);
		Part19.setTextureSize(64, 64);
		Part19.mirror = true;
		setRotation(Part19, 0F, 0F, 0F);
		Part20 = new ModelRenderer(model, 56, 4);
		Part20.addBox(10F, -6F, 1F, 2, 2, 2);
		Part20.setRotationPoint(0F, 0F, 0F);
		Part20.setTextureSize(64, 64);
		Part20.mirror = true;
		setRotation(Part20, 0F, 0F, 0F);
		Part21 = new ModelRenderer(model, 0, 36);
		Part21.addBox(0F, -16F, 0F, 12, 6, 4);
		Part21.setRotationPoint(0F, 0F, 0F);
		Part21.setTextureSize(64, 64);
		Part21.mirror = true;
		setRotation(Part21, 0F, 0F, 0F);
		Part22 = new ModelRenderer(model, 40, 9);
		Part22.addBox(-2F, -14F, 0F, 2, 10, 4);
		Part22.setRotationPoint(0F, 0F, 0F);
		Part22.setTextureSize(64, 64);
		Part22.mirror = true;
		setRotation(Part22, 0F, 0F, 0F);
		Part23 = new ModelRenderer(model, 32, 36);
		Part23.addBox(12F, -14F, 0F, 2, 10, 4);
		Part23.setRotationPoint(0F, 0F, 0F);
		Part23.setTextureSize(64, 64);
		Part23.mirror = true;
		setRotation(Part23, 0F, 0F, 0F);
		Part24 = new ModelRenderer(model, 52, 20);
		Part24.addBox(-4F, -12F, 0F, 2, 2, 4);
		Part24.setRotationPoint(0F, 0F, 0F);
		Part24.setTextureSize(64, 64);
		Part24.mirror = true;
		setRotation(Part24, 0F, 0F, 0F);
		Part25 = new ModelRenderer(model, 28, 0);
		Part25.addBox(2F, -10F, 0F, 2, 2, 4);
		Part25.setRotationPoint(0F, 0F, 0F);
		Part25.setTextureSize(64, 64);
		Part25.mirror = true;
		setRotation(Part25, 0F, 0F, 0F);
		Part26 = new ModelRenderer(model, 12, 0);
		Part26.addBox(0F, -10F, 0F, 2, 4, 4);
		Part26.setRotationPoint(0F, 0F, 0F);
		Part26.setTextureSize(64, 64);
		Part26.mirror = true;
		setRotation(Part26, 0F, 0F, 0F);
		Part27 = new ModelRenderer(model, 52, 14);
		Part27.addBox(14F, -12F, 0F, 2, 2, 4);
		Part27.setRotationPoint(0F, 0F, 0F);
		Part27.setTextureSize(64, 64);
		Part27.mirror = true;
		setRotation(Part27, 0F, 0F, 0F);
		Part28 = new ModelRenderer(model, 0, 0);
		Part28.addBox(8F, -10F, 0F, 2, 2, 4);
		Part28.setRotationPoint(0F, 0F, 0F);
		Part28.setTextureSize(64, 64);
		Part28.mirror = true;
		setRotation(Part28, 0F, 0F, 0F);
		Part29 = new ModelRenderer(model, 12, 8);
		Part29.addBox(10F, -10F, 0F, 2, 4, 4);
		Part29.setRotationPoint(0F, 0F, 0F);
		Part29.setTextureSize(64, 64);
		Part29.mirror = true;
		setRotation(Part29, 0F, 0F, 0F);
		Part30 = new ModelRenderer(model, 0, 0);
		Part30.addBox(10F, -10F, 0F, 2, 4, 4);
		Part30.setRotationPoint(0F, 0F, 0F);
		Part30.setTextureSize(64, 64);
		Part30.mirror = true;
		setRotation(Part30, 0F, 0F, 0F);
	}
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1)
	{
        this.renderDrachma((ElysianEntityDrachma)entity, x, y, z, f, f1);
	}

	float factor = (float) (1.0 / 16.0);
	private void renderDrachma(ElysianEntityDrachma entity, double x, double y, double z, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x, y, z);
		GL11.glScalef(.225F, .25F, .25F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glRotatef(entity.rotationYaw, 0F, 1F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/elysium/textures/misc/Drachma.png");
		
		Part1.render(factor);
		Part2.render(factor);
		Part3.render(factor);
		Part4.render(factor);
		Part5.render(factor);
		Part6.render(factor);
		Part7.render(factor);
		Part8.render(factor);
		Part9.render(factor);
		Part10.render(factor);
		Part11.render(factor);
		Part12.render(factor);
		Part13.render(factor);
		Part14.render(factor);
		Part15.render(factor);
		Part16.render(factor);
		Part17.render(factor);
		Part18.render(factor);
		Part19.render(factor);
		Part20.render(factor);
		Part21.render(factor);
		Part22.render(factor);
		Part23.render(factor);
		Part24.render(factor);
		Part25.render(factor);
		Part26.render(factor);
		Part27.render(factor);
		Part28.render(factor);
		Part29.render(factor);
		Part30.render(factor);
		

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
        
	}

	

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
