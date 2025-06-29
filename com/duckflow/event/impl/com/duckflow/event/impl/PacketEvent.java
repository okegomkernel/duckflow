/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;
import net.minecraft.class_2596;

public abstract class PacketEvent
extends Event {
    private final class_2596<?> packet;

    public PacketEvent(class_2596<?> packet) {
        this.packet = packet;
    }

    public class_2596<?> getPacket() {
        return this.packet;
    }

    public static class Send
    extends PacketEvent {
        public Send(class_2596<?> packet) {
            super(packet);
        }
    }

    public static class Receive
    extends PacketEvent {
        public Receive(class_2596<?> packet) {
            super(packet);
        }
    }
}
