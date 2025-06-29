/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2824$class_5907
 *  net.minecraft.class_2828$class_2829
 */
package com.duckflow.features.modules.combat;

import com.duckflow.event.impl.PacketEvent;
import com.duckflow.features.modules.Module;
import com.duckflow.util.models.Timer;
import com.google.common.eventbus.Subscribe;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2828;

public class Criticals
extends Module {
    private final Timer timer = new Timer();

    public Criticals() {
        super("Criticals", "Makes you do critical hits", Module.Category.COMBAT, true, false, false);
    }

    @Subscribe
    private void onPacketSend(PacketEvent.Send event) {
        class_2596<?> class_25962 = event.getPacket();
        if (class_25962 instanceof class_2824) {
            class_2824 packet = (class_2824)class_25962;
            if (packet.field_12871.method_34211() == class_2824.class_5907.field_29172) {
                class_1297 entity = Criticals.mc.field_1687.method_8469(packet.field_12870);
                if (entity == null || entity instanceof class_1511 || !Criticals.mc.field_1724.method_24828() || !(entity instanceof class_1309) || !this.timer.passedMs(0L)) {
                    return;
                }
                boolean bl = Criticals.mc.field_1724.field_5976;
                Criticals.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(Criticals.mc.field_1724.method_23317(), Criticals.mc.field_1724.method_23318() + (double)0.1f, Criticals.mc.field_1724.method_23321(), false, bl));
                Criticals.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(Criticals.mc.field_1724.method_23317(), Criticals.mc.field_1724.method_23318(), Criticals.mc.field_1724.method_23321(), false, bl));
                Criticals.mc.field_1724.method_7277(entity);
                this.timer.reset();
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        return "Packet";
    }
}
