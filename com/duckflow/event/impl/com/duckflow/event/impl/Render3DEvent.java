/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4587
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;
import net.minecraft.class_4587;

public class Render3DEvent
extends Event {
    private final class_4587 matrix;
    private final float delta;

    public Render3DEvent(class_4587 matrix, float delta) {
        this.matrix = matrix;
        this.delta = delta;
    }

    public class_4587 getMatrix() {
        return this.matrix;
    }

    public float getDelta() {
        return this.delta;
    }
}
