/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1109
 *  net.minecraft.class_1113
 *  net.minecraft.class_124
 *  net.minecraft.class_332
 *  net.minecraft.class_3417
 *  net.minecraft.class_6880
 */
package com.duckflow.features.gui.items.buttons;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.gui.DuckFlowGUI;
import com.duckflow.features.gui.items.buttons.Button;
import com.duckflow.features.modules.client.ClickGui;
import com.duckflow.features.settings.Bind;
import com.duckflow.features.settings.Setting;
import com.duckflow.util.ColorUtil;
import com.duckflow.util.RenderUtil;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_124;
import net.minecraft.class_332;
import net.minecraft.class_3417;
import net.minecraft.class_6880;

public class BindButton
extends Button {
    private final Setting<Bind> setting;
    public boolean isListening;

    public BindButton(Setting<Bind> setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    @Override
    public void drawScreen(class_332 context, int mouseX, int mouseY, float partialTicks) {
        int color = ColorUtil.toARGB(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue(), 255);
        RenderUtil.rect(context.method_51448(), this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, this.getState() ? (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515) : (!this.isHovering(mouseX, mouseY) ? DuckFlowClient.colorManager.getColorWithAlpha(DuckFlowClient.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : DuckFlowClient.colorManager.getColorWithAlpha(DuckFlowClient.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue())));
        if (this.isListening) {
            this.drawString("Press a Key...", (double)(this.x + 2.3f), (double)(this.y - 1.7f - (float)DuckFlowGUI.getClickGui().getTextOffset()), -1);
        } else {
            String str = this.setting.getValue().toString().toUpperCase();
            str = str.replace("KEY.KEYBOARD", "").replace(".", " ");
            this.drawString(this.setting.getName() + " " + String.valueOf(class_124.field_1080) + str, (double)(this.x + 2.3f), (double)(this.y - 1.7f - (float)DuckFlowGUI.getClickGui().getTextOffset()), this.getState() ? -1 : -5592406);
        }
    }

    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            mc.method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, (float)1.0f));
        }
    }

    @Override
    public void onKeyPressed(int key) {
        if (this.isListening) {
            Bind bind = new Bind(key);
            if (key == 261 || key == 259 || key == 256) {
                bind = new Bind(-1);
            }
            this.setting.setValue(bind);
            this.onMouseClick();
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }

    @Override
    public boolean getState() {
        return !this.isListening;
    }
}
