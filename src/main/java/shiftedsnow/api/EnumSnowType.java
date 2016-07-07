package shiftedsnow.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import shiftedsnow.ModBlocks;

public enum EnumSnowType {
  /**
   * Unused now
   */
  VANILLA,
  
  OVER_STAIRS, OVER_WALL,
  
  /**
   * Enchanting table
   */
  MINUS_QUARTER,
  
  /**
   * Slabs and similar
   */
  MINUS_HALF,
  
  /**
   * Saplings, grass, anvil and so on...
   */
  MINUS_FULL;
  
  public IBlockState getSnowBlock() {
    switch (this) {
    case VANILLA:
      return Blocks.SNOW_LAYER.getDefaultState();
    case MINUS_HALF:
      return ModBlocks.SNOW_OVER_SLAB.getDefaultState();
    case MINUS_QUARTER:
      return ModBlocks.SNOW_OVER_TABLE.getDefaultState();
    case MINUS_FULL:
      return ModBlocks.SNOW_OVER_BUSH.getDefaultState();
    case OVER_STAIRS:
      return ModBlocks.SNOW_OVER_STAIRS.getDefaultState();
    case OVER_WALL:
      return ModBlocks.SNOW_OVER_WALL.getDefaultState();
    default:
      return null;
    }
  }
}
