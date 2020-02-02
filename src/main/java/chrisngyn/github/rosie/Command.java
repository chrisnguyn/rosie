package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {

    // all commands are some form of command; all command classes will inherit this class, eg. ping is-a command through inheritance
    private final String name;

    public Command(String name) {   // when a subclass object is created, super() will run and create an object with that name
        this.name = name;
    }

    public String getName() {       // for the map, so it can know what word calls what command
        return name;
    }

    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}