/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Converter
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 *  net.minecraft.class_3675
 */
package com.duckflow.features.settings;

import com.duckflow.features.settings.Bind;
import com.google.common.base.Converter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.class_3675;

public static class Bind.BindConverter
extends Converter<Bind, JsonElement> {
    public JsonElement doForward(Bind bind) {
        return new JsonPrimitive(bind.toString());
    }

    public Bind doBackward(JsonElement jsonElement) {
        String s = jsonElement.getAsString();
        if (s.equalsIgnoreCase("None")) {
            return Bind.none();
        }
        int key = -1;
        try {
            key = class_3675.method_15981((String)s.toUpperCase()).method_1444();
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (key == 0) {
            return Bind.none();
        }
        return new Bind(key);
    }
}
