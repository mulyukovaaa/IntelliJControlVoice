import org.ru.itmo.VoiceMatchToCommand.VoiceMatchToCommand;

public class Test {
    public static void main(String[] args) {
        String filePath = "./src/test/resources/record_out.wav";
        System.out.println(VoiceMatchToCommand.math(filePath));
    }
}
