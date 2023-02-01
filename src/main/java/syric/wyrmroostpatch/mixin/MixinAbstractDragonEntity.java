package syric.wyrmroostpatch.mixin;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragon.RoostStalkerEntity;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import syric.wyrmroostpatch.shushing.Shushing;

@Mixin(AbstractDragonEntity.class)
public class MixinAbstractDragonEntity {

//    @ModifyVariable(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true, remap = false)
//    private boolean notLocal(boolean local) {
//        WyrmroostPatch.LOGGER.info("Successfully set local to false");
//        return false;
//    }

    @Redirect(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At(
            value = "INVOKE",
            target = "Lcom/github/wolfshotz/wyrmroost/entities/dragon/AbstractDragonEntity;getSoundVolume()F"))
    public float getSoundVolume(AbstractDragonEntity instance, SoundEvent sound) {
//        WyrmroostPatch.LOGGER.info("Successfully triggered getSoundVolume redirect");
        if (instance instanceof RoostStalkerEntity) {
            return 0.8F * (float) Shushing.calculateMultiplier(sound);
        }
        return (float) Shushing.calculateMultiplier(sound);
    }

//    @Redirect(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At(
//            value = "INVOKE",
//            target = "Lnet/minecraft/world/World;playLocalSound(DDDLnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FFZ)V"))
//    public void replacementSound(World instance, double getX, double getY, double getZ, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean local) {
//        WyrmroostPatch.LOGGER.info("Successfully triggered playLocalSound redirect");
//        instance.playLocalSound(getX, getY, getZ, sound, category, volume, pitch, true);
//    }






//
//    @ModifyVariable(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", argsOnly = true, at = @At("HEAD"), ordinal = 1)
//    private float updateVolume(float value) {
//        return value *= this.
//    }
//
//
//
//    @Inject(method= "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At(value = "INVOKE_ASSIGN", target = "Lcom/github/wolfshotz/wyrmroost/entities/dragon/AbstractDragonEntity;getSoundVolume()F"))
//    public void updateVolume(SoundEvent sound, float volume, float pitch, boolean local, CallbackInfo ci) {
//        if (Shushing.ALL_SOUNDS.contains(sound)) {
//            volume *= Shushing.calculateMultiplier(sound);
//        }
//    }



}
