package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.Wyrmroost;
import com.github.wolfshotz.wyrmroost.entities.dragon.AlpineEntity;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
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
            () -> new DragonFeedItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON), false));
    public static final RegistryObject<Item> HALF_ALPINE_FEED = ITEMS.register("half_alpine_feed",
            () -> new DragonFeedItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON), true));

    public static final RegistryObject<Item> BFL_FEED = ITEMS.register("bfl_feed",
            () -> new DragonFeedItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON), false));
    public static final RegistryObject<Item> HALF_BFL_FEED = ITEMS.register("half_bfl_feed",
            () -> new DragonFeedItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON), true));


    public static final RegistryObject<Item> BREED_RESETTER = ITEMS.register("breed_resetter",
            () -> new Item(new Item.Properties().tab(Wyrmroost.ITEM_GROUP)));

}
