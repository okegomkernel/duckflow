/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;

public class ChatEvent
extends Event {
    private final String content;

    public ChatEvent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return this.content;
    }
}
