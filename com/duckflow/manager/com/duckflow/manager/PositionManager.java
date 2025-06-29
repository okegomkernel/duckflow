/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 */
package com.duckflow.manager;

import com.duckflow.event.Stage;
import com.duckflow.event.impl.UpdateWalkingPlayerEvent;
import com.duckflow.features.Feature;
import com.google.common.eventbus.Subscribe;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class PositionManager
extends Feature {
    private double x;
    private double y;
    private double z;
    private boolean onground;
    private double fallDistance;

    public PositionManager() {
        EVENT_BUS.register((Object)this);
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (event.getStage() == Stage.POST) {
            return;
        }
        double diff = PositionManager.mc.field_1724.field_6036 - PositionManager.mc.field_1724.method_23318();
        this.fallDistance = PositionManager.mc.field_1724.method_24828() || diff <= 0.0 ? 0.0 : (this.fallDistance += diff);
    }

    public void updatePosition() {
        this.x = PositionManager.mc.field_1724.method_23317();
        this.y = PositionManager.mc.field_1724.method_23318();
        this.z = PositionManager.mc.field_1724.method_23321();
        this.onground = PositionManager.mc.field_1724.method_24828();
    }

    public void restorePosition() {
        PositionManager.mc.field_1724.method_5814(this.x, this.y, this.z);
        PositionManager.mc.field_1724.method_24830(this.onground);
    }

    public void setPlayerPosition(double x, double y, double z) {
        PositionManager.mc.field_1724.method_5814(x, y, z);
    }

    public void setPlayerPosition(double x, double y, double z, boolean onground) {
        PositionManager.mc.field_1724.method_5814(x, y, z);
        PositionManager.mc.field_1724.method_24830(onground);
    }

    public void setPositionPacket(double x, double y, double z, boolean onGround, boolean setPos, boolean noLagBack) {
        boolean bl = PositionManager.mc.field_1724.field_5976;
        PositionManager.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(x, y, z, onGround, bl));
        if (setPos) {
            PositionManager.mc.field_1724.method_5814(x, y, z);
            if (noLagBack) {
                this.updatePosition();
            }
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getFallDistance() {
        return this.fallDistance;
    }
}
