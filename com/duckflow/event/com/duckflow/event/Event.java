/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.event;

public class Event {
    private boolean cancelled;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }
}
