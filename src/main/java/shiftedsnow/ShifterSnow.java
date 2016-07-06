package shiftedsnow;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shiftedsnow.providers.DefaultProviders;

@Mod(modid = ShifterSnow.MODID, version = ShifterSnow.VERSION)
public class ShifterSnow {
  public static final String MODID = "shiftedsnow";
  public static final String VERSION = "${version}";
  
  @EventHandler
  public void init(FMLPreInitializationEvent event) {
    Config.load(new Configuration(event.getSuggestedConfigurationFile()));
    
    MinecraftForge.EVENT_BUS.register(new ModEventHandler());
    
    GameRegistry.register(ModBlocks.SNOW_OVER_SLAB, new ResourceLocation("shiftedsnow:snow_over_slab"));
    GameRegistry.register(ModBlocks.SNOW_OVER_TABLE, new ResourceLocation("shiftedsnow:snow_over_table"));
    GameRegistry.register(ModBlocks.SNOW_OVER_BUSH, new ResourceLocation("shiftedsnow:snow_over_bush"));
    GameRegistry.register(ModBlocks.SNOW_OVER_STAIRS, new ResourceLocation("shiftedsnow:snow_over_stairs"));
    
    
    if(Config.doClassPrepositions) {
      DefaultProviders.addClassProviders();
    }
    if(Config.doAABBPrepositions) {
      DefaultProviders.addAABBProviders();
    }
    if(!Config.doAABBPrepositions && !Config.doClassPrepositions) {
      DefaultProviders.addSafeVanillaProviders();
    }
  }
}
