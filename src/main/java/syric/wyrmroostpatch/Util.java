package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.item.Item;

import java.util.List;

public class Util {

    public static void chatPrint(String input, Entity entity) {
        if (entity instanceof PlayerEntity) {
            String[] outputSplit = input.split("\n");
            for (String i : outputSplit) {
                PlayerEntity player = (PlayerEntity) entity;
                player.displayClientMessage(ITextComponent.nullToEmpty(i), false);
            }
        }
    }
    public static void chatPrint(String input, World level) {
        List<? extends PlayerEntity> playerList = level.players();
        for (PlayerEntity player : playerList) {
            chatPrint(input, player);
        }
    }

    public static int getBreedCap(EntityType type) {
        if (type == WREntities.ALPINE.get()) {
            return WRPatchConfig.alpineBreedCap.get();
        } else if (type == WREntities.ROOSTSTALKER.get()) {
            return WRPatchConfig.rooststalkerBreedCap.get();
        } else {
            return 0;
        }
    }

    public static boolean isDragonFeedItem(Item item) {
        return (item instanceof DragonFeedItem) || item.equals(Items.GOLD_NUGGET);
    }

}
