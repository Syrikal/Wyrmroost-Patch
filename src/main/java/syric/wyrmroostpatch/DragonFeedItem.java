package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragonegg.DragonEggProperties;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import com.github.wolfshotz.wyrmroost.registry.WRItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class DragonFeedItem extends Item {

    private EntityType<? extends AbstractDragonEntity> dragonType;

    public DragonFeedItem(Properties properties) {
        super(properties);
    }

    public void setDragonType(EntityType<? extends AbstractDragonEntity> dragonType) {
        this.dragonType = dragonType;
    }

    public EntityType<? extends AbstractDragonEntity> getDragonType() {
        return dragonType;
    }

    public ItemStack getNextItem(Entity ent) {
        if (this instanceof HalfEatenDragonFeedItem) {
            ItemStack itemStack = new ItemStack(WRItems.DRAGON_EGG.get());
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("DragonType", EntityType.getKey(dragonType).toString());
            nbt.putInt("HatchTime", ((DragonEggProperties)DragonEggProperties.MAP.get(dragonType)).getHatchTime());
            itemStack.setTag(nbt);
            return itemStack;
        } else {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putUUID("FirstParent", ent.getUUID());
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

    public boolean checkTarget(AbstractDragonEntity ent, ItemStack itemStack) {
//        Util.chatPrint("Item is not half-eaten, checkTarget returning true", ent.level);
        return true;
    }
}
