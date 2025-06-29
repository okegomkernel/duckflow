/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 */
package com.duckflow.features.gui;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.Feature;
import com.duckflow.features.gui.Component;
import com.duckflow.features.gui.items.Item;
import com.duckflow.features.gui.items.buttons.ModuleButton;
import com.duckflow.features.modules.Module;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class DuckFlowGUI
extends class_437 {
    private static DuckFlowGUI duckFlowGUI;
    private static DuckFlowGUI INSTANCE;
    private final ArrayList<Component> components = new ArrayList();

    public DuckFlowGUI() {
        super((class_2561)class_2561.method_43470((String)"DuckFlow"));
        this.setInstance();
        this.load();
    }

    public static DuckFlowGUI getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DuckFlowGUI();
        }
        return INSTANCE;
    }

    public static DuckFlowGUI getClickGui() {
        return DuckFlowGUI.getInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    private void load() {
        int x = -84;
        for (final Module.Category category : DuckFlowClient.moduleManager.getCategories()) {
            this.components.add(new Component(this, category.getName(), x += 90, 4, true){

                @Override
                public void setupItems() {
                    counter1 = new int[]{1};
                    DuckFlowClient.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton(new ModuleButton((Module)module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort(Comparator.comparing(Feature::getName)));
    }

    public void updateModule(Module module) {
        for (Component component : this.components) {
            for (Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) continue;
                ModuleButton button = (ModuleButton)item;
                Module mod = button.getModule();
                if (module == null || !module.equals(mod)) continue;
                button.initSettings();
            }
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        Item.context = context;
        int screenWidth = context.method_51421();
        int screenHeight = context.method_51443();
        context.method_25294(0, 0, screenWidth, screenHeight, new Color(0, 0, 0, 120).hashCode());
        this.components.forEach(components -> components.drawScreen(context, mouseX, mouseY, delta));
        String footer = "DuckFlow Client | Made for The Duck Union | Client by iDucky__ | " + DuckFlowClient.VERSION;
        int textWidth = this.field_22793.method_1727(footer);
        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight - 12;
        context.method_25303(this.field_22793, footer, x, y, 0xFFFFFF);
    }

    public boolean method_25402(double mouseX, double mouseY, int clickedButton) {
        this.components.forEach(components -> components.mouseClicked((int)mouseX, (int)mouseY, clickedButton));
        return super.method_25402(mouseX, mouseY, clickedButton);
    }

    public boolean method_25406(double mouseX, double mouseY, int releaseButton) {
        this.components.forEach(components -> components.mouseReleased((int)mouseX, (int)mouseY, releaseButton));
        return super.method_25406(mouseX, mouseY, releaseButton);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (verticalAmount < 0.0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        } else if (verticalAmount > 0.0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
        return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        this.components.forEach(component -> component.onKeyPressed(keyCode));
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public boolean method_25400(char chr, int modifiers) {
        this.components.forEach(component -> component.onKeyTyped(chr, modifiers));
        return super.method_25400(chr, modifiers);
    }

    public boolean method_25421() {
        return false;
    }

    public final ArrayList<Component> getComponents() {
        return this.components;
    }

    public int getTextOffset() {
        return -6;
    }

    public Component getComponentByName(String name) {
        for (Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) continue;
            return component;
        }
        return null;
    }

    static {
        INSTANCE = new DuckFlowGUI();
    }
}
