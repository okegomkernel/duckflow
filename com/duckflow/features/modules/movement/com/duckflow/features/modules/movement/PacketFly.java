/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 */
package com.duckflow.features.modules.movement;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class PacketFly
extends Module {
    private final Setting<Float> flySpeed = this.num("Fly Speed", Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(10.0f));
    private double yLevel = Double.NaN;

    public PacketFly() {
        super("Packet Fly", "Allows flying by sending position packets to the server", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onEnable() {
        if (PacketFly.nullCheck()) {
            return;
        }
        assert (PacketFly.mc.field_1724 != null);
        this.yLevel = PacketFly.mc.field_1724.method_23318();
    }

    @Override
    public void onUpdate() {
        if (PacketFly.nullCheck()) {
            return;
        }
        assert (PacketFly.mc.field_1724 != null);
        double x = PacketFly.mc.field_1724.method_23317();
        double y = PacketFly.mc.field_1724.method_23318();
        double z = PacketFly.mc.field_1724.method_23321();
        float moveForward = PacketFly.mc.field_1724.field_3913.field_3905;
        float moveStrafe = PacketFly.mc.field_1724.field_3913.field_3907;
        float yaw = PacketFly.mc.field_1724.method_36454();
        float speed = this.flySpeed.getValue().floatValue();
        if (PacketFly.mc.field_1690.field_1903.method_1434()) {
            this.yLevel += (double)speed;
        } else if (PacketFly.mc.field_1690.field_1832.method_1434()) {
            this.yLevel -= (double)speed;
        }
        y = this.yLevel;
        if (moveForward != 0.0f || moveStrafe != 0.0f) {
            if (moveForward != 0.0f) {
                if (moveStrafe > 0.0f) {
                    yaw += moveForward > 0.0f ? -45.0f : 45.0f;
                } else if (moveStrafe < 0.0f) {
                    yaw += moveForward > 0.0f ? 45.0f : -45.0f;
                }
                moveStrafe = 0.0f;
                moveForward = moveForward > 0.0f ? 1.0f : -1.0f;
            }
            double rad = Math.toRadians(yaw);
            x += (double)(moveForward * speed) * -Math.sin(rad) + (double)(moveStrafe * speed) * Math.cos(rad);
            z += (double)(moveForward * speed) * Math.cos(rad) - (double)(moveStrafe * speed) * -Math.sin(rad);
        }
        PacketFly.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(x, y, z, true, true));
        PacketFly.mc.field_1724.method_30634(x, y, z);
        PacketFly.mc.field_1724.method_18800(PacketFly.mc.field_1724.method_18798().field_1352, 0.0, PacketFly.mc.field_1724.method_18798().field_1350);
    }

    @Override
    public void onDisable() {
        if (PacketFly.mc.field_1724 == null) {
            return;
        }
        PacketFly.mc.field_1724.method_18800(0.0, 0.0, 0.0);
    }
}
