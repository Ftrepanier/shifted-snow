package shiftedsnow.providers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.BlockWall;
import net.minecraft.init.Blocks;
import shiftedsnow.api.ShiftedSnowApi;
import shiftedsnow.api.EnumSnowType;

public class DefaultProviders {
  public static void addSafeVanillaProviders() {
    
    ShiftedSnowApi.addSimpleMapping(Blocks.IRON_BARS, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.SAPLING, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.RED_FLOWER, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.RED_MUSHROOM, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.YELLOW_FLOWER, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.BROWN_MUSHROOM, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.GRASS, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.ANVIL, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.CHEST, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.TRAPPED_CHEST, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.ACACIA_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.ACACIA_FENCE_GATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.NETHER_BRICK_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.OAK_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.OAK_FENCE_GATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.BIRCH_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.BIRCH_FENCE_GATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.SPRUCE_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.DARK_OAK_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.DARK_OAK_FENCE_GATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.JUNGLE_FENCE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.JUNGLE_FENCE_GATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.COBBLESTONE_WALL, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.STONE_BUTTON, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.STONE_PRESSURE_PLATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.LEVER, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.WOODEN_PRESSURE_PLATE, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.WOODEN_BUTTON, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.CARROTS, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.POTATOES, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.BEETROOTS, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.NETHER_WART, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleMapping(Blocks.WHEAT, EnumSnowType.MINUS_FULL);
    
    ShiftedSnowApi.addSimpleMapping(Blocks.ENCHANTING_TABLE, EnumSnowType.MINUS_QUARTER);
  }
  
  public static void addClassProviders() {
    ShiftedSnowApi.addSimpleClassMapping(BlockBush.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockSign.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockAnvil.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockBush.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockFence.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockWall.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockFenceGate.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockPressurePlate.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockLever.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockRailBase.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockTripWire.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockTripWireHook.class, EnumSnowType.MINUS_FULL);
    
    ShiftedSnowApi.addProvider((state, world, pos) -> {
      if (state.getBlock() instanceof BlockSlab && !((BlockSlab) state.getBlock()).isDouble()) {
        return state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM ? EnumSnowType.MINUS_HALF : null;
      }
      return null;
    });
    
    ShiftedSnowApi.addProvider((state, world, pos) -> {
      if (state.getBlock() instanceof BlockStairs) {
        return state.getValue(BlockStairs.HALF) == BlockStairs.EnumHalf.BOTTOM ? EnumSnowType.OVER_STAIRS : null;
      }
      return null;
    });
  }
  
  public static void addAABBProviders() {
    ShiftedSnowApi.addProvider((state, world, pos) -> {
      if (state.getMaterial().isSolid() && state.getBoundingBox(world, pos).maxY == 0.5D) {
        return EnumSnowType.MINUS_HALF;
      }
      if (state.getMaterial().isSolid() && state.getBoundingBox(world, pos).maxY == 0.75D) {
        return EnumSnowType.MINUS_QUARTER;
      } /*
         * if (state.getMaterial().isSolid() && state.getCollisionBoundingBox(world, pos) == Block.NULL_AABB) { return
         * EnumSnowType.MINUS_FULL; }
         */
      return null;
    });
  }
}
