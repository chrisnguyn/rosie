package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    // all commands are some form of a command; all command classes will inherit this
    private final String name;

    // when a subclass object is created, super() will run and create an object with the provided name...
    public Command(String name) {
        this.name = name;
    }

    // ...for the map, so it can know what trigger word will call the command
    public String getName() {
        return name;
    }

    // .execute() is subject to the behaviour of each command
    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}