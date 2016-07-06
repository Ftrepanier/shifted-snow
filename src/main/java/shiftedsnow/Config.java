package shiftedsnow;

import net.minecraftforge.common.config.Configuration;

public class Config {
  public static boolean doClassPrepositions = true;
  public static boolean doAABBPrepositions = true;
  
  public static void load(Configuration config) {
    config.load();
    
    doClassPrepositions = config.getBoolean("Class_Prepositions", "Placability", doClassPrepositions,
        "May support blocks from other mods that extends minecraft ones in proper way. May be buggy.");
    doAABBPrepositions = config.getBoolean("AABB_Prepositions", "Placability", doAABBPrepositions,
        "May support blocks from other mods by checking their bounding boxes. May be buggy.");
    
    config.save();
  }
}
