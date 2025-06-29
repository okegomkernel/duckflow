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
import com.duckflow.features.settings.Setting;
import com.duckflow.util.RenderUtil;
import com.duckflow.util.models.Timer;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_124;
import net.minecraft.class_332;
import net.minecraft.class_3417;
import net.minecraft.class_6880;

public class StringButton
extends Button {
    private static final Timer idleTimer = new Timer();
    private static boolean idle;
    private final Setting<String> setting;
    public boolean isListening;
    private CurrentString currentString = new CurrentString("");

    public StringButton(Setting<String> setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    public static String removeLastChar(String str) {
        String output = "";
        if (str != null && str.length() > 0) {
            output = str.substring(0, str.length() - 1);
        }
        return output;
    }

    @Override
    public void drawScreen(class_332 context, int mouseX, int mouseY, float partialTicks) {
        RenderUtil.rect(context.method_51448(), this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, this.getState() ? (!this.isHovering(mouseX, mouseY) ? DuckFlowClient.colorManager.getColorWithAlpha(DuckFlowClient.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : DuckFlowClient.colorManager.getColorWithAlpha(DuckFlowClient.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        if (this.isListening) {
            this.drawString(this.currentString.string() + "_", (double)(this.x + 2.3f), (double)(this.y - 1.7f - (float)DuckFlowGUI.getClickGui().getTextOffset()), this.getState() ? -1 : -5592406);
        } else {
            this.drawString((String)(this.setting.getName().equals("Buttons") ? "Buttons " : (this.setting.getName().equals("Prefix") ? "Prefix  " + String.valueOf(class_124.field_1080) : "")) + this.setting.getValue(), (double)(this.x + 2.3f), (double)(this.y - 1.7f - (float)DuckFlowGUI.getClickGui().getTextOffset()), this.getState() ? -1 : -5592406);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            mc.method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, (float)1.0f));
        }
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {
        if (this.isListening) {
            this.setString(this.currentString.string() + typedChar);
        }
    }

    @Override
    public void onKeyPressed(int key) {
        if (this.isListening) {
            switch (key) {
                case 257: {
                    this.enterString();
                }
                case 259: {
                    this.setString(StringButton.removeLastChar(this.currentString.string()));
                }
            }
        }
    }

    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }

    private void enterString() {
        if (this.currentString.string().isEmpty()) {
            this.setting.setValue(this.setting.getDefaultValue());
        } else {
            this.setting.setValue(this.currentString.string());
        }
        this.setString("");
        this.onMouseClick();
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

    public void setString(String newString) {
        this.currentString = new CurrentString(newString);
    }

    public static String getIdleSign() {
        if (idleTimer.passedMs(500L)) {
            idle = !idle;
            idleTimer.reset();
        }
        if (idle) {
            return "_";
        }
        return "";
    }

    public record CurrentString(String string) {
    }
}
