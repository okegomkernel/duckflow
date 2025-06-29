/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1510
 *  net.minecraft.class_1528
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_4587
 *  net.minecraft.class_7260
 */
package com.duckflow.features.modules.render;

import com.duckflow.event.impl.Render3DEvent;
import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import com.duckflow.util.RenderUtil;
import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1510;
import net.minecraft.class_1528;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import net.minecraft.class_7260;

public class Tracers
extends Module {
    public Setting<Boolean> renderSelf = this.bool("Render Self", false);

    public Tracers() {
        super("Tracers", "Draws lines to players and mobs", Module.Category.RENDER, true, false, false);
    }

    @Override
    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (Tracers.mc.field_1687 == null || Tracers.mc.field_1724 == null) {
            return;
        }
        class_4587 matrices = event.getMatrix();
        class_243 playerEyes = Tracers.mc.field_1724.method_33571();
        RenderUtil.setup3D();
        for (class_1297 entity : Tracers.mc.field_1687.method_18112()) {
            if (!(entity instanceof class_1657) && !(entity instanceof class_1528) && !(entity instanceof class_7260) && !(entity instanceof class_1510) || !this.renderSelf.getValue().booleanValue() && entity == Tracers.mc.field_1724) continue;
            class_243 entityPos = entity.method_19538().method_1031(0.0, (double)entity.method_17682() / 2.0, 0.0);
            RenderUtil.drawLine(matrices, playerEyes, entityPos, Color.YELLOW, 2.0f);
        }
        RenderUtil.clean3D();
    }
}
