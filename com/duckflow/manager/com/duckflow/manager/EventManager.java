/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_124
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_2761
 */
package com.duckflow.manager;

import com.duckflow.DuckFlowClient;
import com.duckflow.event.Stage;
import com.duckflow.event.impl.ChatEvent;
import com.duckflow.event.impl.DeathEvent;
import com.duckflow.event.impl.KeyEvent;
import com.duckflow.event.impl.PacketEvent;
import com.duckflow.event.impl.Render2DEvent;
import com.duckflow.event.impl.Render3DEvent;
import com.duckflow.event.impl.UpdateEvent;
import com.duckflow.event.impl.UpdateWalkingPlayerEvent;
import com.duckflow.features.Feature;
import com.duckflow.features.commands.Command;
import com.duckflow.util.models.Timer;
import com.google.common.eventbus.Subscribe;
import net.minecraft.class_124;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_2761;

public class EventManager
extends Feature {
    private final Timer logoutTimer = new Timer();

    public void init() {
        EVENT_BUS.register((Object)this);
    }

    public void onUnload() {
        EVENT_BUS.unregister((Object)this);
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        mc.method_22683().method_24286("DuckFlow " + DuckFlowClient.VERSION);
        if (!EventManager.fullNullCheck()) {
            DuckFlowClient.moduleManager.onUpdate();
            DuckFlowClient.moduleManager.sortModules(true);
            this.onTick();
        }
    }

    public void onTick() {
        if (EventManager.fullNullCheck()) {
            return;
        }
        DuckFlowClient.moduleManager.onTick();
        for (class_1657 player : EventManager.mc.field_1687.method_18456()) {
            if (player == null || player.method_6032() > 0.0f) continue;
            EVENT_BUS.post((Object)new DeathEvent((class_1309)player));
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (EventManager.fullNullCheck()) {
            return;
        }
        if (event.getStage() == Stage.PRE) {
            DuckFlowClient.speedManager.updateValues();
            DuckFlowClient.rotationManager.updateRotations();
            DuckFlowClient.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            DuckFlowClient.rotationManager.restoreRotations();
            DuckFlowClient.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        DuckFlowClient.serverManager.onPacketReceived();
        if (event.getPacket() instanceof class_2761) {
            DuckFlowClient.serverManager.update();
        }
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        DuckFlowClient.moduleManager.onRender3D(event);
    }

    @Subscribe
    public void onRenderGameOverlayEvent(Render2DEvent event) {
        DuckFlowClient.moduleManager.onRender2D(event);
    }

    @Subscribe
    public void onKeyInput(KeyEvent event) {
        DuckFlowClient.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe
    public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    DuckFlowClient.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(String.valueOf(class_124.field_1061) + "An error occurred while running this command. Check the log!");
            }
        }
    }
}
