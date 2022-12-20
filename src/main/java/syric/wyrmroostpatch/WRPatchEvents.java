package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.items.DragonEggItem;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.Random;

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
                //Check valid breeding (rooststalkers).
                //i.e. item is gold nugget, entity is rooststalker, breedcount is below cap, tamed by player, not same dragon
                if (item.equals(Items.GOLD_NUGGET)) {
                    if (entity.getType() == WREntities.ROOSTSTALKER.get()) {
                        assert entity instanceof AbstractDragonEntity;
                        dragonEntity = (AbstractDragonEntity) entity;
                        boolean canBreed = dragonEntity.breedCount < Util.getBreedCap(dragonEntity.getType()) || !WRPatchConfig.enableBreedCaps.get();
                        boolean isTamed = dragonEntity.getOwner() == event.getPlayer();
                        if (canBreed && isTamed) {
                            boolean notSameDragon = DragonFeedItem.checkTargetStalker(dragonEntity, itemStack);
                            if (notSameDragon) {
                                breedSuccess = true;
                            }
                        }
                    }
                //Check valid breeding (everything else)
                //i.e. item and dragon types match, breedcount is below cap, tamed by player, not same dragon
                } else {
                    feedItem = (DragonFeedItem) item;
                    if (entity.getType() == feedItem.getDragonType()) {
                        assert entity instanceof AbstractDragonEntity;
                        dragonEntity = (AbstractDragonEntity) entity;

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

                    ItemStack given;
                    if (item.equals(Items.GOLD_NUGGET)) {
                        given = DragonFeedItem.getNextItemStalker(entity, tempStack);
                    } else {
                        assert feedItem != null;
                        given = feedItem.getNextItem(entity);
                    }

                    PlayerEntity player = event.getPlayer();
                    ServerPlayerEntity serverPlayer = player instanceof ServerPlayerEntity ? (ServerPlayerEntity)player : null;
                    if (serverPlayer != null && given.getItem() instanceof DragonEggItem) {
                        serverPlayer.awardStat(Stats.ANIMALS_BRED);
                        CriteriaTriggers.BRED_ANIMALS.trigger(serverPlayer, dragonEntity, dragonEntity, null);
                    }

                    event.getPlayer().addItem(given);
                    dragonEntity.setInLove(event.getPlayer());
                    dragonEntity.breedCount++;

                    Random random = new Random();
                    world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), itemStack.getEatingSound(), SoundCategory.NEUTRAL, 1.0F, 0.8F + (random.nextFloat() - random.nextFloat()) * 0.4F);

                } else if (event.getSide() == LogicalSide.CLIENT) {
                    dragonEntity.eat(itemStack);
                }
            }



            if (itemStack.getItem() == WRPatchItems.BREED_RESETTER.get() && event.getSide() == LogicalSide.SERVER && entity instanceof AbstractDragonEntity) {
                ((AbstractDragonEntity) entity).breedCount = 0;
                event.setCanceled(true);
            }

    }



}
