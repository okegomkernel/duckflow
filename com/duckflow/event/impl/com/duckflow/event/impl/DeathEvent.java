/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;
import net.minecraft.class_1309;

public class DeathEvent
extends Event {
    private final class_1309 entity;

    public DeathEvent(class_1309 entity) {
        this.entity = entity;
    }

    public class_1309 getEntity() {
        return this.entity;
    }
}
