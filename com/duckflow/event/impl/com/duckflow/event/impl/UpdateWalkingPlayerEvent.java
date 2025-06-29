/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;
import com.duckflow.event.Stage;

public class UpdateWalkingPlayerEvent
extends Event {
    private final Stage stage;

    public UpdateWalkingPlayerEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }
}
