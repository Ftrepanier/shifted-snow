package shiftedsnow;

import net.minecraftforge.common.config.Configuration;

public class Config {
  public static boolean addStairsSnow  = true;
  public static boolean addWallSnow  = true;
  public static boolean addFenceSnow  = false;
  public static boolean addBushSnow  = false;
  public static boolean addSlabSnow  = false;
  public static boolean addEnchTableSnow  = false;
  
  public static boolean doClassPrepositions = true;
  public static boolean doAABBPrepositions = false;
  
  public static void load(Configuration config) {
    config.load();
    addStairsSnow = config.getBoolean("Stairs_Snow", "Blocks", addStairsSnow,
        "Allows place snow on stairs.");
    addWallSnow = config.getBoolean("Wall_Snow", "Blocks", addWallSnow,
        "Allows place snow on walls..");
    addFenceSnow = config.getBoolean("Fence_Snow", "Blocks", addFenceSnow,
        "Allows place snow on fences. Has problems with bounding boxes.");
    addBushSnow = config.getBoolean("Bush_Snow", "Blocks", addBushSnow,
        "Allows place snow over bushes, crops, saplings, iron bars and some other nonfull blocks. Has problems with bounding boxes.");
    addSlabSnow = config.getBoolean("Slab_Snow", "Blocks", addSlabSnow,
        "Allows place snow over slabs. Has problems with bounding boxes.");
    addEnchTableSnow = config.getBoolean("Ench_Table_Snow", "Blocks", addEnchTableSnow,
        "Allows place snow on enchanting table. Has problems with bounding boxes.");
    
    
    doClassPrepositions = config.getBoolean("Class_Prepositions", "Type", doClassPrepositions,
        "Adds snow by checking class inheritance. May support blocks from other mods that extends minecraft ones in proper way.");
    doAABBPrepositions = config.getBoolean("AABB_Prepositions", "Type", doAABBPrepositions,
        "Adds support by cheking AABB. May add support for some blocks from other mods by checking their bounding boxes. May be very buggy.");
    
    config.save();
  }
}
