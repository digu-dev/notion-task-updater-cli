package com.digu.notiontaskupdatercli.adapter.in.cli;

public interface CliPrompt {

    String ask(String label);

    String askOptional(String label);
}
