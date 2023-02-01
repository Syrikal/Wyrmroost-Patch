package syric.wyrmroostpatch.mixin;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragon.RoostStalkerEntity;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import syric.wyrmroostpatch.WRPatchConfig;
import syric.wyrmroostpatch.shushing.Shushing;

@Mixin(AbstractDragonEntity.class)
public class MixinAbstractDragonEntity {

    @Redirect(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At(
            value = "INVOKE",
            target = "Lcom/github/wolfshotz/wyrmroost/entities/dragon/AbstractDragonEntity;getSoundVolume()F"))
    public float getSoundVolume(AbstractDragonEntity instance, SoundEvent sound) {

        float multiplier = WRPatchConfig.enableShush.get() ? (float) Shushing.calculateMultiplier(sound) : 1.0F;

        if (instance instanceof RoostStalkerEntity) {
            return 0.8F * multiplier;
        }
        return multiplier;
    }

}
