/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_310
 *  net.minecraft.class_3966
 */
package com.duckflow.features.modules.combat;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_310;
import net.minecraft.class_3966;

public class TriggerBot
extends Module {
    public final Setting<Float> distance = this.num("Distance", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(6.0f));
    public final Setting<Boolean> players = this.bool("Attack Players", true);
    public final Setting<Boolean> mobs = this.bool("Attack Mobs", true);
    public final Setting<Float> hitDelay = this.num("Delay", Float.valueOf(10.0f), Float.valueOf(0.0f), Float.valueOf(20.0f));
    private final class_310 mc = class_310.method_1551();
    private int ticksSinceLastHit = 0;

    public TriggerBot() {
        super("Trigger Bot", "Attacks players/entities you are looking at.", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        ++this.ticksSinceLastHit;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null || this.mc.field_1765 == null) {
            return;
        }
        if (this.mc.field_1765.method_17783() != class_239.class_240.field_1331) {
            return;
        }
        class_3966 entityHitResult = (class_3966)this.mc.field_1765;
        class_1297 target = entityHitResult.method_17782();
        if (!(target instanceof class_1309)) {
            return;
        }
        if (target == this.mc.field_1724) {
            return;
        }
        float range = this.distance.getValue().floatValue();
        if (this.mc.field_1724.method_5858(target) > (double)(range * range)) {
            return;
        }
        if (target instanceof class_1657 && !this.players.getValue().booleanValue()) {
            return;
        }
        if (target instanceof class_1308 && !this.mobs.getValue().booleanValue()) {
            return;
        }
        if ((float)this.ticksSinceLastHit >= this.hitDelay.getValue().floatValue()) {
            this.attack(target);
            this.ticksSinceLastHit = 0;
        }
    }

    private void attack(class_1297 entity) {
        assert (this.mc.field_1761 != null);
        this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, entity);
        assert (this.mc.field_1724 != null);
        this.mc.field_1724.method_6104(class_1268.field_5808);
    }
}
