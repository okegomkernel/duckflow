/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package com.duckflow.util;

import com.duckflow.util.traits.Util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class MathUtil
implements Util {
    private static final Random random = new Random();

    public static int getRandom(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static double getRandom(double min, double max) {
        return class_3532.method_15350((double)(min + random.nextDouble() * max), (double)min, (double)max);
    }

    public static float getRandom(float min, float max) {
        return class_3532.method_15363((float)(min + random.nextFloat() * max), (float)min, (float)max);
    }

    public static int clamp(int num, int min, int max) {
        return num < min ? min : Math.min(num, max);
    }

    public static float clamp(float num, float min, float max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double clamp(double num, double min, double max) {
        return num < min ? min : Math.min(num, max);
    }

    public static float sin(float value) {
        return class_3532.method_15374((float)value);
    }

    public static float cos(float value) {
        return class_3532.method_15362((float)value);
    }

    public static float wrapDegrees(float value) {
        return class_3532.method_15393((float)value);
    }

    public static double wrapDegrees(double value) {
        return class_3532.method_15338((double)value);
    }

    public static class_243 roundVec(class_243 vec3d, int places) {
        return new class_243(MathUtil.round(vec3d.field_1352, places), MathUtil.round(vec3d.field_1351, places), MathUtil.round(vec3d.field_1350, places));
    }

    public static double square(double input) {
        return input * input;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.doubleValue();
    }

    public static float wrap(float valI) {
        float val = valI % 360.0f;
        if (val >= 180.0f) {
            val -= 360.0f;
        }
        if (val < -180.0f) {
            val += 360.0f;
        }
        return val;
    }

    public static class_243 direction(float yaw) {
        return new class_243(Math.cos(MathUtil.degToRad(yaw + 90.0f)), 0.0, Math.sin(MathUtil.degToRad(yaw + 90.0f)));
    }

    public static float round(float value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.floatValue();
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean descending) {
        LinkedList<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        if (descending) {
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        } else {
            list.sort(Map.Entry.comparingByValue());
        }
        LinkedHashMap result = new LinkedHashMap();
        for (Map.Entry entry : list) {
            result.put(entry.getKey(), (Comparable)entry.getValue());
        }
        return result;
    }

    public static String getTimeOfDay() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(11);
        if (timeOfDay < 12) {
            return "Good Morning ";
        }
        if (timeOfDay < 16) {
            return "Good Afternoon ";
        }
        if (timeOfDay < 21) {
            return "Good Evening ";
        }
        return "Good Night ";
    }

    public static double radToDeg(double rad) {
        return rad * (double)57.29578f;
    }

    public static double degToRad(double deg) {
        return deg * 0.01745329238474369;
    }

    public static double getIncremental(double val, double inc) {
        double one = 1.0 / inc;
        return (double)Math.round(val * one) / one;
    }

    public static double[] directionSpeed(double speed) {
        float forward = MathUtil.mc.field_1724.field_3913.field_3905;
        float side = MathUtil.mc.field_1724.field_3913.field_3907;
        float yaw = MathUtil.mc.field_1724.field_5982 + (MathUtil.mc.field_1724.method_36454() - MathUtil.mc.field_1724.field_5982) * mc.method_61966().method_60637(false);
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += (float)(forward > 0.0f ? -45 : 45);
            } else if (side < 0.0f) {
                yaw += (float)(forward > 0.0f ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            } else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        double posX = (double)forward * speed * cos + (double)side * speed * sin;
        double posZ = (double)forward * speed * sin - (double)side * speed * cos;
        return new double[]{posX, posZ};
    }

    public static List<class_243> getBlockBlocks(class_1297 entity) {
        ArrayList<class_243> vec3ds = new ArrayList<class_243>();
        class_238 bb = entity.method_5829();
        double y = entity.method_23318();
        double minX = MathUtil.round(bb.field_1323, 0);
        double minZ = MathUtil.round(bb.field_1321, 0);
        double maxX = MathUtil.round(bb.field_1320, 0);
        double maxZ = MathUtil.round(bb.field_1324, 0);
        if (minX != maxX) {
            vec3ds.add(new class_243(minX, y, minZ));
            vec3ds.add(new class_243(maxX, y, minZ));
            if (minZ != maxZ) {
                vec3ds.add(new class_243(minX, y, maxZ));
                vec3ds.add(new class_243(maxX, y, maxZ));
                return vec3ds;
            }
        } else if (minZ != maxZ) {
            vec3ds.add(new class_243(minX, y, minZ));
            vec3ds.add(new class_243(minX, y, maxZ));
            return vec3ds;
        }
        vec3ds.add(entity.method_19538());
        return vec3ds;
    }

    public static boolean areVec3dsAligned(class_243 vec3d1, class_243 vec3d2) {
        return MathUtil.areVec3dsAlignedRetarded(vec3d1, vec3d2);
    }

    public static boolean areVec3dsAlignedRetarded(class_243 vec3d1, class_243 vec3d2) {
        class_2338 pos1 = class_2338.method_49638((class_2374)vec3d1);
        class_2338 pos2 = class_2338.method_49637((double)vec3d2.field_1352, (double)vec3d1.field_1351, (double)vec3d2.field_1350);
        return pos1.equals((Object)pos2);
    }

    public static float[] calcAngle(class_243 from, class_243 to) {
        double difX = to.field_1352 - from.field_1352;
        double difY = (to.field_1351 - from.field_1351) * -1.0;
        double difZ = to.field_1350 - from.field_1350;
        double dist = Math.sqrt(difX * difX + difZ * difZ);
        return new float[]{(float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(Math.atan2(difY, dist)))};
    }
}
