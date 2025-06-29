/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package com.duckflow.features.commands.impl;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.commands.Command;
import net.minecraft.class_124;

public class PrefixCommand
extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(String.valueOf(class_124.field_1060) + "Current prefix is " + DuckFlowClient.commandManager.getPrefix());
            return;
        }
        DuckFlowClient.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + String.valueOf(class_124.field_1080) + commands[0]);
    }
}
