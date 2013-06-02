package mods.elysium.handlers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraftforge.client.event.sound.PlayBackgroundMusicEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.collect.Lists;

import cpw.mods.fml.client.FMLClientHandler;

public class ElysianSoundHandler {
	
	private static final String SOUND_RESOURCE_LOCATION = "mods/elysium/sound/";

	public static String[] soundFiles = { SOUND_RESOURCE_LOCATION + "FluteTrack.wav"};
	public static String[] musicFiles = { SOUND_RESOURCE_LOCATION + "Uranus Paradise.wav"};
	
	private final List<SoundPoolEntry> music = Lists.newArrayList();    

	private static Random rand = new Random();
	
	@ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {

		 // For each custom sound file we have defined in Sounds
        for (String soundFile : soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                event.manager.soundPoolSounds.addSound(soundFile, this.getClass().getResource("/" + soundFile));
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                System.out.println("Failed loading sound file: " + soundFile);
            }
        }
        
        //Load background music
        for (String musicFile : musicFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
               	music.add(new SoundPoolEntry(musicFile, this.getClass().getResource("/" + musicFile)));
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                System.out.println("Failed loading sound file: " + musicFile);
            }
        }
    
    }

	@ForgeSubscribe
    public void onBackgroundMusic(PlayBackgroundMusicEvent event) {
		
        if (FMLClientHandler.instance().getClient().theWorld.provider.dimensionId == Elysium.DimensionID)
            event.result = music.get(rand.nextInt(music.size()));
        }
}
