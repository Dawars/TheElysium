package clickme.nocubes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.lwjgl.input.Keyboard;

import clickme.nocubes.gui.GuiCubeSettings;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = NoCubes.MOD_ID, version = NoCubes.VERSION)
public class NoCubes {

    public static final String MOD_ID = "noCubes";
    public static final String VERSION = "0.8";
    private boolean isOutdated = false;

    public static SoftBlockRenderer softBlockRenderer = new SoftBlockRenderer();

    private static List softBlockList = new ArrayList();
    private static List liquidBlockList = new ArrayList();
    private static List leavesBlockList = new ArrayList();

    public static KeyBinding keyOpenSettings;
    public static GuiScreen optionGui;

    private static Configuration cubesConfig;

    @EventHandler
    public void preInitialization(FMLPreInitializationEvent event) {
        if (event.getSide() != Side.CLIENT)
            return;

        keyOpenSettings = new KeyBinding("key.noCubes", Keyboard.KEY_O, "key.noCubes");
        optionGui = new GuiCubeSettings();

        ClientRegistry.registerKeyBinding(keyOpenSettings);

        cubesConfig = new Configuration(event.getSuggestedConfigurationFile());
        // Loading the configuration from it's file.
        cubesConfig.load();

        isNoCubesEnabled = this.cubesConfig.get(Configuration.CATEGORY_GENERAL, "EnableNoCubes", true).getBoolean(true);
        isAutoStepEnabled = this.cubesConfig.get(Configuration.CATEGORY_GENERAL, "EnableAutoStep", true).getBoolean(true);

        // Saving the configuration to it's file.
        cubesConfig.save();

        this.checkForPromotions();
        FMLCommonHandler.instance().bus().register(new ForgeEventHandler(this));
    }

    public static void saveCubeConfig() {
        cubesConfig.load();
        cubesConfig.get(Configuration.CATEGORY_GENERAL, "EnableNoCubes", true).set(isNoCubesEnabled);
        cubesConfig.get(Configuration.CATEGORY_GENERAL, "EnableAutoStep", true).set(isAutoStepEnabled);
        cubesConfig.save();
    }
    
    protected void openCubeSettingsGui() {
        Minecraft.getMinecraft().displayGuiScreen(new GuiCubeSettings());
    }

    protected void notificatePlayerInChat(EntityPlayer player) {
        if (this.isOutdated) {
            player.addChatMessage(new ChatComponentTranslation("animals.outdated"));
            String updateUrl = "http://goo.gl/z7zh90";
            ChatComponentText url = new ChatComponentText(updateUrl);
            url.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, updateUrl));
            url.getChatStyle().setUnderlined(Boolean.valueOf(true));
            player.addChatMessage(new ChatComponentTranslation("animals.download", url));
        }
    }

    private void checkForPromotions() {
        new Thread("No Cubes Version Check") {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://dl.dropboxusercontent.com/u/71419016/nc/promotions.json");
                    InputStream input = url.openStream();
                    String data = new String(ByteStreams.toByteArray(input));
                    input.close();

                    Map<String, Object> json = new Gson().fromJson(data, Map.class);
                    Map<String, String> promos = (Map<String, String>) json.get("promos");

                    String lat = promos.get(MinecraftForge.MC_VERSION + "-latest");
                    ArtifactVersion current = new DefaultArtifactVersion(NoCubes.VERSION);

                    if (lat != null) {
                        ArtifactVersion latest = new DefaultArtifactVersion(lat);
                        if (latest.compareTo(current) > 0)
                            isOutdated = true;
                    }
                } catch (IOException e) {
                } catch (JsonSyntaxException e) {
                }
            }
        }.start();
    }

    public static boolean isBlockSoft(Block block) {
        return softBlockList.contains(block);
    }

    public static boolean isBlockSoftForCollision(Block block) {
        return softBlockList.contains(block) && isAutoStepEnabled;
    }

    public static boolean isBlockLiquid(Block block) {
        return liquidBlockList.contains(block);
    }

    static {
        softBlockList.add(Blocks.grass);
        softBlockList.add(Blocks.dirt);
        softBlockList.add(Blocks.sand);
        softBlockList.add(Blocks.gravel);
        softBlockList.add(Blocks.clay);

        softBlockList.add(Blocks.farmland);
        softBlockList.add(Blocks.mycelium);

        softBlockList.add(Blocks.snow_layer);

        softBlockList.add(Blocks.stone);
        softBlockList.add(Blocks.coal_ore);
        softBlockList.add(Blocks.iron_ore);
        softBlockList.add(Blocks.gold_ore);
        softBlockList.add(Blocks.diamond_ore);
        softBlockList.add(Blocks.redstone_ore);
        softBlockList.add(Blocks.lit_redstone_ore);
        softBlockList.add(Blocks.emerald_ore);
        softBlockList.add(Blocks.bedrock);

        softBlockList.add(Blocks.netherrack);
        softBlockList.add(Blocks.soul_sand);
        softBlockList.add(Blocks.quartz_ore);

        softBlockList.add(Blocks.end_stone);

        liquidBlockList.add(Blocks.water);
        liquidBlockList.add(Blocks.flowing_water);
        liquidBlockList.add(Blocks.lava);
        liquidBlockList.add(Blocks.flowing_lava);

        leavesBlockList.add(Blocks.leaves);
        leavesBlockList.add(Blocks.leaves2);
    }

    public static void renderBlockSoft(Block block) {
        softBlockList.add(block);
    }

    public static void registerAsLiquid(Block block) {
        liquidBlockList.add(block);
    }

    public static void registerAsLeaves(Block block) {
        leavesBlockList.add(block);
    }

    public static boolean isNoCubesEnabled;
    public static boolean isAutoStepEnabled;

}
