/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.class_2535
 *  net.minecraft.class_2596
 *  net.minecraft.class_2598
 *  net.minecraft.class_7648
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.duckflow.event.impl.PacketEvent;
import com.duckflow.util.traits.Util;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.class_2535;
import net.minecraft.class_2596;
import net.minecraft.class_2598;
import net.minecraft.class_7648;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2535.class})
public class MixinClientConnection {
    @Shadow
    private Channel field_11651;
    @Shadow
    @Final
    private class_2598 field_11643;

    @Inject(method={"channelRead0"}, at={@At(value="HEAD")}, cancellable=true)
    public void channelRead0(ChannelHandlerContext chc, class_2596<?> packet, CallbackInfo ci) {
        if (this.field_11651.isOpen() && packet != null) {
            try {
                PacketEvent.Receive event = new PacketEvent.Receive(packet);
                Util.EVENT_BUS.post((Object)event);
                if (event.isCancelled()) {
                    ci.cancel();
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Inject(method={"sendImmediately"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendImmediately(class_2596<?> packet, class_7648 callbacks, boolean flush, CallbackInfo ci) {
        if (this.field_11643 != class_2598.field_11942) {
            return;
        }
        try {
            PacketEvent.Send event = new PacketEvent.Send(packet);
            Util.EVENT_BUS.post((Object)event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}
