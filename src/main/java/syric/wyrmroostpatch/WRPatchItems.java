package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.Wyrmroost;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import syric.wyrmroostpatch.breeding.DragonFeedItem;
import syric.wyrmroostpatch.recoloring.AlpineTinctureItem;

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

    public static final RegistryObject<Item> SKY_ALPINE_TINCTURE = ITEMS.register("sky_tincture",
            () -> new AlpineTinctureItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP), 0));
    public static final RegistryObject<Item> RAVEN_ALPINE_TINCTURE = ITEMS.register("raven_tincture",
            () -> new AlpineTinctureItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP), 1));
    public static final RegistryObject<Item> HONEY_ALPINE_TINCTURE = ITEMS.register("honey_tincture",
            () -> new AlpineTinctureItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP), 2));
    public static final RegistryObject<Item> MINT_ALPINE_TINCTURE = ITEMS.register("mint_tincture",
            () -> new AlpineTinctureItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP), 3));
    public static final RegistryObject<Item> ROSE_ALPINE_TINCTURE = ITEMS.register("rose_tincture",
            () -> new AlpineTinctureItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP), 4));
    public static final RegistryObject<Item> SNOWY_ALPINE_TINCTURE = ITEMS.register("snowy_tincture",
            () -> new AlpineTinctureItem(new Item.Properties().tab(Wyrmroost.ITEM_GROUP), 5));

}
