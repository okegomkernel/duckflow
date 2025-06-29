/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_310
 *  net.minecraft.class_3709
 *  net.minecraft.class_3965
 */
package com.duckflow.features.modules.client;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_310;
import net.minecraft.class_3709;
import net.minecraft.class_3965;

public class OpenProtectionV1
extends Module {
    private final class_310 mc = class_310.method_1551();
    private final String kosWebhook = "https://discord.com/api/webhooks/1378386256089841735/LJOxPqaVV8WxFgtvqELe8tUF4rm03ljm97hZzfxNwsPlvnksjEiIbjDo6StFjffXVX7p";
    private final String actionWebhook = "https://discord.com/api/webhooks/1378386409421017139/c7Oy5TMFLV8tcEF-nenBPqo6bWjVHG2372mTjJeOBWg1FulA99RmfBFBPY5ZzSh4uzzg";
    private final String kosListUrl = "https://the-duck-union-website.pages.dev/api/v1/kos_list.json";
    private final String memberListUrl = "https://the-duck-union-website.pages.dev/api/v1/member_list.json";
    public Setting<Boolean> pingEveryone = this.bool("@everyone", true);
    public Setting<Boolean> logKosCoords = this.bool("Log KOS Coords", true);
    public Setting<Integer> kosMaxDistance = this.num("KOS Max Distance", 128, 5, 256);
    public Setting<Boolean> sameDimensionOnly = this.bool("Same Dimension Only", true);
    public Setting<Boolean> logEnter = this.bool("Log Enters", true);
    public Setting<Boolean> logLeave = this.bool("Log Leaves", true);
    public Setting<Boolean> logPlacements = this.bool("Log Placements", true);
    private final Map<String, String> kosPlayers = new HashMap<String, String>();
    private final Set<String> kosAnnounced = new HashSet<String>();
    private final Map<String, Long> kosLastPosLog = new HashMap<String, Long>();
    private final Map<String, Long> kosLastPing = new HashMap<String, Long>();
    private final Set<UUID> seenPlayers = new HashSet<UUID>();
    private final Map<UUID, String> playerNames = new HashMap<UUID, String>();
    private final Map<class_2338, class_2248> trackedBlocks = new HashMap<class_2338, class_2248>();
    private final Set<class_2248> trackedTypes = Set.of(class_2246.field_10375, class_2246.field_38420, class_2246.field_23152, class_2246.field_10164);
    private Set<String> duckUnionMembers = ConcurrentHashMap.newKeySet();

    public OpenProtectionV1() {
        super("Open Protection", "Strongest base protection ever 45b rad.", Module.Category.CLIENT, true, false, false);
        this.updateMemberList();
    }

    @Override
    public void onEnable() {
        this.kosAnnounced.clear();
        this.kosLastPosLog.clear();
        this.kosLastPing.clear();
        this.seenPlayers.clear();
        this.playerNames.clear();
        this.trackedBlocks.clear();
        this.loadKosList();
        this.updateMemberList();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        long now = System.currentTimeMillis();
        this.handleKosLogic(now);
        this.handleActionLogging();
    }

    private void handleKosLogic(long now) {
        for (class_1657 player : this.mc.field_1687.method_18456()) {
            String name;
            if (player == this.mc.field_1724 || !this.kosPlayers.containsKey(name = player.method_5477().getString()) || this.sameDimensionOnly.getValue().booleanValue() && !player.method_37908().method_27983().equals(this.mc.field_1687.method_27983()) || this.mc.field_1724.method_5858((class_1297)player) > (double)(this.kosMaxDistance.getValue() * this.kosMaxDistance.getValue())) continue;
            String reason = this.kosPlayers.get(name);
            if (!this.kosAnnounced.contains(name)) {
                this.sendWebhook("https://discord.com/api/webhooks/1378386256089841735/LJOxPqaVV8WxFgtvqELe8tUF4rm03ljm97hZzfxNwsPlvnksjEiIbjDo6StFjffXVX7p", this.buildKosMessage(name, reason, true, null));
                this.kosAnnounced.add(name);
                this.kosLastPosLog.put(name, now);
                this.kosLastPing.put(name, now);
            }
            if (this.logKosCoords.getValue().booleanValue() && now - this.kosLastPosLog.getOrDefault(name, 0L) >= 3000L) {
                String pos = this.formatPos(player.method_23317(), player.method_23318(), player.method_23321());
                this.sendWebhook("https://discord.com/api/webhooks/1378386256089841735/LJOxPqaVV8WxFgtvqELe8tUF4rm03ljm97hZzfxNwsPlvnksjEiIbjDo6StFjffXVX7p", this.buildKosMessage(name, null, false, pos));
                this.kosLastPosLog.put(name, now);
                this.interactWithNearestBell();
            }
            if (now - this.kosLastPing.getOrDefault(name, 0L) < 60000L) continue;
            this.sendWebhook("https://discord.com/api/webhooks/1378386256089841735/LJOxPqaVV8WxFgtvqELe8tUF4rm03ljm97hZzfxNwsPlvnksjEiIbjDo6StFjffXVX7p", this.buildKosMessage(name, reason, true, null));
            this.kosLastPing.put(name, now);
        }
    }

    private void handleActionLogging() {
        if (this.logEnter.getValue().booleanValue()) {
            for (class_1657 player : this.mc.field_1687.method_18456()) {
                if (player == this.mc.field_1724) continue;
                UUID uuid = player.method_5667();
                String name = player.method_5477().getString();
                if (!this.seenPlayers.add(uuid)) continue;
                this.playerNames.put(uuid, name);
                if (!this.duckUnionMembers.contains(name)) {
                    this.sendWebhook("https://discord.com/api/webhooks/1378386409421017139/c7Oy5TMFLV8tcEF-nenBPqo6bWjVHG2372mTjJeOBWg1FulA99RmfBFBPY5ZzSh4uzzg", "@here \u26a0\ufe0f **Unknown player entered the base: `" + name + "`**");
                    continue;
                }
                this.sendWebhook("https://discord.com/api/webhooks/1378386409421017139/c7Oy5TMFLV8tcEF-nenBPqo6bWjVHG2372mTjJeOBWg1FulA99RmfBFBPY5ZzSh4uzzg", "Info: `**" + name + "** entered the base.`");
            }
        }
        if (this.logLeave.getValue().booleanValue()) {
            for (UUID uuid : new HashSet<UUID>(this.seenPlayers)) {
                if (this.mc.field_1687.method_18470(uuid) != null) continue;
                String name = this.playerNames.getOrDefault(uuid, uuid.toString());
                this.sendWebhook("https://discord.com/api/webhooks/1378386409421017139/c7Oy5TMFLV8tcEF-nenBPqo6bWjVHG2372mTjJeOBWg1FulA99RmfBFBPY5ZzSh4uzzg", "Info: `**" + name + "** left the base.`");
                this.seenPlayers.remove(uuid);
                this.playerNames.remove(uuid);
            }
        }
        if (this.logPlacements.getValue().booleanValue()) {
            for (class_2338 pos : class_2338.method_25996((class_2338)this.mc.field_1724.method_24515(), (int)10, (int)5, (int)10)) {
                class_2248 block = this.mc.field_1687.method_8320(pos).method_26204();
                if (this.trackedTypes.contains(block)) {
                    if (this.trackedBlocks.containsKey(pos)) continue;
                    this.trackedBlocks.put(pos.method_10062(), block);
                    this.sendWebhook("https://discord.com/api/webhooks/1378386409421017139/c7Oy5TMFLV8tcEF-nenBPqo6bWjVHG2372mTjJeOBWg1FulA99RmfBFBPY5ZzSh4uzzg", String.format("Warn: `%s placed at %s`", block.method_9518().getString(), this.formatPos(pos)));
                    continue;
                }
                this.trackedBlocks.remove(pos);
            }
        }
    }

    private String buildKosMessage(String name, String reason, boolean ping, String coords) {
        if (coords != null) {
            return String.format("URGENT: `%s (%s) is at %s`", name, reason, coords);
        }
        String pingText = ping ? (this.pingEveryone.getValue().booleanValue() ? "@everyone" : "@here") : "";
        String reasonText = reason != null && !reason.isEmpty() ? "KOS Reason: *" + reason + "*." : "";
        return String.format("%s MAJOR WARN: `**%s** has came close to base!` %s", pingText, name, reasonText.isEmpty() ? "" : " " + reasonText).trim();
    }

    private void sendWebhook(String webhook, String content) {
        new Thread(() -> {
            try {
                URL url = new URL(webhook);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                String jsonPayload = String.format("{\"content\":\"%s\"}", content);
                try (OutputStream os = connection.getOutputStream();){
                    os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
                }
                connection.getInputStream().close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void loadKosList() {
        new Thread(() -> {
            try {
                String line;
                URL url = new URL("https://the-duck-union-website.pages.dev/api/v1/kos_list.json");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String json = response.toString();
                int start = json.indexOf("{", json.indexOf("kos_players"));
                int end = json.lastIndexOf("}");
                if (start == -1 || end == -1 || end <= start) {
                    return;
                }
                String kosBlock = json.substring(start + 1, end).trim();
                this.kosPlayers.clear();
                for (String entry : kosBlock.split(",")) {
                    String[] kv = entry.split(":", 2);
                    if (kv.length != 2) continue;
                    String playerName = kv[0].replaceAll("[\"{}]", "").trim();
                    String reason = kv[1].replaceAll("[\"{}]", "").trim();
                    this.kosPlayers.put(playerName, reason);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateMemberList() {
        new Thread(() -> {
            try {
                URL url = new URL("https://the-duck-union-website.pages.dev/api/v1/member_list.json");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                JsonObject root = JsonParser.parseReader((Reader)in).getAsJsonObject();
                in.close();
                this.duckUnionMembers.clear();
                for (String category : List.of("founders", "members")) {
                    if (!root.has(category) || !root.get(category).isJsonObject()) continue;
                    JsonObject group = root.getAsJsonObject(category);
                    for (Map.Entry entry : group.entrySet()) {
                        if (!((JsonElement)entry.getValue()).isJsonPrimitive()) continue;
                        this.duckUnionMembers.add(((JsonElement)entry.getValue()).getAsString());
                    }
                }
            }
            catch (Exception e) {
                System.err.println("Failed to fetch Duck Union member list:");
                e.printStackTrace();
            }
        }).start();
    }

    private void interactWithNearestBell() {
        this.mc.execute(() -> {
            class_2338 nearest = null;
            double minDistSq = Double.MAX_VALUE;
            class_2338 playerPos = this.mc.field_1724.method_24515();
            for (class_2338 pos : class_2338.method_25996((class_2338)playerPos, (int)5, (int)5, (int)5)) {
                double dz;
                double dy;
                double dx;
                double distSq;
                if (!(this.mc.field_1687.method_8320(pos).method_26204() instanceof class_3709) || !((distSq = (dx = (double)pos.method_10263() + 0.5 - this.mc.field_1724.method_23317()) * dx + (dy = (double)pos.method_10264() + 0.5 - this.mc.field_1724.method_23318()) * dy + (dz = (double)pos.method_10260() + 0.5 - this.mc.field_1724.method_23321()) * dz) < minDistSq)) continue;
                minDistSq = distSq;
                nearest = pos;
            }
            if (nearest != null) {
                class_3965 hit = new class_3965(this.mc.field_1724.method_19538(), this.mc.field_1724.method_5735(), nearest, false);
                this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1724.method_6058(), hit);
            }
        });
    }

    private String formatPos(double x, double y, double z) {
        return String.format("X: %.1f, Y: %.1f, Z: %.1f", x, y, z);
    }

    private String formatPos(class_2338 pos) {
        return String.format("X: %d, Y: %d, Z: %d", pos.method_10263(), pos.method_10264(), pos.method_10260());
    }
}
