/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4008
 *  net.minecraft.class_8519
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.duckflow.mixin;

import com.duckflow.manager.SplashTextManager;
import java.util.List;
import java.util.Random;
import net.minecraft.class_4008;
import net.minecraft.class_8519;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4008.class})
public class SplashTextMixin {
    @Unique
    private int currentIndex = 0;
    @Unique
    private final List<String> splashes = SplashTextManager.getInstance().getSplashes();

    @Inject(method={"get"}, at={@At(value="HEAD")}, cancellable=true)
    private void onApply(CallbackInfoReturnable<class_8519> cir) {
        this.currentIndex = new Random().nextInt(this.splashes.size());
        cir.setReturnValue((Object)new class_8519(this.splashes.get(this.currentIndex)));
    }
}
