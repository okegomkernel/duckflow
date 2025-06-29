/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.class_3695
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_757
 *  net.minecraft.class_761
 *  net.minecraft.class_7833
 *  net.minecraft.class_9779
 *  net.minecraft.class_9922
 *  org.joml.Matrix4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.duckflow.event.impl.Render3DEvent;
import com.duckflow.util.traits.Util;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_3695;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_7833;
import net.minecraft.class_9779;
import net.minecraft.class_9922;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_761.class})
public class MixinWorldRenderer {
    @Inject(method={"render"}, at={@At(value="RETURN")})
    private void render(class_9922 allocator, class_9779 tickCounter, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, Matrix4f positionMatrix, Matrix4f projectionMatrix, CallbackInfo ci, @Local class_3695 profiler) {
        class_4587 stack = new class_4587();
        stack.method_22903();
        stack.method_22907(class_7833.field_40714.rotationDegrees(Util.mc.field_1773.method_19418().method_19329()));
        stack.method_22907(class_7833.field_40716.rotationDegrees(Util.mc.field_1773.method_19418().method_19330() + 180.0f));
        profiler.method_15396("duckflow-render-3d");
        Render3DEvent event = new Render3DEvent(stack, tickCounter.method_60637(true));
        Util.EVENT_BUS.post((Object)event);
        stack.method_22909();
        profiler.method_15407();
    }
}
