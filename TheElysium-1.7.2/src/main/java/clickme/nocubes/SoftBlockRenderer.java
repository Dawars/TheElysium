package clickme.nocubes;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import clickme.nocubes.test.SmoothBlockRenderer2;

public class SoftBlockRenderer {

    public boolean renderSoftBlock(Block block, int x, int y, int z, RenderBlocks renderer, IBlockAccess world) {
        // Used for the drawing of the block's sides.
        Tessellator tessellator = Tessellator.instance;

        // The block's metadata.
        int meta = world.getBlockMetadata(x, y, z);

        // The basic block color.
        int color = block.colorMultiplier(world, x, y, z);
        float colorRed = (float) (color >> 16 & 255) / 255.0F;
        float colorGreen = (float) (color >> 8 & 255) / 255.0F;
        float colorBlue = (float) (color & 255) / 255.0F;

        // The shadow values.
        float shadowBottom = 0.6F;
        float shadowTop = 1.0F;
        float shadowLeft = 0.9F;
        float shadowRight = 0.8F;

        // The block's icon
        IIcon icon;
        if (!renderer.hasOverrideBlockTexture())
            icon = renderer.getBlockIconFromSideAndMetadata(block, 1, meta);
        else
            // Used for the crack texture
            icon = renderer.overrideBlockTexture;

        // The icon's UVs
        double minU = (double) icon.getMinU();
        double minV = (double) icon.getMinV();
        double maxU = (double) icon.getMaxU();
        double maxV = (double) icon.getMaxV();

        // The 8 points that make the block.
        Vec3[] points = new Vec3[8];
        points[0] = world.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
        points[1] = world.getWorldVec3Pool().getVecFromPool(1.0D, 0.0D, 0.0D);
        points[2] = world.getWorldVec3Pool().getVecFromPool(1.0D, 0.0D, 1.0D);
        points[3] = world.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 1.0D);
        points[4] = world.getWorldVec3Pool().getVecFromPool(0.0D, 1.0D, 0.0D);
        points[5] = world.getWorldVec3Pool().getVecFromPool(1.0D, 1.0D, 0.0D);
        points[6] = world.getWorldVec3Pool().getVecFromPool(1.0D, 1.0D, 1.0D);
        points[7] = world.getWorldVec3Pool().getVecFromPool(0.0D, 1.0D, 1.0D);

        // Loop through all the points:
        // Here everything will be 'smoothed'.
        for (int point = 0; point < 8; point++) {
            // Give the point the block's coordinates.
            points[point].xCoord += (double) x;
            points[point].yCoord += (double) y;
            points[point].zCoord += (double) z;

            // Check if the point is NOT intersecting with a manufactured block.
            if (!doesPointIntersectWithManufactured(world, points[point])) {
                // Check if the block's bottom side intersects with air.
                if (point < 4 && doesPointBottomIntersectWithAir(world, points[point]))
                    points[point].yCoord = (double) y + 1.0D;
                // Check if the block's top side intersects with air.
                else if (point >= 4 && doesPointTopIntersectWithAir(world, points[point]))
                    points[point].yCoord = (double) y;

                // Give the point some random offset.
                points[point] = givePointRoughness(points[point]);
            }
        }

        // Loop through all the sides of the block:
        for (int side = 0; side < 6; side++) {
            // The coordinates the side is facing to.
            int facingX = x;
            int facingY = y;
            int facingZ = z;
            if (side == 0)
                facingY--;
            else if (side == 1)
                facingY++;
            else if (side == 2)
                facingZ--;
            else if (side == 3)
                facingX++;
            else if (side == 4)
                facingZ++;
            else if (side == 5)
                facingX--;

            // Check if the side should be rendered:
            // This prevents a lot of lag!
            if (renderer.renderAllFaces || block.shouldSideBeRendered(world, facingX, facingY, facingZ, side)) {
                // When you lower this value the block will become darker.
                float colorFactor = 1.0F;

                // This are the vertices used for the side.
                Vec3 vertex0 = null;
                Vec3 vertex1 = null;
                Vec3 vertex2 = null;
                Vec3 vertex3 = null;
                if (side == 0) {
                    // Side 0 is the bottom side.
                    colorFactor = shadowBottom;
                    vertex0 = points[0];
                    vertex1 = points[1];
                    vertex2 = points[2];
                    vertex3 = points[3];

                } else if (side == 1) {
                    // Side 1 is the top side.
                    colorFactor = shadowTop;
                    vertex0 = points[7];
                    vertex1 = points[6];
                    vertex2 = points[5];
                    vertex3 = points[4];

                } else if (side == 2) {
                    colorFactor = shadowLeft;
                    vertex0 = points[1];
                    vertex1 = points[0];
                    vertex2 = points[4];
                    vertex3 = points[5];

                } else if (side == 3) {
                    colorFactor = shadowRight;
                    vertex0 = points[2];
                    vertex1 = points[1];
                    vertex2 = points[5];
                    vertex3 = points[6];
                } else if (side == 4) {
                    colorFactor = shadowLeft;
                    vertex0 = points[3];
                    vertex1 = points[2];
                    vertex2 = points[6];
                    vertex3 = points[7];
                } else if (side == 5) {
                    colorFactor = shadowRight;
                    vertex0 = points[0];
                    vertex1 = points[3];
                    vertex2 = points[7];
                    vertex3 = points[4];
                }

                // Here is the brightness of the block being set.
                tessellator.setBrightness(block.getMixedBrightnessForBlock(world, facingX, facingY, facingZ));
                // Here is the color of the block being set.
                tessellator.setColorOpaque_F(shadowTop * colorFactor * colorRed, shadowTop * colorFactor * colorGreen,
                        shadowTop * colorFactor * colorBlue);

                // And finally the side is going to be rendered!
                tessellator.addVertexWithUV(vertex0.xCoord, vertex0.yCoord, vertex0.zCoord, minU, maxV);
                tessellator.addVertexWithUV(vertex1.xCoord, vertex1.yCoord, vertex1.zCoord, maxU, maxV);
                tessellator.addVertexWithUV(vertex2.xCoord, vertex2.yCoord, vertex2.zCoord, maxU, minV);
                tessellator.addVertexWithUV(vertex3.xCoord, vertex3.yCoord, vertex3.zCoord, minU, minV);
            }
        }

        return true;
    }

    /**
     * Gives the point some random offset.
     * 
     * @param point
     * @return
     */
    private Vec3 givePointRoughness(Vec3 point) {
        long i = (long) (point.xCoord * 3129871) ^ (long) point.yCoord * 116129781L ^ (long) point.zCoord;
        i = i * i * 42317861L + i * 11L;
        point.xCoord += (((float) (i >> 16 & 15L) / 15.0F) - 0.5F) * 0.5F;
        point.yCoord += (((float) (i >> 20 & 15L) / 15.0F) - 0.5F) * 0.5F;
        point.zCoord += (((float) (i >> 24 & 15L) / 15.0F) - 0.5F) * 0.5F;
        return point;
    }

    /**
     * Check if the block's material is air, plants, or vine.
     * 
     * @param block
     * @return
     */
    public static boolean isBlockAirOrPlant(Block block) {
        Material material = block.getMaterial();
        return material == Material.air || material == Material.plants || material == Material.vine
                || NoCubes.isBlockLiquid(block);
    }

    /**
     * Check if a top point of a block intersect with air.
     * 
     * @param world
     * @param point
     * @return
     */
    public static boolean doesPointTopIntersectWithAir(IBlockAccess world, Vec3 point) {
        boolean intersects = false;
        for (int i = 0; i < 4; ++i) {
            int x1 = (int) (point.xCoord - (i & 1));
            int z1 = (int) (point.zCoord - (i >> 1 & 1));
            if (!isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord, z1)))
                return false;
            if (isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord - 1, z1)))
                intersects = true;
        }
        return intersects;
    }

    /**
     * Check if a bottom point of a block intersects with air.
     * 
     * @param world
     * @param point
     * @return
     */
    public static boolean doesPointBottomIntersectWithAir(IBlockAccess world, Vec3 point) {
        boolean intersects = false;
        boolean notOnly = false;
        for (int i = 0; i < 4; ++i) {
            int x1 = (int) (point.xCoord - (i & 1));
            int z1 = (int) (point.zCoord - (i >> 1 & 1));
            if (!isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord - 1, z1)))
                return false;
            if (!isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord + 1, z1)))
                notOnly = true;
            if (isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord, z1)))
                intersects = true;
        }
        return intersects && notOnly;
    }

    /**
     * Check if the point intersects with a manufactured block.
     * 
     * @param world
     * @param point
     * @return
     */
    public static boolean doesPointIntersectWithManufactured(IBlockAccess world, Vec3 point) {
        for (int i = 0; i < 4; ++i) {
            int x1 = (int) (point.xCoord - (i & 1));
            int z1 = (int) (point.zCoord - (i >> 1 & 1));
            Block block = world.getBlock(x1, (int) point.yCoord, z1);
            if (!isBlockAirOrPlant(block) && !NoCubes.isBlockSoft(block))
                return true;
            Block block1 = world.getBlock(x1, (int) point.yCoord - 1, z1);
            if (!isBlockAirOrPlant(block1) && !NoCubes.isBlockSoft(block1))
                return true;
        }
        return false;
    }

    public boolean renderLiquidBlock(Block block, int x, int y, int z, RenderBlocks renderer, IBlockAccess world) {
        boolean rendered = renderer.renderBlockLiquid(block, x, y, z);

        if (NoCubes.isBlockLiquid(world.getBlock(x, y + 1, z)))
            return rendered;

        int brightness = block.getMixedBrightnessForBlock(world, x, y, z);

        if (NoCubes.isBlockSoft(world.getBlock(x + 1, y, z)))
            this.renderGhostLiquid(block, x + 1, y, z, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x, y, z + 1)) && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z + 1)))
            this.renderGhostLiquid(block, x, y, z + 1, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x - 1, y, z)) && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z - 1)))
            this.renderGhostLiquid(block, x - 1, y, z, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x, y, z - 1)) && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z - 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x, y, z - 2))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 1, y, z - 1)))
            this.renderGhostLiquid(block, x, y, z - 1, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x + 1, y, z + 1)) && !NoCubes.isBlockLiquid(world.getBlock(x, y, z + 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 1, y, z))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 2, y, z + 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 1, y, z + 2)))
            this.renderGhostLiquid(block, x + 1, y, z + 1, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x + 1, y, z - 1)) && !NoCubes.isBlockLiquid(world.getBlock(x, y, z - 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 1, y, z - 2))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 2, y, z - 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x + 1, y, z))
                && !NoCubes.isBlockLiquid(world.getBlock(x, y, z - 2)))
            this.renderGhostLiquid(block, x + 1, y, z - 1, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x - 1, y, z - 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z - 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z - 2))
                && !NoCubes.isBlockLiquid(world.getBlock(x, y, z - 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z - 2))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z)))
            this.renderGhostLiquid(block, x - 1, y, z - 1, brightness, renderer, world);

        if (NoCubes.isBlockSoft(world.getBlock(x - 1, y, z + 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z + 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z))
                && !NoCubes.isBlockLiquid(world.getBlock(x, y, z + 1))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z + 2))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z))
                && !NoCubes.isBlockLiquid(world.getBlock(x - 2, y, z + 2))
                && !NoCubes.isBlockLiquid(world.getBlock(x, y, z + 2)))
            this.renderGhostLiquid(block, x - 1, y, z + 1, brightness, renderer, world);

        return rendered;
    }

    public boolean doesPointIntersectWithLiquid(int x, int y, int z, IBlockAccess world) {
        if (NoCubes.isBlockLiquid(world.getBlock(x, y, z)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x, y, z - 1)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x - 1, y, z - 1)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x, y + 1, z)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x - 1, y + 1, z)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x, y + 1, z - 1)))
            return true;
        if (NoCubes.isBlockLiquid(world.getBlock(x - 1, y + 1, z - 1)))
            return true;
        return false;
    }

    public boolean renderGhostLiquid(Block block, int x, int y, int z, int brightness, RenderBlocks renderer,
            IBlockAccess world) {
        Tessellator tessellator = Tessellator.instance;
        Material material = block.getMaterial();

        double height0 = 0.7D;
        double height1 = 0.7D;
        double height2 = 0.7D;
        double height3 = 0.7D;

        if (this.doesPointIntersectWithLiquid(x, y, z, world))
            height0 = (double) renderer.getLiquidHeight(x, y, z, material);
        if (this.doesPointIntersectWithLiquid(x, y, z + 1, world))
            height1 = (double) renderer.getLiquidHeight(x, y, z + 1, material);
        if (this.doesPointIntersectWithLiquid(x + 1, y, z + 1, world))
            height2 = (double) renderer.getLiquidHeight(x + 1, y, z + 1, material);
        if (this.doesPointIntersectWithLiquid(x + 1, y, z, world))
            height3 = (double) renderer.getLiquidHeight(x + 1, y, z, material);

        height0 -= 0.0010000000474974513D;
        height1 -= 0.0010000000474974513D;
        height2 -= 0.0010000000474974513D;
        height3 -= 0.0010000000474974513D;

        IIcon icon = renderer.getBlockIconFromSide(block, 1);
        double minU = (double) icon.getInterpolatedU(0.0D);
        double minV = (double) icon.getInterpolatedV(0.0D);
        double maxU = (double) icon.getInterpolatedU(16.0D);
        double maxV = (double) icon.getInterpolatedV(16.0D);

        tessellator.setBrightness(brightness);
        tessellator.setColorOpaque_I(block.colorMultiplier(world, x, y, z));

        tessellator.addVertexWithUV((double) (x + 0), (double) y + height0, (double) (z + 0), minU, minV);
        tessellator.addVertexWithUV((double) (x + 0), (double) y + height1, (double) (z + 1), minU, maxV);
        tessellator.addVertexWithUV((double) (x + 1), (double) y + height2, (double) (z + 1), maxU, maxV);
        tessellator.addVertexWithUV((double) (x + 1), (double) y + height3, (double) (z + 0), maxU, minV);

        return true;
    }

    public static boolean shouldHookRenderer(Block block) {
        return NoCubes.isNoCubesEnabled && (NoCubes.isBlockSoft(block) || NoCubes.isBlockLiquid(block));
    }

    public boolean directRenderHook(Block block, int x, int y, int z, RenderBlocks renderer) {
        block.setBlockBoundsBasedOnState(renderer.blockAccess, x, y, z);
        renderer.setRenderBoundsFromBlock(block);

        IBlockAccess world = renderer.blockAccess;

        if (NoCubes.isBlockLiquid(block))
            return this.renderLiquidBlock(block, x, y, z, renderer, world);

        return this.renderSoftBlock(block, x, y, z, renderer, world);
    }

    public static void inject(Block block, World world, int x, int y, int z, AxisAlignedBB aabb, List list,
            Entity entity) {
        float f = SmoothBlockRenderer2.getSmoothBlockHeightForCollision(world, block, x, y, z);
        float f1 = SmoothBlockRenderer2.getSmoothBlockHeightForCollision(world, block, x, y, z + 1);
        float f2 = SmoothBlockRenderer2.getSmoothBlockHeightForCollision(world, block, x + 1, y, z + 1);
        float f3 = SmoothBlockRenderer2.getSmoothBlockHeightForCollision(world, block, x + 1, y, z);
        addBBoundsToList(x, y, z, 0.0F, 0.0F, 0.0F, 0.5F, f, 0.5F, aabb, list);
        addBBoundsToList(x, y, z, 0.0F, 0.0F, 0.5F, 0.5F, f1, 1.0F, aabb, list);
        addBBoundsToList(x, y, z, 0.5F, 0.0F, 0.5F, 1.0F, f2, 1.0F, aabb, list);
        addBBoundsToList(x, y, z, 0.5F, 0.0F, 0.0F, 1.0F, f3, 0.5F, aabb, list);
    }

    public static void addBBoundsToList(int x, int y, int z, float minX, float minY, float minZ, float maxX,
            float maxY, float maxZ, AxisAlignedBB aabb, List list) {
        AxisAlignedBB aabb1 = AxisAlignedBB.getAABBPool().getAABB((double) x + minX, (double) y + minY,
                (double) z + minZ, (double) x + maxX, (double) y + maxY, (double) z + maxZ);
        if (aabb1 != null && aabb.intersectsWith(aabb1))
            list.add(aabb1);
    }

}
