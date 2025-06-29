/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.commands.impl;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.commands.Command;
import com.duckflow.features.modules.Module;

public class ToggleCommand
extends Command {
    public ToggleCommand() {
        super("toggle", new String[]{"<module>"});
    }

    @Override
    public void execute(String[] var1) {
        if (var1.length < 1 || var1[0] == null) {
            this.notFound();
            return;
        }
        Module mod = DuckFlowClient.moduleManager.getModuleByName(var1[0]);
        if (mod == null) {
            this.notFound();
            return;
        }
        mod.toggle();
    }

    private void notFound() {
        ToggleCommand.sendMessage("Module is not found.");
    }
}
