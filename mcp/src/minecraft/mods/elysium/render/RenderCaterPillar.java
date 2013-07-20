package mods.elysium.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.model.ModelCaterPillar;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.client.IItemRenderer;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.*;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.*;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class RenderCaterPillar extends RenderLiving
{
    private static final ResourceLocation TEXTURE_CATER_PILLAR = new ResourceLocation("/mods/" + DefaultProps.modId + "/textures/mob/CaterPillar.png");
	private ModelCaterPillar model;

    public RenderCaterPillar()
    {
        super(new ModelCaterPillar(), 1F);
        this.model = (ModelCaterPillar)super.mainModel;
        this.setRenderPassModel(this.model);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return TEXTURE_CATER_PILLAR;
	}
}
