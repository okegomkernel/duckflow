/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 */
package com.duckflow.util.traits;

import com.google.gson.JsonElement;

public interface Jsonable {
    public JsonElement toJson();

    public void fromJson(JsonElement var1);

    default public String getFileName() {
        return "";
    }
}
