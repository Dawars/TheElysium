package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.model.ModelDeer;
import hu.hundevelopers.elysium.model.ModelVoidSpecter;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderVoidSpecter extends RenderLiving
{
	private ModelVoidSpecter model;

    public RenderVoidSpecter()
    {
        super(new ModelVoidSpecter(), 1F);
        this.model = (ModelVoidSpecter)super.mainModel;
        this.setRenderPassModel(this.model);
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Elysium.MODID + ":/textures/mobs/VoidSpecter.png");
	}

}
