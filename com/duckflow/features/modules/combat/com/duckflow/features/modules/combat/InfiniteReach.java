/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_310
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 */
package com.duckflow.features.modules.combat;

import com.duckflow.features.commands.Command;
import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_310;
import net.minecraft.class_3959;

public class InfiniteReach
extends Module {
    public Setting<Double> reachDistance = this.num("Reach", 50.0, 7.0, 250.0);
    public Setting<Boolean> onlyLiving = this.bool("Living Entities", true);
    public Setting<Integer> teleportDistance = this.num("TP Distance", 7, 1, 10);
    public Setting<Integer> hitDelay = this.num("Attack Delay", 1, 1, 20);
    private final class_310 mc = class_310.method_1551();
    private boolean isExecutingReach = false;
    private class_243 originalPosition = null;
    private int ticksSinceLastAttack = 0;

    public InfiniteReach() {
        super("Inf Reach", "Teleport to reach distant targets", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    @Override
    public void onDisable() {
        if (this.isExecutingReach && this.originalPosition != null) {
            this.teleportToPosition(this.originalPosition);
            this.resetState();
        }
    }

    private void onTick(class_310 client) {
        if (!this.isEnabled() || !this.isValidGameState() || this.isExecutingReach) {
            return;
        }
        ++this.ticksSinceLastAttack;
        if (this.mc.field_1690.field_1886.method_1434() && this.ticksSinceLastAttack >= this.hitDelay.getValue()) {
            this.ticksSinceLastAttack = 0;
            this.handleAttackInput();
        }
    }

    private boolean isValidGameState() {
        return this.mc.field_1724 != null && this.mc.field_1687 != null && this.mc.method_1562() != null && !this.mc.method_1493();
    }

    private void handleAttackInput() {
        class_1297 targetEntity = this.findTargetEntity();
        if (targetEntity != null) {
            new Thread(() -> this.executeReachAttack(targetEntity)).start();
        }
    }

    private class_1297 findTargetEntity() {
        class_243 eyePos = this.mc.field_1724.method_33571();
        class_243 lookDirection = this.mc.field_1724.method_5828(1.0f);
        double maxDistance = this.reachDistance.getValue();
        class_1297 closestEntity = null;
        double closestDistance = Double.MAX_VALUE;
        class_238 searchBox = this.mc.field_1724.method_5829().method_1014(maxDistance);
        for (class_1297 entity : this.mc.field_1687.method_8335((class_1297)this.mc.field_1724, searchBox)) {
            double distance;
            Optional<class_243> hitPosition;
            if (!this.isValidTarget(entity) || !(hitPosition = this.raycastToEntity(eyePos, lookDirection, entity, maxDistance)).isPresent() || !((distance = eyePos.method_1022(entity.method_19538())) < closestDistance) || !(distance > 3.5)) continue;
            closestDistance = distance;
            closestEntity = entity;
        }
        return closestEntity;
    }

    private boolean isValidTarget(class_1297 entity) {
        if (entity == null || entity == this.mc.field_1724 || !entity.method_5805()) {
            return false;
        }
        return this.onlyLiving.getValue() == false || entity instanceof class_1309;
    }

    private Optional<class_243> raycastToEntity(class_243 start, class_243 direction, class_1297 entity, double maxDistance) {
        class_238 entityBox = entity.method_5829().method_1014(0.1);
        return entityBox.method_992(start, start.method_1019(direction.method_1021(maxDistance)));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void executeReachAttack(class_1297 target) {
        if (this.isExecutingReach) {
            return;
        }
        this.isExecutingReach = true;
        this.originalPosition = this.mc.field_1724.method_19538();
        try {
            class_243 targetPos = target.method_19538();
            List<class_243> pathToTarget = this.calculateTeleportPath(this.originalPosition, targetPos);
            if (pathToTarget.isEmpty()) {
                Command.sendMessage("\u00a7cNo clear path to target");
                this.resetState();
                return;
            }
            for (class_243 position : pathToTarget) {
                if (this.teleportToPosition(position)) continue;
                Command.sendMessage("\u00a7cTeleport failed, aborting");
                this.returnToOrigin(pathToTarget);
                return;
            }
            if (!this.attackEntity(target)) {
                Command.sendMessage("\u00a7cAttack failed");
            }
            this.returnToOrigin(pathToTarget);
        }
        catch (Exception e) {
            Command.sendMessage("\u00a7cReach attack failed: " + e.getMessage());
            if (this.originalPosition != null) {
                this.teleportToPosition(this.originalPosition);
            }
        }
        finally {
            this.resetState();
        }
    }

    private List<class_243> calculateTeleportPath(class_243 start, class_243 target) {
        class_243 nextPos;
        ArrayList<class_243> path = new ArrayList<class_243>();
        class_243 direction = target.method_1020(start);
        double totalDistance = direction.method_1033();
        double adjustedDistance = Math.max(0.0, totalDistance - 2.0);
        if (adjustedDistance <= (double)(this.teleportDistance.getValue() * 2) && this.isPathClear(start, target) && this.isPositionSafe(target)) {
            path.add(target);
            return path;
        }
        class_243 normalizedDirection = direction.method_1029();
        double tpDist = this.teleportDistance.getValue().intValue();
        int teleportCount = (int)Math.ceil(adjustedDistance / tpDist);
        double actualStepSize = adjustedDistance / (double)teleportCount;
        class_243 currentPos = start;
        for (int i = 1; i <= teleportCount && this.isPositionSafe(nextPos = start.method_1019(normalizedDirection.method_1021(actualStepSize * (double)i))) && this.isPathClear(currentPos, nextPos); ++i) {
            path.add(nextPos);
            currentPos = nextPos;
        }
        return path;
    }

    private boolean isPositionSafe(class_243 position) {
        if (this.mc.field_1687 == null) {
            return false;
        }
        class_2338 base = class_2338.method_49638((class_2374)position);
        return !this.mc.field_1687.method_8320(base).method_26212((class_1922)this.mc.field_1687, base) && !this.mc.field_1687.method_8320(base.method_10084()).method_26212((class_1922)this.mc.field_1687, base.method_10084());
    }

    private boolean isPathClear(class_243 from, class_243 to) {
        if (this.mc.field_1687 == null) {
            return false;
        }
        class_3959 context = new class_3959(from, to, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724);
        return this.mc.field_1687.method_17742(context).method_17783().equals((Object)class_239.class_240.field_1333);
    }

    private boolean teleportToPosition(class_243 position) {
        if (!this.isValidGameState()) {
            return false;
        }
        try {
            class_2828.class_2829 packet = new class_2828.class_2829(position.field_1352, position.field_1351, position.field_1350, this.mc.field_1724.method_24828(), this.mc.field_1724.method_5715());
            this.mc.method_1562().method_52787((class_2596)packet);
            this.mc.field_1724.method_23327(position.field_1352, position.field_1351, position.field_1350);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private void returnToOrigin(List<class_243> forwardPath) {
        try {
            for (int i = forwardPath.size() - 2; i >= 0; --i) {
                this.teleportToPosition(forwardPath.get(i));
            }
            if (this.originalPosition != null) {
                this.teleportToPosition(this.originalPosition);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private boolean attackEntity(class_1297 target) {
        if (!this.isValidGameState() || target == null) {
            return false;
        }
        try {
            class_2824 attackPacket = class_2824.method_34206((class_1297)target, (boolean)this.mc.field_1724.method_5715());
            this.mc.method_1562().method_52787((class_2596)attackPacket);
            this.mc.field_1724.method_6104(class_1268.field_5808);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private void resetState() {
        this.isExecutingReach = false;
        this.originalPosition = null;
    }
}
