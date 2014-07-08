package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.model.ModelCaterPillar;
import hu.hundevelopers.elysium.model.ModelSwan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
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
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Elysium.MODID + ":/textures/models/swan.png");
	}

}
