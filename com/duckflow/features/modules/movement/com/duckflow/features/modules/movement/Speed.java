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

public class Speed
extends Module {
    private final Setting<Float> speedMultiplier = this.num("How fast", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f));

    public Speed() {
        super("Speed", "Increases player ground movement speed", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (Speed.nullCheck()) {
            return;
        }
        float moveForward = Speed.mc.field_1724.field_3913.field_3905;
        float moveStrafe = Speed.mc.field_1724.field_3913.field_3907;
        float yaw = Speed.mc.field_1724.method_36454();
        if (moveForward == 0.0f && moveStrafe == 0.0f) {
            return;
        }
        float speed = this.speedMultiplier.getValue().floatValue();
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
        double x = Speed.mc.field_1724.method_23317() + (double)(moveForward * speed) * -Math.sin(rad) + (double)(moveStrafe * speed) * Math.cos(rad);
        double z = Speed.mc.field_1724.method_23321() + (double)(moveForward * speed) * Math.cos(rad) - (double)(moveStrafe * speed) * -Math.sin(rad);
        double y = Speed.mc.field_1724.method_23318();
        Speed.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(x, y, z, true, true));
        Speed.mc.field_1724.method_30634(x, y, z);
    }

    @Override
    public void onDisable() {
        if (Speed.mc.field_1724 == null) {
            return;
        }
    }
}
