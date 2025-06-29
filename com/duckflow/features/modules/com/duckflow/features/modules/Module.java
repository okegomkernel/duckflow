/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.class_124
 */
package com.duckflow.features.modules;

import com.duckflow.DuckFlowClient;
import com.duckflow.event.impl.ClientEvent;
import com.duckflow.event.impl.Render2DEvent;
import com.duckflow.event.impl.Render3DEvent;
import com.duckflow.features.Feature;
import com.duckflow.features.commands.Command;
import com.duckflow.features.settings.Bind;
import com.duckflow.features.settings.Setting;
import com.duckflow.manager.ConfigManager;
import com.duckflow.util.traits.Jsonable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.class_124;

public class Module
extends Feature
implements Jsonable {
    private static boolean hasRegisteredPlayerCategories = false;
    private final String description;
    private final Category category;
    public Setting<Boolean> enabled = this.bool("Enabled", false);
    public Setting<Bind> bind = this.key("Keybind", new Bind(-1));
    public Setting<String> displayName;
    public boolean hasListener;
    public boolean alwaysListening;
    public boolean hidden;

    public Module(String name, String description, Category category, boolean hasListener, boolean hidden, boolean alwaysListening) {
        super(name);
        this.displayName = this.str("DisplayName", name);
        this.description = description;
        this.category = category;
        this.hasListener = hasListener;
        this.hidden = hidden;
        this.alwaysListening = alwaysListening;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onToggle() {
    }

    public void onLoad() {
    }

    public void onTick() {
    }

    public void onUpdate() {
    }

    public void onRender2D(Render2DEvent event) {
    }

    public void onRender3D(Render3DEvent event) {
    }

    public void onUnload() {
    }

    public String getDisplayInfo() {
        return null;
    }

    public boolean isOn() {
        return this.enabled.getValue();
    }

    public boolean isOff() {
        return this.enabled.getValue() == false;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            this.enable();
        } else {
            this.disable();
        }
    }

    public void enable() {
        this.enabled.setValue(true);
        this.onToggle();
        this.onEnable();
        if (this.isOn() && this.hasListener && !this.alwaysListening) {
            EVENT_BUS.register((Object)this);
        }
    }

    public void disable() {
        if (this.hasListener && !this.alwaysListening) {
            EVENT_BUS.unregister((Object)this);
        }
        this.enabled.setValue(false);
        this.onToggle();
        this.onDisable();
    }

    public void toggle() {
        ClientEvent event = new ClientEvent(!this.isEnabled() ? 1 : 0, this);
        EVENT_BUS.post((Object)event);
        if (!event.isCancelled()) {
            this.setEnabled(!this.isEnabled());
        }
    }

    public String getDisplayName() {
        return this.displayName.getValue();
    }

    public void setDisplayName(String name) {
        Module module = DuckFlowClient.moduleManager.getModuleByDisplayName(name);
        Module originalModule = DuckFlowClient.moduleManager.getModuleByName(name);
        if (module == null && originalModule == null) {
            Command.sendMessage(this.getDisplayName() + ", name: " + this.getName() + ", has been renamed to: " + name);
            this.displayName.setValue(name);
            return;
        }
        Command.sendMessage(String.valueOf(class_124.field_1061) + "A module of this name already exists.");
    }

    @Override
    public boolean isEnabled() {
        return this.isOn();
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getInfo() {
        return null;
    }

    public Bind getBind() {
        return this.bind.getValue();
    }

    public void setBind(int key) {
        this.bind.setValue(new Bind(key));
    }

    public boolean listening() {
        return this.hasListener && this.isOn() || this.alwaysListening;
    }

    public String getFullArrayString() {
        return this.getDisplayName() + String.valueOf(class_124.field_1080) + (String)(this.getDisplayInfo() != null ? " [" + String.valueOf(class_124.field_1068) + this.getDisplayInfo() + String.valueOf(class_124.field_1080) + "]" : "");
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        for (Setting<?> setting : this.getSettings()) {
            try {
                Object obj = setting.getValue();
                if (obj instanceof Bind) {
                    Bind bind = (Bind)obj;
                    object.addProperty(setting.getName(), (Number)bind.getKey());
                    continue;
                }
                object.addProperty(setting.getName(), setting.getValueAsString());
            }
            catch (Throwable throwable) {}
        }
        return object;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject object = element.getAsJsonObject();
        String enabled = object.get("Enabled").getAsString();
        if (Boolean.parseBoolean(enabled)) {
            this.toggle();
        }
        for (Setting<?> setting : this.getSettings()) {
            try {
                ConfigManager.setValueFromJson(this, setting, object.get(setting.getName()));
            }
            catch (Throwable throwable) {}
        }
    }

    public static enum Category {
        CLIENT("Client"),
        COMBAT("Combat"),
        DUPEANARCHY("Dupe Anarchy"),
        MISC("Misc"),
        MOVEMENT("Movement"),
        PLAYER("Player"),
        RENDER("Render");

        private final String name;

        private Category(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
