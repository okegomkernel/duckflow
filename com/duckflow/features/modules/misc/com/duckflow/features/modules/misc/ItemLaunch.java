/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1680
 *  net.minecraft.class_1681
 *  net.minecraft.class_1683
 *  net.minecraft.class_1684
 *  net.minecraft.class_1685
 *  net.minecraft.class_243
 */
package com.duckflow.features.modules.misc;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1680;
import net.minecraft.class_1681;
import net.minecraft.class_1683;
import net.minecraft.class_1684;
import net.minecraft.class_1685;
import net.minecraft.class_243;

public class ItemLaunch
extends Module {
    public Setting<Integer> strength = this.num("Strength", 100, 0, 500);
    public Setting<Boolean> tridents = this.bool("Tridents", true);
    public Setting<Boolean> pearls = this.bool("Pearls", true);
    public Setting<Boolean> snowballs = this.bool("Snowballs", true);
    public Setting<Boolean> eggs = this.bool("Eggs", true);
    public Setting<Boolean> xp = this.bool("Xp Bottles", true);
    private final Set<Integer> boostedEntities = new HashSet<Integer>();

    public ItemLaunch() {
        super("Item Launch", "Boosts velocity of thrown items by modifying projectile momentum", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (ItemLaunch.mc.field_1687 == null || ItemLaunch.mc.field_1724 == null) {
            return;
        }
        for (class_1297 entity : ItemLaunch.mc.field_1687.method_18112()) {
            if (entity == null || this.boostedEntities.contains(entity.method_5628())) continue;
            if (this.tridents.getValue().booleanValue() && entity instanceof class_1685) {
                this.boostProjectileVelocity(entity);
                continue;
            }
            if (this.pearls.getValue().booleanValue() && entity instanceof class_1684) {
                this.boostProjectileVelocity(entity);
                continue;
            }
            if (this.snowballs.getValue().booleanValue() && entity instanceof class_1680) {
                this.boostProjectileVelocity(entity);
                continue;
            }
            if (this.eggs.getValue().booleanValue() && entity instanceof class_1681) {
                this.boostProjectileVelocity(entity);
                continue;
            }
            if (!this.xp.getValue().booleanValue() || !(entity instanceof class_1683)) continue;
            this.boostProjectileVelocity(entity);
        }
    }

    private void boostProjectileVelocity(class_1297 projectile) {
        class_243 currentVel = projectile.method_18798();
        class_243 look = ItemLaunch.mc.field_1724.method_5828(1.0f).method_1029();
        double scale = (double)this.strength.getValue().intValue() / 100.0;
        class_243 newVel = currentVel.method_1019(look.method_1021(scale));
        projectile.method_18800(newVel.field_1352, newVel.field_1351, newVel.field_1350);
        this.boostedEntities.add(projectile.method_5628());
    }
}
