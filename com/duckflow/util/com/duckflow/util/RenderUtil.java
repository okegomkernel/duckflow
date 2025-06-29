/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  net.minecraft.class_9974
 */
package com.duckflow.util;

import com.duckflow.util.traits.Util;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import net.minecraft.class_9974;

public class RenderUtil
implements Util {
    private static final class_310 mc = class_310.method_1551();

    public static void rect(class_4587 stack, float x1, float y1, float x2, float y2, int color) {
        RenderUtil.rectFilled(stack, x1, y1, x2, y2, color);
    }

    public static void rect(class_4587 stack, float x1, float y1, float x2, float y2, int color, float width) {
        RenderUtil.drawHorizontalLine(stack, x1, x2, y1, color, width);
        RenderUtil.drawVerticalLine(stack, x2, y1, y2, color, width);
        RenderUtil.drawHorizontalLine(stack, x1, x2, y2, color, width);
        RenderUtil.drawVerticalLine(stack, x1, y1, y2, color, width);
    }

    protected static void drawHorizontalLine(class_4587 matrices, float x1, float x2, float y, int color) {
        if (x2 < x1) {
            float i = x1;
            x1 = x2;
            x2 = i;
        }
        RenderUtil.rectFilled(matrices, x1, y, x2 + 1.0f, y + 1.0f, color);
    }

    protected static void drawVerticalLine(class_4587 matrices, float x, float y1, float y2, int color) {
        if (y2 < y1) {
            float i = y1;
            y1 = y2;
            y2 = i;
        }
        RenderUtil.rectFilled(matrices, x, y1 + 1.0f, x + 1.0f, y2, color);
    }

    protected static void drawHorizontalLine(class_4587 matrices, float x1, float x2, float y, int color, float width) {
        if (x2 < x1) {
            float i = x1;
            x1 = x2;
            x2 = i;
        }
        RenderUtil.rectFilled(matrices, x1, y, x2 + width, y + width, color);
    }

    protected static void drawVerticalLine(class_4587 matrices, float x, float y1, float y2, int color, float width) {
        if (y2 < y1) {
            float i = y1;
            y1 = y2;
            y2 = i;
        }
        RenderUtil.rectFilled(matrices, x, y1 + width, x + width, y2, color);
    }

    public static void rectFilled(class_4587 matrix, float x1, float y1, float x2, float y2, int color) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float g = (float)(color >> 16 & 0xFF) / 255.0f;
        float h = (float)(color >> 8 & 0xFF) / 255.0f;
        float j = (float)(color & 0xFF) / 255.0f;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        bufferBuilder.method_22918(matrix.method_23760().method_23761(), x1, y2, 0.0f).method_22915(g, h, j, f);
        bufferBuilder.method_22918(matrix.method_23760().method_23761(), x2, y2, 0.0f).method_22915(g, h, j, f);
        bufferBuilder.method_22918(matrix.method_23760().method_23761(), x2, y1, 0.0f).method_22915(g, h, j, f);
        bufferBuilder.method_22918(matrix.method_23760().method_23761(), x1, y1, 0.0f).method_22915(g, h, j, f);
        class_286.method_43433((class_9801)bufferBuilder.method_60800());
        RenderSystem.disableBlend();
    }

    public static void drawBoxFilled(class_4587 stack, class_238 box, Color c) {
        float minX = (float)(box.field_1323 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float minY = (float)(box.field_1322 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float minZ = (float)(box.field_1321 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        float maxX = (float)(box.field_1320 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float maxY = (float)(box.field_1325 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float maxZ = (float)(box.field_1324 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        class_289 tessellator = class_289.method_1348();
        RenderUtil.setup3D();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, minY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, minY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, minY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, minY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, maxY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, maxY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, maxY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, maxY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, minY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, maxY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, maxY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, minY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, minY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, maxY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, maxY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, minY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, minY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, minY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), maxX, maxY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, maxY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, minY, minZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, minY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, maxY, maxZ).method_39415(c.getRGB());
        bufferBuilder.method_22918(stack.method_23760().method_23761(), minX, maxY, minZ).method_39415(c.getRGB());
        class_286.method_43433((class_9801)bufferBuilder.method_60800());
        RenderUtil.clean3D();
    }

    public static void drawBoxFilled(class_4587 stack, class_243 vec, Color c) {
        RenderUtil.drawBoxFilled(stack, class_238.method_29968((class_243)vec), c);
    }

    public static void drawBoxFilled(class_4587 stack, class_2338 bp, Color c) {
        RenderUtil.drawBoxFilled(stack, new class_238(bp), c);
    }

    public static void drawBox(class_4587 stack, class_238 box, Color c, double lineWidth) {
        float minX = (float)(box.field_1323 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float minY = (float)(box.field_1322 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float minZ = (float)(box.field_1321 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        float maxX = (float)(box.field_1320 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float maxY = (float)(box.field_1325 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float maxZ = (float)(box.field_1324 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        RenderUtil.setup3D();
        RenderSystem.lineWidth((float)((float)lineWidth));
        RenderSystem.setShader((class_10156)class_10142.field_53864);
        RenderSystem.defaultBlendFunc();
        class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_29337);
        class_9974.method_62292((class_4587)stack, (class_4588)bufferBuilder, (double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ, (float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)((float)c.getAlpha() / 255.0f));
        class_286.method_43433((class_9801)bufferBuilder.method_60800());
        RenderUtil.clean3D();
    }

    public static void drawBox(class_4587 stack, class_243 vec, Color c, double lineWidth) {
        RenderUtil.drawBox(stack, class_238.method_29968((class_243)vec), c, lineWidth);
    }

    public static void drawBox(class_4587 stack, class_2338 bp, Color c, double lineWidth) {
        RenderUtil.drawBox(stack, new class_238(bp), c, lineWidth);
    }

    public static class_4587 matrixFrom(class_243 pos) {
        class_4587 matrices = new class_4587();
        class_4184 camera = RenderUtil.mc.field_1773.method_19418();
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrices.method_22904(pos.method_10216() - camera.method_19326().field_1352, pos.method_10214() - camera.method_19326().field_1351, pos.method_10215() - camera.method_19326().field_1350);
        return matrices;
    }

    public static void setup() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }

    public static void setup3D() {
        RenderUtil.setup();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
    }

    public static void clean() {
        RenderSystem.disableBlend();
    }

    public static void clean3D() {
        RenderUtil.clean();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
    }

    public static void drawLine(class_4587 matrices, class_243 start, class_243 end, Color color, float width) {
        RenderUtil.setup3D();
        RenderSystem.lineWidth((float)width);
        RenderSystem.setShader((class_10156)class_10142.field_53864);
        class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_1576);
        float r = (float)color.getRed() / 255.0f;
        float g = (float)color.getGreen() / 255.0f;
        float b = (float)color.getBlue() / 255.0f;
        float a = (float)color.getAlpha() / 255.0f;
        class_243 camPos = RenderUtil.mc.method_1561().field_4686.method_19326();
        bufferBuilder.method_22918(matrices.method_23760().method_23761(), (float)(start.field_1352 - camPos.field_1352), (float)(start.field_1351 - camPos.field_1351), (float)(start.field_1350 - camPos.field_1350)).method_22915(r, g, b, a);
        bufferBuilder.method_22918(matrices.method_23760().method_23761(), (float)(end.field_1352 - camPos.field_1352), (float)(end.field_1351 - camPos.field_1351), (float)(end.field_1350 - camPos.field_1350)).method_22915(r, g, b, a);
        class_286.method_43433((class_9801)bufferBuilder.method_60800());
        RenderUtil.clean3D();
    }
}
