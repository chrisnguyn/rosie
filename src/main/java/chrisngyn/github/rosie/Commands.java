package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Commands {
    String name;

    public Commands(String name) {
        this.name = name;
        System.out.println("Called command constructor.");
    }

    public abstract void execute(MessageReceivedEvent event, String[] args);
}