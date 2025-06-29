/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1922
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_265
 *  net.minecraft.class_3965
 */
package com.duckflow.features.modules.render;

import com.duckflow.event.impl.Render3DEvent;
import com.duckflow.features.modules.Module;
import com.duckflow.util.RenderUtil;
import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import net.minecraft.class_1922;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_265;
import net.minecraft.class_3965;

public class BlockHighlight
extends Module {
    public BlockHighlight() {
        super("Block Highlight", "Draws box at the block that you are looking at", Module.Category.RENDER, true, false, false);
    }

    @Override
    @Subscribe
    public void onRender3D(Render3DEvent event) {
        class_239 class_2392 = BlockHighlight.mc.field_1765;
        if (class_2392 instanceof class_3965) {
            class_3965 result = (class_3965)class_2392;
            class_265 shape = BlockHighlight.mc.field_1687.method_8320(result.method_17777()).method_26218((class_1922)BlockHighlight.mc.field_1687, result.method_17777());
            if (shape.method_1110()) {
                return;
            }
            class_238 box = shape.method_1107();
            box = box.method_996(result.method_17777());
            RenderUtil.drawBox(event.getMatrix(), box, Color.yellow, 2.0);
        }
    }
}
