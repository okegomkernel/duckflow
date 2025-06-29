/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.class_1657
 */
package com.duckflow.manager;

import com.duckflow.util.traits.Jsonable;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1657;

public class FriendManager
implements Jsonable {
    private final List<String> friends = new ArrayList<String>();

    public boolean isFriend(String name) {
        return this.friends.stream().anyMatch(friend -> friend.equalsIgnoreCase(name));
    }

    public boolean isFriend(class_1657 player) {
        return this.isFriend(player.method_7334().getName());
    }

    public void addFriend(String name) {
        this.friends.add(name);
    }

    public void removeFriend(String name) {
        this.friends.removeIf(s -> s.equalsIgnoreCase(name));
    }

    public List<String> getFriends() {
        return this.friends;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        for (String friend : this.friends) {
            array.add(friend);
        }
        object.add("friends", (JsonElement)array);
        return object;
    }

    @Override
    public void fromJson(JsonElement element) {
        for (JsonElement e : element.getAsJsonObject().get("friends").getAsJsonArray()) {
            this.friends.add(e.getAsString());
        }
    }

    @Override
    public String getFileName() {
        return "friends.json";
    }
}
