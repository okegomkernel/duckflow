/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1887
 *  net.minecraft.class_5321
 *  net.minecraft.class_6880
 */
package com.duckflow.util;

import com.duckflow.util.traits.Util;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_5321;
import net.minecraft.class_6880;

public final class EnchantmentUtil
implements Util {
    private EnchantmentUtil() {
        throw new IllegalArgumentException("\u043f\u043e\u0448\u0435\u043b \u043d\u0430\u0445\u0443\u0439");
    }

    public static int getLevel(class_5321<class_1887> key, class_1799 stack) {
        if (stack.method_7960()) {
            return 0;
        }
        for (Object2IntMap.Entry enchantment : stack.method_58657().method_57539()) {
            if (!((class_6880)enchantment.getKey()).method_40225(key)) continue;
            return enchantment.getIntValue();
        }
        return 0;
    }

    public static int getLevel(class_5321<class_1887> key, class_1304 slot, class_1309 entity) {
        return EnchantmentUtil.getLevel(key, entity.method_6118(slot));
    }

    public static int getLevel(class_5321<class_1887> key, class_1304 slot) {
        return EnchantmentUtil.getLevel(key, slot, (class_1309)EnchantmentUtil.mc.field_1724);
    }

    public static boolean has(class_5321<class_1887> key, class_1799 stack) {
        return EnchantmentUtil.getLevel(key, stack) > 0;
    }

    public static boolean has(class_5321<class_1887> key, class_1304 slot, class_1309 entity) {
        return EnchantmentUtil.getLevel(key, slot, entity) > 0;
    }

    public static boolean has(class_5321<class_1887> key, class_1304 slot) {
        return EnchantmentUtil.getLevel(key, slot) > 0;
    }
}
