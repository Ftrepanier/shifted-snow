package shiftedsnow.block;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnowOverSlab extends BlockSnowAbstract {
  protected static final AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[] {
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 1D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 2D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 3D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 4D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 5D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 6D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 7D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, -0.5D + 8D / 8D, 1.0D) };
      
  public BlockSnowOverSlab() {
    super();
    
    this.setDefaultState(this.blockState.getBaseState().withProperty(BlockSnowAbstract.HEIGHT_8, 1));
  }
  
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return SNOW_AABB[((Integer) state.getValue(BlockSnowAbstract.HEIGHT_8)).intValue()];
  }
  
  @Nullable
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    int i = blockState.getValue(BlockSnowAbstract.HEIGHT_8) - 1;
    float f = 0.125F;
    AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
    return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX,
        (i * f) - 0.5, axisalignedbb.maxZ);
  }
}
