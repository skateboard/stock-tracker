package me.brennan.stocktracker.util.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class Config {
    @SerializedName("discord_token")
    private String token;

    @SerializedName("guild_id")
    private String guildID;

    @SerializedName("staff_role_id")
    private String staffRole;

    @SerializedName("loop_time")
    private int threadLoop;

    public String getStaffRole() {
        return staffRole;
    }

    public String getGuildID() {
        return guildID;
    }

    public int getThreadLoop() {
        return threadLoop;
    }

    public String getToken() {
        return token;
    }
}
