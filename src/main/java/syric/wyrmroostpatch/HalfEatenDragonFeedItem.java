package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.UUID;

public class HalfEatenDragonFeedItem extends DragonFeedItem {

    public HalfEatenDragonFeedItem(Properties properties) {
        super(properties, true);
    }

    @Override
    public boolean checkTarget(AbstractDragonEntity ent, ItemStack itemStack) {
        if (!itemStack.hasTag()) {
            Util.chatPrint("item has no nbt tag, checkTarget returning false", ent.level);
            return false;
        }
        assert itemStack.getTag() != null;
        UUID id = itemStack.getTag().getUUID("FirstParent");
//        Util.chatPrint("First parent ID: " + id, ent.level);
//        Util.chatPrint("Second parent ID: " + ent.getUUID(), ent.level);


        if (id.equals(ent.getUUID())) {
//            Util.chatPrint("Same parent, checkTarget returning false", ent.level);
            return false;
        } else {
//            Util.chatPrint("Different parent, checkTarget returning true", ent.level);
            return true;
        }
//        return id != ent.getUUID();
    }

}
