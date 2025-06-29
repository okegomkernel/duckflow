/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675
 *  net.minecraft.class_3802
 *  net.minecraft.class_3871
 *  net.minecraft.class_3873
 *  net.minecraft.class_3874
 *  net.minecraft.class_3934
 *  net.minecraft.class_3979
 *  net.minecraft.class_408
 *  net.minecraft.class_418
 *  net.minecraft.class_433
 *  net.minecraft.class_437
 *  net.minecraft.class_466
 *  net.minecraft.class_471
 *  net.minecraft.class_472
 *  net.minecraft.class_476
 *  net.minecraft.class_479
 *  net.minecraft.class_480
 *  net.minecraft.class_486
 *  net.minecraft.class_488
 *  net.minecraft.class_4895
 *  net.minecraft.class_490
 *  net.minecraft.class_491
 *  net.minecraft.class_492
 *  net.minecraft.class_494
 *  net.minecraft.class_495
 */
package com.duckflow.features.modules.misc;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_3802;
import net.minecraft.class_3871;
import net.minecraft.class_3873;
import net.minecraft.class_3874;
import net.minecraft.class_3934;
import net.minecraft.class_3979;
import net.minecraft.class_408;
import net.minecraft.class_418;
import net.minecraft.class_433;
import net.minecraft.class_437;
import net.minecraft.class_466;
import net.minecraft.class_471;
import net.minecraft.class_472;
import net.minecraft.class_476;
import net.minecraft.class_479;
import net.minecraft.class_480;
import net.minecraft.class_486;
import net.minecraft.class_488;
import net.minecraft.class_4895;
import net.minecraft.class_490;
import net.minecraft.class_491;
import net.minecraft.class_492;
import net.minecraft.class_494;
import net.minecraft.class_495;

public class InvMove
extends Module {
    private final Setting<Boolean> rotate = this.bool("Rotate", true);
    private final Setting<Boolean> sneak = this.bool("Sneak", false);
    private final Setting<Boolean> jump = this.bool("Jump", true);
    private final Setting<Boolean> sprint = this.bool("Sprint", true);
    private final class_310 mc = class_310.method_1551();

    public InvMove() {
        super("InvMove", "Move while in inventories", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (this.mc.field_1724 == null || !this.shouldAllowMovement()) {
            return;
        }
        this.handleMovementKeys();
        if (this.rotate.getValue().booleanValue()) {
            // empty if block
        }
    }

    private void handleMovementKeys() {
        this.updateKeyState(this.mc.field_1690.field_1894);
        this.updateKeyState(this.mc.field_1690.field_1881);
        this.updateKeyState(this.mc.field_1690.field_1913);
        this.updateKeyState(this.mc.field_1690.field_1849);
        if (this.jump.getValue().booleanValue()) {
            this.updateKeyState(this.mc.field_1690.field_1903);
        }
        if (this.sneak.getValue().booleanValue()) {
            this.updateKeyState(this.mc.field_1690.field_1832);
        }
        if (this.sprint.getValue().booleanValue()) {
            this.updateKeyState(this.mc.field_1690.field_1867);
        }
    }

    private void updateKeyState(class_304 keyBinding) {
        boolean isPressed = class_3675.method_15987((long)class_310.method_1551().method_22683().method_4490(), (int)keyBinding.method_1429().method_1444());
        keyBinding.method_23481(isPressed);
    }

    private boolean shouldAllowMovement() {
        class_437 currentScreen = this.mc.field_1755;
        if (currentScreen == null) {
            return false;
        }
        return currentScreen instanceof class_490 || currentScreen instanceof class_479 || currentScreen instanceof class_3873 || currentScreen instanceof class_3871 || currentScreen instanceof class_3874 || currentScreen instanceof class_472 || currentScreen instanceof class_486 || currentScreen instanceof class_471 || currentScreen instanceof class_4895 || currentScreen instanceof class_3934 || currentScreen instanceof class_494 || currentScreen instanceof class_3979 || currentScreen instanceof class_3802 || currentScreen instanceof class_492 || currentScreen instanceof class_466 || currentScreen instanceof class_488 || currentScreen instanceof class_495 || currentScreen instanceof class_480 || currentScreen instanceof class_476 || currentScreen instanceof class_491;
    }

    private boolean isRestrictedScreen(class_437 screen) {
        return screen instanceof class_408 || screen instanceof class_418 || screen instanceof class_433;
    }

    @Override
    public void onDisable() {
        if (this.mc.field_1690 != null) {
            this.mc.field_1690.field_1894.method_23481(false);
            this.mc.field_1690.field_1881.method_23481(false);
            this.mc.field_1690.field_1913.method_23481(false);
            this.mc.field_1690.field_1849.method_23481(false);
            this.mc.field_1690.field_1903.method_23481(false);
            this.mc.field_1690.field_1832.method_23481(false);
            this.mc.field_1690.field_1867.method_23481(false);
        }
    }
}
