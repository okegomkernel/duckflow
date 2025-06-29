/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_408
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.duckflow.mixin;

import com.duckflow.DuckFlowClient;
import java.util.List;
import net.minecraft.class_408;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_408.class})
public abstract class ChatScreenMixin {
    @Inject(method={"sendMessage(Ljava/lang/String;Z)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onSendMessage(String message, boolean addToHistory, CallbackInfo ci) {
        List<String> prefixes = DuckFlowClient.prefixManager.getPrefixes();
        boolean hasBlockedPrefix = prefixes.stream().anyMatch(message::startsWith);
        boolean hasSuffix = message.endsWith(" | \ud835\udce3\ud835\udcf1\ud835\udcee \ud835\udcd3\ud835\udcfe\ud835\udcec\ud835\udcf4 \ud835\udce4\ud835\udcf7\ud835\udcf2\ud835\udcf8\ud835\udcf7");
        if (!hasBlockedPrefix && !hasSuffix) {
            String newMessage = message + " | \ud835\udce3\ud835\udcf1\ud835\udcee \ud835\udcd3\ud835\udcfe\ud835\udcec\ud835\udcf4 \ud835\udce4\ud835\udcf7\ud835\udcf2\ud835\udcf8\ud835\udcf7";
            ((class_408)this).method_44056(newMessage, addToHistory);
            ci.cancel();
        }
    }
}
