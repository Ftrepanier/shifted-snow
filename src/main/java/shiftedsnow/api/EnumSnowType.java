package shiftedsnow.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import shiftedsnow.ModBlocks;

public enum EnumSnowType {
  /**
   * Unused now
   */
  VANILLA,
  
  /**
   * WIP... World.getBlockState(...) seems to not return actual block state, but always with property "shape" set to
   * "straight"
   */
  OVER_STAIRS,
  
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
    default:
      return null;
    }
  }
  
  public static EnumSnowType ofBlock(IBlockState block) {
    if (block == null)
      return null;
      
    if (block.getBlock() == Blocks.SNOW_LAYER)
      return VANILLA;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_SLAB)
      return MINUS_HALF;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_TABLE)
      return MINUS_QUARTER;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_BUSH)
      return MINUS_FULL;
    
    if (block.getBlock() == ModBlocks.SNOW_OVER_STAIRS)
      return OVER_STAIRS;
      
    return null;
  }
}
