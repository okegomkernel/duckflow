/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_5321
 *  net.minecraft.class_640
 */
package com.duckflow.features.modules.client;

import com.duckflow.DuckFlowClient;
import com.duckflow.event.impl.Render2DEvent;
import com.duckflow.features.modules.Module;
import com.duckflow.features.settings.Setting;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_5321;
import net.minecraft.class_640;

public class HudModule
extends Module {
    public Setting<Boolean> brand = this.bool("Client Brand", true);
    public Setting<Boolean> fps = this.bool("FPS", true);
    public Setting<Boolean> pos = this.bool("Coordinates", true);
    public Setting<Boolean> direction = this.bool("Direction", true);
    public Setting<Boolean> biome = this.bool("Biome", true);
    public Setting<Boolean> ping = this.bool("Ping", true);
    public Setting<Boolean> serverIp = this.bool("Server IP", true);
    public Setting<Boolean> time = this.bool("Time", true);
    public Setting<Boolean> tps = this.bool("TPS", true);
    public Setting<Boolean> health = this.bool("Health", true);
    public Setting<Boolean> hunger = this.bool("Hunger", true);
    public Setting<Boolean> armor = this.bool("Armor", true);
    public Setting<Boolean> worldTime = this.bool("World Time", true);
    public Setting<Boolean> dimension = this.bool("Dimension", true);
    public Setting<Boolean> colored = this.bool("Colored Text", true);
    public Setting<Integer> xPos = this.num("X", 2, 2, 840);
    public Setting<Integer> yPos = this.num("Y", 2, 2, 435);
    public Setting<Integer> spacing = this.num("Spacing", 10, 10, 30);

    public HudModule() {
        super("Hud", "Customizable HUD", Module.Category.CLIENT, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        int x = this.xPos.getValue();
        int y = this.yPos.getValue();
        int line = 0;
        if (this.brand.getValue().booleanValue()) {
            this.draw(event, "\u00a76DuckFlow " + DuckFlowClient.VERSION, x, y + this.spacing.getValue() * line++);
        }
        if (this.fps.getValue().booleanValue()) {
            this.draw(event, this.format("\u00a7a", mc.method_47599() + " FPS"), x, y + this.spacing.getValue() * line++);
        }
        if (this.pos.getValue().booleanValue()) {
            class_243 posVec = HudModule.mc.field_1724.method_19538();
            this.draw(event, this.format("\u00a7b", String.format("X: %d, Y: %d, Z: %d", Math.round(posVec.field_1352), Math.round(posVec.field_1351), Math.round(posVec.field_1350))), x, y + this.spacing.getValue() * line++);
        }
        if (this.direction.getValue().booleanValue()) {
            class_2350 facing = HudModule.mc.field_1724.method_5735();
            this.draw(event, this.format("\u00a7d", "Facing: " + facing.toString()), x, y + this.spacing.getValue() * line++);
        }
        if (this.biome.getValue().booleanValue()) {
            String biomeName = ((class_5321)HudModule.mc.field_1724.method_37908().method_23753(HudModule.mc.field_1724.method_24515()).method_40230().get()).method_29177().method_12832();
            this.draw(event, this.format("\u00a73", "Biome: " + biomeName), x, y + this.spacing.getValue() * line++);
        }
        if (this.ping.getValue().booleanValue()) {
            class_640 entry = mc.method_1562().method_2871(HudModule.mc.field_1724.method_5667());
            int playerPing = entry != null ? entry.method_2959() : -1;
            this.draw(event, this.format("\u00a7e", "Ping: " + playerPing + "ms"), x, y + this.spacing.getValue() * line++);
        }
        if (this.serverIp.getValue().booleanValue()) {
            String ip = mc.method_1558() != null ? HudModule.mc.method_1558().field_3761 : "Singleplayer";
            this.draw(event, this.format("\u00a77", "Server: " + ip), x, y + this.spacing.getValue() * line++);
        }
        if (this.time.getValue().booleanValue()) {
            String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.draw(event, this.format("\u00a7f", "Time: " + now), x, y + this.spacing.getValue() * line++);
        }
        if (this.tps.getValue().booleanValue()) {
            this.draw(event, this.format("\u00a7a", "TPS: 20.0"), x, y + this.spacing.getValue() * line++);
        }
        if (this.health.getValue().booleanValue()) {
            float healthVal = HudModule.mc.field_1724.method_6032();
            this.draw(event, this.format("\u00a7c", "Health: " + Math.round(healthVal)), x, y + this.spacing.getValue() * line++);
        }
        if (this.hunger.getValue().booleanValue()) {
            int hungerVal = HudModule.mc.field_1724.method_7344().method_7586();
            this.draw(event, this.format("\u00a76", "Hunger: " + hungerVal), x, y + this.spacing.getValue() * line++);
        }
        if (this.armor.getValue().booleanValue()) {
            int armorVal = HudModule.mc.field_1724.method_6096();
            this.draw(event, this.format("\u00a79", "Armor: " + armorVal), x, y + this.spacing.getValue() * line++);
        }
        if (this.worldTime.getValue().booleanValue()) {
            long timeTicks = HudModule.mc.field_1687.method_8532() % 24000L;
            this.draw(event, this.format("\u00a72", "World Time: " + timeTicks + " ticks"), x, y + this.spacing.getValue() * line++);
        }
        if (this.dimension.getValue().booleanValue()) {
            String dimension = HudModule.mc.field_1687.method_27983().method_29177().method_12832();
            this.draw(event, this.format("\u00a7f", "Dimension: " + dimension), x, y + this.spacing.getValue() * line++);
        }
    }

    private void draw(Render2DEvent event, String text, int x, int y) {
        event.getContext().method_25303(HudModule.mc.field_1772, text, x, y, -1);
    }

    private String format(String colorCode, String text) {
        return this.colored.getValue() != false ? colorCode + text : text;
    }
}
