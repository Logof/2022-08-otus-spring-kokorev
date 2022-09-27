package ru.otus.service;


import org.jboss.logging.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static java.lang.String.format;

@ShellComponent
public class ShellCommandService {

    private boolean anonimous = true;
    Logger log = Logger.getLogger(ShellCommandService.class.getName());

    @ShellMethod(value = "login")
    public void login() {
        this.anonimous = false;
        log.info("Signed In!");
    }

    @ShellMethod(key = "my-ssh", value = "connect to remote server")
    public void ssh(@ShellOption(value = "-s") String remoteServer) {
        if (anonimous) {
            log.info("Вы кто такие?! Идите отсюда - я вас не звал");
            return;
        }
        log.info(format("Logged to machine '%s'", remoteServer));
    }
}
