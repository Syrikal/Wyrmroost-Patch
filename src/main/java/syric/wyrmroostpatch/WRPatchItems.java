package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.Wyrmroost;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WRPatchItems {
    // create DeferredRegister object
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WyrmroostPatch.MODID);

    static void register(IEventBus bus) {
        ITEMS.register(bus);
    }


    // register items
    public static final RegistryObject<Item> ALPINE_FEED = ITEMS.register("alpine_feed",
            () -> new AlpineFeedItem(new Item.Properties().stacksTo(1).tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON)));

}
