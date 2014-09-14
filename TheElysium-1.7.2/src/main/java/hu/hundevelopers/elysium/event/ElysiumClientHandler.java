package hu.hundevelopers.elysium.event;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.client.gui.ElysiumGuiEmulator;
import hu.hundevelopers.elysium.client.gui.ElysiumGuiMainMenu;
import hu.hundevelopers.elysium.item.ElysiumStaffItem;
import hu.hundevelopers.elysium.world.ElysiumWorldProvider;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenCorruption;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenDesert;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenForest;
import me.dawars.CraftingPillars.renderer.RenderingHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumClientHandler
{
	@SubscribeEvent
	public void guiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu && Configs.customGui) {
			Minecraft.getMinecraft().displayGuiScreen(new ElysiumGuiEmulator(new ElysiumGuiMainMenu(null)));
			event.setCanceled(true);
		}
	}
	
	@SideOnly(Side.CLIENT)
	private RenderingHelper.ItemRender itemRenderer;
	
	public ElysiumClientHandler()
	{
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
			itemRenderer = new RenderingHelper.ItemRender(false, false);
	}

	@SubscribeEvent
	public void onSoundPlayed(PlaySoundEvent17 event)
    {
//		if(event.category == SoundCategory.ANIMALS || event.category == SoundCategory.BLOCKS || event.category == SoundCategory.MOBS || event.category == SoundCategory.WEATHER) return;
//		System.out.println("Playing " + event.category + " sound: " + event.name + " class name: " + event.result.getClass().getName() + " " + event.sound.getClass().getName());
		
		
		World world = FMLClientHandler.instance().getClient().theWorld; 
		if(world != null && world.provider instanceof ElysiumWorldProvider)
		{
			
			EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity(); 
			BiomeGenBase biomeGenBase = world.getBiomeGenForCoords((int) player.posX, (int) player.posZ);
			
			if(event.category == SoundCategory.AMBIENT || event.category == SoundCategory.MUSIC)
			{
				System.out.println("Sound type: " + event.category + " Name: " + event.name);
			}
			
			if(event.category == SoundCategory.AMBIENT)
			{
				
				if(player.posY <= Configs.labyrinthTop)
				{//TODO: change name
					event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation(Elysium.MODID + ":elysiumLabyrinth"));
				}
				if(biomeGenBase instanceof ElysiumBiomeGenCorruption)
				{
					event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation(Elysium.MODID + ":elysiumShadow"));
				} else if(!(biomeGenBase instanceof ElysiumBiomeGenDesert)){
					event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation(Elysium.MODID + ":elysium"));
				} else {
					event.result = null;
				}
			} else if(event.category == SoundCategory.MUSIC)
			{
				if(player.posY <= Configs.labyrinthTop)
				{//TODO: change name
					event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation(Elysium.MODID + ":elysiumLabyrinth"));
				} else if(biomeGenBase instanceof ElysiumBiomeGenForest)
				{
					event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation(Elysium.MODID + ":elysiumForest"));
				} else {

					event.result = null;
				}
			}
		}
    }
	
	@SubscribeEvent
	public void onEntityRender(RenderLivingEvent.Post event)
	{
		EntityLivingBase entity = event.entity;
		
		ItemStack item = entity.getEquipmentInSlot(0);
				
		if(item != null && item.getItem() instanceof ElysiumStaffItem && item.getItemDamage() == 0)
		{
			Block block = Staff.getBlockHolding(item); 
			if(block != null)
			{
				glPushMatrix();
					glTranslated(0, -0.3D, 0);
					glRotatef(entity.rotationYaw, 0, -1, 0);
					glRotatef(entity.rotationPitch, 1, 0, 0);
					
					float f = 1.5F;
					glScalef(f, f, f);
//						glRotatef(90, 0, -1, 0);
								
					EntityItem entityitem = new EntityItem(entity.worldObj);
					entityitem.setEntityItemStack(new ItemStack(block));
					entityitem.hoverStart = 0F;
					itemRenderer.render(entityitem, 0, 0, 0.5F, false);

				glPopMatrix();
			
			}
		}
	}
}
