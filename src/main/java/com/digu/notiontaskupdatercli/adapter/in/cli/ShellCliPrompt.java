package com.digu.notiontaskupdatercli.adapter.in.cli;

import org.jline.reader.LineReader;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ShellCliPrompt implements CliPrompt {

    private final ObjectProvider<LineReader> lineReaderProvider;

    public ShellCliPrompt(ObjectProvider<LineReader> lineReaderProvider) {
        this.lineReaderProvider = lineReaderProvider;
    }

    @Override
    public String ask(String label) {
        return getLineReader().readLine(label + ": ");
    }

    @Override
    public String askOptional(String label) {
        return getLineReader().readLine(label + ": ");
    }

    private LineReader getLineReader() {
        LineReader lineReader = lineReaderProvider.getIfAvailable();
        if (lineReader == null) {
            throw new CliValidationException("Interactive prompt is unavailable.");
        }

        return lineReader;
    }
}
