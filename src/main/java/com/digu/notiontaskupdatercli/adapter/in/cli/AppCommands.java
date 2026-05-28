package com.digu.notiontaskupdatercli.adapter.in.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AppCommands {

    @ShellMethod(key = "app:ping", value = "Checks if the CLI application is running.")
    public String ping() {
        return "Application is running.";
    }
}
