package mods.elysium;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mods.elysium.api.Plants;
import mods.elysium.blocks.CurlgrassBlock;
import mods.elysium.blocks.ElysianBlockFluid;
import mods.elysium.blocks.ElysiumBlockFlowing;
import mods.elysium.blocks.ElysiumBlockStationary;
import mods.elysium.blocks.ElysiumFlowerBlock;
import mods.elysium.blocks.ElysianBlock;
import mods.elysium.blocks.FostimberLogBlock;
import mods.elysium.blocks.FostimberLeavesBlock;
import mods.elysium.blocks.FostimberSaplingBlock;
import mods.elysium.blocks.GastroShellBlock;
import mods.elysium.blocks.GrassBlock;
import mods.elysium.blocks.LeucosandBlock;
import mods.elysium.blocks.PalestoneBlock;
import mods.elysium.blocks.RiltBlock;
import mods.elysium.blocks.SoilBlock;
import mods.elysium.blocks.SulphurOreBlock;
import mods.elysium.dimension.BiomeGenElysium;
import mods.elysium.dimension.BlockTutorialFire;
import mods.elysium.dimension.BlockTutorialPortal;
import mods.elysium.dimension.ItemPortalPlacer;
import mods.elysium.dimension.WorldProviderElysium;
import mods.elysium.gen.ElysiumWorldGen;
import mods.elysium.handlers.BonemealHandler;
import mods.elysium.items.ElysianItem;
import mods.elysium.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
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
public class Elysium {
	@Instance
	public static Elysium instance;
	
	public static ElysiumConfiguration mainConfiguration;
	
	public static final CreativeTabs tabElysium = new ElysiumTab(12, "The Elysium");
		
	/** Dimension ID **/
	public static int DimensionID;
	
	//Blocks
	
	public static Block paleStone;
	public static Block soilBlock;
	public static Block grassBlock;
	public static Block LeucosandBlock;
	public static Block RiltBlock;
	public static Block FostimberLogBlock;
	public static Block FostimberLeavesBlock;
	public static Block GastroShellBlock;
	public static Block FostimberSaplingBlock;
	public static Block WoodBlock;
	public static Block FlowerBlock;
	public static Block CurlgrassBlock;
	public static Block SulphurOreBlock;
	public static Block CobaltOreBlock;
	public static Block IridiumOreBlock;
	public static Block SiliconOreBlock;
	public static Block JadeOreBlock;
	public static Block TourmalineOreBlock;
	public static Block BerylOreBlock;
	public static Block waterStill;
	public static ElysianBlockFluid waterMoving;
//	public static Block LeucosandBlock;
//	public static Block LeucosandBlock;
//	public static Block LeucosandBlock;
//	public static Block LeucosandBlock;
//	public static Block LeucosandBlock;
	

	//Items
	
	public static Item GracePrismItem;
//	public static Item GracePrismItem;
//	public static Item GracePrismItem;
//	public static Item GracePrismItem;
//	public static Item GracePrismItem;

	
	
	
	public static Item TutorialPortalPlacer;
	public static BlockTutorialFire TutorialFire;
	public static BlockTutorialPortal TutorialPortal;
	
	/** Biome's **/
	public static BiomeGenBase ElysiumPlainBiome = null;

	
	@PreInit
	public void loadConfiguration(FMLPreInitializationEvent evt) {
//		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
//		GameRegistry.registerTileEntity(TileMixer.class, "Mixer");
//		GameRegistry.registerTileEntity(TileCandyMaker.class, "Candy Maker");


//		GameRegistry.addBiome(Halloween);

//		Version.versionCheck();


		mainConfiguration = new ElysiumConfiguration(new File(evt.getModConfigurationDirectory(), "Elysium.cfg"));
		try
		{
			mainConfiguration.load();


			Property idDim = Elysium.mainConfiguration.get("dimensionID", "dim", DefaultProps.DimensionID, "This is the id of the dimension change if needed!");
			DimensionID = idDim.getInt();
			
			// Block Registry
			
			Property idPalestoneBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "palestone.id", DefaultProps.idPalestoneBlock, null);
			paleStone = (new PalestoneBlock(idPalestoneBlock.getInt(), Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Palestone");
			ClientProxy.proxy.registerBlock(paleStone);
			LanguageRegistry.addName(paleStone, "Palestone");
			
			Property idSoilBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "ElysiumDirt.id", DefaultProps.idSoilBlock, null);
			soilBlock = (new SoilBlock(idSoilBlock.getInt(), Material.ground)).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("Gammasoil");
			ClientProxy.proxy.registerBlock(soilBlock);
			LanguageRegistry.addName(soilBlock, "Elysian Soil");

			Property idGrassBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "ElysiumGrass.id", DefaultProps.idGrassBlock, null);
			grassBlock = (new GrassBlock(idGrassBlock.getInt(), Material.ground)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Gammagrass");
			ClientProxy.proxy.registerBlock(grassBlock);
			LanguageRegistry.addName(grassBlock, "Elysian Grass");

			Property idLeucosandBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "Leucogrit.id", DefaultProps.idLeucosandBlock, null);
			LeucosandBlock = (new LeucosandBlock(idLeucosandBlock.getInt(), Material.sand)).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("Leucogrit");
			ClientProxy.proxy.registerBlock(LeucosandBlock);
			LanguageRegistry.addName(LeucosandBlock, "Leucosand");

			Property idRiltBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "Rilt.id", DefaultProps.idRiltBlock, null);
			RiltBlock = (new RiltBlock(idRiltBlock.getInt(), Material.sand)).setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("Rilt");
			ClientProxy.proxy.registerBlock(RiltBlock);
			LanguageRegistry.addName(RiltBlock, "Rilt Block");

			Property idFostimberSaplingBlock = Elysium.mainConfiguration.getBlock("FostimberSaplingBlock.id", DefaultProps.idFostimberSaplingBlock);
			FostimberSaplingBlock = (new FostimberSaplingBlock(idFostimberSaplingBlock.getInt())).setHardness(0F).setUnlocalizedName("fostimber_sapling");
			ClientProxy.proxy.registerBlock(FostimberSaplingBlock);
			LanguageRegistry.addName(FostimberSaplingBlock, "Fostimber Sapling");
			
			Property idFostimberLogBlock = Elysium.mainConfiguration.getBlock("FostimberLog.id", DefaultProps.idFostimberLogBlock);
			FostimberLogBlock = (new FostimberLogBlock(idFostimberLogBlock.getInt(), Material.wood)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Fostimber Log Top");
			ClientProxy.proxy.registerBlock(FostimberLogBlock);
			LanguageRegistry.addName(FostimberLogBlock, "Fostimber Log");

			Property idFostimberLeavesBlock = Elysium.mainConfiguration.getBlock("FostimberLeavesBlock.id", DefaultProps.idFostimberLeavesBlock);
			FostimberLeavesBlock = (new FostimberLeavesBlock(idFostimberLeavesBlock.getInt(), Material.leaves)).setLightOpacity(1).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("fostimber_leaves");
			ClientProxy.proxy.registerBlock(FostimberLeavesBlock);
			LanguageRegistry.addName(FostimberLeavesBlock, "Fostimber Leaves");

			Property idWoodBlock = Elysium.mainConfiguration.getBlock("idWoodBlock.id", DefaultProps.idWoodBlock);
			WoodBlock = (new ElysianBlock(idWoodBlock.getInt(), Material.wood)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("fostimber_planks");
			ClientProxy.proxy.registerBlock(WoodBlock);
			LanguageRegistry.addName(WoodBlock, "Wooden Planks");
			
			Property idGastroShellBlock = Elysium.mainConfiguration.getBlock("idGastroShellBlock.id", DefaultProps.idGastroShellBlock);
			GastroShellBlock = (new GastroShellBlock(idGastroShellBlock.getInt(), Material.leaves)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("gastroshellTop");
			ClientProxy.proxy.registerBlock(GastroShellBlock);
			LanguageRegistry.addName(GastroShellBlock, "Gastro Shell");

			Property idAsphodelFlowerBlock = Elysium.mainConfiguration.getBlock("idAsphodelFlowerBlock.id", DefaultProps.idAsphodelFlowerBlock);
			FlowerBlock = (new ElysiumFlowerBlock(idAsphodelFlowerBlock.getInt())).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("asphodel_flower");
			ClientProxy.proxy.registerBlock(FlowerBlock);
			LanguageRegistry.addName(FlowerBlock, "Asphodel Flower");

			Property idCurlgrassBlock = Elysium.mainConfiguration.getBlock("idCurlgrassBlock.id", DefaultProps.idCurlgrassBlock);
			CurlgrassBlock = new CurlgrassBlock(idCurlgrassBlock.getInt()).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Curlgrass");
			ClientProxy.proxy.registerBlock(CurlgrassBlock);
			LanguageRegistry.addName(CurlgrassBlock, "Curlgrass");

			Property idOreSulphurBlock = Elysium.mainConfiguration.getBlock("idOreSulphurBlock.id", DefaultProps.idOreSulphurBlock);
			SulphurOreBlock = new SulphurOreBlock(idOreSulphurBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSulphur");
			ClientProxy.proxy.registerBlock(SulphurOreBlock);
			LanguageRegistry.addName(SulphurOreBlock, "Sulphur Ore");
			

			Property idOreCobaltBlock = Elysium.mainConfiguration.getBlock("idOreCobaltBlock.id", DefaultProps.idOreCobaltBlock);
	/**/	CobaltOreBlock = new SulphurOreBlock(idOreCobaltBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreCobalt");
			ClientProxy.proxy.registerBlock(CobaltOreBlock);
			LanguageRegistry.addName(CobaltOreBlock, "Cobalt Ore");

			Property idOreIridiumBlock = Elysium.mainConfiguration.getBlock("idOreIridiumBlock.id", DefaultProps.idOreIridiumBlock);
			IridiumOreBlock = new SulphurOreBlock(idOreIridiumBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreIridium");
			ClientProxy.proxy.registerBlock(IridiumOreBlock);
			LanguageRegistry.addName(IridiumOreBlock, "Iridium Ore");
			
			Property idOreSiliconBlock = Elysium.mainConfiguration.getBlock("idOreSiliconBlock.id", DefaultProps.idOreSiliconBlock);
			SiliconOreBlock = new SulphurOreBlock(idOreSiliconBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSilicon");
			ClientProxy.proxy.registerBlock(SiliconOreBlock);
			LanguageRegistry.addName(SiliconOreBlock, "Silicon Ore");
			
			Property idOreJadeBlock = Elysium.mainConfiguration.getBlock("idOreJadeBlock.id", DefaultProps.idOreJadeBlock);
			JadeOreBlock = new SulphurOreBlock(idOreJadeBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreJade");
			ClientProxy.proxy.registerBlock(JadeOreBlock);
			LanguageRegistry.addName(JadeOreBlock, "Jade Ore");

			Property idOreTourmalineBlock = Elysium.mainConfiguration.getBlock("idOreTourmalineBlock.id", DefaultProps.idOreTourmalineBlock);
			TourmalineOreBlock = new SulphurOreBlock(idOreTourmalineBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreTourmaline");
			ClientProxy.proxy.registerBlock(TourmalineOreBlock);
			LanguageRegistry.addName(TourmalineOreBlock, "Tourmaline Ore");

			Property idOreBerylBlock = Elysium.mainConfiguration.getBlock("idOreBerylBlock.id", DefaultProps.idOreBerylBlock);
			BerylOreBlock = new SulphurOreBlock(idOreBerylBlock.getInt(), Material.rock).setHardness(0.2F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreBeryl");
			ClientProxy.proxy.registerBlock(BerylOreBlock);
			LanguageRegistry.addName(BerylOreBlock, "Beryl Ore");
			

			Property idWaterBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "idWaterBlock.id", DefaultProps.idWaterBlock, null);
			waterStill = new ElysiumBlockStationary(idWaterBlock.getInt(), Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("elysian_water");
			ClientProxy.proxy.registerBlock(waterStill);
			LanguageRegistry.addName(waterStill, "Elysium Water Still");
			
			Property idWaterFlowingBlock = Elysium.mainConfiguration.getTerrainBlock("terrainGen", "idWaterFlowingBlock.id", DefaultProps.idWaterFlowingBlock, null);
			waterMoving = (ElysianBlockFluid) new ElysiumBlockFlowing(idWaterFlowingBlock.getInt(), Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("elysian_water_flow");
			ClientProxy.proxy.registerBlock(waterMoving);
			LanguageRegistry.addName(waterMoving, "Elysium Water Flowing");
			
		
			 
			
			//dim
			TutorialPortalPlacer = new ItemPortalPlacer(3048)
			.setUnlocalizedName("Tutorial:TutorialPortalPlacer");
			
			TutorialFire = (BlockTutorialFire) new BlockTutorialFire(
					2028).setUnlocalizedName("Tuturial:Tutorialfire_0");
			TutorialPortal = (BlockTutorialPortal) new BlockTutorialPortal(
					2029).setUnlocalizedName("Tutorial:TutorialPortal");
			
//	        MinecraftForge.setToolClass(Item.pickaxeWood,    "pickaxe", 0);

	 		// Item Registry
			Property idGracePrismItem = Elysium.mainConfiguration.getItem("idGracePrismItem.id", DefaultProps.idGracePrismItem);
			GracePrismItem = new ElysianItem(idGracePrismItem.getInt()).setUnlocalizedName("gracecrystal");
			LanguageRegistry.addName(GracePrismItem, "Grace Prism");
			
			
			
			
//			MinecraftForge.setBlockHarvestLevel(ash, "shovel", 0);
//		 	MinecraftForge.setBlockHarvestLevel(blockAsh, "shovel", 0);
		}
		finally
		{
			mainConfiguration.save();
		}
	}
	@Init
	public void initialize(FMLInitializationEvent evt) {
		
		MinecraftForge.EVENT_BUS.register(new BonemealHandler());
		
		Plants.addGrassPlant(CurlgrassBlock, 0, 30);
		Plants.addGrassPlant(FlowerBlock, 0, 10);
		
//		new LiquidStacks();
//		CoreProxy.proxy.addAnimation();
//		LiquidManager.liquids.add(new LiquidData(LiquidStacks.rawCandy, new ItemStack(rawCandyBucket), new ItemStack(Item.bucketEmpty)));
//		LiquidManager.liquids.add(new LiquidData(LiquidStacks.milk, new ItemStack(Item.bucketMilk), new ItemStack(Item.bucketEmpty)));

//		CoreProxy.proxy.initializeRendering();
//		CoreProxy.proxy.initializeEntityRendering();
		/** Register Blocks **/
		GameRegistry.registerBlock(TutorialFire, "TutorialFire");
		GameRegistry.registerBlock(TutorialPortal, "TutorialPortal");
		/** Register Items **/
		GameRegistry.registerItem(TutorialPortalPlacer, "TutorialPortalPlacer");
		/** Add In-Game Names **/
		LanguageRegistry.addName(TutorialFire, "Tutorial Fire");
		LanguageRegistry.addName(TutorialPortal, "Tutorial Portal");
		LanguageRegistry.addName(TutorialPortalPlacer, "Portal Placer");
		/** Register WorldProvider for Dimension **/
		DimensionManager.registerProviderType(DimensionID, WorldProviderElysium.class, true);
		DimensionManager.registerDimension(DimensionID, DimensionID);

		
		ElysiumPlainBiome = new BiomeGenElysium(25);
		
		
		GameRegistry.registerWorldGenerator(new ElysiumWorldGen());

	}

	
}
