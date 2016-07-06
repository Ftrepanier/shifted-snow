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
import shiftedsnow.api.ShiftedSnowApi;
import shiftedsnow.api.EnumSnowType;
import shiftedsnow.api.IShiftedSnowBlock;

public class ModEventHandler {
  @SubscribeEvent
  public void on(BreakEvent e) {
    Block blockAbove1 = e.getWorld().getBlockState(e.getPos().up(2)).getBlock();
    if (blockAbove1 instanceof IShiftedSnowBlock) {
      // There is Shifted-snow block over it, but it can't update as formally it 2 far away.
      // So we shedult it update, as if it were just at top of it.
      // As result, it prevents snow from dangling in air/on non-solid surface under it.
      e.getWorld().scheduleBlockUpdate(e.getPos().up(2), blockAbove1, 2, 0);
    }
  }
  
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void handleClickOnSnowable(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().up();
    BlockPos posUnder = e.getPos();
    
    boolean result = handleClick(pos, posUnder, e.getWorld());
    if (result)
      e.setCanceled(result);
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void handleClickAtSnowableSide(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().offset(e.getFace()).up();
    BlockPos posUnder = e.getPos().offset(e.getFace());
    
    boolean result = handleClick(pos, posUnder, e.getWorld());
    if (result)
      e.setCanceled(result);
  }
  
  @SubscribeEvent
  public void handleClickUnderSnowable(RightClickBlock e) {
    if (e.getItemStack() == null || e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER))
      return;
      
    BlockPos pos = e.getPos().up(2);
    BlockPos posUnder = e.getPos().up();
    
    boolean result = handleClick(pos, posUnder, e.getWorld());
    if (result)
      e.setCanceled(result);
  }
  /*
   * @SubscribeEvent public void handleClickOnSnow(RightClickBlock e) { if (e.getItemStack() == null ||
   * e.getItemStack().getItem() != Item.getItemFromBlock(Blocks.SNOW_LAYER)) return;
   * 
   * BlockPos pos = e.getPos(); BlockPos posUnder = e.getPos().down();
   * 
   * e.setCanceled(handleClick(pos, posUnder, e.getWorld())); }
   */
  
  private boolean handleClick(BlockPos pos, BlockPos posUnder, World world) {
    IBlockState stateUnder = world.getBlockState(posUnder);
    
    EnumSnowType snowingType = ShiftedSnowApi.getSnowingType(stateUnder, world, posUnder);
    
    if (snowingType == null)
      return false;
      
    IBlockState state = world.getBlockState(pos);
    
    EnumSnowType currentSnowingType = EnumSnowType.ofBlock(state);
    
    if (currentSnowingType == snowingType) {
      IShiftedSnowBlock currentSnowBlock = (IShiftedSnowBlock) state.getBlock();
      
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
