/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2353
 *  org.jetbrains.annotations.Nullable
 */
package com.duckflow.manager;

import com.duckflow.event.impl.UpdateEvent;
import com.duckflow.features.Feature;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import org.jetbrains.annotations.Nullable;

public class HoleManager
extends Feature {
    private final int range = 8;
    private final List<Hole> holes = new ArrayList<Hole>();
    private final class_2338.class_2339 pos = new class_2338.class_2339();

    public HoleManager() {
        EVENT_BUS.register((Object)this);
    }

    @Subscribe
    private void onTick(UpdateEvent event) {
        this.holes.clear();
        for (int x = -8; x < 8; ++x) {
            for (int y = -8; y < 8; ++y) {
                for (int z = -8; z < 8; ++z) {
                    this.pos.method_10102(HoleManager.mc.field_1724.method_23317() + (double)x, HoleManager.mc.field_1724.method_23318() + (double)y, HoleManager.mc.field_1724.method_23321() + (double)z);
                    Hole hole = this.getHole((class_2338)this.pos);
                    if (hole == null) continue;
                    this.holes.add(hole);
                }
            }
        }
    }

    @Nullable
    public Hole getHole(class_2338 pos) {
        if (HoleManager.mc.field_1687.method_8320(pos).method_26204() != class_2246.field_10124) {
            return null;
        }
        HoleType type = HoleType.BEDROCK;
        for (class_2350 direction : class_2350.class_2353.field_11062) {
            class_2248 block = HoleManager.mc.field_1687.method_8320(pos.method_10093(direction)).method_26204();
            if (block == class_2246.field_10540) {
                type = HoleType.UNSAFE;
                continue;
            }
            if (block == class_2246.field_9987) continue;
            return null;
        }
        return new Hole(pos, type);
    }

    private record Hole(class_2338 pos, HoleType holeType) {
    }

    private static enum HoleType {
        BEDROCK,
        UNSAFE;

    }
}
