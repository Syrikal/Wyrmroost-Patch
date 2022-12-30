package syric.wyrmroostpatch.recoloring;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import syric.wyrmroostpatch.WRPatchConfig;

import javax.annotation.Nullable;
import java.util.List;

public class AlpineTinctureItem extends Item {

    public final int color;

    public AlpineTinctureItem(Properties properties, int color) {
        super(properties);
        this.color = color;
    }

    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (WRPatchConfig.enableAlpineRecoloring.get()) {
            tooltip.add(new TranslationTextComponent("item.wyrmroostpatch.tincture_tooltip"));
        } else {
            tooltip.add(new TranslationTextComponent("item.wyrmroostpatch.tincture_disabled"));
        }
    }

}
