/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_2680
 *  net.minecraft.class_310
 *  net.minecraft.class_638
 *  net.minecraft.class_746
 */
package com.duckflow.features.modules.movement;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_746;

public class Jesus
extends Module {
    private final Setting<Boolean> walkOnLava = this.bool("Lava", true);
    private final Setting<Boolean> walkOnWater = this.bool("Water", true);
    private final class_310 mc = class_310.method_1551();

    public Jesus() {
        super("Jesus", "Walk on liquids", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        class_2338 belowPlayer;
        class_2680 belowState;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        class_746 player = this.mc.field_1724;
        class_638 world = this.mc.field_1687;
        if (player.method_18798().field_1351 < 0.0 && !player.method_5715()) {
            class_2338 playerPos = class_2338.method_49638((class_2374)player.method_19538());
            for (int y = 0; y <= 2; ++y) {
                class_2338 checkPos = playerPos.method_10087(y);
                class_2680 state = world.method_8320(checkPos);
                if (!this.shouldWalkOn(state)) continue;
                double liquidTop = (double)checkPos.method_10264() + 1.0;
                double playerY = player.method_23318();
                if (!(playerY <= liquidTop + 0.5) || !(playerY >= liquidTop - 0.1)) break;
                player.method_18800(player.method_18798().field_1352, 0.0, player.method_18798().field_1350);
                player.method_5814(player.method_23317(), liquidTop, player.method_23321());
                player.method_24830(true);
                break;
            }
        }
        if (!player.method_5715() && this.shouldWalkOn(belowState = world.method_8320(belowPlayer = class_2338.method_49638((class_2374)player.method_19538()).method_10074()))) {
            double liquidSurface = (double)belowPlayer.method_10264() + 1.0;
            double playerY = player.method_23318();
            if (playerY < liquidSurface + 0.1) {
                if (player.method_18798().field_1351 < 0.0) {
                    player.method_18800(player.method_18798().field_1352, 0.0, player.method_18798().field_1350);
                }
                player.method_5814(player.method_23317(), liquidSurface, player.method_23321());
                player.method_24830(true);
            }
        }
    }

    private boolean shouldWalkOn(class_2680 state) {
        if (this.walkOnWater.getValue().booleanValue() && state.method_27852(class_2246.field_10382)) {
            return true;
        }
        return this.walkOnLava.getValue() != false && state.method_27852(class_2246.field_10164);
    }
}
