package syric.wyrmroostpatch.shushing;

import com.github.wolfshotz.wyrmroost.registry.WRSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import syric.wyrmroostpatch.WyrmroostPatch;

import static syric.wyrmroostpatch.Util.chatPrint;
import static syric.wyrmroostpatch.shushing.ShushingCommon.calculateMultiplier;

public class ShushingClient {

    public static void shush(PlaySoundAtEntityEvent event) {

//            WyrmroostPatch.LOGGER.info("ALL_SOUNDS: " + ShushingCommon.ALL_SOUNDS.size());
//            WyrmroostPatch.LOGGER.info("Event: " + event.toString());
//            WyrmroostPatch.LOGGER.info("Sound: " + event.getSound().getLocation());
            if (ShushingCommon.ALL_SOUNDS.contains(event.getSound())) {
                event.setVolume((float) (event.getVolume() * calculateMultiplier(event.getSound(), false)));
//                if (event.getSound().equals(WRSounds.ENTITY_ROYALRED_ROAR.get())) {
//                    WyrmroostPatch.LOGGER.info("Entity: " + event.getEntity().toString());
//                    WyrmroostPatch.LOGGER.info("Level: " + event.getEntity().level.toString());
//                    chatPrint("royal red roar", event.getEntity().level);
//                }
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


}
