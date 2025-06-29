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

public class SplashTextManager {
    private static final Path SPLASHES_PATH = FabricLoader.getInstance().getGameDir().resolve("DuckFlow/splashes.json");
    private static final Gson gson = new Gson();
    private List<String> splashes = new ArrayList<String>(List.of("~ DuckFlow ~", "~ DuckyFlowing ~", "~ DuckSwim ~"));
    private static SplashTextManager instance;

    public SplashTextManager() {
        this.load();
    }

    public static SplashTextManager getInstance() {
        if (instance == null) {
            instance = new SplashTextManager();
        }
        return instance;
    }

    public List<String> getSplashes() {
        return this.splashes;
    }

    public void load() {
        try {
            if (Files.exists(SPLASHES_PATH, new LinkOption[0])) {
                Type type;
                String content = Files.readString(SPLASHES_PATH);
                List loaded = (List)gson.fromJson(content, type = new TypeToken<List<String>>(this){}.getType());
                if (loaded != null && !loaded.isEmpty()) {
                    this.splashes = loaded;
                }
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
            Files.createDirectories(SPLASHES_PATH.getParent(), new FileAttribute[0]);
            Files.writeString(SPLASHES_PATH, (CharSequence)gson.toJson(this.splashes), new OpenOption[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
