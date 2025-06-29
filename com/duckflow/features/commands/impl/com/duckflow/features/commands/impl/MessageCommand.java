/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.duckflow.features.commands.impl;

import com.duckflow.features.commands.Command;
import net.minecraft.class_310;

public class MessageCommand
extends Command {
    public MessageCommand() {
        super("msg", new String[]{"<message>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 0) {
            MessageCommand.sendMessage("Usage: msg <message>");
            return;
        }
        String message = String.join((CharSequence)" ", commands);
        class_310.method_1551().field_1724.field_3944.method_45730("/clanchat " + message);
    }
}
