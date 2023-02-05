package syric.wyrmroostpatch.mixin;

import com.github.wolfshotz.wyrmroost.entities.dragon.AbstractDragonEntity;
import com.github.wolfshotz.wyrmroost.entities.dragon.RoostStalkerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import syric.wyrmroostpatch.WRPatchConfig;
import syric.wyrmroostpatch.shushing.ShushingCommon;

@Mixin(AbstractDragonEntity.class)
public class MixinAbstractDragonEntity {

    @Redirect(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At(
            value = "INVOKE",
            target = "Lcom/github/wolfshotz/wyrmroost/entities/dragon/AbstractDragonEntity;getSoundVolume()F"))
    public float getSoundVolume(AbstractDragonEntity instance, SoundEvent sound) {

        float multiplier = WRPatchConfig.enableShushSv.get() ? (float) ShushingCommon.calculateMultiplier(sound, true) : 1.0F;

        if (instance instanceof RoostStalkerEntity) {
            return 0.8F * multiplier;
        }
        return multiplier;
    }

//    @ModifyVariable(method="playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
//    private boolean switcheroo(boolean local) {
//        return !WRPatchConfig.enableShush.get() && local;
//    }

    @Redirect(method = "playSound(Lnet/minecraft/util/SoundEvent;FFZ)V", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;playLocalSound(DDDLnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FFZ)V"))
    public void playLocalSound(World instance, double getX, double getY, double getZ, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean delayed) {
        if (WRPatchConfig.enableShush.get()) {
            instance.playSound(null, getX, getY, getZ, sound, category, volume, pitch);
        } else {
            instance.playLocalSound(getX, getY, getZ, sound, category, volume, pitch, delayed);
        }
    }

}
