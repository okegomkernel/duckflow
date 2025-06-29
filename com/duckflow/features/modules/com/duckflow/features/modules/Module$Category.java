/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.modules;

public static enum Module.Category {
    CLIENT("Client"),
    COMBAT("Combat"),
    DUPEANARCHY("Dupe Anarchy"),
    MISC("Misc"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render");

    private final String name;

    private Module.Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
