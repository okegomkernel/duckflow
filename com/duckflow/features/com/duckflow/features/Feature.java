/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features;

import com.duckflow.features.settings.Setting;
import com.duckflow.features.settings.SettingFactory;
import com.duckflow.util.traits.Util;
import java.util.ArrayList;
import java.util.List;

public class Feature
implements Util,
SettingFactory {
    public List<Setting<?>> settings = new ArrayList();
    private String name;

    public Feature() {
    }

    public Feature(String name) {
        this.name = name;
    }

    public static boolean nullCheck() {
        return Feature.mc.field_1724 == null;
    }

    public static boolean fullNullCheck() {
        return Feature.mc.field_1724 == null || Feature.mc.field_1687 == null;
    }

    public String getName() {
        return this.name;
    }

    public List<Setting<?>> getSettings() {
        return this.settings;
    }

    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }

    public boolean isEnabled() {
        return false;
    }

    public boolean isDisabled() {
        return !this.isEnabled();
    }

    @Override
    public <T extends Setting<?>> T register(T setting) {
        setting.setFeature(this);
        this.settings.add(setting);
        return setting;
    }

    public Setting<?> getSettingByName(String name) {
        for (Setting<?> setting : this.settings) {
            if (!setting.getName().equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public void reset() {
        for (Setting<?> setting : this.settings) {
            setting.reset();
        }
    }

    public void clearSettings() {
        this.settings = new ArrayList();
    }
}
