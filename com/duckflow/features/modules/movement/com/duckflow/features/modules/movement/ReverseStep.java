/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.modules.movement;

import com.duckflow.features.modules.Module;

public class ReverseStep
extends Module {
    public ReverseStep() {
        super("Reverse Step", "step but reversed..", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (ReverseStep.nullCheck()) {
            return;
        }
        if (ReverseStep.mc.field_1724.method_5771() || ReverseStep.mc.field_1724.method_5799() || !ReverseStep.mc.field_1724.method_24828()) {
            return;
        }
        ReverseStep.mc.field_1724.method_5762(0.0, -1.0, 0.0);
    }
}
