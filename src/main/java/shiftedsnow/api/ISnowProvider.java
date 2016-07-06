package shiftedsnow.api;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ISnowProvider {
	@Nullable EnumSnowType type(IBlockState state, World world, BlockPos pos);
}
