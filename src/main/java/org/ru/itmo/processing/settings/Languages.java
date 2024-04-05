package org.ru.itmo.processing.settings;

public enum Languages {
    RUSSIAN("ru"),
    ENGLISH("en"),
    ANY(null);

    String language;

    Languages(String lang) {
        this.language = lang;
    }

    public String getLanguage() {
        return language;
    }
}
