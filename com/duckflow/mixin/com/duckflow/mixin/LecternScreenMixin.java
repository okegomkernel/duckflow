/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  net.minecraft.class_1703
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_2371
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2813
 *  net.minecraft.class_310
 *  net.minecraft.class_364
 *  net.minecraft.class_3935
 *  net.minecraft.class_4185$class_7840
 *  net.minecraft.class_437
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2813;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_3935;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_3935.class})
public class LecternScreenMixin
extends class_437 {
    private final class_310 mc = class_310.method_1551();

    protected LecternScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(at={@At(value="TAIL")}, method={"init"})
    public void init(CallbackInfo ci) {
        this.method_37063((class_364)new class_4185.class_7840(class_2561.method_30163((String)"Lag Server"), button -> {
            class_1703 screenHandler = this.field_22787.field_1724.field_7512;
            class_2371 defaultedList = screenHandler.field_7761;
            int i = defaultedList.size();
            ArrayList list = Lists.newArrayListWithCapacity((int)i);
            for (class_1735 slot : defaultedList) {
                list.add(slot.method_7677().method_7972());
            }
            Int2ObjectOpenHashMap int2ObjectMap = new Int2ObjectOpenHashMap();
            for (int slot = 0; slot < i; ++slot) {
                class_1799 current;
                class_1799 original = (class_1799)list.get(slot);
                if (class_1799.method_7973((class_1799)original, (class_1799)(current = ((class_1735)defaultedList.get(slot)).method_7677()))) continue;
                int2ObjectMap.put(slot, (Object)current.method_7972());
            }
            class_1799 cursorStack = this.field_22787.field_1724.field_7512.method_34255();
            this.field_22787.method_1562().method_52787((class_2596)new class_2813(screenHandler.field_7763, screenHandler.method_37421(), 0, 0, class_1713.field_7794, cursorStack, (Int2ObjectMap)int2ObjectMap));
            this.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)"[Lectern Lagger] Attempting to lag the server. May not work!"), false);
            button.field_22763 = false;
        }).method_46433(5, 25).method_46437(100, 20).method_46431());
    }
}
