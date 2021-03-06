package shiftedsnow.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShiftedSnowApi {
  private static final List<ISnowProvider> SNOW_PROVIDERS = new ArrayList<>();
  
  public static EnumSnowType getSnowingType(IBlockState state, World world, BlockPos pos) {
    return SNOW_PROVIDERS.stream().map(predicate -> predicate.getType(state, world, pos)).filter(snow -> snow != null)
        .findFirst().orElse(null);
  }
  
  public static void addProvider(ISnowProvider provider) {
    SNOW_PROVIDERS.add(provider);
  }
  
  public static void addSimpleClassMapping(Class<? extends Block> block, EnumSnowType snow) {
    if (snow != EnumSnowType.MINUS_FULL && snow != EnumSnowType.OVER_WALL) {
      SNOW_PROVIDERS.add((state, world, pos) -> {
        return block.isInstance(state.getBlock()) ? snow : null;
      });
    } else {
      SNOW_PROVIDERS.add((state, world, pos) -> {
        return block.isInstance(state.getBlock()) && world.isSideSolid(pos.down(), EnumFacing.UP) ? snow : null;
      });
    }
  }
  
  public static void addSimpleMapping(Block block, EnumSnowType snow) {
    if (snow != EnumSnowType.MINUS_FULL && snow != EnumSnowType.OVER_WALL) {
      SNOW_PROVIDERS.add((state, world, pos) -> {
        return (block == state.getBlock()) ? snow : null;
      });
    } else {
      SNOW_PROVIDERS.add((state, world, pos) -> {
        return (block == state.getBlock() && world.isSideSolid(pos.down(), EnumFacing.UP)) ? snow : null;
      });
    }
  }
}
