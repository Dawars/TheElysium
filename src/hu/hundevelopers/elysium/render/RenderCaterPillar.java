package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.model.ModelCaterPillar;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCaterPillar extends RenderLiving
{
    private static final ResourceLocation TEXTURE_CATER_PILLAR = new ResourceLocation(Elysium.MODID + ":/textures/models/CaterPillar.png");
	private ModelCaterPillar model;

    public RenderCaterPillar()
    {
        super(new ModelCaterPillar(), 1F);
        this.model = (ModelCaterPillar)super.mainModel;
        this.setRenderPassModel(this.model);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE_CATER_PILLAR;
	}
}