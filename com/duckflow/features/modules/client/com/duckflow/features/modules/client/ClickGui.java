/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_124
 *  net.minecraft.class_437
 */
package com.duckflow.features.modules.client;

import com.duckflow.DuckFlowClient;
import com.duckflow.event.impl.ClientEvent;
import com.duckflow.features.commands.Command;
import com.duckflow.features.gui.DuckFlowGUI;
import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import com.google.common.eventbus.Subscribe;
import net.minecraft.class_124;
import net.minecraft.class_437;

public class ClickGui
extends Module {
    public static ClickGui INSTANCE = new ClickGui();
    public Setting<String> prefix = this.str("Prefix", ";");
    public Setting<Integer> red = this.num("Red", 255, 0, 255);
    public Setting<Integer> green = this.num("Green", 200, 0, 255);
    public Setting<Integer> blue = this.num("Blue", 0, 0, 255);
    public Setting<Integer> alpha = this.num("Alpha", 180, 0, 255);
    public Setting<Boolean> rainbow = this.bool("Rainbow", false);
    public Setting<Integer> rainbowHue = this.num("Delay", 240, 0, 600);
    public Setting<Float> rainbowBrightness = this.num("Brightness ", Float.valueOf(150.0f), Float.valueOf(1.0f), Float.valueOf(255.0f));
    public Setting<Float> rainbowSaturation = this.num("Saturation", Float.valueOf(150.0f), Float.valueOf(1.0f), Float.valueOf(255.0f));
    private DuckFlowGUI click;

    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
        this.setBind(59);
        this.rainbowHue.setVisibility(v -> this.rainbow.getValue());
        this.rainbowBrightness.setVisibility(v -> this.rainbow.getValue());
        this.rainbowSaturation.setVisibility(v -> this.rainbow.getValue());
        this.setInstance();
    }

    public static ClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGui();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Subscribe
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                DuckFlowClient.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + String.valueOf(class_124.field_1063) + DuckFlowClient.commandManager.getPrefix());
            }
            DuckFlowClient.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.alpha.getPlannedValue());
        }
    }

    @Override
    public void onEnable() {
        if (ClickGui.fullNullCheck()) {
            return;
        }
        mc.method_1507((class_437)DuckFlowGUI.getClickGui());
    }

    @Override
    public void onLoad() {
        DuckFlowClient.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
        DuckFlowClient.commandManager.setPrefix(this.prefix.getValue());
    }

    @Override
    public void onTick() {
        if (!(ClickGui.mc.field_1755 instanceof DuckFlowGUI)) {
            this.disable();
        }
    }

    public static enum rainbowMode {
        Static,
        Sideway;

    }

    public static enum rainbowModeArray {
        Static,
        Up;

    }
}
