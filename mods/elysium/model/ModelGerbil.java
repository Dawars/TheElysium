package mods.elysium.model;

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import mods.elysium.entity.EntityGerbil;

public class ModelGerbil extends ModelBase
{
	ModelRenderer brleg3;
	ModelRenderer brleg4;
	ModelRenderer brleg2;
	ModelRenderer brleg1;
	ModelRenderer tail8;
	ModelRenderer body3;
	ModelRenderer body2;
	ModelRenderer nose2;
	ModelRenderer body4;
	ModelRenderer frleg2;
	ModelRenderer blleg4;
	ModelRenderer blleg3;
	ModelRenderer blleg2;
	ModelRenderer body5;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer tail5;
	ModelRenderer tail6;
	ModelRenderer tail9;
	ModelRenderer tail7;
	ModelRenderer blleg1;
	ModelRenderer frleg1;
	ModelRenderer flleg1;
	ModelRenderer flleg2;
	ModelRenderer body1;
	ModelRenderer head6;
	ModelRenderer head1;
	ModelRenderer nose1;
	ModelRenderer head2;
	ModelRenderer head5;
	ModelRenderer head4;
	ModelRenderer head3;
	
	public ModelGerbil()
	{
		textureWidth = 128;
		textureHeight = 128;
		
		head1 = new ModelRenderer(this, 0, 16);
		head1.addBox(-3F, -2F, -11F, 6, 6, 3);
		head1.setRotationPoint(0F, 8F, -14F);
		head1.setTextureSize(128, 128);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4F, -4F, -8F, 8, 8, 8);
		head2.setRotationPoint(0F, 8F, -14F);
		head2.setTextureSize(128, 128);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		head3 = new ModelRenderer(this, 0, 29);
		head3.addBox(-0.5F, -8F, -4F, 1, 3, 4);
		head3.setRotationPoint(0F, 8F, -14F);
		head3.setTextureSize(128, 128);
		head3.mirror = true;
		setRotation(head3, 0F, 0F, -0.7853982F);
		head4 = new ModelRenderer(this, 0, 36);
		head4.addBox(-0.5F, -9F, -3F, 1, 1, 2);
		head4.setRotationPoint(0F, 8F, -14F);
		head4.setTextureSize(128, 128);
		head4.mirror = true;
		setRotation(head4, 0F, 0F, -0.7853982F);
		head5 = new ModelRenderer(this, 0, 29);
		head5.addBox(-0.5F, -8F, -4F, 1, 3, 4);
		head5.setRotationPoint(0F, 8F, -14F);
		head5.setTextureSize(128, 128);
		head5.mirror = false;
		setRotation(head5, 0F, 0F, 0.7853982F);
		head6 = new ModelRenderer(this, 0, 36);
		head6.addBox(-0.5F, -9F, -3F, 1, 1, 2);
		head6.setRotationPoint(0F, 8F, -14F);
		head6.setTextureSize(128, 128);
		head6.mirror = true;
		setRotation(head6, 0F, 0F, 0.7853982F);
		
		body1 = new ModelRenderer(this, 4, 58);
		body1.addBox(-6F, -6F, -14F, 12, 12, 6);
		body1.setRotationPoint(0F, 8F, 0F);
		body1.setTextureSize(128, 128);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 0, 76);
		body2.addBox(-6F, -8F, -8F, 12, 16, 16);
		body2.setRotationPoint(0F, 8F, 0F);
		body2.setTextureSize(128, 128);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 40, 52);
		body3.addBox(-8F, -6F, -6F, 16, 12, 12);
		body3.setRotationPoint(0F, 8F, 0F);
		body3.setTextureSize(128, 128);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		body4 = new ModelRenderer(this, 56, 76);
		body4.addBox(-6F, -6F, 8F, 12, 12, 2);
		body4.setRotationPoint(0F, 8F, 0F);
		body4.setTextureSize(128, 128);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		body5 = new ModelRenderer(this, 56, 92);
		body5.addBox(-4F, -2F, 10F, 8, 8, 2);
		body5.setRotationPoint(0F, 8F, 0F);
		body5.setTextureSize(128, 128);
		body5.mirror = true;
		setRotation(body5, 0F, 0F, 0F);
		
		tail1 = new ModelRenderer(this, 100, 0);
		tail1.addBox(-1.5F, 0.5F, 0F, 3, 3, 8);
		tail1.setRotationPoint(0F, 8F, 12F);
		tail1.setTextureSize(128, 128);
		tail1.mirror = true;
		setRotation(tail1, -0.5061455F, 0F, 0F);
		tail2 = new ModelRenderer(this, 100, 0);
		tail2.addBox(-1.5F, 2.5F, 6.6F, 3, 3, 8);
		tail2.setRotationPoint(0F, 8F, 12F);
		tail2.setTextureSize(128, 128);
		tail2.mirror = true;
		setRotation(tail2, -0.2268928F, 0F, 0F);
		tail3 = new ModelRenderer(this, 100, 12);
		tail3.addBox(-2F, 5.5F, 12.6F, 4, 4, 8);
		tail3.setRotationPoint(0F, 8F, 12F);
		tail3.setTextureSize(128, 128);
		tail3.mirror = true;
		setRotation(tail3, 0.0349066F, 0F, 0F);
		tail4 = new ModelRenderer(this, 100, 12);
		tail4.addBox(-2F, 9.9F, 18F, 4, 4, 8);
		tail4.setRotationPoint(0F, 8F, 12F);
		tail4.setTextureSize(128, 128);
		tail4.mirror = true;
		setRotation(tail4, 0.2617994F, 0F, 0F);
		tail5 = new ModelRenderer(this, 100, 24);
		tail5.addBox(2F, 12.1F, 23.7F, 1, 4, 8);
		tail5.setRotationPoint(0F, 8F, 12F);
		tail5.setTextureSize(128, 128);
		tail5.mirror = true;
		setRotation(tail5, 0.3490659F, 0F, 0F);
		tail6 = new ModelRenderer(this, 100, 35);
		tail6.addBox(-2F, 11.1F, 23.7F, 4, 1, 8);
		tail6.setRotationPoint(0F, 8F, 12F);
		tail6.setTextureSize(128, 128);
		tail6.mirror = true;
		setRotation(tail6, 0.3490659F, 0F, 0F);
		tail7 = new ModelRenderer(this, 100, 24);
		tail7.addBox(-3F, 12.1F, 23.7F, 1, 4, 8);
		tail7.setRotationPoint(0F, 8F, 12F);
		tail7.setTextureSize(128, 128);
		tail7.mirror = true;
		setRotation(tail7, 0.3490659F, 0F, 0F);
		tail8 = new ModelRenderer(this, 100, 35);
		tail8.addBox(-2F, 16.1F, 23.7F, 4, 1, 8);
		tail8.setRotationPoint(0F, 8F, 12F);
		tail8.setTextureSize(128, 128);
		tail8.mirror = true;
		setRotation(tail8, 0.3490659F, 0F, 0F);
		tail9 = new ModelRenderer(this, 100, 12);
		tail9.addBox(-2F, 12.1F, 24.7F, 4, 4, 8);
		tail9.setRotationPoint(0F, 8F, 12F);
		tail9.setTextureSize(128, 128);
		tail9.mirror = true;
		setRotation(tail9, 0.3490659F, 0F, 0F);
		
		nose1 = new ModelRenderer(this, 0, 25);
		nose1.addBox(-1F, 1F, -11.2F, 2, 1, 1);
		nose1.setRotationPoint(0F, 8F, -14F);
		nose1.setTextureSize(128, 128);
		nose1.mirror = true;
		setRotation(nose1, 0F, 0F, 0F);
		nose2 = new ModelRenderer(this, 0, 27);
		nose2.addBox(-0.5F, 1.5F, -11.1F, 1, 1, 1);
		nose2.setRotationPoint(0F, 8F, -14F);
		nose2.setTextureSize(128, 128);
		nose2.mirror = true;
		setRotation(nose2, 0F, 0F, 0F);
		
		frleg1 = new ModelRenderer(this, 36, 0);
		frleg1.addBox(-4F, 0F, -12F, 4, 4, 12);
		frleg1.setRotationPoint(-3.9F, 12F, -5F);
		frleg1.setTextureSize(128, 128);
		frleg1.mirror = true;
		setRotation(frleg1, 0.8726646F, 0F, 0F);
		frleg2 = new ModelRenderer(this, 52, 16);
		frleg2.addBox(-4.1F, 8F, -8.8F, 4, 4, 4);
		frleg2.setRotationPoint(-3.9F, 12F, -5F);
		frleg2.setTextureSize(128, 128);
		frleg2.mirror = true;
		setRotation(frleg2, 0F, 0F, 0F);
		
		flleg1 = new ModelRenderer(this, 36, 0);
		flleg1.addBox(0F, 0F, -12F, 4, 4, 12);
		flleg1.setRotationPoint(3.9F, 12F, -5F);
		flleg1.setTextureSize(128, 128);
		flleg1.mirror = true;
		setRotation(flleg1, 0.8726646F, 0F, 0F);
		flleg2 = new ModelRenderer(this, 52, 16);
		flleg2.addBox(0.1F, 8F, -8.8F, 4, 4, 4);
		flleg2.setRotationPoint(3.9F, 12F, -5F);
		flleg2.setTextureSize(128, 128);
		flleg2.mirror = true;
		setRotation(flleg2, 0F, 0F, 0F);
		
		brleg1 = new ModelRenderer(this, 72, 0);
		brleg1.addBox(-4F, -4F, -4F, 4, 8, 8);
		brleg1.setRotationPoint(-6F, 12F, 6F);
		brleg1.setTextureSize(128, 128);
		brleg1.mirror = true;
		setRotation(brleg1, 0.8726646F, 0F, 0F);
		brleg2 = new ModelRenderer(this, 80, 16);
		brleg2.addBox(-4F, 4F, -4F, 4, 4, 4);
		brleg2.setRotationPoint(-6F, 12F, 6F);
		brleg2.setTextureSize(128, 128);
		brleg2.mirror = true;
		setRotation(brleg2, 0.8726646F, 0F, 0F);
		brleg3 = new ModelRenderer(this, 80, 26);
		brleg3.addBox(-4.1F, 6F, 2F, 4, 2, 4);
		brleg3.setRotationPoint(-6F, 12F, 6F);
		brleg3.setTextureSize(128, 128);
		brleg3.mirror = true;
		setRotation(brleg3, 0F, 0F, 0F);
		brleg4 = new ModelRenderer(this, 72, 32);
		brleg4.addBox(-4.1F, 8F, -2F, 4, 4, 8);
		brleg4.setRotationPoint(-6F, 12F, 6F);
		brleg4.setTextureSize(128, 128);
		brleg4.mirror = true;
		setRotation(brleg4, 0F, 0F, 0F);
		
		blleg1 = new ModelRenderer(this, 72, 0);
		blleg1.addBox(0F, -4F, -4F, 4, 8, 8);
		blleg1.setRotationPoint(6F, 12F, 6F);
		blleg1.setTextureSize(128, 128);
		blleg1.mirror = true;
		setRotation(blleg1, 0.8726646F, 0F, 0F);
		blleg2 = new ModelRenderer(this, 80, 16);
		blleg2.addBox(0F, 4F, -4F, 4, 4, 4);
		blleg2.setRotationPoint(6F, 12F, 6F);
		blleg2.setTextureSize(128, 128);
		blleg2.mirror = true;
		setRotation(blleg2, 0.8726646F, 0F, 0F);
		blleg3 = new ModelRenderer(this, 80, 26);
		blleg3.addBox(0.1F, 6F, 2F, 4, 2, 4);
		blleg3.setRotationPoint(6F, 12F, 6F);
		blleg3.setTextureSize(128, 128);
		blleg3.mirror = true;
		setRotation(blleg3, 0F, 0F, 0F);
		blleg4 = new ModelRenderer(this, 72, 32);
		blleg4.addBox(0.1F, 8F, -2F, 4, 4, 8);
		blleg4.setRotationPoint(6F, 12F, 6F);
		blleg4.setTextureSize(128, 128);
		blleg4.mirror = true;
		setRotation(blleg4, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		EntityGerbil gerbil = (EntityGerbil) entity;
		
		glPushMatrix();
			glTranslatef(0F, 1.1F, 0F);
			glScalef(0.25F, 0.25F, 0.25F);
			
			body1.render(f5);
			body2.render(f5);
			body3.render(f5);
			body4.render(f5);
			body5.render(f5);
			
			head1.render(f5);
			head2.render(f5);
			head3.render(f5);
			head4.render(f5);
			head5.render(f5);
			head6.render(f5);
			
			tail1.render(f5);
			tail2.render(f5);
			tail3.render(f5);
			tail4.render(f5);
			tail5.render(f5);
			tail6.render(f5);
			tail7.render(f5);
			tail8.render(f5);
			tail9.render(f5);
			
			nose1.render(f5);
			nose2.render(f5);
			
			frleg1.rotateAngleX += gerbil.leg1;
			frleg2.rotateAngleX += gerbil.leg1;
			frleg1.render(f5);
			frleg2.render(f5);
			frleg1.rotateAngleX -= gerbil.leg1;
			frleg2.rotateAngleX -= gerbil.leg1;
			
			flleg1.rotateAngleX += gerbil.leg2;
			flleg2.rotateAngleX += gerbil.leg2;
			flleg1.render(f5);
			flleg2.render(f5);
			flleg1.rotateAngleX -= gerbil.leg2;
			flleg2.rotateAngleX -= gerbil.leg2;
			
			brleg1.rotateAngleX += gerbil.leg2;
			brleg2.rotateAngleX += gerbil.leg2;
			brleg3.rotateAngleX += gerbil.leg2;
			brleg4.rotateAngleX += gerbil.leg2;
			brleg1.render(f5);
			brleg2.render(f5);
			brleg3.render(f5);
			brleg4.render(f5);
			brleg1.rotateAngleX -= gerbil.leg2;
			brleg2.rotateAngleX -= gerbil.leg2;
			brleg3.rotateAngleX -= gerbil.leg2;
			brleg4.rotateAngleX -= gerbil.leg2;
			
			blleg1.rotateAngleX += gerbil.leg1;
			blleg2.rotateAngleX += gerbil.leg1;
			blleg3.rotateAngleX += gerbil.leg1;
			blleg4.rotateAngleX += gerbil.leg1;
			blleg1.render(f5);
			blleg2.render(f5);
			blleg3.render(f5);
			blleg4.render(f5);
			blleg1.rotateAngleX -= gerbil.leg1;
			blleg2.rotateAngleX -= gerbil.leg1;
			blleg3.rotateAngleX -= gerbil.leg1;
			blleg4.rotateAngleX -= gerbil.leg1;
		glPopMatrix();
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
