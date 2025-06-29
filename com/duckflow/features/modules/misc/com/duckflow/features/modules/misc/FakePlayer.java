/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1799
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_638
 *  net.minecraft.class_745
 */
package com.duckflow.features.modules.misc;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_745;

public class FakePlayer
extends Module {
    private final Setting<String> playerName = this.str("Name", "FakePlayer");
    private final Setting<Boolean> copyInventory = this.bool("CopyInv", true);
    private final Setting<Boolean> copyArmor = this.bool("CopyArmor", true);
    private final Setting<Boolean> glowing = this.bool("Glowing", false);
    private final Setting<Integer> maxPlayers = this.num("MaxPlayers", 5, 1, 20);
    private final class_310 mc = class_310.method_1551();
    private final List<FakePlayerEntity> fakePlayers = new ArrayList<FakePlayerEntity>();

    public FakePlayer() {
        super("FakePlayer", "Spawns fake players for testing", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        this.spawnFakePlayer();
    }

    @Override
    public void onDisable() {
        this.removeAllFakePlayers();
    }

    @Override
    public void onUpdate() {
        this.fakePlayers.removeIf(fakePlayer -> {
            if (fakePlayer.entity.method_31481()) {
                return true;
            }
            fakePlayer.entity.method_5834(this.glowing.getValue().booleanValue());
            return false;
        });
    }

    public void spawnFakePlayer() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.fakePlayers.size() >= this.maxPlayers.getValue()) {
            return;
        }
        class_243 playerPos = this.mc.field_1724.method_19538();
        FakePlayerEntity fakePlayerEntity = new FakePlayerEntity(this.mc.field_1687, playerPos, this.mc.field_1724.method_36454(), this.mc.field_1724.method_36455(), this.playerName.getValue());
        if (this.copyInventory.getValue().booleanValue()) {
            this.copyPlayerInventory(fakePlayerEntity);
        }
        if (this.copyArmor.getValue().booleanValue()) {
            this.copyPlayerArmor(fakePlayerEntity);
        }
        fakePlayerEntity.entity.method_5834(this.glowing.getValue().booleanValue());
        this.mc.field_1687.method_53875((class_1297)fakePlayerEntity.entity);
        this.fakePlayers.add(fakePlayerEntity);
    }

    public void removeLastFakePlayer() {
        if (!this.fakePlayers.isEmpty()) {
            FakePlayerEntity lastPlayer = this.fakePlayers.get(this.fakePlayers.size() - 1);
            lastPlayer.entity.method_5650(class_1297.class_5529.field_26999);
            this.fakePlayers.remove(lastPlayer);
        }
    }

    public void removeAllFakePlayers() {
        for (FakePlayerEntity fakePlayer : this.fakePlayers) {
            fakePlayer.entity.method_5650(class_1297.class_5529.field_26999);
        }
        this.fakePlayers.clear();
    }

    private void copyPlayerInventory(FakePlayerEntity fakePlayer) {
        if (this.mc.field_1724 == null) {
            return;
        }
        for (int i = 0; i < this.mc.field_1724.method_31548().field_7547.size(); ++i) {
            fakePlayer.entity.method_31548().field_7547.set(i, (Object)((class_1799)this.mc.field_1724.method_31548().field_7547.get(i)).method_7972());
        }
        fakePlayer.entity.method_31548().field_7545 = this.mc.field_1724.method_31548().field_7545;
    }

    private void copyPlayerArmor(FakePlayerEntity fakePlayer) {
        if (this.mc.field_1724 == null) {
            return;
        }
        for (int i = 0; i < this.mc.field_1724.method_31548().field_7548.size(); ++i) {
            fakePlayer.entity.method_31548().field_7548.set(i, (Object)((class_1799)this.mc.field_1724.method_31548().field_7548.get(i)).method_7972());
        }
        fakePlayer.entity.method_31548().field_7544.set(0, (Object)this.mc.field_1724.method_6079().method_7972());
    }

    public void addFakePlayer() {
        this.spawnFakePlayer();
    }

    public void removeFakePlayer() {
        this.removeLastFakePlayer();
    }

    public void clearAllFakePlayers() {
        this.removeAllFakePlayers();
    }

    public int getFakePlayerCount() {
        return this.fakePlayers.size();
    }

    public List<FakePlayerEntity> getFakePlayers() {
        return new ArrayList<FakePlayerEntity>(this.fakePlayers);
    }

    private static class FakePlayerEntity {
        public final class_745 entity;

        public FakePlayerEntity(class_638 world, class_243 pos, float yaw, float pitch, String name) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), name);
            this.entity = new class_745(world, profile);
            this.entity.method_5814(pos.field_1352, pos.field_1351, pos.field_1350);
            this.entity.method_36456(yaw);
            this.entity.method_36457(pitch);
            this.entity.field_6241 = yaw;
            this.entity.field_6283 = yaw;
            this.entity.method_24830(true);
            this.entity.method_5660(false);
            this.entity.method_5728(false);
        }
    }
}
