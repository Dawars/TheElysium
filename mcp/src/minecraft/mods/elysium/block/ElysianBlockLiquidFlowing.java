package mods.elysium.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;

public class ElysianBlockLiquidFlowing extends BlockFluidClassic
{

    public ElysianBlockLiquidFlowing(int par1, Material par2Material)
    {
        super(par1, FluidRegistry.WATER, par2Material);
    }

}
