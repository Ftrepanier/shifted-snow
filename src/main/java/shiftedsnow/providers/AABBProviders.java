package shiftedsnow.providers;

import shiftedsnow.Config;
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.ShiftedSnowApi;

public class AABBProviders {
  
  public static void addAABBProviders() {
    ShiftedSnowApi.addProvider((state, world, pos) -> {
      if (Config.addSlabSnow)
        if (state.getMaterial().isSolid() && state.getBoundingBox(world, pos).maxY == 0.5D) {
          return EnumSnowType.MINUS_HALF;
        }
      if (Config.addEnchTableSnow)
        if (state.getMaterial().isSolid() && state.getBoundingBox(world, pos).maxY == 0.75D) {
          return EnumSnowType.MINUS_QUARTER;
        } /*
           * if (state.getMaterial().isSolid() && state.getCollisionBoundingBox(world, pos) == Block.NULL_AABB) { return
           * EnumSnowType.MINUS_FULL; }
           */
      return null;
    });
  }
  
}
