/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2830
 */
package com.duckflow.features.modules.player;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.modules.Module;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class NoFall
extends Module {
    public NoFall() {
        super("No Fall", "Removes fall damage", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (!NoFall.mc.field_1724.method_24828() && DuckFlowClient.positionManager.getFallDistance() > 3.0) {
            boolean bl = NoFall.mc.field_1724.field_5976;
            class_2828.class_2830 pakcet = new class_2828.class_2830(NoFall.mc.field_1724.method_23317(), NoFall.mc.field_1724.method_23318() + 1.0E-9, NoFall.mc.field_1724.method_23321(), NoFall.mc.field_1724.method_36454(), NoFall.mc.field_1724.method_36455(), false, bl);
            NoFall.mc.field_1724.field_3944.method_52787((class_2596)pakcet);
        }
    }
}
