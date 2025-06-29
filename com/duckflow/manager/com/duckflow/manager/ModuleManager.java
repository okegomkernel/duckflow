/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.EventBus
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 */
package com.duckflow.manager;

import com.duckflow.event.impl.Render2DEvent;
import com.duckflow.event.impl.Render3DEvent;
import com.duckflow.features.Feature;
import com.duckflow.features.modules.Module;
import com.duckflow.features.modules.client.ClickGui;
import com.duckflow.features.modules.client.HudModule;
import com.duckflow.features.modules.client.OpenProtectionV1;
import com.duckflow.features.modules.combat.Criticals;
import com.duckflow.features.modules.combat.InfiniteReach;
import com.duckflow.features.modules.combat.KillAura;
import com.duckflow.features.modules.combat.TriggerBot;
import com.duckflow.features.modules.dupeanarchy.AutoAnchorDuper;
import com.duckflow.features.modules.dupeanarchy.AutoCrystalDuper;
import com.duckflow.features.modules.dupeanarchy.AutoGlowstoneDuper;
import com.duckflow.features.modules.dupeanarchy.AutoObsidianDuper;
import com.duckflow.features.modules.misc.FakePlayer;
import com.duckflow.features.modules.misc.Friender;
import com.duckflow.features.modules.misc.InvMove;
import com.duckflow.features.modules.misc.ItemLaunch;
import com.duckflow.features.modules.misc.Zoom;
import com.duckflow.features.modules.movement.Flight;
import com.duckflow.features.modules.movement.Jesus;
import com.duckflow.features.modules.movement.PacketFly;
import com.duckflow.features.modules.movement.ReverseStep;
import com.duckflow.features.modules.movement.Speed;
import com.duckflow.features.modules.movement.Step;
import com.duckflow.features.modules.player.FastUse;
import com.duckflow.features.modules.player.NoFall;
import com.duckflow.features.modules.player.Velocity;
import com.duckflow.features.modules.render.BlockHighlight;
import com.duckflow.features.modules.render.ESP;
import com.duckflow.features.modules.render.Fullbright;
import com.duckflow.features.modules.render.Tracers;
import com.duckflow.util.traits.Jsonable;
import com.duckflow.util.traits.Util;
import com.google.common.eventbus.EventBus;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ModuleManager
implements Jsonable,
Util {
    public List<Module> modules = new ArrayList<Module>();
    public List<Module> sortedModules = new ArrayList<Module>();
    public List<String> sortedModulesABC = new ArrayList<String>();
    private static boolean hasRegisteredPlayerModules = false;

    public void init() {
        this.modules.add(new ClickGui());
        this.modules.add(new HudModule());
        this.modules.add(new Criticals());
        this.modules.add(new InfiniteReach());
        this.modules.add(new KillAura());
        this.modules.add(new TriggerBot());
        this.modules.add(new FakePlayer());
        this.modules.add(new Friender());
        this.modules.add(new InvMove());
        this.modules.add(new ItemLaunch());
        this.modules.add(new Zoom());
        this.modules.add(new Flight());
        this.modules.add(new Jesus());
        this.modules.add(new PacketFly());
        this.modules.add(new ReverseStep());
        this.modules.add(new Speed());
        this.modules.add(new Step());
        this.modules.add(new FastUse());
        this.modules.add(new NoFall());
        this.modules.add(new Velocity());
        this.modules.add(new BlockHighlight());
        this.modules.add(new ESP());
        this.modules.add(new Fullbright());
        this.modules.add(new Tracers());
        this.modules.add(new AutoAnchorDuper());
        this.modules.add(new AutoCrystalDuper());
        this.modules.add(new AutoGlowstoneDuper());
        this.modules.add(new AutoObsidianDuper());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.field_1724 == null || client.field_1687 == null) {
                return;
            }
            if (!hasRegisteredPlayerModules) {
                String name = client.field_1724.method_5477().getString();
                if (name.equals("BoyKisser12")) {
                    this.modules.add(new OpenProtectionV1());
                }
                hasRegisteredPlayerModules = true;
            }
        });
    }

    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }

    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module module : this.modules) {
            if (!clazz.isInstance(module)) continue;
            return (T)module;
        }
        return null;
    }

    public void enableModule(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.disable();
        }
    }

    public void enableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.disable();
        }
    }

    public boolean isModuleEnabled(String name) {
        Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }

    public boolean isModuleEnabled(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        return module != null && module.isOn();
    }

    public Module getModuleByDisplayName(String displayName) {
        for (Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) continue;
            return module;
        }
        return null;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            enabledModules.add(module);
        }
        return enabledModules;
    }

    public ArrayList<String> getEnabledModulesName() {
        ArrayList<String> enabledModules = new ArrayList<String>();
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            enabledModules.add(module.getFullArrayString());
        }
        return enabledModules;
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesCategory = new ArrayList<Module>();
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                modulesCategory.add((Module)module);
            }
        });
        return modulesCategory;
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(arg_0 -> ((EventBus)EVENT_BUS).register(arg_0));
        this.modules.forEach(Module::onLoad);
    }

    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }

    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }

    public void sortModules(boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().sorted(Comparator.comparing(module -> ModuleManager.mc.field_1772.method_1727(module.getFullArrayString()) * (reverse ? -1 : 1))).collect(Collectors.toList());
    }

    public void sortModulesABC() {
        this.sortedModulesABC = new ArrayList<String>(this.getEnabledModulesName());
        this.sortedModulesABC.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public void onUnload() {
        this.modules.forEach(arg_0 -> ((EventBus)EVENT_BUS).unregister(arg_0));
        this.modules.forEach(Module::onUnload);
    }

    public void onUnloadPost() {
        for (Module module : this.modules) {
            module.enabled.setValue(false);
        }
    }

    public void onKeyPressed(int eventKey) {
        if (eventKey <= 0) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
                module.toggle();
            }
        });
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        for (Module module : this.modules) {
            object.add(module.getName(), module.toJson());
        }
        return object;
    }

    @Override
    public void fromJson(JsonElement element) {
        for (Module module : this.modules) {
            module.fromJson(element.getAsJsonObject().get(module.getName()));
        }
    }

    @Override
    public String getFileName() {
        return "modules.json";
    }
}
