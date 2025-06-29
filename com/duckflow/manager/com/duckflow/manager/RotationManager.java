/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 */
package com.duckflow.manager;

import com.duckflow.util.MathUtil;
import com.duckflow.util.traits.Util;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_243;

public class RotationManager
implements Util {
    private float yaw;
    private float pitch;

    public void updateRotations() {
        this.yaw = RotationManager.mc.field_1724.method_36454();
        this.pitch = RotationManager.mc.field_1724.method_36455();
    }

    public void restoreRotations() {
        RotationManager.mc.field_1724.method_36456(this.yaw);
        RotationManager.mc.field_1724.field_6241 = this.yaw;
        RotationManager.mc.field_1724.method_36457(this.pitch);
    }

    public void setPlayerRotations(float yaw, float pitch) {
        RotationManager.mc.field_1724.method_36456(yaw);
        RotationManager.mc.field_1724.field_6241 = yaw;
        RotationManager.mc.field_1724.method_36457(pitch);
    }

    public void setPlayerYaw(float yaw) {
        RotationManager.mc.field_1724.method_36456(yaw);
        RotationManager.mc.field_1724.field_6241 = yaw;
    }

    public void lookAtPos(class_2338 pos) {
        float[] angle = MathUtil.calcAngle(RotationManager.mc.field_1724.method_33571(), new class_243((double)((float)pos.method_10263() + 0.5f), (double)((float)pos.method_10264() + 0.5f), (double)((float)pos.method_10260() + 0.5f)));
        this.setPlayerRotations(angle[0], angle[1]);
    }

    public void lookAtVec3d(class_243 vec3d) {
        float[] angle = MathUtil.calcAngle(RotationManager.mc.field_1724.method_33571(), new class_243(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350));
        this.setPlayerRotations(angle[0], angle[1]);
    }

    public void lookAtVec3d(double x, double y, double z) {
        class_243 vec3d = new class_243(x, y, z);
        this.lookAtVec3d(vec3d);
    }

    public void lookAtEntity(class_1297 entity) {
        float[] angle = MathUtil.calcAngle(RotationManager.mc.field_1724.method_33571(), entity.method_33571());
        this.setPlayerRotations(angle[0], angle[1]);
    }

    public void setPlayerPitch(float pitch) {
        RotationManager.mc.field_1724.method_36457(pitch);
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
