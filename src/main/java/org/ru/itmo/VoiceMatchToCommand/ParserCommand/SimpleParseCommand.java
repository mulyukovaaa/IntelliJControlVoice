package org.ru.itmo.VoiceMatchToCommand.ParserCommand;

public class SimpleParseCommand {

    public static String parse(String command) {
        command = command.toLowerCase().trim();
        command = command.replaceAll("[\\p{Punct}&&[^']]+", "");
        command = command.replaceAll("\\s+", " ");
        return command.trim();
    }

}
