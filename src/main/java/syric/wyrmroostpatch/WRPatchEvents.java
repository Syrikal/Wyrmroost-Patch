package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragon.AlpineEntity;
import com.github.wolfshotz.wyrmroost.entities.dragon.RoostStalkerEntity;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import com.github.wolfshotz.wyrmroost.registry.WRSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.LogicalSide;

import java.util.logging.LogManager;

import static syric.wyrmroostpatch.Util.isDragonFeedItem;

public class WRPatchEvents {


    public static void interactEntity(PlayerInteractEvent.EntityInteract event) {
            Entity entity = event.getTarget();
            ItemStack itemStack = event.getItemStack();
            Item item = itemStack.getItem();
            World world = event.getWorld();

            boolean breedSuccess = false;
            DragonFeedItem feedItem = null;
            AbstractDragonEntity dragonEntity = null;

            if (event.getSide() != LogicalSide.SERVER) {
                return;
            }

            if (isDragonFeedItem(item) && entity instanceof AbstractDragonEntity && event.getSide() == LogicalSide.SERVER) {
                event.setCanceled(true);
            }

            if (isDragonFeedItem(item)) {
                if (item.equals(Items.GOLD_NUGGET)) {
                    if (entity.getType() == WREntities.ROOSTSTALKER.get()) {
                        assert entity instanceof AbstractDragonEntity;
                        dragonEntity = (AbstractDragonEntity) entity;
                        if (event.getSide() == LogicalSide.SERVER) {
                            Util.chatPrint("Breedcount: " + dragonEntity.breedCount, world);
                        }
                        boolean canBreed = dragonEntity.breedCount < Util.getBreedCap(dragonEntity.getType()) || !WRPatchConfig.enableBreedCaps.get();
                        boolean isTamed = dragonEntity.getOwner() == event.getPlayer();
                        if (canBreed && isTamed) {
                            boolean notSameDragon = DragonFeedItem.checkTargetStalker(dragonEntity, itemStack);
                            if (notSameDragon) {
                                breedSuccess = true;
                            }
                        }
                    }
                } else {
                    feedItem = (DragonFeedItem) item;
                    if (entity.getType() == feedItem.getDragonType()) {
                        assert entity instanceof AbstractDragonEntity;
                        dragonEntity = (AbstractDragonEntity) entity;

                        if (event.getSide() == LogicalSide.SERVER) {
                            Util.chatPrint("Breedcount: " + dragonEntity.breedCount, world);
                        }

                        boolean canBreed = dragonEntity.breedCount < Util.getBreedCap(dragonEntity.getType()) || !WRPatchConfig.enableBreedCaps.get();
                        boolean isTamed = dragonEntity.getOwner() == event.getPlayer();
                        if (canBreed && isTamed) {
                            boolean notSameDragon = ((DragonFeedItem) item).checkTarget(dragonEntity, itemStack);
                            if (notSameDragon) {
                                breedSuccess = true;
                            }
                        }
                    }
                }



            }

            if (breedSuccess) {
                if (event.getSide() == LogicalSide.SERVER) {
                    ItemStack tempStack = itemStack.copy();

                    if (!event.getPlayer().isCreative()) {
                        itemStack.shrink(1);
                    }

                    if (item.equals(Items.GOLD_NUGGET)) {
                        event.getPlayer().addItem(DragonFeedItem.getNextItemStalker(entity, tempStack));
                    } else {
                        assert feedItem != null;
                        event.getPlayer().addItem(feedItem.getNextItem(entity));
                    }

                    dragonEntity.setInLove(event.getPlayer());
                    dragonEntity.breedCount++;
                } else if (event.getSide() == LogicalSide.CLIENT) {
                    dragonEntity.eat(itemStack);
                }
            }



            if (itemStack.getItem() == WRPatchItems.BREED_RESETTER.get() && event.getSide() == LogicalSide.SERVER && entity instanceof AbstractDragonEntity) {
                ((AbstractDragonEntity) entity).breedCount = 0;
                event.setCanceled(true);
            }

    }

    public static void cancelRooststalkers(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getSide() == LogicalSide.SERVER) {
            Util.chatPrint("cancelRooststalkers checked", event.getEntity().level);
        }
        if (event.getTarget() instanceof RoostStalkerEntity) {
            Util.chatPrint("No interacting with rooststalkers allowed", event.getEntity().level);
            event.setCanceled(true);
            event.setResult(Event.Result.DENY);
        } else {
            Util.chatPrint("entity not a rooststalker. it is a " + event.getTarget().getType(), event.getEntity().level);
        }
    }



}
