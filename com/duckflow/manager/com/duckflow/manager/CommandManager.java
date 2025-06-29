/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.class_124
 */
package com.duckflow.manager;

import com.duckflow.features.Feature;
import com.duckflow.features.commands.Command;
import com.duckflow.features.commands.impl.BindCommand;
import com.duckflow.features.commands.impl.FriendCommand;
import com.duckflow.features.commands.impl.HelpCommand;
import com.duckflow.features.commands.impl.HwidCommand;
import com.duckflow.features.commands.impl.MessageCommand;
import com.duckflow.features.commands.impl.ModuleCommand;
import com.duckflow.features.commands.impl.PrefixCommand;
import com.duckflow.features.commands.impl.ToggleCommand;
import com.duckflow.util.traits.Jsonable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.class_124;

public class CommandManager
extends Feature
implements Jsonable {
    private final List<Command> commands = new ArrayList<Command>();
    private String clientMessage = "\u00a7eDuck\u00a79Flow \u00a77>";
    private String prefix = ";";

    public CommandManager() {
        super("Command");
        this.commands.add(new ToggleCommand());
        this.commands.add(new BindCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new ModuleCommand());
        this.commands.add(new PrefixCommand());
        this.commands.add(new HwidCommand());
        this.commands.add(new MessageCommand());
        this.commands.add(new HelpCommand());
    }

    public static String[] removeElement(String[] input, int indexToDelete) {
        LinkedList<String> result = new LinkedList<String>();
        for (int i = 0; i < input.length; ++i) {
            if (i == indexToDelete) continue;
            result.add(input[i]);
        }
        return result.toArray(input);
    }

    private static String strip(String str, String key) {
        if (str.startsWith(key) && str.endsWith(key)) {
            return str.substring(key.length(), str.length() - key.length());
        }
        return str;
    }

    public void executeCommand(String command) {
        String[] parts = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String name = parts[0].substring(1);
        String[] args = CommandManager.removeElement(parts, 0);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] == null) continue;
            args[i] = CommandManager.strip(args[i], "\"");
        }
        for (Command c : this.commands) {
            if (!c.getName().equalsIgnoreCase(name)) continue;
            c.execute(parts);
            return;
        }
        Command.sendMessage(String.valueOf(class_124.field_1080) + "Command not found, type 'help' for the commands list.");
    }

    public Command getCommandByName(String name) {
        for (Command command : this.commands) {
            if (!command.getName().equals(name)) continue;
            return command;
        }
        return null;
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    public String getClientMessage() {
        return this.clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("prefix", this.prefix);
        return object;
    }

    @Override
    public void fromJson(JsonElement element) {
        this.setPrefix(element.getAsJsonObject().get("prefix").getAsString());
    }

    @Override
    public String getFileName() {
        return "commands.json";
    }
}
