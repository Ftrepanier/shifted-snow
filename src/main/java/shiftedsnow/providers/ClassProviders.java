package shiftedsnow.providers;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockEnchantmentTable;
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
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.ShiftedSnowApi;

public class ClassProviders {

  public static void addBush() {
    ShiftedSnowApi.addSimpleClassMapping(BlockBush.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockSign.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockAnvil.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockBush.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockPressurePlate.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockLever.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockRailBase.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockTripWire.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockTripWireHook.class, EnumSnowType.MINUS_FULL);
  }

  public static void addFences() {
    ShiftedSnowApi.addSimpleClassMapping(BlockFence.class, EnumSnowType.MINUS_FULL);
    ShiftedSnowApi.addSimpleClassMapping(BlockFenceGate.class, EnumSnowType.MINUS_FULL);
  }

  public static void addStairs() {
    ShiftedSnowApi.addProvider((state, world, pos) -> {
      if (state.getBlock() instanceof BlockStairs) {
        return state.getValue(BlockStairs.HALF) == BlockStairs.EnumHalf.BOTTOM ? EnumSnowType.OVER_STAIRS : null;
      }
      return null;
    });
  }

  public static void addSlab() {
    ShiftedSnowApi.addProvider((state, world, pos) -> {
      if (state.getBlock() instanceof BlockSlab && !((BlockSlab) state.getBlock()).isDouble()) {
        return state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM ? EnumSnowType.MINUS_HALF : null;
      }
      return null;
    });
  }

  public static void addWall() {
    ShiftedSnowApi.addSimpleClassMapping(BlockWall.class, EnumSnowType.OVER_WALL);
  }

  public static void addEnchTable() {
    ShiftedSnowApi.addSimpleClassMapping(BlockEnchantmentTable.class, EnumSnowType.MINUS_QUARTER);
  }
}
