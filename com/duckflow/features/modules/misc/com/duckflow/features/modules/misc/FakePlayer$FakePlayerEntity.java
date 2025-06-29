/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_243
 *  net.minecraft.class_638
 *  net.minecraft.class_745
 */
package com.duckflow.features.modules.misc;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.class_243;
import net.minecraft.class_638;
import net.minecraft.class_745;

private static class FakePlayer.FakePlayerEntity {
    public final class_745 entity;

    public FakePlayer.FakePlayerEntity(class_638 world, class_243 pos, float yaw, float pitch, String name) {
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
