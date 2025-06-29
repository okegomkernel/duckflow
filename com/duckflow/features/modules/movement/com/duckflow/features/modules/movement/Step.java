/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5134
 */
package com.duckflow.features.modules.movement;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_5134;

public class Step
extends Module {
    private final Setting<Float> height = this.num("Height", Float.valueOf(2.0f), Float.valueOf(1.0f), Float.valueOf(3.0f));
    private float prev;

    public Step() {
        super("Step", "step..", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onEnable() {
        if (Step.nullCheck()) {
            this.prev = 0.6f;
            return;
        }
        this.prev = Step.mc.field_1724.method_49476();
    }

    @Override
    public void onDisable() {
        if (Step.nullCheck()) {
            return;
        }
        Step.mc.field_1724.method_5996(class_5134.field_47761).method_6192((double)this.prev);
    }

    @Override
    public void onUpdate() {
        if (Step.nullCheck()) {
            return;
        }
        Step.mc.field_1724.method_5996(class_5134.field_47761).method_6192((double)this.height.getValue().floatValue());
    }
}
