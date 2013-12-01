package mods.elysium.dimension;

import net.minecraft.world.WorldProvider;
import micdoodle8.mods.galacticraft.api.world.ICelestialBody;
import micdoodle8.mods.galacticraft.api.world.IGalaxy;
import micdoodle8.mods.galacticraft.api.world.IMapObject;
import micdoodle8.mods.galacticraft.core.GCCoreGalaxyBlockyWay;
import mods.elysium.Elysium;

public class ElysiumPlanet implements ICelestialBody {

	@Override
    public String getName() {
            return "The Elysium";//This string must be the same as the one in the PlanetWorldProvider class
    }

    @Override
    public boolean isReachable() {
            return true;//Whether or not you can get to this planet, or is it just there for something else, like moons, to exist off of.
    }

    @Override
    public IMapObject getMapObject() {
            return new ElysiumMapObject();//This renders the planet in the universe map.
    }

    @Override
    public boolean addToList() {
            return true;//This is whether or not the planet should be visible in the Planet Selection GUI when you leave the atmosphere
    }

    @Override
    public boolean autoRegister() {
            return true;
    }

    @Override
    public Class<? extends WorldProvider> getWorldProvider() {
            return ElysiumWorldProvider.class;
    }

    @Override
    public int getDimensionID() {
    	return Elysium.DimensionID;
	}
}
