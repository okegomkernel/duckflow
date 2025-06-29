/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 */
package com.duckflow.features.modules.dupeanarchy;

import com.duckflow.features.modules.dupeanarchy.BaseDuperModule;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public class AutoObsidianDuper
extends BaseDuperModule {
    public AutoObsidianDuper() {
        super("Obsidian Duper", "Dupes obsidian on play.dupeanarchy.com");
    }

    @Override
    protected class_1792 getTargetItem() {
        return class_1802.field_8281;
    }

    @Override
    protected String getCommandItemName() {
        return "obsidian";
    }
}
