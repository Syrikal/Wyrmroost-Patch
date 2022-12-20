package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.registry.WREntities;
import com.github.wolfshotz.wyrmroost.registry.WRSounds;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.GL;

import java.util.*;

import static syric.wyrmroostpatch.Util.chatPrint;

public class Shushing {

    private static List<SoundEvent> ALL_SOUNDS;

    private static final Map<List<SoundEvent>, Double> MODIFIED_SPECIES = new HashMap<List<SoundEvent>, Double>();

    private static List<SoundEvent> ROARS;
    private static List<SoundEvent> IDLES;

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

        if (WRPatchConfig.rooststalkerMult.get() != 1) {
            List<SoundEvent> ROOSTSTALKER_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_STALKER_IDLE.get(),
                    WRSounds.ENTITY_STALKER_DEATH.get(),
                    WRSounds.ENTITY_STALKER_HURT.get()));
            MODIFIED_SPECIES.put(ROOSTSTALKER_SOUNDS, WRPatchConfig.rooststalkerMult.get());
        }

        if (WRPatchConfig.butterflyleviathanMult.get() != 1) {
            List<SoundEvent> BFL_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_BFLY_IDLE.get(),
                    WRSounds.ENTITY_BFLY_DEATH.get(),
                    WRSounds.ENTITY_BFLY_ROAR.get(),
                    WRSounds.ENTITY_BFLY_HURT.get()));
            MODIFIED_SPECIES.put(BFL_SOUNDS, WRPatchConfig.butterflyleviathanMult.get());
        }

        if (WRPatchConfig.silvergliderMult.get() != 1) {
            List<SoundEvent> GLIDER_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_SILVERGLIDER_DEATH.get(),
                    WRSounds.ENTITY_SILVERGLIDER_HURT.get(),
                    WRSounds.ENTITY_SILVERGLIDER_IDLE.get()));
            MODIFIED_SPECIES.put(GLIDER_SOUNDS, WRPatchConfig.silvergliderMult.get());
        }

        if (WRPatchConfig.owdrakeMult.get() != 1) {
            List<SoundEvent> OWDRAKE_SOUNDS = new ArrayList<>(Arrays.asList(
                    WRSounds.ENTITY_OWDRAKE_DEATH.get(),
                    WRSounds.ENTITY_OWDRAKE_HURT.get(),
                    WRSounds.ENTITY_OWDRAKE_ROAR.get(),
                    WRSounds.ENTITY_OWDRAKE_IDLE.get()));
            MODIFIED_SPECIES.put(OWDRAKE_SOUNDS, WRPatchConfig.owdrakeMult.get());
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

        if (WRPatchConfig.desertwyrmMult.get() != 1) {
            List<SoundEvent> DESERTWYRM_SOUNDS = new ArrayList<>(Collections.singletonList(
                    WRSounds.ENTITY_LDWYRM_IDLE.get()));
            MODIFIED_SPECIES.put(DESERTWYRM_SOUNDS, WRPatchConfig.desertwyrmMult.get());
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
        //Alt faster version that doesn't account for some species (ldw, coin dragon)
//        if (!(event.getEntity() instanceof AbstractDragonEntity)) {
//            return;
//        }
        if (ALL_SOUNDS.contains(event.getSound())) {
            event.setVolume((float) (event.getVolume() * calculateMultiplier(event.getSound())));
            if (event.getSound().equals(WRSounds.ENTITY_ROYALRED_ROAR.get())) {
                chatPrint("royal red roar", event.getEntity().level);
            }
        }
    }

    //Doesn't work unfortunately
    public static void shushRR(PlaySoundEvent event) {
        if (!event.getSound().getLocation().equals(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation())) {
            return;
        } else {
            ISound sound = event.getSound();
//            SimpleSound replacement = new SimpleSound(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation(), SoundCategory.NEUTRAL, (float) (sound.getVolume() * calculateMultiplier(WRSounds.ENTITY_ROYALRED_ROAR.get())), sound.getPitch(), sound.isLooping(), sound.getDelay(), sound.getAttenuation(), sound.getX(), sound.getY(), sound.getZ(), sound.isRelative());
            SimpleSound replacement = new SimpleSound(WRSounds.ENTITY_ROYALRED_ROAR.get().getLocation(), SoundCategory.NEUTRAL, (float) (sound.getVolume() * 0.1), sound.getPitch(), sound.isLooping(), sound.getDelay(), sound.getAttenuation(), sound.getX(), sound.getY(), sound.getZ(), sound.isRelative());
            event.setResultSound(replacement);
        }
    }


    private static Double calculateMultiplier(SoundEvent sound) {
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

        return global * species * roar;
    }


}
