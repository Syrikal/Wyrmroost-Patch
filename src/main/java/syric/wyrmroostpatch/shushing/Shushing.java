package syric.wyrmroostpatch.shushing;

import com.github.wolfshotz.wyrmroost.registry.WRSounds;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import syric.wyrmroostpatch.WRPatchConfig;

import java.util.*;

@OnlyIn(Dist.CLIENT)
public class Shushing {

    public static List<SoundEvent> ALL_SOUNDS;
    public static List<SoundEvent> SECONDARY_SHUSHING;

    public static final Map<List<SoundEvent>, Double> MODIFIED_SPECIES = new HashMap<>();

    public static List<SoundEvent> ROARS;
    public static List<SoundEvent> IDLES;


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

        SECONDARY_SHUSHING = new ArrayList<>(Arrays.asList(
                WRSounds.ENTITY_LDWYRM_IDLE.get(),
                WRSounds.ENTITY_COINDRAGON_IDLE.get()
        ));


        if (WRPatchConfig.alpineMult.get() != 1) {
            List<SoundEvent> ALPINE_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_ALPINE_IDLE.get(),
                    WRSounds.ENTITY_ALPINE_DEATH.get(),
                    WRSounds.ENTITY_ALPINE_HURT.get(),
                    WRSounds.ENTITY_ALPINE_ROAR.get()));
            MODIFIED_SPECIES.put(ALPINE_SOUNDS, WRPatchConfig.alpineMult.get());
        }

        if (WRPatchConfig.royalredMult.get() != 1) {
            List<SoundEvent> ROYALRED_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_ROYALRED_IDLE.get(),
                    WRSounds.ENTITY_ROYALRED_DEATH.get(),
                    WRSounds.ENTITY_ROYALRED_HURT.get(),
                    WRSounds.ENTITY_ROYALRED_ROAR.get()));
            MODIFIED_SPECIES.put(ROYALRED_SOUNDS, WRPatchConfig.royalredMult.get());
        }

        if (WRPatchConfig.rooststalkerMult.get() != 1 || WRPatchConfig.monoResourcePackMode.get()) {
            List<SoundEvent> ROOSTSTALKER_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_STALKER_IDLE.get(),
                    WRSounds.ENTITY_STALKER_DEATH.get(),
                    WRSounds.ENTITY_STALKER_HURT.get()));
            double monoCompensationMultiplier = WRPatchConfig.monoResourcePackMode.get() ? 2.0 : 1.0;
            MODIFIED_SPECIES.put(ROOSTSTALKER_SOUNDS, WRPatchConfig.rooststalkerMult.get() * monoCompensationMultiplier);
        }

        if (WRPatchConfig.butterflyleviathanMult.get() != 1 || WRPatchConfig.monoResourcePackMode.get()) {
            List<SoundEvent> BFL_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_BFLY_IDLE.get(),
                    WRSounds.ENTITY_BFLY_DEATH.get(),
                    WRSounds.ENTITY_BFLY_ROAR.get(),
                    WRSounds.ENTITY_BFLY_HURT.get()));
            double monoCompensationMultiplier = WRPatchConfig.monoResourcePackMode.get() ? 5.0 : 1.0;
            MODIFIED_SPECIES.put(BFL_SOUNDS, WRPatchConfig.butterflyleviathanMult.get() * monoCompensationMultiplier);
        }

        if (WRPatchConfig.silvergliderMult.get() != 1 || WRPatchConfig.monoResourcePackMode.get()) {
            List<SoundEvent> GLIDER_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_SILVERGLIDER_DEATH.get(),
                    WRSounds.ENTITY_SILVERGLIDER_HURT.get(),
                    WRSounds.ENTITY_SILVERGLIDER_IDLE.get()));
            double monoCompensationMultiplier = WRPatchConfig.monoResourcePackMode.get() ? 2.0 : 1.0;
            MODIFIED_SPECIES.put(GLIDER_SOUNDS, WRPatchConfig.silvergliderMult.get() * monoCompensationMultiplier);
        }

        if (WRPatchConfig.owdrakeMult.get() != 1 || WRPatchConfig.monoResourcePackMode.get()) {
            List<SoundEvent> OWDRAKE_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_OWDRAKE_DEATH.get(),
                    WRSounds.ENTITY_OWDRAKE_HURT.get(),
                    WRSounds.ENTITY_OWDRAKE_ROAR.get(),
                    WRSounds.ENTITY_OWDRAKE_IDLE.get()));
            double monoCompensationMultiplier = WRPatchConfig.monoResourcePackMode.get() ? 2.0 : 1.0;
            MODIFIED_SPECIES.put(OWDRAKE_SOUNDS, WRPatchConfig.owdrakeMult.get() * monoCompensationMultiplier);
        }

        if (WRPatchConfig.canariwyvernMult.get() != 1) {
            List<SoundEvent> CANARI_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_CANARI_DEATH.get(),
                    WRSounds.ENTITY_CANARI_HURT.get(),
                    WRSounds.ENTITY_CANARI_IDLE.get()));
            MODIFIED_SPECIES.put(CANARI_SOUNDS, WRPatchConfig.canariwyvernMult.get());
        }

        if (WRPatchConfig.dragonfruitMult.get() != 1) {
            List<SoundEvent> DFD_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_DFD_DEATH.get(),
                    WRSounds.ENTITY_DFD_HURT.get(),
                    WRSounds.ENTITY_DFD_IDLE.get()));
            MODIFIED_SPECIES.put(DFD_SOUNDS, WRPatchConfig.dragonfruitMult.get());
        }

        if (WRPatchConfig.desertwyrmMult.get() != 1 || WRPatchConfig.monoResourcePackMode.get()) {
            List<SoundEvent> DESERTWYRM_SOUNDS = new ArrayList<>(Collections.singletonList(
                    WRSounds.ENTITY_LDWYRM_IDLE.get()));
            double monoCompensationMultiplier = WRPatchConfig.monoResourcePackMode.get() ? 2.0 : 1.0;
            MODIFIED_SPECIES.put(DESERTWYRM_SOUNDS, WRPatchConfig.desertwyrmMult.get() * monoCompensationMultiplier);
        }

        if (WRPatchConfig.coindragonMult.get() != 1) {
            List<SoundEvent> COIN_SOUNDS = new ArrayList<>(Collections.singletonList(
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

        if (WRPatchConfig.idleCancel.get() != 0) {
            IDLES = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_ALPINE_IDLE.get(),
                    WRSounds.ENTITY_COINDRAGON_IDLE.get(),
                    WRSounds.ENTITY_DFD_IDLE.get(),
                    WRSounds.ENTITY_LDWYRM_IDLE.get(),
                    WRSounds.ENTITY_CANARI_IDLE.get(),
                    WRSounds.ENTITY_OWDRAKE_IDLE.get(),
                    WRSounds.ENTITY_SILVERGLIDER_IDLE.get(),
                    WRSounds.ENTITY_STALKER_IDLE.get(),
                    WRSounds.ENTITY_ROYALRED_IDLE.get(),
                    WRSounds.ENTITY_BFLY_IDLE.get()));
        }
    }

    public static void shush(PlaySoundAtEntityEvent event) {

        if (SECONDARY_SHUSHING.contains(event.getSound())) {
            event.setVolume((float) (event.getVolume() * calculateMultiplier(event.getSound())));
        }

    }

//    //Doesn't work unfortunately
//    public static void shushRR(PlaySoundEvent event) {
////        if (SECONDARY_SHUSHES.containsKey(event.getSound())) {
//////            double volume = SECONDARY_SHUSHES.get(event.getSound()) * calculateMultiplier(getSound(event.getSound()));
////            //REPLACE WITH CORRECT SOUND
////
////
//////            SimpleSound replacement = new SimpleSound(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation(), SoundCategory.NEUTRAL, (float) (sound.getVolume() * 0.1), sound.getPitch(), sound.isLooping(), sound.getDelay(), sound.getAttenuation(), sound.getX(), sound.getY(), sound.getZ(), sound.isRelative());
////        }
//        if (event.getSound().getLocation().equals(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation())) {
//            WyrmroostPatch.LOGGER.info("Detected Royal Red Roar");
//            ISound sound = event.getSound();
////            SimpleSound replacement = new SimpleSound(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation(), SoundCategory.NEUTRAL, (float) (sound.getVolume() * calculateMultiplier(WRSounds.ENTITY_ROYALRED_ROAR.get())), sound.getPitch(), sound.isLooping(), sound.getDelay(), sound.getAttenuation(), sound.getX(), sound.getY(), sound.getZ(), sound.isRelative());
////            SimpleSound replacement = new SimpleSound(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation(), SoundCategory.NEUTRAL, (float) (sound.getVolume() * 0.1), sound.getPitch(), sound.isLooping(), sound.getDelay(), sound.getAttenuation(), sound.getX(), sound.getY(), sound.getZ(), sound.isRelative());
////            event.setResultSound(replacement);
//        }
//        else if (event.getSound().getLocation().equals(WRSounds.ENTITY_BFLY_HURT.get().getLocation())) {
//            WyrmroostPatch.LOGGER.info("Detected BFL roar, location: " + event.getSound().getLocation());
//            WyrmroostPatch.LOGGER.info("Sound data: " + event.getSound());
//            if (event.getSound() instanceof SimpleSound) {
//                WyrmroostPatch.LOGGER.info("It's a simple sound");
//            }
//            WyrmroostPatch.LOGGER.info("Sound's sound: " + event.getSound().getSound());
////            WyrmroostPatch.LOGGER.info("Sound's sound's volume: " + event.getSound().getSound().getVolume());
//
//            WyrmroostPatch.LOGGER.info("ResultSound data: " + event.getSound());
//            if (event.getResultSound() instanceof SimpleSound) {
//                WyrmroostPatch.LOGGER.info("It's a simple sound");
//            }
//            WyrmroostPatch.LOGGER.info("ResultSound's sound: " + event.getResultSound().getSound());
//
//        }
//    }


    public static double calculateMultiplier(SoundEvent sound) {
        if (sound == null) {
            return 1.0;
        }

        Random random = new Random();
        if (WRPatchConfig.idleCancel.get() != 0 && IDLES.contains(sound) && random.nextDouble() < WRPatchConfig.idleCancel.get()) {
            return 0.0;
        }

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
        if (sound.equals(WRSounds.WING_FLAP.get())) {
            species = WRPatchConfig.flapMult.get() * (WRPatchConfig.monoResourcePackMode.get() ? 2.0 : 1.0);
        }
        if (sound.equals(WRSounds.FIRE_BREATH.get())) {
            species = WRPatchConfig.fireMult.get();
        }

//        WyrmroostPatch.LOGGER.info("Set sound multiplier for " + sound.getLocation() + " to: " + global * species * roar);
        return global * species * roar;
    }


}
