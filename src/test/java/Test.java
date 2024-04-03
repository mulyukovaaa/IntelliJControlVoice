import java.util.concurrent.CompletableFuture;

import org.ru.itmo.VoiceMatchToCommand.VoiceMatchToCommand;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {
    public static void main(String[] args) {
        String filePath = "/Users/z1kurat/Library/Mobile Documents/com~apple~CloudDocs/Hackatons/IntelliJControlVoice/src/main/java/org/ru/itmo/VoiceMatchToCommand/VoiceRecognitions/record_out.wav";

        System.out.println(VoiceMatchToCommand.math(filePath));
    }
}
