package shiftedsnow.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shiftedsnow.api.ShiftedSnowApi;
import shiftedsnow.ModBlocks;
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.IShiftedSnowBlock;

public abstract class BlockSnowAbstract extends Block implements IShiftedSnowBlock {
  public static final PropertyInteger HEIGHT_8 = PropertyInteger.create("height", 1, 8);
  
  public BlockSnowAbstract() {
    super(Material.SNOW, MapColor.SNOW);
    
    setHardness(0.1F);
    setLightOpacity(0);
    setTickRandomly(true);
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
  
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos).getValue(HEIGHT_8) < 8;
  }
  
  public boolean isFullyOpaque(IBlockState state) {
    return state.getValue(HEIGHT_8) == 8;
  }
  
  @Override
  public final boolean isOpaqueCube(IBlockState state) {
    return false;
  }
  
  @Override
  public final boolean isFullCube(IBlockState state) {
    return false;
  }
  
  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    this.checkAndDropBlock(worldIn, pos, state);
  }
  
  @Override
  public final void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    this.checkAndDropBlock(worldIn, pos, state);
  }
  
  protected boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
    if (!this.canPlaceBlockAt(worldIn, pos)) {
      worldIn.setBlockToAir(pos);
      return false;
    } else {
      return true;
    }
  }
  
  @SuppressWarnings("deprecation")
  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    if (side == EnumFacing.UP) {
      return true;
    } else {
      IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
      return iblockstate.getBlock() == this && iblockstate.getValue(HEIGHT_8) >= blockState.getValue(HEIGHT_8) ? true
          : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
  }
  
  @Override
  public int quantityDropped(IBlockState state, int fortune, Random random) {
    return (state.getValue(HEIGHT_8));
  }
  
  @Override
  public int getMaxHeight() {
    return 8;
  }
  
  @Override
  public PropertyInteger getHeightProperty() {
    return HEIGHT_8;
  }
  
  @Override
  public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos).getValue(HEIGHT_8) == 1;
  }
  
  public EnumSnowType getSnowType(IBlockState block) {
    if (block == null)
      return null;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_SLAB)
      return EnumSnowType.MINUS_HALF;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_TABLE)
      return EnumSnowType.MINUS_QUARTER;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_BUSH)
      return EnumSnowType.MINUS_FULL;
      
    if (block.getBlock() == ModBlocks.SNOW_OVER_STAIRS)
      return EnumSnowType.OVER_STAIRS;
      
    return null;
  }
  
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return (ShiftedSnowApi.getSnowingType(worldIn.getBlockState(pos.down()), worldIn,
        pos.down()) == getSnowType(worldIn.getBlockState(pos)));
  }
}
