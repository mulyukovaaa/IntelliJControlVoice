import org.ru.itmo.processing.recognition.VoiceMatchToCommand;

import static org.junit.Assert.assertEquals;


public class TestVoiceTranscriber {
    public static void main(String[] args) {
        String filePath = "src/test/resources/record_out.wav";
        assertEquals("open the file", VoiceMatchToCommand.match(filePath));
    }
}
