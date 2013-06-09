package mods.elysium.handlers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraftforge.client.event.sound.PlayBackgroundMusicEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.collect.Lists;

import cpw.mods.fml.client.FMLClientHandler;

public class ElysianSoundHandler {

	private static final String SOUND_RESOURCE_LOCATION = "mods/elysium/sound/";


//	private final List<SoundPoolEntry> music = Lists.newArrayList();

	private static Random rand = new Random();

	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		SoundManager manager = event.manager;
		String[] soundFiles = { "sound/flute/track.ogg" };

		for (int i = 0; i < soundFiles.length; i++) {

			// remove 'sound/' portion for the name
			String soundName = soundFiles[i].substring(6);
			try {
				manager.soundPoolSounds.addSound(soundName, new URL("file:" + soundFiles[i]));
				System.out.println("Loaded sound: " + soundName);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("Failed to load sound + " + soundName);

			}
		}
		/*
		 * 
		 * //Load background music for (String musicFile : musicFiles) { // Try
		 * to add the custom sound file to the pool of sounds try {
		 * music.add(new SoundPoolEntry(musicFile,
		 * this.getClass().getResource("/" + musicFile))); } // If we cannot add
		 * the custom sound file to the pool, log the exception catch (Exception
		 * e) { System.out.println("Failed loading sound file: " + musicFile); }
		 * 
		 * }
		 */
	}

//	@ForgeSubscribe
//	public void onBackgroundMusic(PlayBackgroundMusicEvent event) {
//
//		if (FMLClientHandler.instance().getClient().theWorld.provider.dimensionId == Elysium.DimensionID)
//			event.result = music.get(rand.nextInt(music.size()));
//	}
}