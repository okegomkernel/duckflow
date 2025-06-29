/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  org.lwjgl.glfw.GLFW
 */
package com.duckflow.features.modules.misc;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.commands.Command;
import com.duckflow.features.modules.Module;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import org.lwjgl.glfw.GLFW;

public class Friender
extends Module {
    private boolean pressed;

    public Friender() {
        super("Friender", "Middle click friend", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (GLFW.glfwGetMouseButton((long)mc.method_22683().method_4490(), (int)2) == 1) {
            if (!this.pressed) {
                class_1297 targetedEntity = Friender.mc.field_1692;
                if (!(targetedEntity instanceof class_1657)) {
                    return;
                }
                String name = ((class_1657)targetedEntity).method_7334().getName();
                if (DuckFlowClient.friendManager.isFriend(name)) {
                    DuckFlowClient.friendManager.removeFriend(name);
                    Command.sendMessage(String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1061) + " has been unfriended.");
                } else {
                    DuckFlowClient.friendManager.addFriend(name);
                    Command.sendMessage(String.valueOf(class_124.field_1075) + name + String.valueOf(class_124.field_1075) + " has been friended.");
                }
                this.pressed = true;
            }
        } else {
            this.pressed = false;
        }
    }
}
