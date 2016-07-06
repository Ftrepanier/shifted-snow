package shiftedsnow.block;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shiftedsnow.api.IBetterSnowBlock;

public class BlockSnowOverBush extends BlockSnowAbstract implements IBetterSnowBlock {
  protected static final AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[] {
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 1D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 2D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 3D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 4D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 5D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 6D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 7D / 8D, 1.0D),
      new AxisAlignedBB(0.0D, -1D, 0.0D, 1.0D, -1D + 9D / 8D, 1.0D) };
      
  public BlockSnowOverBush() {
    super();
    
    this.setDefaultState(this.blockState.getBaseState().withProperty(HEIGHT_8, 1));
  }
  
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return SNOW_AABB[state.getValue(HEIGHT_8)];
  }
  
  @Nullable
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    int i = blockState.getValue(HEIGHT_8) - 1;
    float f = 0.125F;
    AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
    return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX,
        (i * f) - 1D, axisalignedbb.maxZ);
  }
}
