package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;
import hu.hundevelopers.elysium.entity.EntitySwan;
import hu.hundevelopers.elysium.model.ModelCaterPillar;
import hu.hundevelopers.elysium.model.ModelSwan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderSwan extends RenderLiving
{
	private ModelSwan model;

    public RenderSwan()
    {
        super(new ModelSwan(), 1F);
        this.model = (ModelSwan)super.mainModel;
        this.setRenderPassModel(this.model);
    }
    
    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    protected float handleRotationFloat(EntityLivingBase entity, float par2)
    {
    	EntitySwan swan = (EntitySwan) entity;
    	
        float f1 = swan.field_70888_h + (swan.field_70886_e - swan.field_70888_h) * par2;
        float f2 = swan.field_70884_g + (swan.destPos - swan.field_70884_g) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Elysium.MODID + ":textures/mobs/Swan.png");
	}

}
