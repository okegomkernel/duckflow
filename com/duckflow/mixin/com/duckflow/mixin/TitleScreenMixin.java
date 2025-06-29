/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.duckflow.DuckFlowClient;
import com.duckflow.util.traits.Util;
import java.awt.Color;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_442.class})
public abstract class TitleScreenMixin
extends class_437 {
    @Unique
    private String brandText;
    @Unique
    private int textColor;

    protected TitleScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo info) {
        this.brandText = "Welcome (back) to DuckFlow client!\nClient made by iDucky with love \u2665!\nDuckFlow Version: " + DuckFlowClient.VERSION;
        this.textColor = -256;
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        int x = 3;
        int y = 3;
        long time = System.currentTimeMillis();
        float animOffset = (float)(time % 5000L) / 5000.0f;
        Color[] colors = new Color[]{new Color(255, 255, 153), new Color(255, 242, 102), new Color(255, 229, 51), new Color(255, 204, 0), new Color(255, 178, 0), new Color(255, 153, 0), new Color(255, 128, 0), new Color(255, 102, 0), new Color(255, 77, 0), new Color(255, 51, 0), new Color(255, 26, 0), new Color(255, 0, 0), new Color(204, 0, 0), new Color(153, 0, 0), new Color(102, 0, 0), new Color(51, 0, 0), new Color(0, 0, 0), new Color(51, 0, 0), new Color(102, 0, 0), new Color(153, 0, 0), new Color(204, 0, 0), new Color(255, 0, 0), new Color(255, 26, 0), new Color(255, 51, 0), new Color(255, 77, 0), new Color(255, 102, 0), new Color(255, 128, 0), new Color(255, 153, 0), new Color(255, 178, 0), new Color(255, 204, 0), new Color(255, 229, 51), new Color(255, 242, 102), new Color(255, 255, 153)};
        String[] lines = this.brandText.split("\n");
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            float xOffset = x;
            for (int j = 0; j < line.length(); ++j) {
                char c = line.charAt(j);
                float pos = (float)j / (float)line.length() + animOffset;
                float blendPos = pos % 1.0f * (float)(colors.length - 1);
                int index = (int)blendPos;
                float t = blendPos - (float)index;
                Color c1 = colors[index % colors.length];
                Color c2 = colors[(index + 1) % colors.length];
                int r = (int)((float)c1.getRed() * (1.0f - t) + (float)c2.getRed() * t);
                int g = (int)((float)c1.getGreen() * (1.0f - t) + (float)c2.getGreen() * t);
                int b = (int)((float)c1.getBlue() * (1.0f - t) + (float)c2.getBlue() * t);
                int rgb = new Color(r, g, b).getRGB();
                String s = String.valueOf(c);
                context.method_25303(Util.mc.field_1772, s, (int)xOffset, y + i * 10, rgb);
                xOffset += (float)Util.mc.field_1772.method_1727(s);
            }
        }
    }
}
