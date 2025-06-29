/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.modules.player;

import com.duckflow.features.modules.Module;

public class FastUse
extends Module {
    public FastUse() {
        super("Fast Use", "Makes you place blocks and interact faster", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (FastUse.nullCheck()) {
            return;
        }
        FastUse.mc.field_1752 = 0;
    }
}
