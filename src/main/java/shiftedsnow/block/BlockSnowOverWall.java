package shiftedsnow.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.ShiftedSnowApi;

public class BlockSnowOverWall extends BlockSnowAbstract {
  protected static final AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[] {
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 0, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D),
      new AxisAlignedBB(1 / 4D, 0, 1 / 4D, 3 / 4D, 1D / 8D, 3 / 4D) };
      
  public BlockSnowOverWall() {
    super();
    
    setTickRandomly(true);
    setDefaultState(
        getBlockState().getBaseState().withProperty(HEIGHT_8, 1));
    setHardness(0.1F);
    setLightOpacity(0);
  }
  
  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(HEIGHT_8, meta);
  }
  
  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(HEIGHT_8);
  }
  
  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, HEIGHT_8);
  }
  
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return SNOW_AABB[state.getValue(HEIGHT_8)];
  }
  
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos).getValue(HEIGHT_8) < 8;
  }
  
  public boolean isFullyOpaque(IBlockState state) {
    return false;
  }
  
  @Nullable
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    int i = blockState.getValue(HEIGHT_8) - 1;
    float f = 0.125F;
    AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
    return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, (i * f),
        axisalignedbb.maxZ);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    if (side == EnumFacing.UP) {
      return true;
    } else {
      IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
      return iblockstate.getBlock() == this && (iblockstate.getValue(HEIGHT_8)) >= blockState.getValue(HEIGHT_8) ? true
          : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
  }
  
  @Override
  public int quantityDropped(IBlockState state, int fortune, Random random) {
    return state.getValue(HEIGHT_8);
  }
  
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return (ShiftedSnowApi.getSnowingType(worldIn.getBlockState(pos.down()), worldIn,
        pos.down()) == EnumSnowType.OVER_WALL);
  }
  
  @Override
  public int getMaxHeight() {
    return 8;
  }
  
  @Override
  public PropertyInteger getHeightProperty() {
    return HEIGHT_8;
  }
  
  public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
    return true;
  }

  @Override
  public EnumSnowType getSnowType(IBlockState block) {
    return EnumSnowType.OVER_WALL;
  }  
}
