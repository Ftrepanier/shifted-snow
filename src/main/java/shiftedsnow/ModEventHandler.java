package shiftedsnow;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shiftedsnow.api.BetterSnowRegistry;
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.IBetterSnowBlock;

public class ModEventHandler {
  @SubscribeEvent
  public void on(BreakEvent e) {
    Block blockAbove1 = e.getWorld().getBlockState(e.getPos().up(2)).getBlock();
    if (blockAbove1 instanceof IBetterSnowBlock) {
      e.getWorld().scheduleBlockUpdate(e.getPos().up(2), blockAbove1, 2, 0);
    }
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onClickAtSideOfSnowableBlock(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().offset(e.getFace()).up();
    BlockPos posUnder = e.getPos().offset(e.getFace());
    
    World world = e.getWorld();
    
    handleClick(pos, posUnder, world);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onClickOnSnowableBlock(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().up();
    BlockPos posUnder = e.getPos();
    
    World world = e.getWorld();
    handleClick(pos, posUnder, world);
  }
  
  @SubscribeEvent
  public void onClickUnderSnowableBlock(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().up(2);
    BlockPos posUnder = e.getPos().up();
    
    World world = e.getWorld();

    e.setCanceled(handleClick(pos, posUnder, world));
  }
  
  @SubscribeEvent
  public void onClickAtSnowBlock(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().up(2);
    BlockPos posUnder = e.getPos().up();
    
    World world = e.getWorld();
    
    e.setCanceled(handleClick(pos, posUnder, world));
  }
  
  private boolean handleClick(BlockPos pos, BlockPos posUnder, World world) {
    IBlockState stateUnder = world.getBlockState(posUnder);
    
    EnumSnowType snowingType = BetterSnowRegistry.getSnowingType(stateUnder, world, posUnder);
    
    if (snowingType == null)
      return false;
      
    IBlockState state = world.getBlockState(pos);
    
    EnumSnowType currentSnowingType = EnumSnowType.ofBlock(state);
    
    if (currentSnowingType == snowingType) {
      IBetterSnowBlock currentSnowBlock = (IBetterSnowBlock) state.getBlock();
      
      PropertyInteger heightProperty = currentSnowBlock.getHeightProperty();
      
      if (state.getValue(heightProperty) < currentSnowBlock.getMaxHeight()) {
        IBlockState newState = state.cycleProperty(heightProperty);
        
        world.setBlockState(pos, newState, 2);
        return true;
      }
    } else if (world.isAirBlock(pos)) {
      IBlockState snowBlock = snowingType.getSnowBlock();
      
      world.setBlockState(pos, snowBlock, 2);
      return true;
    }
    return false;
  }
}
