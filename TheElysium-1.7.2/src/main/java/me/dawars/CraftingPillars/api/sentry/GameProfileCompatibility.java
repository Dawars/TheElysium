package me.dawars.CraftingPillars.api.sentry;

import hu.hundevelopers.elysium.Elysium;

import java.lang.reflect.Constructor;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

public class GameProfileCompatibility {
//Unused
	public static Object get(String id, String name) {

		if (!Elysium.proxy.getMinecraftVersion().equals("1.7.2")) {
			try {
				Class<?> fake = Class.forName("com.mojang.authlib.GameProfile");
				Constructor<?> constructor = fake.getConstructor(UUID.class, String.class);
				return fake.cast(constructor.newInstance(null, name));
			} catch (Exception e) {
				System.out.println("Exception in CraftingPillars mod Sentry Pillar: " + e.getMessage());
				return null;
			}
		} else {
			return new GameProfile("sentry", "Sentry Pillar");
		}
	}
}
