package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command { // all cmds are some form of a cmd; all cmd classes will inherit this common base
    // command respective trigger word
    private final String commandName;

    // when a subclass object is created, super() will run and create an object with the provided name...
    public Command(String name) {
        this.commandName = name;
    }

    // ...for the map, so it can know what trigger word will call what command <trigger word: respective object>
    public String getName() {
        return commandName;
    }

    // subject to the behaviour of each command; we'll retrieve the object from our map and call .execute() on it
    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}