package net.fabricmc.BoundlessTranslation.mixin;

import net.minecraft.text.StringVisitable;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TranslatableText.class)
public class BoundlessTranslation
{
    @Shadow @Final private static StringVisitable NULL_ARGUMENT;

    @Inject(method = "getArg", at = @At("HEAD"), cancellable = true)
    public void fixCrashExploit(int index, CallbackInfoReturnable<StringVisitable> cir)
    {
        if (index < 0)
        {
            cir.setReturnValue(NULL_ARGUMENT);
        }
    }
}