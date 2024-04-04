import static org.junit.Assert.assertTrue;

import org.ru.itmo.CodeGenerate.OpenAiCodeGenerate;
import org.ru.itmo.CodeGenerate.CodeGenerate;

public class TestCodeGenerate {
        public static void main(String[] args) {
        CodeGenerate codeGen = new OpenAiCodeGenerate();
        String command = "создай змейку на python";

        String codeText = codeGen.getResultGenerate(command);
        System.out.println(codeText);
        assertTrue("Fail code generate", codeText != null);
    }
}
