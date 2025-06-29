/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  net.fabricmc.loader.api.FabricLoader
 */
package com.duckflow.manager;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.Feature;
import com.duckflow.features.settings.Bind;
import com.duckflow.features.settings.EnumConverter;
import com.duckflow.features.settings.Setting;
import com.duckflow.util.traits.Jsonable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigManager {
    private static final Path DuckFlow_PATH = FabricLoader.getInstance().getGameDir().resolve("DuckFlow");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final List<Jsonable> jsonables = List.of(DuckFlowClient.friendManager, DuckFlowClient.moduleManager, DuckFlowClient.commandManager);

    public static void setValueFromJson(Feature feature, Setting setting, JsonElement element) {
        switch (setting.getType()) {
            case "Boolean": {
                setting.setValue(element.getAsBoolean());
                break;
            }
            case "Double": {
                setting.setValue(element.getAsDouble());
                break;
            }
            case "Float": {
                setting.setValue(Float.valueOf(element.getAsFloat()));
                break;
            }
            case "Integer": {
                setting.setValue(element.getAsInt());
                break;
            }
            case "String": {
                String str = element.getAsString();
                setting.setValue(str.replace("_", " "));
                break;
            }
            case "Bind": {
                setting.setValue(new Bind(element.getAsInt()));
                break;
            }
            case "Enum": {
                try {
                    EnumConverter converter = new EnumConverter(((Enum)setting.getValue()).getClass());
                    Enum value = converter.doBackward(element);
                    setting.setValue(value == null ? setting.getDefaultValue() : value);
                }
                catch (Exception exception) {}
                break;
            }
            default: {
                DuckFlowClient.LOGGER.error("Unknown Setting type for: " + feature.getName() + " : " + setting.getName());
            }
        }
    }

    public void load() {
        if (!DuckFlow_PATH.toFile().exists()) {
            DuckFlow_PATH.toFile().mkdirs();
        }
        for (Jsonable jsonable : this.jsonables) {
            try {
                String read = Files.readString(DuckFlow_PATH.resolve(jsonable.getFileName()));
                jsonable.fromJson(JsonParser.parseString((String)read));
            }
            catch (Throwable e) {
                e.printStackTrace();
            }
        }
        DuckFlowClient.prefixManager.load();
    }

    public void save() {
        if (!DuckFlow_PATH.toFile().exists()) {
            DuckFlow_PATH.toFile().mkdirs();
        }
        for (Jsonable jsonable : this.jsonables) {
            try {
                JsonElement json = jsonable.toJson();
                Files.writeString(DuckFlow_PATH.resolve(jsonable.getFileName()), (CharSequence)gson.toJson(json), new OpenOption[0]);
            }
            catch (Throwable throwable) {}
        }
    }
}
