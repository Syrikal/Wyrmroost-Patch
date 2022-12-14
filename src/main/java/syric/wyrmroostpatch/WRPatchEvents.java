package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragon.AlpineEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.LogicalSide;

public class WRPatchEvents {


    public static void interactEntity(PlayerInteractEvent.EntityInteract event) {
            Entity entity = event.getTarget();
            ItemStack itemStack = event.getItemStack();
            World world = event.getWorld();
            if (itemStack.getItem() instanceof DragonFeedItem && event.getSide() == LogicalSide.SERVER) {
                DragonFeedItem item = (DragonFeedItem) itemStack.getItem();
                if (entity.getType() == item.getDragonType()) {
                    Util.chatPrint("Successfully clicked a dragon with the right item", event.getWorld());
                    AbstractDragonEntity ent = (AbstractDragonEntity) entity;
                    Util.chatPrint("Breedcount: " + ent.breedCount, event.getWorld());
                    if (ent.breedCount < Util.getBreedCap(ent.getType()) && ent.getOwner() == event.getPlayer()) {
//                        if (item.checkTarget(ent)) {
                            Util.chatPrint("Successful breeding", event.getWorld());
                            ItemStack newStack = item.getNextItem();
                            event.getPlayer().addItem(newStack);

                            //Survival requirement temporarily disabled
//                        if (!event.getPlayer().isCreative()) {
//                            itemStack.shrink(1);
//                        }
                            itemStack.shrink(1);


                            ent.breedCount++;
//                        } else {
//                            Util.chatPrint("Invalid breeding target", world);
//                        }
                    } else {
                        Util.chatPrint("Dragon cannot breed anymore or you don't own it", world);
                    }
                    event.setResult(Event.Result.DENY);
                    event.setCanceled(true);
                }
            }


//            if (itemStack.getItem() == WRPatchItems.ALPINE_FEED.get() && entity instanceof AlpineEntity && event.getSide() == LogicalSide.SERVER) {
//                Util.chatPrint("Successfully clicked an Alpine", event.getWorld());
//                int breedcount = ((AlpineEntity) entity).breedCount;
//                Util.chatPrint("Breedcount: " + breedcount, event.getWorld());
//                ((AlpineEntity) entity).breedCount++;
//                event.setResult(Event.Result.DENY);
//                event.setCanceled(true);
//            }

    }

}
