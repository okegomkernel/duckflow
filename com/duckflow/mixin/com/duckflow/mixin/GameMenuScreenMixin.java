/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_364
 *  net.minecraft.class_412
 *  net.minecraft.class_433
 *  net.minecraft.class_437
 *  net.minecraft.class_5250
 *  net.minecraft.class_639
 *  net.minecraft.class_642
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.duckflow.util.ReconnectButtonWidget;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_412;
import net.minecraft.class_433;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_639;
import net.minecraft.class_642;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_433.class})
public abstract class GameMenuScreenMixin
extends class_437 {
    protected GameMenuScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(at={@At(value="RETURN")}, method={"initWidgets"})
    private void addReconnectButton(CallbackInfo ci) {
        assert (this.field_22787 != null);
        boolean inSingleplayer = this.field_22787.method_1542();
        boolean inRealms = false;
        class_642 currentServer = this.field_22787.method_1558();
        if (currentServer != null && currentServer.field_3761 != null && currentServer.method_52811()) {
            inRealms = true;
        }
        if (!inSingleplayer && !inRealms) {
            class_5250 text = (class_5250)class_2561.method_30163((String)"<");
            this.method_37063((class_364)new ReconnectButtonWidget(this.field_22789 / 2 - 102 + 208, this.field_22790 / 4 + 120 + -16, 20, 20, text, button -> {
                assert (currentServer != null);
                class_639 serverIp = class_639.method_2950((String)currentServer.field_3761);
                button.field_22763 = false;
                assert (this.field_22787.field_1687 != null);
                this.field_22787.field_1687.method_8525();
                this.field_22787.method_18099();
                class_412.method_36877(null, (class_310)this.field_22787, (class_639)serverIp, (class_642)currentServer, (boolean)true, null);
            }, narrationSupplier -> class_2561.method_43471((String)"narration.reconnect_button")));
        }
    }
}
