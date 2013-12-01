package mods.elysium.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ElysianWaterFluid extends Fluid {

	public ElysianWaterFluid(String fluidName) {
		super(fluidName);
		FluidRegistry.registerFluid(this);

	}

}
