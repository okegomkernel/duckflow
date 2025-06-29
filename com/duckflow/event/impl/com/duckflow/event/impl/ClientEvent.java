/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.event.impl;

import com.duckflow.event.Event;
import com.duckflow.features.Feature;
import com.duckflow.features.settings.Setting;

public class ClientEvent
extends Event {
    private Feature feature;
    private Setting<?> setting;
    private int stage;

    public ClientEvent(int stage, Feature feature) {
        this.stage = stage;
        this.feature = feature;
    }

    public ClientEvent(Setting<?> setting) {
        this.stage = 2;
        this.setting = setting;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public Setting<?> getSetting() {
        return this.setting;
    }

    public int getStage() {
        return this.stage;
    }
}
