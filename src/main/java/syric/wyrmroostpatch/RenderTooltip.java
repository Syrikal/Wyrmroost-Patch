package syric.wyrmroostpatch;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class RenderTooltip {

    public static void renderTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem().equals(Items.GOLD_NUGGET) && stack.hasTag()) {
            boolean halfeaten = stack.getTag().getBoolean("HalfEaten");
            if (halfeaten) {
                List<ITextComponent> tooltip = event.getToolTip();
//                tooltip.set(0, tooltip.get(0).copy().withStyle(TextFormatting.AQUA));
                tooltip.set(0, new TranslationTextComponent("item.wyrmroostpatch.halfnugget", new Object[]{}).withStyle(TextFormatting.YELLOW));
                tooltip.add(1, new TranslationTextComponent("item.wyrmroostpatch.halfnugget.tooltip", new Object[]{}).withStyle(TextFormatting.WHITE));
            }
        }

//        nbt2.putString("Lore", ITextComponent.Serializer.toJson(new TranslationTextComponent("item.wyrmroostpatch.halfnugget.tooltip", new Object[]{}).withStyle(TextFormatting.WHITE)));
//
//
//        if (!stack.isEmpty() && CommunityGlobals.MOD_ID.equals(stack.getItem().getRegistryName().getNamespace()))
//        {
//            SubModContainer subMod = SubModLoader.getSubModOrigin(stack.getItem());
//
//            if (subMod != null)
//            {
//                event.getToolTip().add(TextFormatting.DARK_GRAY + "(" + subMod.getName() + " - " + subMod.getAttribution() + ")");
//            }
//        }
    }

}
