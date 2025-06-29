/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_310
 */
package com.duckflow.features.modules.render;

import com.duckflow.features.modules.Module;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_310;

public class Fullbright
extends Module {
    private final class_310 mc = class_310.method_1551();
    private final class_1293 nightVision = new class_1293(class_1294.field_5925, -1, 255, false, false, true);

    public Fullbright() {
        super("Fullbright", "Makes the world fully bright using Night Vision", Module.Category.RENDER, true, false, false);
    }

    @Override
    public void onEnable() {
        if (this.mc.field_1724 != null) {
            this.mc.field_1724.method_6092(this.nightVision);
        }
    }

    @Override
    public void onDisable() {
        if (this.mc.field_1724 != null) {
            this.mc.field_1724.method_6016(class_1294.field_5925);
        }
    }
}
