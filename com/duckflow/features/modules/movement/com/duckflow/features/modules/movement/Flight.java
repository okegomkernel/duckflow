/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 */
package com.duckflow.features.modules.movement;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_243;
import net.minecraft.class_310;

public class Flight
extends Module {
    private final Setting<Integer> speed = this.num("Speed", 5, 0, 25);
    private final Setting<Boolean> antiKick = this.bool("AntiKick", false);
    private final Setting<Boolean> bobbing = this.bool("Bobbing", true);
    private final class_310 mc = class_310.method_1551();
    private int antiKickTimer = 0;

    public Flight() {
        super("Fly", "Allows you to fly freely in the air", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (Flight.nullCheck() || this.mc.field_1724 == null) {
            return;
        }
        this.mc.field_1724.method_5875(true);
        class_243 velocity = class_243.field_1353;
        double flySpeed = (double)this.speed.getValue().intValue() * 0.15;
        class_243 movementInput = this.getMovementInput();
        if (movementInput.method_1027() > 0.0) {
            velocity = velocity.method_1019(movementInput.method_1021(flySpeed));
        }
        if (this.mc.field_1690.field_1903.method_1434()) {
            velocity = velocity.method_1031(0.0, flySpeed, 0.0);
        }
        if (this.mc.field_1690.field_1832.method_1434()) {
            velocity = velocity.method_1031(0.0, -flySpeed, 0.0);
        }
        if (this.bobbing.getValue().booleanValue() && movementInput.method_1027() > 0.0) {
            double bobOffset = Math.sin((double)this.mc.field_1724.field_6012 * 0.1) * 0.02;
            velocity = velocity.method_1031(0.0, bobOffset, 0.0);
        }
        if (movementInput.method_1027() == 0.0 && !this.mc.field_1690.field_1903.method_1434() && !this.mc.field_1690.field_1832.method_1434()) {
            class_243 currentVel = this.mc.field_1724.method_18798();
            velocity = currentVel.method_1021(0.8);
        }
        this.mc.field_1724.method_18799(velocity);
        if (this.antiKick.getValue().booleanValue()) {
            ++this.antiKickTimer;
            if (this.antiKickTimer >= 80) {
                class_243 currentVel = this.mc.field_1724.method_18798();
                this.mc.field_1724.method_18800(currentVel.field_1352, -0.04, currentVel.field_1350);
                this.antiKickTimer = 0;
            }
        }
    }

    private class_243 getMovementInput() {
        class_243 forward = class_243.field_1353;
        if (this.mc.field_1690.field_1894.method_1434()) {
            forward = forward.method_1019(this.getForwardVector());
        }
        if (this.mc.field_1690.field_1881.method_1434()) {
            forward = forward.method_1020(this.getForwardVector());
        }
        if (this.mc.field_1690.field_1913.method_1434()) {
            forward = forward.method_1019(this.getRightVector().method_1021(1.0));
        }
        if (this.mc.field_1690.field_1849.method_1434()) {
            forward = forward.method_1019(this.getRightVector().method_1021(-1.0));
        }
        return forward.method_1029();
    }

    private class_243 getForwardVector() {
        assert (this.mc.field_1724 != null);
        float yaw = (float)Math.toRadians(this.mc.field_1724.method_36454());
        return new class_243(-Math.sin(yaw), 0.0, Math.cos(yaw));
    }

    private class_243 getRightVector() {
        assert (this.mc.field_1724 != null);
        float yaw = (float)Math.toRadians(this.mc.field_1724.method_36454());
        return new class_243(Math.cos(yaw), 0.0, Math.sin(yaw));
    }

    @Override
    public void onEnable() {
        this.antiKickTimer = 0;
    }

    @Override
    public void onDisable() {
        if (this.mc.field_1724 == null) {
            return;
        }
        this.mc.field_1724.method_5875(false);
        class_243 currentVel = this.mc.field_1724.method_18798();
        if (currentVel.field_1351 < -0.5) {
            this.mc.field_1724.method_18800(currentVel.field_1352 * 0.5, Math.max(currentVel.field_1351, -0.5), currentVel.field_1350 * 0.5);
        }
    }
}
