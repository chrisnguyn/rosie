package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    // all cmds are some form of a cmd, so all cmd classes will inherit this common base class
    private final String name;

    // when a subclass object is created, super() will run and create an object with the provided name...
    public Command(String name) {
        this.name = name;
    }

    // ...for the map, so it can know what trigger word will call what command
    public String getName() {
        return name;
    }

    // subject to the behaviour of each command; we'll retrieve the object from our map and call .execute() on it
    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}