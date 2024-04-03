package org.ru.itmo.VoiceMatchToCommand.ParserCommand;

public class SimpleParseCommand {

    public static String parse(String command) {
        command = command.toLowerCase();
        command = command.replaceAll("[\\p{Punct}&&[^']]+", "");
        return command;
    }

}
