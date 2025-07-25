/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_332
 *  org.lwjgl.glfw.GLFW
 */
package com.duckflow.features.gui.items.buttons;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.gui.Component;
import com.duckflow.features.gui.DuckFlowGUI;
import com.duckflow.features.gui.items.buttons.Button;
import com.duckflow.features.modules.client.ClickGui;
import com.duckflow.features.settings.Setting;
import com.duckflow.util.RenderUtil;
import net.minecraft.class_124;
import net.minecraft.class_332;
import org.lwjgl.glfw.GLFW;

public class Slider
extends Button {
    private final Number min;
    private final Number max;
    private final int difference;
    public Setting<Number> setting;

    public Slider(Setting<Number> setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = setting.getMin();
        this.max = setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }

    @Override
    public void drawScreen(class_332 context, int mouseX, int mouseY, float partialTicks) {
        this.dragSetting(mouseX, mouseY);
        RenderUtil.rect(context.method_51448(), this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, !this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515);
        RenderUtil.rect(context.method_51448(), this.x, this.y, this.setting.getValue().floatValue() <= this.min.floatValue() ? this.x : this.x + ((float)this.width + 7.4f) * this.partialMultiplier(), this.y + (float)this.height - 0.5f, !this.isHovering(mouseX, mouseY) ? DuckFlowClient.colorManager.getColorWithAlpha(DuckFlowClient.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : DuckFlowClient.colorManager.getColorWithAlpha(DuckFlowClient.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()));
        this.drawString(this.getName() + " " + String.valueOf(class_124.field_1080) + String.valueOf(this.setting.getValue() instanceof Float ? (Number)this.setting.getValue() : (Number)this.setting.getValue().doubleValue()), (double)(this.x + 2.3f), (double)(this.y - 1.7f - (float)DuckFlowGUI.getClickGui().getTextOffset()), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            this.setSettingFromX(mouseX);
        }
    }

    @Override
    public boolean isHovering(int mouseX, int mouseY) {
        for (Component component : DuckFlowGUI.getClickGui().getComponents()) {
            if (!component.drag) continue;
            return false;
        }
        return (float)mouseX >= this.getX() && (float)mouseX <= this.getX() + (float)this.getWidth() + 8.0f && (float)mouseY >= this.getY() && (float)mouseY <= this.getY() + (float)this.height;
    }

    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }

    private void dragSetting(int mouseX, int mouseY) {
        if (this.isHovering(mouseX, mouseY) && GLFW.glfwGetMouseButton((long)mc.method_22683().method_4490(), (int)0) == 1) {
            this.setSettingFromX(mouseX);
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    private void setSettingFromX(int mouseX) {
        float percent = ((float)mouseX - this.x) / ((float)this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            double result = (Double)this.setting.getMin() + (double)((float)this.difference * percent);
            this.setting.setValue((double)Math.round(10.0 * result) / 10.0);
        } else if (this.setting.getValue() instanceof Float) {
            float result = ((Float)this.setting.getMin()).floatValue() + (float)this.difference * percent;
            this.setting.setValue(Float.valueOf((float)Math.round(10.0f * result) / 10.0f));
        } else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue((Integer)this.setting.getMin() + (int)((float)this.difference * percent));
        }
    }

    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }

    private float part() {
        return this.setting.getValue().floatValue() - this.min.floatValue();
    }

    private float partialMultiplier() {
        return this.part() / this.middle();
    }
}
