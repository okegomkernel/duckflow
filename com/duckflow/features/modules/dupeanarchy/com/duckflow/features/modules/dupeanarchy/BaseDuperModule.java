/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 */
package com.duckflow.features.modules.dupeanarchy;

import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;

public abstract class BaseDuperModule
extends Module {
    protected final class_310 mc = class_310.method_1551();
    public Setting<Integer> amount = this.num("Dupe Amount", 64, 0, 64);
    public Setting<Integer> minAmount = this.num("Min Amount", 16, 0, 64);
    private int tickDelay = 0;

    public BaseDuperModule(String name, String description) {
        super(name, description, Module.Category.DUPEANARCHY, true, false, false);
    }

    protected abstract class_1792 getTargetItem();

    protected abstract String getCommandItemName();

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.mc.method_1558() == null || !this.mc.method_1558().field_3761.equalsIgnoreCase("play.dupeanarchy.com")) {
            this.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)"Disabling module as you arent in dupe anarchy"), false);
            this.toggle();
            return;
        }
        if (this.tickDelay > 0) {
            --this.tickDelay;
            return;
        }
        int count = this.countItems();
        if (count <= this.minAmount.getValue()) {
            String command = String.format("/dupe %d %s", this.amount.getValue(), this.getCommandItemName());
            this.mc.field_1724.field_3944.method_45730(command);
            this.tickDelay = 40;
        }
    }

    private int countItems() {
        int count = 0;
        for (class_1799 stack : this.mc.field_1724.method_31548().field_7547) {
            if (stack.method_7909() != this.getTargetItem()) continue;
            count += stack.method_7947();
        }
        return count;
    }
}
