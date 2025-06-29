/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1510
 *  net.minecraft.class_1528
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
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
import net.minecraft.class_238;
import net.minecraft.class_7260;

public class ESP
extends Module {
    public Setting<Boolean> renderSelf = this.bool("Render Self", false);

    public ESP() {
        super("ESP", "Highlights players with boxes", Module.Category.RENDER, true, false, false);
    }

    @Override
    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (ESP.mc.field_1687 == null) {
            return;
        }
        for (class_1297 entity : ESP.mc.field_1687.method_18112()) {
            if (!(entity instanceof class_1657) && !(entity instanceof class_1528) && !(entity instanceof class_7260) && !(entity instanceof class_1510) || !this.renderSelf.getValue().booleanValue() && entity == ESP.mc.field_1724) continue;
            class_238 box = entity.method_5829().method_1014(0.1);
            RenderUtil.drawBox(event.getMatrix(), box, Color.YELLOW, 1.0);
        }
    }
}
