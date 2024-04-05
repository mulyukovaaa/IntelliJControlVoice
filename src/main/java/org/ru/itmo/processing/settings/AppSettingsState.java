package org.ru.itmo.processing.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@Service
@State(
        name = "org.ru.itmo.processing.settings.AppSettingsState",
        storages = @Storage("SdkSettingsPlugin.xml")
)
public final class AppSettingsState implements PersistentStateComponent<AppSettingsState> {
    public String openAiKey = "enter openai key";
    public String proxyAddress = "enter proxy address";
    public TranscriberType transcriberType  = TranscriberType.ONLINE;
    public Languages language = Languages.RUSSIAN;

    public Map<String, CommandEntity> userCommands = new LinkedHashMap<>();

    public static AppSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(AppSettingsState.class);
    }

    @Override
    public @Nullable AppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public void noStateLoaded() {
        PersistentStateComponent.super.noStateLoaded();
    }

    @Override
    public void initializeComponent() {
        PersistentStateComponent.super.initializeComponent();
    }
}