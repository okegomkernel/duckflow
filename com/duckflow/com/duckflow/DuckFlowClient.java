/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.ModInitializer
 *  net.minecraft.class_155
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.duckflow;

import com.duckflow.manager.ColorManager;
import com.duckflow.manager.CommandManager;
import com.duckflow.manager.ConfigManager;
import com.duckflow.manager.EventManager;
import com.duckflow.manager.FriendManager;
import com.duckflow.manager.HoleManager;
import com.duckflow.manager.ModuleManager;
import com.duckflow.manager.PositionManager;
import com.duckflow.manager.PrefixManager;
import com.duckflow.manager.RotationManager;
import com.duckflow.manager.ServerManager;
import com.duckflow.manager.SpeedManager;
import com.duckflow.manager.SplashTextManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.stream.Collectors;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.class_155;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuckFlowClient
implements ModInitializer,
ClientModInitializer {
    public static final String NAME = "DuckFlow";
    public static final String VERSION = "1.0.0 - " + class_155.method_16673().method_48019();
    public static float TIMER = 1.0f;
    public static final Logger LOGGER = LogManager.getLogger((String)"DuckFlow");
    public static ServerManager serverManager;
    public static ColorManager colorManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static HoleManager holeManager;
    public static EventManager eventManager;
    public static SpeedManager speedManager;
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static PrefixManager prefixManager;
    public static SplashTextManager splashManager;

    public void onInitialize() {
        eventManager = new EventManager();
        serverManager = new ServerManager();
        rotationManager = new RotationManager();
        positionManager = new PositionManager();
        friendManager = new FriendManager();
        colorManager = new ColorManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        speedManager = new SpeedManager();
        holeManager = new HoleManager();
        prefixManager = new PrefixManager();
        splashManager = new SplashTextManager();
    }

    public void onInitializeClient() {
        LOGGER.info("Checking HWID for permissions!");
        try {
            String hwid = DuckFlowClient.generateHWID();
            LOGGER.info("HWID: " + hwid);
            URL url = new URL("https://the-duck-union-website.pages.dev/api/duckflow/hwids.json");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            if (conn.getResponseCode() != 200) {
                throw new IOException("Failed to fetch HWID list: HTTP " + conn.getResponseCode());
            }
            InputStream is = conn.getInputStream();
            String json = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
            JsonObject obj = JsonParser.parseString((String)json).getAsJsonObject();
            JsonArray hwids = obj.getAsJsonArray("allowed_hwids");
            boolean allowed = false;
            for (JsonElement element : hwids) {
                if (!element.getAsString().equalsIgnoreCase(hwid)) continue;
                allowed = true;
                break;
            }
            if (!allowed) {
                throw new SecurityException("HWID not authorized.");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unauthorized access. HWID check failed.");
        }
        LOGGER.info("Valid HWID, continuing!");
        eventManager.init();
        moduleManager.init();
        configManager = new ConfigManager();
        configManager.load();
        colorManager.init();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.save()));
        LOGGER.info("Welcome (back) to DuckFlow! [" + VERSION + "]");
    }

    public static String generateHWID() throws NoSuchAlgorithmException {
        String raw = System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.name");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}
