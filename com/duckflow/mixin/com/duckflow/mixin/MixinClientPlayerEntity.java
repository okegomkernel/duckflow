/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.duckflow.event.Stage;
import com.duckflow.event.impl.UpdateEvent;
import com.duckflow.event.impl.UpdateWalkingPlayerEvent;
import com.duckflow.util.traits.Util;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_746.class})
public class MixinClientPlayerEntity {
    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void tickHook(CallbackInfo ci) {
        Util.EVENT_BUS.post((Object)new UpdateEvent());
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", shift=At.Shift.AFTER)})
    private void tickHook2(CallbackInfo ci) {
        Util.EVENT_BUS.post((Object)new UpdateWalkingPlayerEvent(Stage.PRE));
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;sendMovementPackets()V", shift=At.Shift.AFTER)})
    private void tickHook3(CallbackInfo ci) {
        Util.EVENT_BUS.post((Object)new UpdateWalkingPlayerEvent(Stage.POST));
    }
}
