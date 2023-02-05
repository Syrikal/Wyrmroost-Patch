package syric.wyrmroostpatch;

import net.minecraftforge.common.ForgeConfigSpec;

public final class WRPatchConfig {

    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> enableShush;
    public static final ForgeConfigSpec.ConfigValue<Boolean> monoResourcePackMode;
    public static final ForgeConfigSpec.ConfigValue<Double> globalMult;
    public static final ForgeConfigSpec.ConfigValue<Double> roarMult;
    public static final ForgeConfigSpec.ConfigValue<Double> alpineMult;
    public static final ForgeConfigSpec.ConfigValue<Double> royalredMult;
    public static final ForgeConfigSpec.ConfigValue<Double> owdrakeMult;
    public static final ForgeConfigSpec.ConfigValue<Double> rooststalkerMult;
    public static final ForgeConfigSpec.ConfigValue<Double> silvergliderMult;
    public static final ForgeConfigSpec.ConfigValue<Double> butterflyleviathanMult;
    public static final ForgeConfigSpec.ConfigValue<Double> canariwyvernMult;
    public static final ForgeConfigSpec.ConfigValue<Double> desertwyrmMult;
    public static final ForgeConfigSpec.ConfigValue<Double> coindragonMult;
    public static final ForgeConfigSpec.ConfigValue<Double> dragonfruitMult;
    public static final ForgeConfigSpec.ConfigValue<Double> flapMult;
    public static final ForgeConfigSpec.ConfigValue<Double> fireMult;

    public static final ForgeConfigSpec.ConfigValue<Boolean> enableShushSv;
    public static final ForgeConfigSpec.ConfigValue<Boolean> monoResourcePackModeSv;
    public static final ForgeConfigSpec.ConfigValue<Double> globalMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> roarMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> alpineMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> royalredMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> owdrakeMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> rooststalkerMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> silvergliderMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> butterflyleviathanMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> canariwyvernMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> dragonfruitMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> flapMultSv;
    public static final ForgeConfigSpec.ConfigValue<Double> fireMultSv;

    public static final ForgeConfigSpec.ConfigValue<Double> idleCancel;

    public static final ForgeConfigSpec.ConfigValue<Boolean> tooltipFix;


    public static final ForgeConfigSpec.ConfigValue<Boolean> enableBreedCaps;
    public static final ForgeConfigSpec.ConfigValue<Integer> alpineBreedCap;
    public static final ForgeConfigSpec.ConfigValue<Integer> rooststalkerBreedCap;
    public static final ForgeConfigSpec.ConfigValue<Integer> leviathanBreedCap;

    public static final ForgeConfigSpec.ConfigValue<Boolean> enableAlpineRecoloring;



    static {
        CLIENT_BUILDER.push("Enable Client-Side Shush Mode");
        enableShush = CLIENT_BUILDER.comment("This mode allows modification of Wyrmroost sounds' volumes on the client side.\nClient-side multipliers affect the subjective volume of a sound, but not how far it carries.\nYou can use client or server shushing, or both. If you're not sure, client only is recommended.\nAll applicable multipliers are applied to a given sound. If you have the global\nmultiplier set to 0.5, the roar multiplier set to 0.8, and the Alpine multiplier\nset to 0.25, then Alpine dragons' roars will be at 0.5*0.8*0.25 = 10% volume.\n\nThis feature may produce minor lag.\n\nEnable Client-Side Shush Mode. Default: false").define("Client Shush Mode", false);
        monoResourcePackMode = CLIENT_BUILDER.comment("If you have the mono-audio resource pack (available on CurseForge),\nmany dragons that are usually loud due to an audio glitch will be quieter.\nThis mode applies an optional multiplier to their volume to compensate slightly.\nEnable Mono Pack Mode. Default: false").define("Mono-Audio Pack Mode", false);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Client-Side Shush Mode Volume Multipliers");
        globalMult = CLIENT_BUILDER.comment("Global multiplier for all Wyrmroost noises. Default: 1").defineInRange("Global Multiplier", 1.0F, 0.0F, 20F);
        roarMult = CLIENT_BUILDER.comment("Multiplier for all dragon roars. Default: 1").defineInRange("Roar Multiplier", 1.0F, 0.0F, 20F);
        alpineMult = CLIENT_BUILDER.comment("Multiplier for Alpine noises. Default: 1").defineInRange("Alpine Multiplier", 1.0F, 0.0F, 20F);
        royalredMult = CLIENT_BUILDER.comment("Multiplier for Royal Red noises. Default: 1").defineInRange("Royal Red Multiplier", 1.0F, 0.0F, 20F);
        owdrakeMult = CLIENT_BUILDER.comment("Multiplier for Overworld Drake noises. Default: 1").defineInRange("Overworld Drake Multiplier", 1.0F, 0.0F, 20F);
        rooststalkerMult = CLIENT_BUILDER.comment("Multiplier for Rooststalker noises. Default: 1").defineInRange("Rooststalker Multiplier", 1.0F, 0.0F, 20F);
        silvergliderMult = CLIENT_BUILDER.comment("Multiplier for Silver Glider noises. Default: 1").defineInRange("Silver Glider Multiplier", 1.0F, 0.0F, 20F);
        butterflyleviathanMult = CLIENT_BUILDER.comment("Multiplier for Butterfly Leviathan noises. Default: 1").defineInRange("Butterfly Leviathan Multiplier", 1.0F, 0.0F, 20F);
        canariwyvernMult = CLIENT_BUILDER.comment("Multiplier for Canari Wyvern noises. Default: 1").defineInRange("Canari Wyvern Multiplier", 1.0F, 0.0F, 20F);
        desertwyrmMult = CLIENT_BUILDER.comment("Multiplier for Lesser Desertwyrm noises. Default: 1").defineInRange("Lesser Desertwyrm Multiplier", 1.0F, 0.0F, 20F);
        coindragonMult = CLIENT_BUILDER.comment("Multiplier for Coin Dragon noises. Default: 1").defineInRange("Coin Dragon Multiplier", 1.0F, 0.0F, 20F);
        dragonfruitMult = CLIENT_BUILDER.comment("Multiplier for Dragonfruit Drake noises. Default: 1").defineInRange("Dragonfruit Drake Multiplier", 1.0F, 0.0F, 20F);
        flapMult = CLIENT_BUILDER.comment("Multiplier for flapping noises. Default: 1").defineInRange("Flap Multiplier", 1.0F, 0.0F, 20F);
        fireMult = CLIENT_BUILDER.comment("Multiplier for fire-breathing noises. Default: 1").defineInRange("Fire Multiplier", 1.0F, 0.0F, 20F);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Sound Frequency Reducer");
        idleCancel = CLIENT_BUILDER.comment("Chance that idle sounds will be cancelled. Default: 0").defineInRange("Idle Sound Cancellation Chance", 0.0, 0.0, 1);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Fixed Rooststalker Tooltip");
        tooltipFix = CLIENT_BUILDER.comment("Fixes tooltip for half-eaten gold nuggets. Produces negligible performance hit. Default: true").define("Fixed Rooststalker Tooltip", true);
        CLIENT_BUILDER.pop();


        COMMON_BUILDER.push("Breed Caps");
        enableBreedCaps = COMMON_BUILDER.comment("Whether alpines, rooststalkers, and butterfly leviathans have a limit on the number of times they can breed. Default: true").define("Enable Breed Caps", true);
        alpineBreedCap = COMMON_BUILDER.comment("Alpine dragon breed cap. Set to 0 to disable. Default: 3").defineInRange("Alpine Breed Cap", 3, 0, 10000);
        rooststalkerBreedCap = COMMON_BUILDER.comment("Rooststalker breed cap. Set to 0 to disable. Default: 5").defineInRange("Rooststalker Breed Cap", 5, 0, 10000);
        leviathanBreedCap = COMMON_BUILDER.comment("Butterfly Leviathan breed cap. Set to 0 to disable. Default: 3").defineInRange("Butterfly Leviathan Breed Cap", 3, 0, 10000);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Alpine Recoloring");
        enableAlpineRecoloring = COMMON_BUILDER.comment("Enables recoloring alpines with Alpine Tinctures. Default: true").define("Enable Alpine Recoloring", true);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Server-Side Shush Mode");
        enableShushSv = COMMON_BUILDER.comment("This mode allows modification of Wyrmroost sounds' volumes on the SERVER side.\nServer-side multipliers are good for making quiet sounds carry further. They are not as good for making loud sounds quieter.\nAll applicable multipliers are applied to a given sound. If you have the global\nmultiplier set to 0.5, the roar multiplier set to 0.8, and the Alpine multiplier\nset to 0.25, then Alpine dragons' roars will be at 0.5*0.8*0.25 = 10% volume.\n\nEnable Server-Side Shush Mode. Default: false").define("Server Shush Mode", false);
        monoResourcePackModeSv = COMMON_BUILDER.comment("If you have the mono-audio resource pack (available on CurseForge),\nmany dragons that are usually loud due to an audio glitch will be quieter.\nThis mode applies an optional multiplier to their volume to compensate slightly.\nEnable Mono Pack Mode. Default: false").define("Mono-Audio Pack Mode", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Server-Side Shush Mode Volume Multipliers");
        globalMultSv = COMMON_BUILDER.comment("Global multiplier for all Wyrmroost noises. Default: 1").defineInRange("Global Multiplier", 1.0F, 0.0F, 20F);
        roarMultSv = COMMON_BUILDER.comment("Multiplier for all dragon roars. Default: 1").defineInRange("Roar Multiplier", 1.0F, 0.0F, 20F);
        alpineMultSv = COMMON_BUILDER.comment("Multiplier for Alpine noises. Default: 1").defineInRange("Alpine Multiplier", 1.0F, 0.0F, 20F);
        royalredMultSv = COMMON_BUILDER.comment("Multiplier for Royal Red noises. Default: 1").defineInRange("Royal Red Multiplier", 1.0F, 0.0F, 20F);
        owdrakeMultSv = COMMON_BUILDER.comment("Multiplier for Overworld Drake noises. Default: 1").defineInRange("Overworld Drake Multiplier", 1.0F, 0.0F, 20F);
        rooststalkerMultSv = COMMON_BUILDER.comment("Multiplier for Rooststalker noises. Default: 1").defineInRange("Rooststalker Multiplier", 1.0F, 0.0F, 20F);
        silvergliderMultSv = COMMON_BUILDER.comment("Multiplier for Silver Glider noises. Default: 1").defineInRange("Silver Glider Multiplier", 1.0F, 0.0F, 20F);
        butterflyleviathanMultSv = COMMON_BUILDER.comment("Multiplier for Butterfly Leviathan noises. Default: 1").defineInRange("Butterfly Leviathan Multiplier", 1.0F, 0.0F, 20F);
        canariwyvernMultSv = COMMON_BUILDER.comment("Multiplier for Canari Wyvern noises. Default: 1").defineInRange("Canari Wyvern Multiplier", 1.0F, 0.0F, 20F);
        dragonfruitMultSv = COMMON_BUILDER.comment("Multiplier for Dragonfruit Drake noises. Default: 1").defineInRange("Dragonfruit Drake Multiplier", 1.0F, 0.0F, 20F);
        flapMultSv = COMMON_BUILDER.comment("Multiplier for flapping noises. Default: 1").defineInRange("Flap Multiplier", 1.0F, 0.0F, 20F);
        fireMultSv = COMMON_BUILDER.comment("Multiplier for fire-breathing noises. Default: 1").defineInRange("Fire Multiplier", 1.0F, 0.0F, 20F);
        COMMON_BUILDER.pop();


        CLIENT_SPEC = CLIENT_BUILDER.build();
        COMMON_SPEC = COMMON_BUILDER.build();
    }

}
