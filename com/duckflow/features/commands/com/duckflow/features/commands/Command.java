/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 */
package com.duckflow.features.commands;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.Feature;
import net.minecraft.class_124;
import net.minecraft.class_2561;

public abstract class Command
extends Feature {
    protected String name;
    protected String[] commands;

    public Command(String name) {
        super(name);
        this.name = name;
        this.commands = new String[]{""};
    }

    public Command(String name, String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }

    public static void sendMessage(String message) {
        Command.sendSilentMessage(DuckFlowClient.commandManager.getClientMessage() + " " + String.valueOf(class_124.field_1080) + message);
    }

    public static void sendSilentMessage(String message) {
        if (Command.nullCheck()) {
            return;
        }
        Command.mc.field_1705.method_1743().method_1812((class_2561)class_2561.method_43470((String)message));
    }

    public static String getCommandPrefix() {
        return DuckFlowClient.commandManager.getPrefix();
    }

    public abstract void execute(String[] var1);

    @Override
    public String getName() {
        return this.name;
    }

    public String[] getCommands() {
        return this.commands;
    }
}
