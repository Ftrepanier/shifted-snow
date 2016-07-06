package shiftedsnow.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import shiftedsnow.api.BetterSnowRegistry;
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.IBetterSnowBlock;

public class BlockSnowOverTable extends Block implements IBetterSnowBlock {
	public static final PropertyInteger HEIGHT_6 = PropertyInteger.create("height", 1, 6);
	protected static final AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D, 1.0D),
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D + 1D / 8D, 1.0D),
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D + 2D / 8D, 1.0D),
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D + 3D / 8D, 1.0D),
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D + 4D / 8D, 1.0D),
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D + 5D / 8D, 1.0D),
			new AxisAlignedBB(0.0D, -0.25D, 0.0D, 1.0D, -0.25D + 6D / 8D, 1.0D) };

	public BlockSnowOverTable() {
		super(Material.SNOW);

		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HEIGHT_6, 1));
		setHardness(0.1F).setLightOpacity(0);
		// this.setLightOpacity(0);
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(HEIGHT_6, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(HEIGHT_6);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HEIGHT_6);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SNOW_AABB[((Integer) state.getValue(HEIGHT_6)).intValue()];
	}

	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return ((Integer) worldIn.getBlockState(pos).getValue(HEIGHT_6)).intValue() < 8;
	}

	public boolean isFullyOpaque(IBlockState state) {
		return state.getValue(HEIGHT_6) == 8;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		int i = blockState.getValue(HEIGHT_6) - 1;
		float f = 0.125F;
		AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
		return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX,
				(i * f) - 0.25D, axisalignedbb.maxZ);
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		this.checkAndDropBlock(worldIn, pos, state);
	}

	private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canPlaceBlockAt(worldIn, pos)) {
			worldIn.setBlockToAir(pos);
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		if (side == EnumFacing.UP) {
			return true;
		} else {
			IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
			return iblockstate.getBlock() == this && iblockstate.getValue(HEIGHT_6) >= blockState.getValue(HEIGHT_6) ? true
					: super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return (state.getValue(HEIGHT_6));
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return (BetterSnowRegistry.getSnowingType(worldIn.getBlockState(pos.down()), worldIn, pos.down()) == EnumSnowType.MINUS_QUARTER);
	}

	@Override
	public int getMaxHeight() {
		return 6;
	}

	@Override
	public PropertyInteger getHeightProperty() {
		return HEIGHT_6;
	}

	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getValue(HEIGHT_6) == 1;
	}
}
