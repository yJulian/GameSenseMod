package de.juliany.minesensefabric.util;

import net.minecraft.util.Identifier;
import net.minecraft.util.Language;

public class LanguageUtil {

    public static String translate(String type, Identifier identifier) {
        return Language.getInstance().get("%s.%s.%s".formatted(type, identifier.getNamespace(), identifier.getPath()));
    }

}
