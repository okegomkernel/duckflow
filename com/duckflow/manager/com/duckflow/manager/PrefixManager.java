/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.reflect.TypeToken
 *  net.fabricmc.loader.api.FabricLoader
 */
package com.duckflow.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.loader.api.FabricLoader;

public class PrefixManager {
    private static final Path PREFIXES_PATH = FabricLoader.getInstance().getGameDir().resolve("DuckFlow/prefixes.json");
    private static final Gson gson = new Gson();
    private List<String> prefixes = new ArrayList<String>(List.of("/", ";"));

    public List<String> getPrefixes() {
        return this.prefixes;
    }

    public void load() {
        try {
            if (Files.exists(PREFIXES_PATH, new LinkOption[0])) {
                String content = Files.readString(PREFIXES_PATH);
                Type type = new TypeToken<List<String>>(this){}.getType();
                this.prefixes = (List)gson.fromJson(content, type);
            } else {
                this.save();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Files.createDirectories(PREFIXES_PATH.getParent(), new FileAttribute[0]);
            Files.writeString(PREFIXES_PATH, (CharSequence)gson.toJson(this.prefixes), new OpenOption[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
