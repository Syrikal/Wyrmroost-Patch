package syric.wyrmroostpatch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

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
}
