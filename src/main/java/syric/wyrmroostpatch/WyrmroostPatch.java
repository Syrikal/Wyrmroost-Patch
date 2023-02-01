package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.registry.WREntities;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import syric.wyrmroostpatch.breeding.DragonFeedItem;
import syric.wyrmroostpatch.breeding.RenderTooltip;
import syric.wyrmroostpatch.shushing.Shushing;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("wyrmroostpatch")
public class WyrmroostPatch
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "wyrmroostpatch";

    public WyrmroostPatch() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        WRPatchItems.register(modEventBus);

        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        forgeEventBus.addListener(this::interactEntity);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, WRPatchConfig.CLIENT_SPEC, "wyrmroostpatch-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WRPatchConfig.COMMON_SPEC, "wyrmroostpatch-common.toml");

    }

    private void setup(final FMLCommonSetupEvent event) {
        ((DragonFeedItem) WRPatchItems.ALPINE_FEED.get()).setDragonType(WREntities.ALPINE.get());
        ((DragonFeedItem) WRPatchItems.HALF_ALPINE_FEED.get()).setDragonType(WREntities.ALPINE.get());
        ((DragonFeedItem) WRPatchItems.BFL_FEED.get()).setDragonType(WREntities.BUTTERFLY_LEVIATHAN.get());
        ((DragonFeedItem) WRPatchItems.HALF_BFL_FEED.get()).setDragonType(WREntities.BUTTERFLY_LEVIATHAN.get());


        if (WRPatchConfig.enableShush.get()) {
            Shushing.init();
            MinecraftForge.EVENT_BUS.addListener(this::shush);
        }


    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
        if (WRPatchConfig.tooltipFix.get()) {
            MinecraftForge.EVENT_BUS.addListener(this::renderNuggetTooltip);
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
//        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    private void interactEntity(PlayerInteractEvent.EntityInteract event) {
        WRPatchEvents.interactEntity(event);
    }
    private void shush(PlaySoundAtEntityEvent event) {
        Shushing.shush(event);
    }
//    private void shushRR(PlaySoundEvent event) {
//        Shushing.shushRR(event);
//    }
    private void renderNuggetTooltip(ItemTooltipEvent event) { RenderTooltip.renderTooltip(event); }




    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
