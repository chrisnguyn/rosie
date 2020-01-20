package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

    private final String name;

    public Command(String name) { this.name = name; }
    public abstract void execute(MessageReceivedEvent event, String[] args);
    public String getName() { return name; }
}