package syric.wyrmroostpatch;

import net.minecraftforge.common.ForgeConfigSpec;
import org.lwjgl.system.CallbackI;

public final class WRPatchConfig {

    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> enableShush;
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

    public static final ForgeConfigSpec.ConfigValue<Double> idleCancel;


    public static final ForgeConfigSpec.ConfigValue<Boolean> enableBreedCaps;
    public static final ForgeConfigSpec.ConfigValue<Integer> alpineBreedCap;
    public static final ForgeConfigSpec.ConfigValue<Integer> rooststalkerBreedCap;
    public static final ForgeConfigSpec.ConfigValue<Integer> leviathanBreedCap;



    static {
        CLIENT_BUILDER.push("Enable Shush Mode");
        enableShush = CLIENT_BUILDER.comment("This mode allows modification of Wyrmroost sounds' volumes.\nAll applicable multipliers are applied to a given sound. If you have the global\nmultiplier set to 0.5, the roar multiplier set to 0.8, and the Alpine multiplier\nset to 0.25, then Alpine dragons' roars will be at 0.5*0.8*0.25 = 10% volume.\n\nEnable Shush Mode. Default: false").define("Shush Mode", false);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Shush Mode Volume Multipliers");
        globalMult = CLIENT_BUILDER.comment("Global multiplier for all Wyrmroost noises. Default: 1").defineInRange("Global Multiplier", 1.0F, 0.0F, 2F);
        roarMult = CLIENT_BUILDER.comment("Multiplier for all dragon roars. Default: 1").defineInRange("Roar Multiplier", 1.0F, 0.0F, 2F);
        alpineMult = CLIENT_BUILDER.comment("Multiplier for Alpine noises. Default: 1").defineInRange("Alpine Multiplier", 1.0F, 0.0F, 2F);
        royalredMult = CLIENT_BUILDER.comment("Multiplier for Royal Red noises. Default: 1").defineInRange("Royal Red Multiplier", 1.0F, 0.0F, 2F);
        owdrakeMult = CLIENT_BUILDER.comment("Multiplier for Overworld Drake noises. Default: 1").defineInRange("Overworld Drake Multiplier", 1.0F, 0.0F, 2F);
        rooststalkerMult = CLIENT_BUILDER.comment("Multiplier for Rooststalker noises. Default: 1").defineInRange("Rooststalker Multiplier", 1.0F, 0.0F, 2F);
        silvergliderMult = CLIENT_BUILDER.comment("Multiplier for Silver Glider noises. Default: 1").defineInRange("Silver Glider Multiplier", 1.0F, 0.0F, 2F);
        butterflyleviathanMult = CLIENT_BUILDER.comment("Multiplier for Butterfly Leviathan noises. Default: 1").defineInRange("Butterfly Leviathan Multiplier", 1.0F, 0.0F, 2F);
        canariwyvernMult = CLIENT_BUILDER.comment("Multiplier for Canari Wyvern noises. Default: 1").defineInRange("Canari Wyvern Multiplier", 1.0F, 0.0F, 2F);
        desertwyrmMult = CLIENT_BUILDER.comment("Multiplier for Lesser Desertwyrm noises. Default: 1").defineInRange("Lesser Desertwyrm Multiplier", 1.0F, 0.0F, 2F);
        coindragonMult = CLIENT_BUILDER.comment("Multiplier for Coin Dragon noises. Default: 1").defineInRange("Coin Dragon Multiplier", 1.0F, 0.0F, 2F);
        dragonfruitMult = CLIENT_BUILDER.comment("Multiplier for Dragonfruit Drake noises. Default: 1").defineInRange("Dragonfruit Drake Multiplier", 1.0F, 0.0F, 2F);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Idle Sound Reducer");
        idleCancel = CLIENT_BUILDER.comment("Chance that idle sounds will be cancelled. Default: 0").defineInRange("Idle Sound Cancellation Chance", 0.0, 0.0, 1);
        CLIENT_BUILDER.pop();

        COMMON_BUILDER.push("Breed Caps");
        enableBreedCaps = COMMON_BUILDER.comment("Whether alpines and rooststalkers have a limit on the number of times they can breed. Default: true").define("Enable Breed Caps", true);
        alpineBreedCap = COMMON_BUILDER.comment("Alpine dragon breed cap. Default: 3").defineInRange("Alpine Breed Cap", 3, 1, 10000);
        rooststalkerBreedCap = COMMON_BUILDER.comment("Rooststalker breed cap. Default: 5").defineInRange("Rooststalker Breed Cap", 5, 1, 10000);
        leviathanBreedCap = COMMON_BUILDER.comment("Butterfly Leviathan breed cap. Default: 3").defineInRange("Butterfly Leviathan Breed Cap", 3, 1, 10000);
        COMMON_BUILDER.pop();

        CLIENT_SPEC = CLIENT_BUILDER.build();
        COMMON_SPEC = COMMON_BUILDER.build();
    }

}
