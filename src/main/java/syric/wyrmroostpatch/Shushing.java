package syric.wyrmroostpatch;

import com.github.wolfshotz.wyrmroost.registry.WRSounds;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class Shushing {

    public static void shush(PlaySoundAtEntityEvent event) {
        DeferredRegister<SoundEvent> wrreg = WRSounds.REGISTRY;
        for (RegistryObject<SoundEvent> reg : wrreg.getEntries()) {
            if (event.getSound().equals(reg.get())) {
                event.setVolume(event.getVolume() * calculateMultiplier(event.getSound()));
            }
        }
    }

    private static float calculateMultiplier(SoundEvent sound) {
        return 0.1F;
    }


}
