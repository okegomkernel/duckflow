/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;
import net.minecraft.class_332;

public class Render2DEvent
extends Event {
    private final class_332 context;
    private final float delta;

    public Render2DEvent(class_332 context, float delta) {
        this.context = context;
        this.delta = delta;
    }

    public class_332 getContext() {
        return this.context;
    }

    public float getDelta() {
        return this.delta;
    }
}
