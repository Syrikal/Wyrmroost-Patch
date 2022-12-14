package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AlpineEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.LogicalSide;

public class WRPatchEvents {


    public static void interactEntity(PlayerInteractEvent.EntityInteract event) {
            Entity entity = event.getTarget();
            ItemStack itemStack = event.getItemStack();
            if (itemStack.getItem() == WRPatchItems.ALPINE_FEED.get() && entity instanceof AlpineEntity && event.getSide() == LogicalSide.SERVER) {
                Util.chatPrint("Successfully clicked an Alpine", event.getWorld());
                int breedcount = ((AlpineEntity) entity).breedCount;
                Util.chatPrint("Breedcount: " + breedcount, event.getWorld());
                ((AlpineEntity) entity).breedCount++;
                event.setResult(Event.Result.DENY);
                event.setCanceled(true);
            }

    }

}
