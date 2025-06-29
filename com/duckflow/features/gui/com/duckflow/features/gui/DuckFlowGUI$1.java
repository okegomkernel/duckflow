/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.gui;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.gui.Component;
import com.duckflow.features.gui.DuckFlowGUI;
import com.duckflow.features.gui.items.buttons.ModuleButton;
import com.duckflow.features.modules.Module;

class DuckFlowGUI.1
extends Component {
    final /* synthetic */ Module.Category val$category;

    DuckFlowGUI.1(DuckFlowGUI this$0, String name, int x, int y, boolean open, Module.Category category) {
        this.val$category = category;
        super(name, x, y, open);
    }

    @Override
    public void setupItems() {
        counter1 = new int[]{1};
        DuckFlowClient.moduleManager.getModulesByCategory(this.val$category).forEach(module -> {
            if (!module.hidden) {
                this.addButton(new ModuleButton((Module)module));
            }
        });
    }
}
