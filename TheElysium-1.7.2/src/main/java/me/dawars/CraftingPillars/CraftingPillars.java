package me.dawars.CraftingPillars;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.dawars.CraftingPillars.api.CraftingPillarAPI;
import me.dawars.CraftingPillars.api.FreezerRecipes;
import me.dawars.CraftingPillars.api.baubles.Baubles;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviorArrow;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviorEgg;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviorFireball;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviorPotion;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviorSnowball;
import me.dawars.CraftingPillars.api.sentry.SentryBehaviors;
import me.dawars.CraftingPillars.baubles.ModEnderPearl;
import me.dawars.CraftingPillars.blocks.AnvilPillarBlock;
import me.dawars.CraftingPillars.blocks.BrewingPillarBlock;
import me.dawars.CraftingPillars.blocks.ChristmasLeavesBlock;
import me.dawars.CraftingPillars.blocks.ChristmasLightBlock;
import me.dawars.CraftingPillars.blocks.ChristmasPresentBlock;
import me.dawars.CraftingPillars.blocks.ChristmasTreeSapling;
import me.dawars.CraftingPillars.blocks.CraftingPillarBlock;
import me.dawars.CraftingPillars.blocks.DiskPlayerPillarBlock;
import me.dawars.CraftingPillars.blocks.ExtendPillarBlock;
import me.dawars.CraftingPillars.blocks.FreezerPillarBlock;
import me.dawars.CraftingPillars.blocks.FurnacePillarBlock;
import me.dawars.CraftingPillars.blocks.PotPillarBlock;
import me.dawars.CraftingPillars.blocks.PumpPillarBlock;
import me.dawars.CraftingPillars.blocks.SentryPillarBlock;
import me.dawars.CraftingPillars.blocks.ShowOffPillarBlock;
import me.dawars.CraftingPillars.blocks.TankPillarBlock;
import me.dawars.CraftingPillars.blocks.TrashPillarBlock;
import me.dawars.CraftingPillars.handlers.PillarEventHandler;
import me.dawars.CraftingPillars.items.BaseItem;
import me.dawars.CraftingPillars.items.ItemRingBase;
import me.dawars.CraftingPillars.items.ItemVirgacs;
import me.dawars.CraftingPillars.items.PillarRecord;
import me.dawars.CraftingPillars.items.WinterFood2013;
import me.dawars.CraftingPillars.proxy.CommonProxy;
import me.dawars.CraftingPillars.tiles.TileEntityAnvilPillar;
import me.dawars.CraftingPillars.tiles.TileEntityBrewingPillar;
import me.dawars.CraftingPillars.tiles.TileEntityChristmasPresent;
import me.dawars.CraftingPillars.tiles.TileEntityCraftingPillar;
import me.dawars.CraftingPillars.tiles.TileEntityDiskPlayerPillar;
import me.dawars.CraftingPillars.tiles.TileEntityExtendPillar;
import me.dawars.CraftingPillars.tiles.TileEntityFreezerPillar;
import me.dawars.CraftingPillars.tiles.TileEntityFurnacePillar;
import me.dawars.CraftingPillars.tiles.TileEntityLight;
import me.dawars.CraftingPillars.tiles.TileEntityPotPillar;
import me.dawars.CraftingPillars.tiles.TileEntityPumpPillar;
import me.dawars.CraftingPillars.tiles.TileEntitySentryPillar;
import me.dawars.CraftingPillars.tiles.TileEntityShowOffPillar;
import me.dawars.CraftingPillars.tiles.TileEntityTankPillar;
import me.dawars.CraftingPillars.tiles.TileEntityTrashPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name = CraftingPillars.name, version = CraftingPillars.version, useMetadata = false, modid = CraftingPillars.ID/*, dependencies = "required-after:Forge@[9.11.1.953,)"*/)
public class CraftingPillars
{
	@Instance(CraftingPillars.ID)
	private static CraftingPillars instance;

	public static CraftingPillars getInstance()
	{
		return instance;
	}

	public static final String version = "1.6.0";//keep the 0 there!
	public static final String name = "Crafting Pillars";
	public static final String ID = "craftingpillars";
	public static final String channelGame = "PillarGameClick";
	public static final String channelGui = "PillarGuiClick";
	public static final String channelProps = "PillarProps";


	// The Handler For Opening Guis
//	private GuiHandler guiHandler = new GuiHandler();
	private File configPath;
	public static Property checkUpdates;

	public static boolean modForestry = false;
	public static boolean modElysium = false;
	public static boolean modThaumcraft = false;
	public static boolean modBaubles = false;

	public static Item itemWandThaumcraft;

	@SidedProxy(clientSide = "me.dawars.CraftingPillars.proxy.ClientProxy", serverSide = "me.dawars.CraftingPillars.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Configuration config;

	public static final CreativeTabs tabPillar = new CreativeTabs("CraftingPillars")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(CraftingPillars.blockCraftingPillar);
		}
	};

	public static int extendPillarRenderID;
	public static int showOffPillarRenderID;
	public static int craftingPillarRenderID;
	public static int furnacePillarRenderID;
	public static int anvilPillarRenderID;
	public static int tankPillarRenderID;
	public static int brewingillarRenderID;
	public static int diskPlayerRenderID;
	public static int freezerPillarRenderID;
	public static int potPillarRenderID;
	public static int sentryPillarRenderID;
	public static int trashPillarRenderID;
	public static int pumpPillarRenderID;

	public static int PresentRenderID;
	public static int lightRenderID;
	public static int christmasLeavesRenderID;

	public static Block blockBasePillar;
	public static Block blockShowOffPillar;
	public static Block blockCraftingPillar;
	public static Block blockFurnacePillar;
	public static Block blockAnvilPillar;
	public static Block blockTankPillar;
	public static Block blockBrewingPillar;
	public static Block blockDiskPlayerPillar;
	public static Block blockFreezerPillar;
	public static Block blockPotPillar;
	public static Block blockSentryPillar;
	public static Block blockTrashPillar;
	public static Block blockPumpPillar;

	public static Block blockChristmasLeaves;
	public static Block blockChristmasTreeSapling;
	public static Block blockChristmasPresent;
	public static Block blockChristmasLight;

	public static Item itemDiscElysium;
//	public static Item itemCalendar2013;
//	public static Item itemElysiumLoreBook;
	public static Item itemRibbonDiamond;
	public static Item itemWinterFood2013;
	public static Item itemVirgacs;
	
	public static Item itemRing;
	public static Item itemRingDummy;

	public static boolean floatingItems = true;
	public static boolean rayTrace = false;
	public static boolean renderHitBoxes = true;
	public static boolean winter;
	public static boolean easter;
	public static boolean valentine;
	public static boolean halloween;

	public static AchievementPage achievementPage;

	public static Achievement achievementGettingStarted;
	public static Achievement achievementChristmas;
	public static Achievement achievementRecursion;
	public static Achievement achievementCompressingLiquids;

	public static Achievement achievementShowoff;
	public static Achievement achievementDiamond;
	public static Achievement achievementDisc;
	public static Achievement achievementRecursion3;
	public static boolean debug = false;

	public void addItemsAndBlocks()
	{
		// Block Registering
		blockBasePillar = (new ExtendPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("extendPillar");
		registerBlock(blockBasePillar);

		blockShowOffPillar = (new ShowOffPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("showOffPillar");
		registerBlock(blockShowOffPillar);
		
		blockCraftingPillar = (new CraftingPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("craftingPillar");
		registerBlock(blockCraftingPillar);
		
		blockFurnacePillar = (new FurnacePillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("furnacePillar");
		registerBlock(blockFurnacePillar);
		
		blockAnvilPillar = (new AnvilPillarBlock(Material.anvil)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("anvilPillar");
		registerBlock(blockAnvilPillar);
		
		blockTankPillar = (new TankPillarBlock(Material.glass)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("tankPillar");
		registerBlock(blockTankPillar);
		
		blockBrewingPillar = (new BrewingPillarBlock(Material.iron)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("brewingPillar");
		registerBlock(blockBrewingPillar);
		
		blockDiskPlayerPillar = (new DiskPlayerPillarBlock(Material.iron)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("diskPillar");
		registerBlock(blockDiskPlayerPillar);
		
		blockFreezerPillar = (new FreezerPillarBlock(Material.glass)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("freezerPillar");
		registerBlock(blockFreezerPillar);
		
		blockPotPillar = (new PotPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("potPillar");
		registerBlock(blockPotPillar);
		
		blockSentryPillar = (new SentryPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("sentryPillar");
		registerBlock(blockSentryPillar);
		
		blockTrashPillar = (new TrashPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("trashPillar");
		registerBlock(blockTrashPillar);
		
		blockPumpPillar = (new PumpPillarBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("pumpPillar").setCreativeTab(null);
		registerBlock(blockPumpPillar);
		
		
		//Christmas
		blockChristmasLeaves = (new ChristmasLeavesBlock(Material.leaves)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("xmasLeaves");
		if(!winter)
			blockChristmasLeaves.setCreativeTab(null);
		registerBlock(blockChristmasLeaves);
		            
		blockChristmasTreeSapling = (new ChristmasTreeSapling()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("sapling").setBlockName("ChristmasTreeSapling");
		if(!winter)
			blockChristmasTreeSapling.setCreativeTab(null);
		registerBlock(blockChristmasTreeSapling);
		        
		blockChristmasPresent = (new ChristmasPresentBlock(Material.cloth)).setHardness(1.0F).setStepSound(Block.soundTypeCloth).setBlockName("present");
		if(!winter)
			blockChristmasPresent.setCreativeTab(null);
		registerBlock(blockChristmasPresent);
		
		blockChristmasLight = (new ChristmasLightBlock(Material.glass)).setHardness(0.1F).setStepSound(Block.soundTypeGlass).setBlockName("christmas_light");
		if(!winter)
			blockChristmasLight.setCreativeTab(null);
		registerBlock(blockChristmasLight);


		//Items
		itemDiscElysium = new PillarRecord("UranusParadise").setUnlocalizedName("record").setTextureName(CraftingPillars.ID + ":ElysiumDisk").setCreativeTab(tabPillar);
		GameRegistry.registerItem(itemDiscElysium, itemDiscElysium.getUnlocalizedName());

//		itemCalendar2013 = new AdventCalendar2013().setUnlocalizedName("AdventCalendar2013");
//		if(!winter)
//			itemCalendar2013.setCreativeTab(null);

		itemWinterFood2013 = new WinterFood2013(5, 0.5F).setUnlocalizedName("WinterFood");
		if(!winter)
			itemWinterFood2013.setCreativeTab(null);
		GameRegistry.registerItem(itemWinterFood2013, itemWinterFood2013.getUnlocalizedName());

		itemRibbonDiamond = new BaseItem().setUnlocalizedName("RibbonDiamond");
		GameRegistry.registerItem(itemRibbonDiamond, itemRibbonDiamond.getUnlocalizedName());

		itemVirgacs = new ItemVirgacs().setUnlocalizedName("Virgacs");
		if(!winter)
			itemVirgacs.setCreativeTab(null);
		GameRegistry.registerItem(itemVirgacs, itemVirgacs.getUnlocalizedName());

//		itemElysiumLoreBook = new BookElysium().setUnlocalizedName("ElysiumLoreBook");

		itemRing = new ItemRingBase().setUnlocalizedName("ring_base").setCreativeTab(tabPillar);
		GameRegistry.registerItem(itemRing, itemRing.getUnlocalizedName());

		itemRingDummy = new Item().setUnlocalizedName("ring_dummy").setTextureName(ID + ":iron_ring").setCreativeTab(null);
		GameRegistry.registerItem(itemRingDummy, itemRingDummy.getUnlocalizedName());
		
	}

	public void addAchievementsAndCreativeTab()
	{
		achievementGettingStarted = new Achievement("achievement.gettingstarted", "gettingstarted", 0, 0, blockBasePillar, null).registerStat();

		achievementRecursion = new Achievement("achievement.recursion", "recursion", 1, 1, blockCraftingPillar, achievementGettingStarted).registerStat();
		achievementShowoff = new Achievement("achievement.showoff", "showoff", 3, 1, blockShowOffPillar, achievementRecursion).registerStat();
		achievementRecursion3 = new Achievement("achievement.recursion3", "recursion3", 5, 1, blockChristmasPresent, achievementShowoff).registerStat();

		achievementCompressingLiquids = new Achievement("achievement.liquids", "liquids", 1, 2, blockFreezerPillar, achievementGettingStarted).registerStat();

		achievementChristmas = new Achievement("achievement.christmaspillar", "christmaspillar", 0, 4, blockChristmasTreeSapling, null).setSpecial().initIndependentStat().registerStat();
		achievementDiamond = new Achievement("achievement.christmasdiamond", "christmasdiamond", 2, 4, itemRibbonDiamond, achievementChristmas).setSpecial().registerStat();
		achievementDisc = new Achievement("achievement.elysiandisc", "elysiandisc", 4, 4, itemDiscElysium, achievementChristmas).setSpecial().registerStat();

		if(winter)
		{
			achievementPage = new AchievementPage(name,
					achievementGettingStarted,
					achievementRecursion,
					achievementShowoff,
					achievementCompressingLiquids,
					achievementRecursion3,
					achievementChristmas,
					achievementDiamond,
					achievementDisc
					);
		} else
		{
			achievementPage = new AchievementPage(name,
					achievementGettingStarted,
					achievementRecursion,
					achievementShowoff,
					achievementCompressingLiquids,
					achievementRecursion3,
					achievementDisc
					);
		}
		
		AchievementPage.registerAchievementPage(achievementPage);
	}

	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityExtendPillar.class, "TileEntityExtendPillar");
		GameRegistry.registerTileEntity(TileEntityShowOffPillar.class, "TileEntityShowOffPillar");
		GameRegistry.registerTileEntity(TileEntityCraftingPillar.class, "TileEntityCraftingPillar");
		GameRegistry.registerTileEntity(TileEntityFurnacePillar.class, "TileEntityFurnacePillar");
		GameRegistry.registerTileEntity(TileEntityAnvilPillar.class, "TileEntityAnvilPillar");
		GameRegistry.registerTileEntity(TileEntityTankPillar.class, "TileEntityTankPillar");
//		GameRegistry.registerTileEntity(TileEntityEnchantmentPillar.class, "TileEntityEnchantmentPillar");
		GameRegistry.registerTileEntity(TileEntityBrewingPillar.class, "TileEntityBrewingPillar");
		GameRegistry.registerTileEntity(TileEntityDiskPlayerPillar.class, "TileEntityDiskPlayerPillar");
		GameRegistry.registerTileEntity(TileEntityFreezerPillar.class, "TileEntityFreezerPillar");
		GameRegistry.registerTileEntity(TileEntityPotPillar.class, "TileEntityPotPillar");
		GameRegistry.registerTileEntity(TileEntitySentryPillar.class, "TileEntitySentryPillar");
		GameRegistry.registerTileEntity(TileEntityTrashPillar.class, "TileEntityTrashPillar");
		GameRegistry.registerTileEntity(TileEntityPumpPillar.class, "TileEntityPumpPillar");

		GameRegistry.registerTileEntity(TileEntityChristmasPresent.class, "TileEntityChristmasPresent");
		GameRegistry.registerTileEntity(TileEntityLight.class, "TileEntityLight");
	}

	public void addCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(blockBasePillar), new Object[] { "SSS", " S ", "SSS", Character.valueOf('S'), Blocks.stone });
		GameRegistry.addRecipe(new ItemStack(blockFreezerPillar), new Object[] { "SSS", "GPG", "SSS", Character.valueOf('S'), Blocks.snow, Character.valueOf('P'), blockBasePillar, Character.valueOf('G'), Blocks.glass_pane});
		GameRegistry.addShapelessRecipe(new ItemStack(blockShowOffPillar), new ItemStack(Items.item_frame), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockCraftingPillar), new ItemStack(Blocks.crafting_table), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockFurnacePillar), new ItemStack(Blocks.furnace), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockBrewingPillar), new ItemStack(Items.brewing_stand), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockDiskPlayerPillar), new ItemStack(Blocks.jukebox), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockSentryPillar), new ItemStack(Blocks.dispenser), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockAnvilPillar), new ItemStack(Blocks.anvil), new ItemStack(blockBasePillar));
		GameRegistry.addRecipe(new ItemStack(blockPotPillar), new Object[] { "S", "F", "P", Character.valueOf('S'), Blocks.dirt, Character.valueOf('P'), blockBasePillar , Character.valueOf('F'), Items.flower_pot});
		GameRegistry.addRecipe(new ItemStack(blockChristmasLight, 3), new Object[] { "G", "L", Character.valueOf('G'), Items.gold_ingot, Character.valueOf('L'), Blocks.glowstone});
		GameRegistry.addRecipe(new ItemStack(blockTrashPillar, 1), new Object[] { "SSS", "SLS", "SSS", Character.valueOf('S'), Blocks.stone, Character.valueOf('L'), Items.ender_pearl});
		GameRegistry.addRecipe(new ItemStack(itemRing, 1), new Object[] { " S ", "S S", " S ", Character.valueOf('S'), Items.iron_ingot});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond), new ItemStack(itemRibbonDiamond));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(blockChristmasTreeSapling), "treeSapling", new ItemStack(blockChristmasLight)));
	}

	public void addToOreDictionary()
	{
		OreDictionary.registerOre("record", itemDiscElysium);
		OreDictionary.registerOre("treeSapling", blockChristmasTreeSapling);
		OreDictionary.registerOre("treeLeaves",  blockChristmasLeaves);
	}

	public void initAPI()
	{
		CraftingPillarAPI.addDiskTexture(itemDiscElysium, CraftingPillars.ID + ":textures/models/disk_elysium.png");
		
		CraftingPillarAPI.addDiskTexture(Items.record_13, CraftingPillars.ID + ":textures/models/disk_13.png");
		CraftingPillarAPI.addDiskTexture(Items.record_cat, CraftingPillars.ID + ":textures/models/disk_cat.png");
		CraftingPillarAPI.addDiskTexture(Items.record_blocks, CraftingPillars.ID + ":textures/models/disk_blocks.png");
		CraftingPillarAPI.addDiskTexture(Items.record_chirp, CraftingPillars.ID + ":textures/models/disk_chirp.png");
		CraftingPillarAPI.addDiskTexture(Items.record_far, CraftingPillars.ID + ":textures/models/disk_far.png");
		CraftingPillarAPI.addDiskTexture(Items.record_mall, CraftingPillars.ID + ":textures/models/disk_mall.png");
		CraftingPillarAPI.addDiskTexture(Items.record_mellohi, CraftingPillars.ID + ":textures/models/disk_mellohi.png");
		CraftingPillarAPI.addDiskTexture(Items.record_stal, CraftingPillars.ID + ":textures/models/disk_stal.png");
		CraftingPillarAPI.addDiskTexture(Items.record_strad, CraftingPillars.ID + ":textures/models/disk_strad.png");
		CraftingPillarAPI.addDiskTexture(Items.record_ward, CraftingPillars.ID + ":textures/models/disk_ward.png");
		CraftingPillarAPI.addDiskTexture(Items.record_11, CraftingPillars.ID + ":textures/models/disk_11.png");
		CraftingPillarAPI.addDiskTexture(Items.record_wait, CraftingPillars.ID + ":textures/models/disk_wait.png");

		SentryBehaviors.add(Items.arrow, new SentryBehaviorArrow());
		SentryBehaviors.add(Items.snowball, new SentryBehaviorSnowball());
		SentryBehaviors.add(Items.fire_charge, new SentryBehaviorFireball());
		SentryBehaviors.add(Items.potionitem, new SentryBehaviorPotion());
		SentryBehaviors.add(Items.egg, new SentryBehaviorEgg());

		Baubles.addModifier(Items.ender_pearl, new ModEnderPearl());
//		Baubles.addModifier(Item.getItemFromBlock(Blocks.glowstone), new ModGlowStone());
	}

	public void registerHandlers()
	{
		//NetworkRegistry.instance().registerGuiHandler(this, this.guiHandler);
		MinecraftForge.EVENT_BUS.register(new PillarEventHandler());
		FMLCommonHandler.instance().bus().register(new PillarEventHandler());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		try
		{
			configPath = evt.getModConfigurationDirectory();
			config = new Configuration(new File(configPath, "CraftingPillars.cfg"));
			config.load();

			winter = (isWinterTime() && config.get("default", "enableWinter", true).getBoolean()) || config.get("default", "forceWinter", false).getBoolean();
			easter = (isEasterTime() && config.get("default", "enableEaster", true).getBoolean()) || config.get("default", "forceEaster", false).getBoolean();
			halloween = (isHalloweenTime() && config.get("default", "enableHalloween", true).getBoolean()) || config.get("default", "forceHalloween", false).getBoolean();
			valentine = isValentineTime() || config.get("default", "forceValentine's day", false).getBoolean();
			
			
			//ChristmasPresentBlock.init();
//			ContainerAdventCalendar2013.init();


			config.save();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		FMLInterModComms.sendRuntimeMessage(ID, "VersionChecker", "addVersionCheck", "https://dl.dropboxusercontent.com/s/m7089q5s9f2dbk7/versionChecker.txt");

	}
	
	@EventHandler
	public void init(FMLInitializationEvent evt)
	{
		this.addItemsAndBlocks();

		this.registerHandlers();
		this.addAchievementsAndCreativeTab();
		this.addCrafting();
		
		this.registerTileEntities();

		proxy.initTileRenderer();
		proxy.initIconRegistry();


	}
	
	public static ItemStack[] present_loot;

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent evt)
	{
		present_loot = new ItemStack[]
		{
			new ItemStack(CraftingPillars.blockChristmasPresent, 1, 0),
			new ItemStack(CraftingPillars.blockChristmasPresent, 1, 1),
			new ItemStack(CraftingPillars.itemWinterFood2013, 2, 0),
			new ItemStack(CraftingPillars.itemWinterFood2013, 3, 1),
			new ItemStack(CraftingPillars.itemWinterFood2013, 4, 2),
			new ItemStack(CraftingPillars.itemWinterFood2013, 3, 3),
			new ItemStack(CraftingPillars.itemWinterFood2013, 5, 4),
			new ItemStack(CraftingPillars.itemWinterFood2013, 4, 5),
			new ItemStack(CraftingPillars.itemWinterFood2013, 2, 6),
			new ItemStack(CraftingPillars.itemWinterFood2013, 1, 7),
			new ItemStack(CraftingPillars.itemWinterFood2013, 4, 8),
//					new ItemStack(CraftingPillars.itemElysiumLoreBook, 1, 0),
			new ItemStack(CraftingPillars.blockCraftingPillar, 1, 0),
			new ItemStack(CraftingPillars.blockAnvilPillar, 1, 0),
			new ItemStack(CraftingPillars.blockFurnacePillar, 1, 0),
			new ItemStack(CraftingPillars.blockFreezerPillar, 1, 0),
			new ItemStack(CraftingPillars.blockShowOffPillar, 1, 0),
			new ItemStack(CraftingPillars.blockPotPillar, 1, 0),
			new ItemStack(CraftingPillars.blockBrewingPillar, 1, 0),
			new ItemStack(CraftingPillars.blockDiskPlayerPillar, 1, 0),
			new ItemStack(CraftingPillars.blockSentryPillar, 1, 0),
			new ItemStack(CraftingPillars.blockTankPillar, 1, 0),
			new ItemStack(CraftingPillars.blockTrashPillar, 1, 0),
			new ItemStack(CraftingPillars.blockBasePillar, 3, 0),
			new ItemStack(CraftingPillars.itemRibbonDiamond, 2, 0),
			new ItemStack(CraftingPillars.itemDiscElysium, 1, 0)
		};
		
		this.addToOreDictionary();

//		modThaumcraft = Loader.isModLoaded("Thaumcraft");
		modBaubles = Loader.isModLoaded("Baubles");

//		if(modThaumcraft)
//		{
//			FMLLog.info("Loading Thaumcraft 4 wand...");
//
//			itemWandThaumcraft = ItemApi.getItem("itemWandCasting", 0).getItem();
//			
//			if(itemWandThaumcraft == null)
//			{
//				modThaumcraft = false;
//				FMLLog.warning("Thaumcraft compatibility disabled...");
//			}
//		}
		
		this.initAPI();

		FreezerRecipes.addRecipe("water", new ItemStack(Blocks.ice));
		FreezerRecipes.addRecipe("lava", new ItemStack(Blocks.obsidian));
		
	}

	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	public static boolean isAfter(String date)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date c = new Date();
			Date v = format.parse(date);
			return c.after(v);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isWinterTime()
	{
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.DECEMBER);
		b.set(Calendar.DAY_OF_MONTH, 1);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
		e.set(Calendar.MONTH, Calendar.JANUARY);
		e.set(Calendar.DAY_OF_MONTH, 15);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}

	public static boolean isEasterTime()
	{
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.APRIL);
		b.set(Calendar.DAY_OF_MONTH, 19);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.MONTH, Calendar.APRIL);
		e.set(Calendar.DAY_OF_MONTH, 25);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}
	

	public static boolean isHalloweenTime()
	{
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.OCTOBER);
		b.set(Calendar.DAY_OF_MONTH, 24);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.MONTH, Calendar.NOVEMBER);
		e.set(Calendar.DAY_OF_MONTH, 7);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}
	

	private boolean isValentineTime()
	{
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) == Calendar.FEBRUARY && c.get(Calendar.DAY_OF_MONTH) == 14;
	}
}
