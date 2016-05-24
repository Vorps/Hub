package me.vorps.hub.utils;

import lombok.Getter;

/**
 * Project Hub Created by Vorps on 18/05/2016 at 14:58.
 */
public enum Color {
    WHITE(16777215),
    SILVER(12632256),
    GRAY(8421504),
    BLACK(0),
    RED(16711680),
    MAROON(8388608),
    YELLOW(16776960),
    OLIVE(8421376),
    LIME('\uff00'),
    GREEN('耀'),
    AQUA('\uffff'),
    TEAL('肀'),
    BLUE(255),
    NAVY(128),
    FUCHSIA(16711935),
    PURPLE(8388736),
    ORANGE(16753920);

    private @Getter org.bukkit.Color color;

    Color(int rgb){
        color = org.bukkit.Color.fromRGB(rgb);
    }
}
