package mods.elysium;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mods.elysium.api.Plants;
import mods.elysium.block.*;
import mods.elysium.dimension.*;
import mods.elysium.dimension.biome.BiomeGenElysium;
import mods.elysium.dimension.gen.feature.WorldGenElysium;
import mods.elysium.dimension.portal.ElysianBlockPortalCore;
import mods.elysium.dimension.portal.ElysianTileEntityPortal;
import mods.elysium.dimension.portal.ElysianTileEntityPortalRenderer;
import mods.elysium.handlers.ElysianBonemealHandler;
import mods.elysium.handlers.ElysianFuelHandler;
import mods.elysium.handlers.ElysianSoundHandler;
import mods.elysium.item.*;
import mods.elysium.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.EventBus;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(name="The Elysium", version="1.0", useMetadata = false, modid = "TheElysium", dependencies="required-after:Forge@[7.8.0,)")
//@NetworkMod(channels = {DefaultProps.NET_CHANNEL_NAME}, packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = true)
public class Elysium
{
	@Instance("The Elysium")
	public static Elysium instance;
	
	public static ElysianConfiguration mainConfiguration;
	
	public static final CreativeTabs tabElysium = new ElysianTab(12, "The Elysium");
		
	/** Dimension ID **/
	public static int DimensionID;
	
	//Blocks
	
	public static Block paleStone;
	/** It means elysian dirt. **/
	public static Block soilBlock;
	/** It means elysian grass. **/
	public static Block grassBlock;
	public static Block LeucosandBlock;
	public static Block RiltBlock;
	public static Block FostimberLogBlock;
	public static Block FostimberLeavesBlock;
	public static Block GastroShellBlock;
	public static Block FostimberSaplingBlock;
	public static Block WoodBlock;
	public static Block FlowerBlock;
	/** It means elysian grass overlay. **/
	public static Block CurlgrassBlock;
	public static Block SulphurOreBlock;
	public static Block CobaltOreBlock;
	public static Block IridiumOreBlock;
	public static Block SiliconOreBlock;
	public static Block JadeOreBlock;
	public static Block TourmalineOreBlock;
	public static Block BerylOreBlock;
	public static Block waterStill;
	public static ElysianBlockLiquid waterMoving;
	public static Block shellFloatingBlock;
	public static Block conchFloatingBlock;
	public static Block paleCobbletone;
//	public static Block LeucosandBlock;
//	public static Block LeucosandBlock;
	
	public static Block portalCore;
	

	//Items
	
	public static Item GracePrismItem;


	public static Item WhistleItem;
	public static Item PepperSeedItem;
	public static Item AsphodelPetalsItem;
	public static Item BerlyItem;
	public static Item ingotCobaltItem;
	public static Item ingotIridiumItem;
	public static Item jadeItem;
	public static Item siliconChunk;
	public static Item sturdyHideItem;
	public static Item SulphurItem;
	public static Item TourmalineItem;
	
	public static Item SwordFostimberItem;
	public static Item PickaxeFostimberItem;
	public static Item AxeFostimberItem;
	public static Item ShovelFostimberItem;
	public static Item HoeFostimberItem;
	
	public static Item SwordStoneItem;
	public static Item PickaxeStoneItem;
	public static Item AxeStoneItem;
	public static Item ShovelStoneItem;
	public static Item HoeStoneItem;

	public static Item OverKillItem;
	public static Item DebugItem;
	
	
	/** Biome's **/
	public static BiomeGenBase ElysiumPlainBiome = null;

	
	@PreInit
	public void loadConfiguration(FMLPreInitializationEvent evt) {
//		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
//		GameRegistry.registerTileEntity(TileMixer.class, "Mixer");
//		GameRegistry.registerTileEntity(TileCandyMaker.class, "Candy Maker");


//		GameRegistry.addBiome(Halloween);

//		Version.versionCheck();


		mainConfiguration = new ElysianConfiguration(new File(evt.getModConfigurationDirectory(), "Elysium.cfg"));
		try
		{
			mainConfiguration.load();


			Property idDim = Elysium.mainConfiguration.get("dimensionID", "dim", DefaultProps.DimensionID, "This is the id of the dimension change if needed!");
			DimensionID = idDim.getInt();
			
			//Handlers
			MinecraftForge.EVENT_BUS.register(new ElysianBonemealHandler());
			MinecraftForge.EVENT_BUS.register(new ElysianSoundHandler());
			GameRegistry.registerFuelHandler(new ElysianFuelHandler());

			// Block Registry
			
			Property idPalestoneBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "palestone.id", DefaultProps.idPalestoneBlock, null);
			paleStone = (new ElysianBlockPalestone(idPalestoneBlock.getInt(), Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Palestone");
			ClientProxy.proxy.registerBlock(paleStone);
			LanguageRegistry.addName(paleStone, "Palestone");
			
			Property idSoilBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "ElysiumDirt.id", DefaultProps.idSoilBlock, null);
			soilBlock = (new ElysianBlockDirt(idSoilBlock.getInt(), Material.ground)).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("Gammasoil");
			ClientProxy.proxy.registerBlock(soilBlock);
			LanguageRegistry.addName(soilBlock, "Elysian Soil");

			Property idGrassBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "ElysiumGrass.id", DefaultProps.idGrassBlock, null);
			grassBlock = (new ElysianBlockGrass(idGrassBlock.getInt(), Material.ground)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Gammagrass");
			ClientProxy.proxy.registerBlock(grassBlock);
			LanguageRegistry.addName(grassBlock, "Elysian Grass");

			Property idLeucosandBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "Leucogrit.id", DefaultProps.idLeucosandBlock, null);
			LeucosandBlock = (new ElysianBlockSand(idLeucosandBlock.getInt(), Material.sand)).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("Leucogrit");
			ClientProxy.proxy.registerBlock(LeucosandBlock);
			LanguageRegistry.addName(LeucosandBlock, "Leucosand");

			Property idRiltBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "Rilt.id", DefaultProps.idRiltBlock, null);
			RiltBlock = (new ElysianBlockRilt(idRiltBlock.getInt(), Material.sand)).setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("Rilt");
			ClientProxy.proxy.registerBlock(RiltBlock);
			LanguageRegistry.addName(RiltBlock, "Rilt Block");

			Property idFostimberSaplingBlock = Elysium.mainConfiguration.getBlock("FostimberSaplingBlock.id", DefaultProps.idFostimberSaplingBlock);
			FostimberSaplingBlock = (new ElysianBlockFostimberSapling(idFostimberSaplingBlock.getInt())).setHardness(0F).setUnlocalizedName("fostimber_sapling");
			ClientProxy.proxy.registerBlock(FostimberSaplingBlock);
			LanguageRegistry.addName(FostimberSaplingBlock, "Fostimber Sapling");
			
			Property idFostimberLogBlock = Elysium.mainConfiguration.getBlock("FostimberLog.id", DefaultProps.idFostimberLogBlock);
			FostimberLogBlock = (new ElysianBlockFostimberLog(idFostimberLogBlock.getInt(), Material.wood)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Fostimber Log Top");
			ClientProxy.proxy.registerBlock(FostimberLogBlock);
			LanguageRegistry.addName(FostimberLogBlock, "Fostimber Log");

			Property idFostimberLeavesBlock = Elysium.mainConfiguration.getBlock("FostimberLeavesBlock.id", DefaultProps.idFostimberLeavesBlock);
			FostimberLeavesBlock = (new ElysianBlockFostimberLeaves(idFostimberLeavesBlock.getInt(), Material.leaves)).setLightOpacity(1).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("fostimber_leaves");
			ClientProxy.proxy.registerBlock(FostimberLeavesBlock);
			LanguageRegistry.addName(FostimberLeavesBlock, "Fostimber Leaves");

			Property idWoodBlock = Elysium.mainConfiguration.getBlock("idWoodBlock.id", DefaultProps.idWoodBlock);
			WoodBlock = (new ElysianBlock(idWoodBlock.getInt(), Material.wood)).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("fostimber_planks");
			ClientProxy.proxy.registerBlock(WoodBlock);
			LanguageRegistry.addName(WoodBlock, "Wooden Planks");
			
			Property idGastroShellBlock = Elysium.mainConfiguration.getBlock("idGastroShellBlock.id", DefaultProps.idGastroShellBlock);
			GastroShellBlock = (new ElysianBlockGastroShell(idGastroShellBlock.getInt(), Material.rock)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("gastroshellTop");
			ClientProxy.proxy.registerBlock(GastroShellBlock);
			LanguageRegistry.addName(GastroShellBlock, "Gastro Shell");

			Property idAsphodelFlowerBlock = Elysium.mainConfiguration.getBlock("idAsphodelFlowerBlock.id", DefaultProps.idAsphodelFlowerBlock);
			FlowerBlock = (new ElysiumFlowerBlock(idAsphodelFlowerBlock.getInt())).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("asphodel_flower");
			ClientProxy.proxy.registerBlock(FlowerBlock);
			LanguageRegistry.addName(FlowerBlock, "Asphodel Flower");

			Property idCurlgrassBlock = Elysium.mainConfiguration.getBlock("idCurlgrassBlock.id", DefaultProps.idCurlgrassBlock);
			CurlgrassBlock = new ElysianBlockTallgrass(idCurlgrassBlock.getInt()).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Curlgrass");
			ClientProxy.proxy.registerBlock(CurlgrassBlock);
			LanguageRegistry.addName(CurlgrassBlock, "Curlgrass");

			Property idOreSulphurBlock = Elysium.mainConfiguration.getBlock("idOreSulphurBlock.id", DefaultProps.idOreSulphurBlock);
			SulphurOreBlock = new ElysianBlockOre(idOreSulphurBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSulphur");
			ClientProxy.proxy.registerBlock(SulphurOreBlock);
			LanguageRegistry.addName(SulphurOreBlock, "Sulphur Ore");
			

			Property idOreBerylBlock = Elysium.mainConfiguration.getBlock("idOreBerylBlock.id", DefaultProps.idOreBerylBlock);
			BerylOreBlock = new ElysianBlockOre(idOreBerylBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreBeryl");
			ClientProxy.proxy.registerBlock(BerylOreBlock);
			LanguageRegistry.addName(BerylOreBlock, "Beryl Ore");
			
			Property idOreCobaltBlock = Elysium.mainConfiguration.getBlock("idOreCobaltBlock.id", DefaultProps.idOreCobaltBlock);
			CobaltOreBlock = new ElysianBlockOre(idOreCobaltBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreCobalt");
			ClientProxy.proxy.registerBlock(CobaltOreBlock);
			LanguageRegistry.addName(CobaltOreBlock, "Cobalt Ore");

			Property idOreIridiumBlock = Elysium.mainConfiguration.getBlock("idOreIridiumBlock.id", DefaultProps.idOreIridiumBlock);
			IridiumOreBlock = new ElysianBlockOre(idOreIridiumBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreIridium");
			ClientProxy.proxy.registerBlock(IridiumOreBlock);
			LanguageRegistry.addName(IridiumOreBlock, "Iridium Ore");
			
			Property idOreSiliconBlock = Elysium.mainConfiguration.getBlock("idOreSiliconBlock.id", DefaultProps.idOreSiliconBlock);
			SiliconOreBlock = new ElysianBlockOre(idOreSiliconBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSilicon");
			ClientProxy.proxy.registerBlock(SiliconOreBlock);
			LanguageRegistry.addName(SiliconOreBlock, "Silicon Ore");
			
			Property idOreJadeBlock = Elysium.mainConfiguration.getBlock("idOreJadeBlock.id", DefaultProps.idOreJadeBlock);
			JadeOreBlock = new ElysianBlockOre(idOreJadeBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreJade");
			ClientProxy.proxy.registerBlock(JadeOreBlock);
			LanguageRegistry.addName(JadeOreBlock, "Jade Ore");

			Property idOreTourmalineBlock = Elysium.mainConfiguration.getBlock("idOreTourmalineBlock.id", DefaultProps.idOreTourmalineBlock);
			TourmalineOreBlock = new ElysianBlockOre(idOreTourmalineBlock.getInt()).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreTourmaline");
			ClientProxy.proxy.registerBlock(TourmalineOreBlock);
			LanguageRegistry.addName(TourmalineOreBlock, "Tourmaline Ore");

			

			Property idWaterBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "idWaterBlock.id", DefaultProps.idWaterBlock, null);
			waterStill = new ElysianBlockLiquidStationary(idWaterBlock.getInt(), Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("elysian_water");
			ClientProxy.proxy.registerBlock(waterStill);
			LanguageRegistry.addName(waterStill, "Elysium Water Still");
			
			Property idWaterFlowingBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "idWaterFlowingBlock.id", DefaultProps.idWaterFlowingBlock, null);
			waterMoving = (ElysianBlockLiquid) new ElysianBlockLiquidFlowing(idWaterFlowingBlock.getInt(), Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("elysian_water_flow");
			ClientProxy.proxy.registerBlock(waterMoving);
			LanguageRegistry.addName(waterMoving, "Elysium Water Flowing");
			
			Property idPortalCoreBlock = Elysium.mainConfiguration.getBlock("idPortalCoreBlock.id", DefaultProps.idPortalCoreBlock);
			portalCore = new ElysianBlockPortalCore(idPortalCoreBlock.getInt(), Material.glass).setHardness(5F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("portalCore");
			ClientProxy.proxy.registerBlock(portalCore);
			LanguageRegistry.addName(portalCore, "Elysian Portal Block");

			Property idShellsBlock = Elysium.mainConfiguration.getBlock("idShellsBlock.id", DefaultProps.idShellsBlock);
			shellFloatingBlock = new ElysianBlockShell(idShellsBlock.getInt()).setHardness(0.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("shell");
			ClientProxy.proxy.registerBlock(shellFloatingBlock);
			LanguageRegistry.addName(shellFloatingBlock, "Shell");

			Property idConchBlock = Elysium.mainConfiguration.getBlock("idConchBlock.id", DefaultProps.idConchBlock);
			conchFloatingBlock = new ElysianBlockShell(idConchBlock.getInt()).setHardness(0.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("conch");
			ClientProxy.proxy.registerBlock(conchFloatingBlock);
			LanguageRegistry.addName(conchFloatingBlock, "Conch");

			Property idPaleCobblestoneBlock = Elysium.mainConfiguration.getBlock("idPaleCobblestoneBlock.id", DefaultProps.idPaleCobblestoneBlock);
			paleCobbletone = (new ElysianBlock(idPaleCobblestoneBlock.getInt(), Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("palecobblestone");
			ClientProxy.proxy.registerBlock(paleCobbletone);
			LanguageRegistry.addName(paleCobbletone, "Cobble Palestone");
			
			
			Block.dragonEgg.setCreativeTab(tabElysium);


			// Item Registry
			Property idGracePrismItem = Elysium.mainConfiguration.getItem("idGracePrismItem.id", DefaultProps.idGracePrismItem);
			GracePrismItem = new ElysianItemGracePrism(idGracePrismItem.getInt()).setUnlocalizedName("gracecrystal");
			LanguageRegistry.addName(GracePrismItem, "Grace Prism");

			Property idWhistleItem = Elysium.mainConfiguration.getItem("idWhistleItem.id", DefaultProps.idWhistleItem);
			WhistleItem = new ElysianItemWhistle(idWhistleItem.getInt()).setUnlocalizedName("enderflute");
			LanguageRegistry.addName(WhistleItem, "Ender Flute");
		
			Property idPepperSeedItem = Elysium.mainConfiguration.getItem("idPepperSeedItem.id", DefaultProps.idPepperSeedItem);
			PepperSeedItem = new ElysianItem(idPepperSeedItem.getInt()).setUnlocalizedName("seeds_pepper");
			LanguageRegistry.addName(PepperSeedItem, "Pepper Seed");

			Property idOverkillItem = Elysium.mainConfiguration.getItem("idOverkillItem.id", DefaultProps.idOverkillItem);
			OverKillItem = new ElysianItemOverkill(idOverkillItem.getInt()).setUnlocalizedName("asd");
			LanguageRegistry.addName(OverKillItem, "Overkill Item");
		
			Property idAsphodelPetalsItem = Elysium.mainConfiguration.getItem("idAsphodelPetalsItem.id", DefaultProps.idAsphodelPetalsItem);
			AsphodelPetalsItem = new ElysianItem(idAsphodelPetalsItem.getInt()).setUnlocalizedName("asphodelpetal");
			LanguageRegistry.addName(AsphodelPetalsItem, "Asphodel Petals");
			
			Property idDebugItem = Elysium.mainConfiguration.getItem("idDebugItem.id", DefaultProps.idDebugItem);
			DebugItem = new ElysianItemDebug(idDebugItem.getInt()).setUnlocalizedName("debug");
			LanguageRegistry.addName(DebugItem, "Modder Item");
			
			Property idBerylItem = Elysium.mainConfiguration.getItem("idBerylItem.id", DefaultProps.idBerylItem);
			BerlyItem = new ElysianItem(idBerylItem.getInt()).setUnlocalizedName("Beryl");
			LanguageRegistry.addName(BerlyItem, "Beryl");
			
			Property idCobaltIngotItem = Elysium.mainConfiguration.getItem("idCobaltIngotItem.id", DefaultProps.idCobaltIngotItem);
			ingotCobaltItem = new ElysianItem(idCobaltIngotItem.getInt()).setUnlocalizedName("ingotCobalt");
			LanguageRegistry.addName(ingotCobaltItem, "Cobalt Ingot");
			
			Property idIridiumIngotItem = Elysium.mainConfiguration.getItem("idIridiumIngotItem.id", DefaultProps.idIridiumIngotItem);
			ingotIridiumItem = new ElysianItem(idIridiumIngotItem.getInt()).setUnlocalizedName("ingotIridium");
			LanguageRegistry.addName(ingotIridiumItem, "Iridium Ingot");
			
			Property idJadeItem = Elysium.mainConfiguration.getItem("idJadeItem.id", DefaultProps.idJadeItem);
			jadeItem = new ElysianItem(idJadeItem.getInt()).setUnlocalizedName("Jade");
			LanguageRegistry.addName(jadeItem, "Jade");
			
			Property idSiliconChunk = Elysium.mainConfiguration.getItem("idSiliconChunk.id", DefaultProps.idSiliconChunk);
			siliconChunk = new ElysianItem(idSiliconChunk.getInt()).setUnlocalizedName("siliconchunk");
			LanguageRegistry.addName(siliconChunk, "Berly");

			Property idSulphurItem = Elysium.mainConfiguration.getItem("idSulphurItem.id", DefaultProps.idSulphurItem);
			SulphurItem = new ElysianItem(idSulphurItem.getInt()).setUnlocalizedName("Sulphur");
			LanguageRegistry.addName(SulphurItem, "Sulphur");

			Property idTourmalineItem = Elysium.mainConfiguration.getItem("idTourmalineItem.id", DefaultProps.idTourmalineItem);
			TourmalineItem = new ElysianItem(idTourmalineItem.getInt()).setUnlocalizedName("Tourmaline");
			LanguageRegistry.addName(TourmalineItem, "Tourmaline");
			
			Property idSturdyHideItem = Elysium.mainConfiguration.getItem("idSturdyHideItem.id", DefaultProps.idSturdyHideItem);
			sturdyHideItem = new ElysianItem(idSturdyHideItem.getInt()).setUnlocalizedName("SturdyHide");
			LanguageRegistry.addName(sturdyHideItem, "Sturdy Hide");
			
			
			
			//Tools
			EnumToolMaterial FOSTIMBER_MAT = EnumHelper.addToolMaterial("FOSTIMBER", 0, 59, 2.0F, 0, 15);

			Property idWoodSwordItem = Elysium.mainConfiguration.getItem("idWoodSwordItem.id", DefaultProps.idWoodSwordItem);
			SwordFostimberItem = new ElysianItemSword(idWoodSwordItem.getInt(), FOSTIMBER_MAT).setUnlocalizedName("swordFostimber");
			LanguageRegistry.addName(SwordFostimberItem, "Fostimber Sword");

			Property idWoodPickaxeItem = Elysium.mainConfiguration.getItem("idWoodPickaxeItem.id", DefaultProps.idWoodPickaxeItem);
			PickaxeFostimberItem = new ElysianItemPickaxe(idWoodPickaxeItem.getInt(), FOSTIMBER_MAT).setUnlocalizedName("pickaxeFostimber");
			LanguageRegistry.addName(PickaxeFostimberItem, "Fostimber Pickaxe");

			Property idWoodAxeItem = Elysium.mainConfiguration.getItem("idWoodAxeItem.id", DefaultProps.idWoodAxeItem);
			AxeFostimberItem = new ElysianItemAxe(idWoodAxeItem.getInt(), FOSTIMBER_MAT).setUnlocalizedName("axeFostimber");
			LanguageRegistry.addName(AxeFostimberItem, "Fostimber Axe");

			Property idWoodShovelItem = Elysium.mainConfiguration.getItem("idWoodShovelItem.id", DefaultProps.idWoodShovelItem);
			ShovelFostimberItem = new ElysianItemShovel(idWoodShovelItem.getInt(), FOSTIMBER_MAT).setUnlocalizedName("shovelFostimber");
			LanguageRegistry.addName(ShovelFostimberItem, "Fostimber Shovel");
			
			Property idWoodHoeItem = Elysium.mainConfiguration.getItem("idWoodHoeItem.id", DefaultProps.idWoodHoeItem);
			HoeFostimberItem = new ElysianItemHoe(idWoodHoeItem.getInt(), FOSTIMBER_MAT).setUnlocalizedName("hoeFostimber");
			LanguageRegistry.addName(HoeFostimberItem, "Fostimber Hoe");

			EnumToolMaterial STONE_MAT = EnumHelper.addToolMaterial("PALESTONE", 1, 131, 4.0F, 1, 5);

			Property idStoneSwordItem = Elysium.mainConfiguration.getItem("idStoneSwordItem.id", DefaultProps.idStoneSwordItem);
			SwordStoneItem = new ElysianItemSword(idStoneSwordItem.getInt(), STONE_MAT).setUnlocalizedName("swordPalestone");
			LanguageRegistry.addName(SwordStoneItem, "Palestone Sword");

			Property idStonePickaxeItem = Elysium.mainConfiguration.getItem("idStonePickaxeItem.id", DefaultProps.idStonePickaxeItem);
			PickaxeStoneItem = new ElysianItemPickaxe(idStonePickaxeItem.getInt(), STONE_MAT).setUnlocalizedName("pickaxePalestone");
			LanguageRegistry.addName(PickaxeStoneItem, "Palestone Pickaxe");

			Property idStoneAxeItem = Elysium.mainConfiguration.getItem("idStoneAxeItem.id", DefaultProps.idStoneAxeItem);
			AxeStoneItem = new ElysianItemAxe(idStoneAxeItem.getInt(), STONE_MAT).setUnlocalizedName("axePalestone");
			LanguageRegistry.addName(AxeStoneItem, "Palestone Axe");

			Property idStoneShovelItem = Elysium.mainConfiguration.getItem("idStoneShovelItem.id", DefaultProps.idStoneShovelItem);
			ShovelStoneItem = new ElysianItemShovel(idStoneShovelItem.getInt(), STONE_MAT).setUnlocalizedName("shovelPalestone");
			LanguageRegistry.addName(ShovelStoneItem, "Palestone Shovel");
			
			Property idStoneHoeItem = Elysium.mainConfiguration.getItem("idStoneHoeItem.id", DefaultProps.idStoneHoeItem);
			HoeStoneItem = new ElysianItemHoe(idStoneHoeItem.getInt(), STONE_MAT).setUnlocalizedName("hoePalestone");
			LanguageRegistry.addName(HoeStoneItem, "Palestone Hoe");
			
			MinecraftForge.setToolClass(PickaxeFostimberItem, "pickaxe", 0);
	        MinecraftForge.setToolClass(AxeFostimberItem, "axe", 0);
	        MinecraftForge.setToolClass(ShovelFostimberItem, "shovel", 0);
	        MinecraftForge.setToolClass(PickaxeStoneItem, "pickaxe", 1);
	        MinecraftForge.setToolClass(AxeStoneItem, "axe", 1);
	        MinecraftForge.setToolClass(ShovelStoneItem, "shovel", 1);

			MinecraftForge.setBlockHarvestLevel(SulphurOreBlock, "pickaxe", 0);
			MinecraftForge.setBlockHarvestLevel(CobaltOreBlock, "pickaxe", 1);
			MinecraftForge.setBlockHarvestLevel(SiliconOreBlock, "pickaxe", 2);
			MinecraftForge.setBlockHarvestLevel(IridiumOreBlock, "pickaxe", 2);
			MinecraftForge.setBlockHarvestLevel(JadeOreBlock, "pickaxe", 2);
			MinecraftForge.setBlockHarvestLevel(BerylOreBlock, "pickaxe", 2);
			MinecraftForge.setBlockHarvestLevel(TourmalineOreBlock, "pickaxe", 3);
			
			
			// Crafting Registry
			GameRegistry.addRecipe(new ItemStack(GracePrismItem), new Object[] {"SMS","MDM","SMS", Character.valueOf('S'), Block.whiteStone, Character.valueOf('M'), Item.bucketMilk, Character.valueOf('D'), Item.diamond});
			GameRegistry.addRecipe(new ItemStack(this.PickaxeFostimberItem), new Object[] {"WW "," SW","S W", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.WoodBlock});
			GameRegistry.addRecipe(new ItemStack(this.PickaxeStoneItem), new Object[] {"WW "," SW","S W", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.paleCobbletone});
			GameRegistry.addRecipe(new ItemStack(this.ShovelFostimberItem), new Object[] {" WW"," SW","S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.WoodBlock});
			GameRegistry.addRecipe(new ItemStack(this.ShovelStoneItem), new Object[] {" WW"," SW","S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.paleCobbletone});
			GameRegistry.addRecipe(new ItemStack(this.HoeFostimberItem), new Object[] {"WWW"," S ","S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.WoodBlock});
			GameRegistry.addRecipe(new ItemStack(this.HoeStoneItem), new Object[] {"WWW"," S ","S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.paleCobbletone});
			GameRegistry.addRecipe(new ItemStack(this.AxeFostimberItem), new Object[] {"WW ","WS ", "S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.WoodBlock});
			GameRegistry.addRecipe(new ItemStack(this.AxeStoneItem), new Object[] {"WW ","WS ", "S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.paleCobbletone});
			GameRegistry.addRecipe(new ItemStack(this.SwordFostimberItem), new Object[] {"  W"," W ", "S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.WoodBlock});
			GameRegistry.addRecipe(new ItemStack(this.SwordStoneItem), new Object[] {"  W"," W ", "S  ", Character.valueOf('S'), Item.stick, Character.valueOf('W'), this.paleCobbletone});
			GameRegistry.addRecipe(new ItemStack(this.WhistleItem), new Object[] {" OO","O O", "EO ", Character.valueOf('O'), Block.obsidian, Character.valueOf('E'), Item.eyeOfEnder});
			
			GameRegistry.addShapelessRecipe(new ItemStack(AsphodelPetalsItem, 2), new Object[] {FlowerBlock});

			//Smelting Regostry
			GameRegistry.addSmelting(this.CobaltOreBlock.blockID, new ItemStack(this.ingotCobaltItem), 0.7F);
			GameRegistry.addSmelting(this.IridiumOreBlock.blockID, new ItemStack(this.ingotIridiumItem), 1.0F);
			

			
			// Entity Registry
			GameRegistry.registerTileEntity(ElysianTileEntityPortal.class, "ElysiumTileEntityPortal");
			
			ClientProxy.proxy.RegisterRenders();
		}
		finally
		{
			mainConfiguration.save();
		}
	}
	@Init
	public void initialize(FMLInitializationEvent evt) {
		
		
		Plants.addGrassPlant(CurlgrassBlock, 0, 30);
		Plants.addGrassPlant(FlowerBlock, 0, 10);
		Plants.addGrassSeed(new ItemStack(PepperSeedItem), 10);
		
//		new LiquidStacks();
//		CoreProxy.proxy.addAnimation();
//		LiquidManager.liquids.add(new LiquidData(LiquidStacks.rawCandy, new ItemStack(rawCandyBucket), new ItemStack(Item.bucketEmpty)));
//		LiquidManager.liquids.add(new LiquidData(LiquidStacks.milk, new ItemStack(Item.bucketMilk), new ItemStack(Item.bucketEmpty)));

//		CoreProxy.proxy.initializeRendering();
//		CoreProxy.proxy.initializeEntityRendering();
		
	
		/** Register WorldProvider for Dimension **/
		DimensionManager.registerProviderType(DimensionID, WorldProviderElysium.class, true);
		DimensionManager.registerDimension(DimensionID, DimensionID);

		
		ElysiumPlainBiome = new BiomeGenElysium(25);
		
		
		GameRegistry.registerWorldGenerator(new WorldGenElysium());

	}

	
}
