package me.spuny.xpbottle.utils;


import me.spuny.xpbottle.utils.colors.ColorAPI;

import java.text.DecimalFormat;

public class Utils {

    public static String colorize(String message) {
        return ColorAPI.colorize(message);
    }

    public static String convertNumber(String pattern, int number) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(number);
    }

    public static String deColorize(String message) {
        return ColorAPI.deColorize(message);
    }
}
