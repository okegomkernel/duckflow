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

public class FriendCommand
extends Command {
    public FriendCommand() {
        super("friend", new String[]{"<add/del/name/clear>", "<name>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            if (DuckFlowClient.friendManager.getFriends().isEmpty()) {
                FriendCommand.sendMessage("Friend list empty D:.");
            } else {
                StringBuilder f = new StringBuilder("Friends: ");
                for (String friend : DuckFlowClient.friendManager.getFriends()) {
                    try {
                        f.append(friend).append(", ");
                    }
                    catch (Exception exception) {}
                }
                FriendCommand.sendMessage(f.toString());
            }
            return;
        }
        if (commands.length == 2) {
            if (commands[0].equals("reset")) {
                DuckFlowClient.friendManager.getFriends().clear();
                FriendCommand.sendMessage("Friends got reset.");
                return;
            }
            FriendCommand.sendMessage(commands[0] + (DuckFlowClient.friendManager.isFriend(commands[0]) ? " is friended." : " isn't friended."));
            return;
        }
        if (commands.length >= 2) {
            switch (commands[0]) {
                case "add": {
                    DuckFlowClient.friendManager.addFriend(commands[1]);
                    FriendCommand.sendMessage(String.valueOf(class_124.field_1060) + commands[1] + " has been friended");
                    return;
                }
                case "del": 
                case "remove": {
                    DuckFlowClient.friendManager.removeFriend(commands[1]);
                    FriendCommand.sendMessage(String.valueOf(class_124.field_1061) + commands[1] + " has been unfriended");
                    return;
                }
            }
            FriendCommand.sendMessage("Unknown Command, try friend add/del (name)");
        }
    }
}
