package syric.wyrmroostpatch;

import net.minecraftforge.common.ForgeConfigSpec;
import org.lwjgl.system.CallbackI;

public final class WRPatchConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

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


    public static final ForgeConfigSpec.ConfigValue<Boolean> enableBreedCaps;
    public static final ForgeConfigSpec.ConfigValue<Integer> alpineBreedCap;
    public static final ForgeConfigSpec.ConfigValue<Integer> rooststalkerBreedCap;



    static {
        BUILDER.push("Enable Shush Mode");
        enableShush = BUILDER.comment("This mode allows modification of Wyrmroost sounds' volumes.\nAll applicable multipliers are applied to a given sound. If you have the global\nmultiplier set to 0.5, the roar multiplier set to 0.8, and the Alpine multiplier\nset to 0.25, then Alpine dragons' roars will be at 0.5*0.8*0.25 = 10% volume.\n\nEnable Shush Mode. Default: false").define("Shush Mode", false);
        BUILDER.pop();

        BUILDER.push("Shush Mode Volume Multipliers");
        globalMult = BUILDER.comment("Global multiplier for all Wyrmroost noises. Default: 1").defineInRange("Global Multiplier", 1.0F, 0.0F, 2F);
        roarMult = BUILDER.comment("Multiplier for all dragon roars. Default: 1").defineInRange("Roar Multiplier", 1.0F, 0.0F, 2F);
        alpineMult = BUILDER.comment("Multiplier for Alpine noises. Default: 1").defineInRange("Alpine Multiplier", 1.0F, 0.0F, 2F);
        royalredMult = BUILDER.comment("Multiplier for Royal Red noises. Default: 1").defineInRange("Royal Red Multiplier", 1.0F, 0.0F, 2F);
        owdrakeMult = BUILDER.comment("Multiplier for Overworld Drake noises. Default: 1").defineInRange("Overworld Drake Multiplier", 1.0F, 0.0F, 2F);
        rooststalkerMult = BUILDER.comment("Multiplier for Rooststalker noises. Default: 1").defineInRange("Rooststalker Multiplier", 1.0F, 0.0F, 2F);
        silvergliderMult = BUILDER.comment("Multiplier for Silver Glider noises. Default: 1").defineInRange("Silver Glider Multiplier", 1.0F, 0.0F, 2F);
        butterflyleviathanMult = BUILDER.comment("Multiplier for Butterfly Leviathan noises. Default: 1").defineInRange("Butterfly Leviathan Multiplier", 1.0F, 0.0F, 2F);
        canariwyvernMult = BUILDER.comment("Multiplier for Canari Wyvern noises. Default: 1").defineInRange("Canari Wyvern Multiplier", 1.0F, 0.0F, 2F);
        desertwyrmMult = BUILDER.comment("Multiplier for Lesser Desertwyrm noises. Default: 1").defineInRange("Lesser Desertwyrm Multiplier", 1.0F, 0.0F, 2F);
        coindragonMult = BUILDER.comment("Multiplier for Coin Dragon noises. Default: 1").defineInRange("Coin Dragon Multiplier", 1.0F, 0.0F, 2F);
        dragonfruitMult = BUILDER.comment("Multiplier for Dragonfruit Drake noises. Default: 1").defineInRange("Dragonfruit Drake Multiplier", 1.0F, 0.0F, 2F);
        BUILDER.pop();

        BUILDER.push("Breed Caps");
        enableBreedCaps = BUILDER.comment("Whether alpines and rooststalkers have a limit on the number of times they can breed. Default: true").define("Enable Breed Caps", true);
        alpineBreedCap = BUILDER.comment("Alpine dragon breed cap. Default: 3").defineInRange("Alpine Breed Cap", 3, 1, 10000);
        rooststalkerBreedCap = BUILDER.comment("Rooststalker breed cap. Default: 5").defineInRange("Rooststalker Breed Cap", 5, 1, 10000);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

}
