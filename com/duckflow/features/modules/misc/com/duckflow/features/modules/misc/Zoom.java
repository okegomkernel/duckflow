/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.modules.misc;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import java.util.Objects;

public class Zoom
extends Module {
    public Setting<Integer> zoomFov = this.num("Zoom Amount", 15, 5, 64);
    private int originalFov = -1;
    private boolean appliedZoom = false;

    public Zoom() {
        super("Zoom", "Always zoomed in", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        if (!this.appliedZoom) {
            this.originalFov = (Integer)Zoom.mc.field_1690.method_41808().method_41753();
            Zoom.mc.field_1690.method_41808().method_41748((Object)this.zoomFov.getValue());
            this.appliedZoom = true;
        }
    }

    @Override
    public void onTick() {
        if (this.appliedZoom && !Objects.equals(Zoom.mc.field_1690.method_41808().method_41753(), this.zoomFov.getValue())) {
            Zoom.mc.field_1690.method_41808().method_41748((Object)this.zoomFov.getValue());
        }
    }

    @Override
    public void onDisable() {
        if (this.appliedZoom) {
            Zoom.mc.field_1690.method_41808().method_41748((Object)this.originalFov);
            this.appliedZoom = false;
        }
    }
}
