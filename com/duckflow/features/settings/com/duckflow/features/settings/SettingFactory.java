/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.settings;

import com.duckflow.features.settings.Bind;
import com.duckflow.features.settings.Setting;

public interface SettingFactory {
    public <T extends Setting<?>> T register(T var1);

    default public Setting<Boolean> bool(String name, boolean value) {
        return this.register(new Setting<Boolean>(name, value));
    }

    default public <T extends Number> Setting<T> num(String name, T value, T min, T max) {
        return this.register(new Setting<T>(name, value, min, max));
    }

    default public Setting<String> str(String name, String value) {
        return this.register(new Setting<String>(name, value));
    }

    default public <T extends Enum<?>> Setting<T> mode(String name, T value) {
        return this.register(new Setting<T>(name, value));
    }

    default public Setting<Bind> key(String name, Bind bind) {
        return this.register(new Setting<Bind>(name, bind));
    }
}
