/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;

public class KeyEvent
extends Event {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
