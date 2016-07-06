package shiftedsnow.api;

import java.util.Random;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IShiftedSnowBlock {
	int getMaxHeight();
	PropertyInteger getHeightProperty();
  boolean isReplaceable(IBlockAccess worldIn, BlockPos pos);
  int quantityDropped(IBlockState state, int fortune, Random random);
  boolean isFullCube(IBlockState state);
  boolean isOpaqueCube(IBlockState state);
  
  EnumSnowType getSnowType(IBlockState block);
}
