package org.ru.itmo.CodeGenerate;

import java.util.concurrent.CompletableFuture;

public interface CodeGenerate {
    public String getResultGenerate(String prompt);
    public CompletableFuture<String> generate(String promt);
}
