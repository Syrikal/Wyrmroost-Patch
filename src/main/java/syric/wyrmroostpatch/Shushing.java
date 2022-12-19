package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.registry.WRSounds;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.GL;

import java.util.*;

public class Shushing {

    private static List<SoundEvent> ALL_SOUNDS;

    private static List<SoundEvent> ALPINE_SOUNDS;
    private static List<SoundEvent> ROYALRED_SOUNDS;
    private static List<SoundEvent> ROOSTSTALKER_SOUNDS;
    private static List<SoundEvent> BFL_SOUNDS;
    private static List<SoundEvent> GLIDER_SOUNDS;
    private static List<SoundEvent> OWDRAKE_SOUNDS;
    private static List<SoundEvent> CANARI_SOUNDS;
    private static List<SoundEvent> DFD_SOUNDS;
    private static List<SoundEvent> DESERTWYRM_SOUNDS;
    private static List<SoundEvent> COIN_SOUNDS;

    private static Map<List<SoundEvent>, Double> MODIFIED_SPECIES = new HashMap<List<SoundEvent>, Double>();

    private static List<SoundEvent> ROARS;

    public static void init() {
        ALL_SOUNDS = new ArrayList<>(Arrays.asList(
                WRSounds.ENTITY_ALPINE_IDLE.get(),
                WRSounds.ENTITY_ALPINE_DEATH.get(),
                WRSounds.ENTITY_ALPINE_HURT.get(),
                WRSounds.ENTITY_ALPINE_ROAR.get(),
                WRSounds.ENTITY_ROYALRED_IDLE.get(),
                WRSounds.ENTITY_ROYALRED_DEATH.get(),
                WRSounds.ENTITY_ROYALRED_HURT.get(),
                WRSounds.ENTITY_ROYALRED_ROAR.get(),
                WRSounds.ENTITY_STALKER_IDLE.get(),
                WRSounds.ENTITY_STALKER_DEATH.get(),
                WRSounds.ENTITY_STALKER_HURT.get(),
                WRSounds.ENTITY_BFLY_IDLE.get(),
                WRSounds.ENTITY_BFLY_DEATH.get(),
                WRSounds.ENTITY_BFLY_ROAR.get(),
                WRSounds.ENTITY_BFLY_HURT.get(),
                WRSounds.ENTITY_SILVERGLIDER_DEATH.get(),
                WRSounds.ENTITY_SILVERGLIDER_HURT.get(),
                WRSounds.ENTITY_SILVERGLIDER_IDLE.get(),
                WRSounds.ENTITY_OWDRAKE_DEATH.get(),
                WRSounds.ENTITY_OWDRAKE_HURT.get(),
                WRSounds.ENTITY_OWDRAKE_ROAR.get(),
                WRSounds.ENTITY_OWDRAKE_IDLE.get(),
                WRSounds.ENTITY_CANARI_DEATH.get(),
                WRSounds.ENTITY_CANARI_HURT.get(),
                WRSounds.ENTITY_CANARI_IDLE.get(),
                WRSounds.ENTITY_DFD_DEATH.get(),
                WRSounds.ENTITY_DFD_HURT.get(),
                WRSounds.ENTITY_DFD_IDLE.get(),
                WRSounds.ENTITY_LDWYRM_IDLE.get(),
                WRSounds.ENTITY_COINDRAGON_IDLE.get(),
                WRSounds.FIRE_BREATH.get(),
                WRSounds.WING_FLAP.get()
                ));


        if (WRPatchConfig.alpineMult.get() != 1) {
            ALPINE_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_ALPINE_IDLE.get(),
                    WRSounds.ENTITY_ALPINE_DEATH.get(),
                    WRSounds.ENTITY_ALPINE_HURT.get(),
                    WRSounds.ENTITY_ALPINE_ROAR.get()));
            MODIFIED_SPECIES.put(ALPINE_SOUNDS, WRPatchConfig.alpineMult.get());
        }

        if (WRPatchConfig.royalredMult.get() != 1) {
            ROYALRED_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_ROYALRED_IDLE.get(),
                    WRSounds.ENTITY_ROYALRED_DEATH.get(),
                    WRSounds.ENTITY_ROYALRED_HURT.get(),
                    WRSounds.ENTITY_ROYALRED_ROAR.get()));
            MODIFIED_SPECIES.put(ROYALRED_SOUNDS, WRPatchConfig.royalredMult.get());
        }

        if (WRPatchConfig.rooststalkerMult.get() != 1) {
            ROOSTSTALKER_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_STALKER_IDLE.get(),
                    WRSounds.ENTITY_STALKER_DEATH.get(),
                    WRSounds.ENTITY_STALKER_HURT.get()));
            MODIFIED_SPECIES.put(ROOSTSTALKER_SOUNDS, WRPatchConfig.rooststalkerMult.get());
        }

        if (WRPatchConfig.butterflyleviathanMult.get() != 1) {
            BFL_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_BFLY_IDLE.get(),
                    WRSounds.ENTITY_BFLY_DEATH.get(),
                    WRSounds.ENTITY_BFLY_ROAR.get(),
                    WRSounds.ENTITY_BFLY_HURT.get()));
            MODIFIED_SPECIES.put(BFL_SOUNDS, WRPatchConfig.butterflyleviathanMult.get());
        }

        if (WRPatchConfig.silvergliderMult.get() != 1) {
            GLIDER_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_SILVERGLIDER_DEATH.get(),
                    WRSounds.ENTITY_SILVERGLIDER_HURT.get(),
                    WRSounds.ENTITY_SILVERGLIDER_IDLE.get()));
            MODIFIED_SPECIES.put(GLIDER_SOUNDS, WRPatchConfig.silvergliderMult.get());
        }

        if (WRPatchConfig.owdrakeMult.get() != 1) {
            OWDRAKE_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_OWDRAKE_DEATH.get(),
                    WRSounds.ENTITY_OWDRAKE_HURT.get(),
                    WRSounds.ENTITY_OWDRAKE_ROAR.get(),
                    WRSounds.ENTITY_OWDRAKE_IDLE.get()));
            MODIFIED_SPECIES.put(OWDRAKE_SOUNDS, WRPatchConfig.owdrakeMult.get());
        }

        if (WRPatchConfig.canariwyvernMult.get() != 1) {
            CANARI_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_CANARI_DEATH.get(),
                    WRSounds.ENTITY_CANARI_HURT.get(),
                    WRSounds.ENTITY_CANARI_IDLE.get()));
            MODIFIED_SPECIES.put(CANARI_SOUNDS, WRPatchConfig.canariwyvernMult.get());
        }

        if (WRPatchConfig.dragonfruitMult.get() != 1) {
            DFD_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_DFD_DEATH.get(),
                    WRSounds.ENTITY_DFD_HURT.get(),
                    WRSounds.ENTITY_DFD_IDLE.get()));
            MODIFIED_SPECIES.put(DFD_SOUNDS, WRPatchConfig.dragonfruitMult.get());
        }

        if (WRPatchConfig.desertwyrmMult.get() != 1) {
            DESERTWYRM_SOUNDS = new ArrayList<>(Collections.singletonList(
                    WRSounds.ENTITY_LDWYRM_IDLE.get()));
            MODIFIED_SPECIES.put(DESERTWYRM_SOUNDS, WRPatchConfig.desertwyrmMult.get());
        }

        if (WRPatchConfig.coindragonMult.get() != 1) {
            COIN_SOUNDS = new ArrayList<>(Collections.singletonList(
                    WRSounds.ENTITY_COINDRAGON_IDLE.get()));
            MODIFIED_SPECIES.put(COIN_SOUNDS, WRPatchConfig.coindragonMult.get());
        }

        if (WRPatchConfig.roarMult.get() != 1) {
            ROARS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_ALPINE_ROAR.get(),
                    WRSounds.ENTITY_BFLY_ROAR.get(),
                    WRSounds.ENTITY_OWDRAKE_ROAR.get(),
                    WRSounds.ENTITY_ROYALRED_ROAR.get()));
        }
    }

    public static void shush(PlaySoundAtEntityEvent event) {
//        DeferredRegister<SoundEvent> wrreg = WRSounds.REGISTRY;
        if (ALL_SOUNDS.contains(event.getSound())) {
            event.setVolume((float) (event.getVolume() * calculateMultiplier(event.getSound())));
        }
//        for (RegistryObject<SoundEvent> reg : wrreg.getEntries()) {
//            if (event.getSound().equals(reg.get())) {
//                event.setVolume((float) (event.getVolume() * calculateMultiplier(event.getSound())));
//            }
//        }
    }

    private static Double calculateMultiplier(SoundEvent sound) {
        double global = WRPatchConfig.globalMult.get();
        double species = 1;
        double roar = 1;

        if (WRPatchConfig.roarMult.get() != 1 && ROARS.contains(sound)) {
            roar = WRPatchConfig.roarMult.get();
        }

        for (Map.Entry<List<SoundEvent>, Double> specEntry : MODIFIED_SPECIES.entrySet()) {
            if (specEntry.getKey().contains(sound)) {
                species = specEntry.getValue();
                break;
            }
        }

        return global * species * roar;
    }


}
