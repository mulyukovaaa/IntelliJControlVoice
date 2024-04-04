import static org.junit.Assert.assertEquals;

import org.ru.itmo.VoiceMatchToCommand.VoiceMatchToCommand;

public class TestVoiceTranscriber {
    public static void main(String[] args) {
        String filePath = "./src/test/resources/record_out.wav";
        assertEquals("открыть файл", VoiceMatchToCommand.math(filePath));
    }
}
