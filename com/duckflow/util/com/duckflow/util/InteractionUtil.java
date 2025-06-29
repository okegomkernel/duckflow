/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1269$class_9860
 *  net.minecraft.class_1269$class_9861
 *  net.minecraft.class_1292
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1303
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1683
 *  net.minecraft.class_1799
 *  net.minecraft.class_1887
 *  net.minecraft.class_1893
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2879
 *  net.minecraft.class_3486
 *  net.minecraft.class_3965
 *  net.minecraft.class_5321
 */
package com.duckflow.util;

import com.duckflow.util.EnchantmentUtil;
import com.duckflow.util.traits.Util;
import java.util.Iterator;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1292;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1303;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1683;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2846;
import net.minecraft.class_2879;
import net.minecraft.class_3486;
import net.minecraft.class_3965;
import net.minecraft.class_5321;

public class InteractionUtil
implements Util {
    public static boolean canBreak(class_2338 blockPos, class_2680 state) {
        if (!InteractionUtil.mc.field_1724.method_7337() && state.method_26214((class_1922)InteractionUtil.mc.field_1687, blockPos) < 0.0f) {
            return false;
        }
        return state.method_26218((class_1922)InteractionUtil.mc.field_1687, blockPos) != class_259.method_1073();
    }

    public static boolean isPlaceable(class_2338 pos, boolean entityCheck) {
        if (!InteractionUtil.mc.field_1687.method_8320(pos).method_45474()) {
            return false;
        }
        Iterator iterator = InteractionUtil.mc.field_1687.method_8390(class_1297.class, new class_238(pos), e -> !(e instanceof class_1683) && !(e instanceof class_1542) && !(e instanceof class_1303)).iterator();
        if (iterator.hasNext()) {
            class_1297 e2 = (class_1297)iterator.next();
            if (e2 instanceof class_1657) {
                return false;
            }
            return !entityCheck;
        }
        return true;
    }

    public static boolean breakBlock(class_2338 pos) {
        if (!InteractionUtil.canBreak(pos, InteractionUtil.mc.field_1687.method_8320(pos))) {
            return false;
        }
        class_2338 bp = pos instanceof class_2338.class_2339 ? new class_2338((class_2382)pos) : pos;
        mc.method_1562().method_52787((class_2596)new class_2846(class_2846.class_2847.field_12968, bp, class_2350.field_11036));
        InteractionUtil.mc.field_1724.method_6104(class_1268.field_5808);
        mc.method_1562().method_52787((class_2596)new class_2846(class_2846.class_2847.field_12973, bp, class_2350.field_11036));
        mc.method_1562().method_52787((class_2596)new class_2879(class_1268.field_5808));
        return true;
    }

    public static void useItem(class_2338 pos) {
        InteractionUtil.useItem(pos, class_1268.field_5808);
    }

    public static void useItem(class_2338 pos, class_1268 hand) {
        class_1269.class_9860 success;
        if (InteractionUtil.mc.field_1687 == null || InteractionUtil.mc.field_1724 == null || InteractionUtil.mc.field_1761 == null) {
            return;
        }
        class_2350 direction = InteractionUtil.mc.field_1765 != null ? ((class_3965)InteractionUtil.mc.field_1765).method_17780() : class_2350.field_11033;
        class_1269 result = InteractionUtil.mc.field_1761.method_2896(InteractionUtil.mc.field_1724, hand, new class_3965(class_243.method_24953((class_2382)pos), direction, pos, false));
        if (result instanceof class_1269.class_9860 && (success = (class_1269.class_9860)result).comp_2909() != class_1269.class_9861.field_52426) {
            InteractionUtil.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(hand));
        }
    }

    public static boolean place(class_2338 pos, boolean airPlace) {
        return InteractionUtil.place(pos, airPlace, class_1268.field_5808);
    }

    public static boolean place(class_2338 pos, boolean airPlace, class_1268 hand) {
        class_1269.class_9860 success;
        class_2338 bp;
        class_1269 result;
        if (InteractionUtil.mc.field_1687 == null || InteractionUtil.mc.field_1724 == null || InteractionUtil.mc.field_1761 == null) {
            return false;
        }
        if (!InteractionUtil.isPlaceable(pos, false)) {
            return false;
        }
        class_2350 direction = InteractionUtil.calcSide(pos);
        if (direction == null) {
            if (airPlace) {
                direction = InteractionUtil.mc.field_1765 != null ? ((class_3965)InteractionUtil.mc.field_1765).method_17780() : class_2350.field_11033;
            } else {
                return false;
            }
        }
        if ((result = InteractionUtil.mc.field_1761.method_2896(InteractionUtil.mc.field_1724, hand, new class_3965(airPlace ? class_243.method_24953((class_2382)pos) : class_243.method_24953((class_2382)pos).method_43206(direction.method_10153(), 0.5), airPlace ? direction : direction.method_10153(), bp = airPlace ? pos : pos.method_10093(direction), false))) instanceof class_1269.class_9860 && (success = (class_1269.class_9860)result).comp_2909() != class_1269.class_9861.field_52426) {
            InteractionUtil.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(hand));
        }
        return true;
    }

    public static class_2350 calcSide(class_2338 pos) {
        for (class_2350 d : class_2350.values()) {
            if (InteractionUtil.mc.field_1687.method_8320(pos.method_10081(d.method_62675())).method_45474()) continue;
            return d;
        }
        return null;
    }

    public static double getBlockBreakingSpeed(int slot, class_2338 pos) {
        return InteractionUtil.getBlockBreakingSpeed(slot, InteractionUtil.mc.field_1687.method_8320(pos));
    }

    public static double getBlockBreakingSpeed(int slot, class_2680 block) {
        float hardness;
        class_1799 tool;
        int efficiency;
        double speed = ((class_1799)InteractionUtil.mc.field_1724.method_31548().field_7547.get(slot)).method_7924(block);
        if (speed > 1.0 && (efficiency = EnchantmentUtil.getLevel((class_5321<class_1887>)class_1893.field_9131, tool = InteractionUtil.mc.field_1724.method_31548().method_5438(slot))) > 0 && !tool.method_7960()) {
            speed += (double)(efficiency * efficiency + 1);
        }
        if (class_1292.method_5576((class_1309)InteractionUtil.mc.field_1724)) {
            speed *= (double)(1.0f + (float)(class_1292.method_5575((class_1309)InteractionUtil.mc.field_1724) + 1) * 0.2f);
        }
        if (InteractionUtil.mc.field_1724.method_6059(class_1294.field_5901)) {
            float k = switch (InteractionUtil.mc.field_1724.method_6112(class_1294.field_5901).method_5578()) {
                case 0 -> 0.3f;
                case 1 -> 0.09f;
                case 2 -> 0.0027f;
                default -> 8.1E-4f;
            };
            speed *= (double)k;
        }
        if (InteractionUtil.mc.field_1724.method_5777(class_3486.field_15517) && EnchantmentUtil.has((class_5321<class_1887>)class_1893.field_9105, class_1304.field_6169)) {
            speed /= 5.0;
        }
        if (!InteractionUtil.mc.field_1724.method_24828()) {
            speed /= 5.0;
        }
        if ((hardness = block.method_26214(null, null)) == -1.0f) {
            return 0.0;
        }
        float ticks = (float)(Math.floor(1.0 / (speed /= (double)(hardness / (float)(!block.method_29291() || ((class_1799)InteractionUtil.mc.field_1724.method_31548().field_7547.get(slot)).method_7951(block) ? 30 : 100)))) + 1.0);
        return (long)(ticks / 20.0f * 1000.0f);
    }

    public static class_2350 right(class_2350 direction) {
        return switch (direction) {
            case class_2350.field_11034 -> class_2350.field_11035;
            case class_2350.field_11035 -> class_2350.field_11039;
            case class_2350.field_11039 -> class_2350.field_11043;
            case class_2350.field_11043 -> class_2350.field_11034;
            default -> throw new IllegalStateException("Unexpected value: " + String.valueOf(direction));
        };
    }

    public static class_2350 left(class_2350 direction) {
        return switch (direction) {
            case class_2350.field_11034 -> class_2350.field_11043;
            case class_2350.field_11043 -> class_2350.field_11039;
            case class_2350.field_11039 -> class_2350.field_11035;
            case class_2350.field_11035 -> class_2350.field_11034;
            default -> throw new IllegalStateException("Unexpected value: " + String.valueOf(direction));
        };
    }
}
