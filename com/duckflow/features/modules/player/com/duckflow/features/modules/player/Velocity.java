/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2664
 *  net.minecraft.class_2743
 */
package com.duckflow.features.modules.player;

import com.duckflow.event.impl.PacketEvent;
import com.duckflow.features.modules.Module;
import com.google.common.eventbus.Subscribe;
import net.minecraft.class_2664;
import net.minecraft.class_2743;

public class Velocity
extends Module {
    public Velocity() {
        super("Velocity", "Removes velocity from explosions and entities", Module.Category.PLAYER, true, false, false);
    }

    @Subscribe
    private void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2743 || event.getPacket() instanceof class_2664) {
            event.cancel();
        }
    }
}
