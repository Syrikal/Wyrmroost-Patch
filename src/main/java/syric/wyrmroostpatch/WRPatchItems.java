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
            () -> new DragonFeedItem(new Item.Properties().stacksTo(1).tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HALF_ALPINE_FEED = ITEMS.register("half_alpine_feed",
            () -> new HalfEatenDragonFeedItem(new Item.Properties().stacksTo(1).tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ROOST_FEED = ITEMS.register("roost_feed",
            () -> new DragonFeedItem(new Item.Properties().stacksTo(1).tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HALF_ROOST_FEED = ITEMS.register("half_roost_feed",
            () -> new HalfEatenDragonFeedItem(new Item.Properties().stacksTo(1).tab(Wyrmroost.ITEM_GROUP).rarity(Rarity.UNCOMMON)));


}
