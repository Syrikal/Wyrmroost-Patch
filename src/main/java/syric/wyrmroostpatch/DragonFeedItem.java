package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragonegg.DragonEggProperties;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import com.github.wolfshotz.wyrmroost.registry.WRItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.UUID;

public class DragonFeedItem extends Item {

    public final boolean halfEaten;

    private EntityType<? extends AbstractDragonEntity> dragonType;

    public DragonFeedItem(Properties properties, boolean halfEaten) {
        super(properties);
        this.halfEaten = halfEaten;
    }



    public void setDragonType(EntityType<? extends AbstractDragonEntity> dragonType) {
        this.dragonType = dragonType;
    }

    public EntityType<? extends AbstractDragonEntity> getDragonType() {
        return dragonType;
    }

    //Returns either a dragon egg, or the half-eaten version (with the first parent's ID appended).
    public ItemStack getNextItem(Entity parentCandidate) {
        if (halfEaten) {
            ItemStack itemStack = new ItemStack(WRItems.DRAGON_EGG.get());
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("DragonType", EntityType.getKey(dragonType).toString());
            nbt.putInt("HatchTime", ((DragonEggProperties)DragonEggProperties.MAP.get(dragonType)).getHatchTime());
            itemStack.setTag(nbt);
            return itemStack;
        } else {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putUUID("FirstParent", parentCandidate.getUUID());
            if (this.dragonType == WREntities.ALPINE.get()) {
                ItemStack output = WRPatchItems.HALF_ALPINE_FEED.get().getDefaultInstance();
                output.setTag(nbt);
                return output;
            } else if (this.dragonType == WREntities.ROOSTSTALKER.get()) {
                ItemStack output = WRPatchItems.HALF_ROOST_FEED.get().getDefaultInstance();
                output.setTag(nbt);
                return output;
            } else {
                return this.getDefaultInstance();
            }
        }
    }

    public static ItemStack getNextItemStalker(Entity parentCandidate, ItemStack stack) {

        boolean halfeaten = false;
        if (!stack.hasTag()) {
            Util.chatPrint("Item has no tag, halfeaten is false", parentCandidate.level);
        } else {
            assert stack.getTag() != null;
            halfeaten = stack.getTag().getBoolean("HalfEaten");
            Util.chatPrint("Item has tag, halfeaten = " + halfeaten, parentCandidate.level);
        }

        if (halfeaten) {
            Util.chatPrint("Item is half-eaten, returning dragon egg", parentCandidate.level);
            ItemStack itemStack = new ItemStack(WRItems.DRAGON_EGG.get());
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("DragonType", EntityType.getKey(WREntities.ROOSTSTALKER.get()).toString());
            nbt.putInt("HatchTime", ((DragonEggProperties)DragonEggProperties.MAP.get(WREntities.ROOSTSTALKER.get())).getHatchTime());
            itemStack.setTag(nbt);
            return itemStack;
        } else {
            Util.chatPrint("Item is not half-eaten, returning gold nugget with NBT", parentCandidate.level);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putUUID("FirstParent", parentCandidate.getUUID());
            nbt.putBoolean("HalfEaten", true);

            ItemStack output = Items.GOLD_NUGGET.getDefaultInstance();
            output.setTag(nbt);
            output.setHoverName(ITextComponent.nullToEmpty("Stored Parent: " + parentCandidate.getUUID()));
            return output;
        }
    }


    //Checks if the dragon clicked is an acceptable mate. Returns 'false' if the item is half-eaten
    //and the stored dragon has just been clicked again.
    //Effectively, prevents you from breeding a dragon with itself.
    public boolean checkTarget(AbstractDragonEntity parentCandidate, ItemStack itemStack) {
        //The first parent can be any dragon that meets the criteria.
        if (!halfEaten) {
            return true;
        }
        if (!itemStack.hasTag()) {
            Util.chatPrint("item has no nbt tag, checkTarget returning false", parentCandidate.level);
            return false;
        } else {
            assert itemStack.getTag() != null;
            UUID firstParentID = itemStack.getTag().getUUID("FirstParent");
//            Util.chatPrint("First parent ID: " + firstParentID, parentCandidate.level);
//            Util.chatPrint("Second parent ID: " + parentCandidate.getUUID(), parentCandidate.level);


            if (firstParentID.equals(parentCandidate.getUUID())) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean checkTargetStalker(AbstractDragonEntity parentCandidate, ItemStack itemStack) {
        if (!itemStack.hasTag()) {
            Util.chatPrint("item has no nbt tag, checkTargetStalker returning true", parentCandidate.level);
            return true;
        } else {
            assert itemStack.getTag() != null;
            UUID firstParentID = itemStack.getTag().getUUID("FirstParent");

            if (firstParentID.equals(parentCandidate.getUUID())) {
                Util.chatPrint("has tag but same parent, checkTargetStalker returning false", parentCandidate.level);
                return false;
            } else {
                Util.chatPrint("has tag and not same parent, checkTargetStalker returning true", parentCandidate.level);
                return true;
            }
        }
    }

}
