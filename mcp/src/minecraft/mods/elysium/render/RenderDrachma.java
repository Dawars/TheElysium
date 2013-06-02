package mods.elysium.render;

import mods.elysium.DefaultProps;
import mods.elysium.entity.ElysianEntityDrachma2;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;

public class RenderDrachma extends Render
{
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1)
	{
        this.renderDrachma((ElysianEntityDrachma2)entity, x, y, z, f, f1);
	}

	private void renderDrachma(ElysianEntityDrachma2 entity, double x, double y, double z, float f, float f1)
	{
		new ModelDrachma().render(entity, (float) x, (float) y, (float) z, f, f1, (float) (1.0 / 16.0));
	}

}
