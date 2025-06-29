/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_124
 */
package com.duckflow.features.commands.impl;

import com.duckflow.DuckFlowClient;
import com.duckflow.event.impl.KeyEvent;
import com.duckflow.features.commands.Command;
import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Bind;
import com.duckflow.util.KeyboardUtil;
import com.google.common.eventbus.Subscribe;
import net.minecraft.class_124;

public class BindCommand
extends Command {
    private boolean listening;
    private Module module;

    public BindCommand() {
        super("bind", new String[]{"<module>"});
        EVENT_BUS.register((Object)this);
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            BindCommand.sendMessage("Please specify a module.");
            return;
        }
        String moduleName = commands[0];
        Module module = DuckFlowClient.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            BindCommand.sendMessage("Unknown module '" + String.valueOf(module) + "'!");
            return;
        }
        BindCommand.sendMessage(String.valueOf(class_124.field_1080) + "Press a key.");
        this.listening = true;
        this.module = module;
    }

    @Subscribe
    private void onKey(KeyEvent event) {
        if (BindCommand.nullCheck() || !this.listening) {
            return;
        }
        this.listening = false;
        if (event.getKey() == 256) {
            BindCommand.sendMessage(String.valueOf(class_124.field_1080) + "Operation cancelled.");
            return;
        }
        BindCommand.sendMessage("Bind for " + String.valueOf(class_124.field_1060) + this.module.getName() + String.valueOf(class_124.field_1068) + " set to " + String.valueOf(class_124.field_1080) + KeyboardUtil.getKeyName(event.getKey()));
        this.module.bind.setValue(new Bind(event.getKey()));
    }
}
