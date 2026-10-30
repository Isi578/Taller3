package org.example.command;

public class CommandInvoker {

    public void execute(Command command) {
        try {
            command.execute();
        } catch (Exception exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            System.out.println("Failed operation skipped. Continuing with the next one.");
        }
    }
}
