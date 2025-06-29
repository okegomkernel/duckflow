/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_310
 */
package com.duckflow.features.modules.combat;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_310;

public class KillAura
extends Module {
    public final Setting<Float> distance = this.num("Distance", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(6.0f));
    public final Setting<Boolean> players = this.bool("Attack Players", true);
    public final Setting<Boolean> mobs = this.bool("Attack Mobs", true);
    public final Setting<Float> hitDelay = this.num("Delay", Float.valueOf(10.0f), Float.valueOf(0.0f), Float.valueOf(20.0f));
    public final Setting<Float> maxTargets = this.num("Targets", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(25.0f));
    private final class_310 mc = class_310.method_1551();
    private int ticksSinceLastHit = 0;
    private List<class_1309> currentTargets;

    public KillAura() {
        super("Kill Aura", "Attacks players/entities even if you aren't clicking/looking at them.", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        ++this.ticksSinceLastHit;
        if ((float)this.ticksSinceLastHit < this.hitDelay.getValue().floatValue()) {
            return;
        }
        ArrayList<class_1309> targets = new ArrayList<class_1309>();
        for (class_1297 e2 : this.mc.field_1687.method_18112()) {
            if (!(e2 instanceof class_1309) || !this.isValidTarget((class_1309)e2)) continue;
            targets.add((class_1309)e2);
        }
        targets.sort(Comparator.comparingDouble(e -> this.mc.field_1724.method_5858((class_1297)e)));
        this.currentTargets = targets.stream().limit(this.maxTargets.getValue().intValue()).collect(Collectors.toList());
        for (class_1309 target : this.currentTargets) {
            if (!target.method_5805()) continue;
            assert (this.mc.field_1761 != null);
            this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, (class_1297)target);
            this.mc.field_1724.method_6104(class_1268.field_5808);
        }
        this.ticksSinceLastHit = 0;
    }

    private boolean isValidTarget(class_1309 entity) {
        if (entity == this.mc.field_1724 || entity.method_5722((class_1297)this.mc.field_1724) || entity.method_5767()) {
            return false;
        }
        if (!entity.method_5805() || entity.method_29504()) {
            return false;
        }
        if (entity.method_5739((class_1297)this.mc.field_1724) > this.distance.getValue().floatValue()) {
            return false;
        }
        if (entity instanceof class_1657 && this.players.getValue().booleanValue()) {
            return true;
        }
        return entity instanceof class_1308 && this.mobs.getValue() != false;
    }
}
