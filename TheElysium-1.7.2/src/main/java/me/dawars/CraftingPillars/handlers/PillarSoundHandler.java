package me.dawars.CraftingPillars.handlers;

import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraftforge.client.event.sound.SoundLoadEvent;


public class PillarSoundHandler {
	private static final String SOUND_RESOURCE_LOCATION = CraftingPillars.id.toLowerCase() + ":";

	public static String[] soundFiles = {"UranusParadiseShort.ogg"};

	public void onSoundLoad(SoundLoadEvent event) {

		for (String soundFile : soundFiles) {
			try {
				//event.manager.soundPoolStreaming.addSound(SOUND_RESOURCE_LOCATION + soundFile);
				System.out.println("Sound file loaded: " + soundFile);

			}
			catch (Exception e) {
				System.out.println("Failed loading sound file: " + soundFile);
			}
		}
	}
}