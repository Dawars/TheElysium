package mods.elysium.handlers;

import me.dawars.CraftingPillars.CraftingPillars;
import mods.elysium.Elysium;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ElysiumSoundHandler {
	private static final String SOUND_RESOURCE_LOCATION = "elysium:";

	public static String[] soundFiles = {"FluteTrack.ogg"};

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {

        for (String soundFile : soundFiles) {
            try {
                event.manager.addStreaming(SOUND_RESOURCE_LOCATION + soundFile);
                System.out.println("Sound file loaded: " + soundFile);

            }
            catch (Exception e) {
                System.out.println("Failed loading sound file: " + soundFile);
            }
        }
    }
}