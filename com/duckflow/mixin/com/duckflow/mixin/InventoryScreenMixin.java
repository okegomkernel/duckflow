/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1713
 *  net.minecraft.class_1723
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2820
 *  net.minecraft.class_310
 *  net.minecraft.class_364
 *  net.minecraft.class_4185$class_7840
 *  net.minecraft.class_419
 *  net.minecraft.class_437
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 *  net.minecraft.class_518
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2820;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_419;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_490;
import net.minecraft.class_518;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_490.class})
public abstract class InventoryScreenMixin
extends class_465<class_1723>
implements class_518 {
    private final class_310 mc = class_310.method_1551();

    public InventoryScreenMixin(class_1723 container, class_1661 playerInventory, class_2561 name) {
        super((class_1703)container, playerInventory, name);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    protected void init(CallbackInfo ci) {
        this.method_37063((class_364)new class_4185.class_7840((class_2561)class_2561.method_43470((String)"Paper Dupe"), button -> this.bookDupe()).method_46433(500, 160).method_46437(67, 20).method_46431());
    }

    @Unique
    private void bookDupe() {
        if (this.mc.field_1724 == null || this.mc.field_1761 == null) {
            return;
        }
        for (int i = 0; i < this.mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_8674) continue;
            int selectedSlot = this.mc.field_1724.method_31548().field_7545;
            if (i != selectedSlot) {
                this.mc.field_1761.method_2906(this.mc.field_1724.field_7512.field_7763, i, selectedSlot, class_1713.field_7791, (class_1657)this.mc.field_1724);
            }
            for (int slot = 9; slot <= 44; ++slot) {
                if (selectedSlot == slot - 36) continue;
                this.mc.field_1761.method_2906(this.mc.field_1724.field_7512.field_7763, slot, 10, class_1713.field_7795, (class_1657)this.mc.field_1724);
            }
            this.mc.field_1724.field_3944.method_52787((class_2596)new class_2820(this.mc.field_1724.method_31548().field_7545, new ArrayList(), Optional.of("BookUpdateC2SPacket.launch(458*defaultVar)")));
            this.mc.execute(() -> this.mc.method_1507((class_437)new class_419(this.mc.field_1755, class_2561.method_30163((String)"\u00a74Lost connection :/"), class_2561.method_30163((String)"DuckFlow attempted to dupe!\nKicked for: BookUpdateC2SPacket.optionTooLong\n\n(Expect another kick screen)"))));
            break;
        }
    }
}
