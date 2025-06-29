/*
 * Decompiled with CFR 0.152.
 */
package com.duckflow.features.commands.impl;

import com.duckflow.DuckFlowClient;
import com.duckflow.features.commands.Command;
import java.security.NoSuchAlgorithmException;

public class HwidCommand
extends Command {
    public HwidCommand() {
        super("hwid");
    }

    @Override
    public void execute(String[] var1) {
        try {
            HwidCommand.sendMessage("HWID: " + DuckFlowClient.generateHWID());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
