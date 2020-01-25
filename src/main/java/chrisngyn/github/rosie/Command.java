package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {

    // all commands are some form of command; all command classes will inherit this class!

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() { // for the map, so it can know what word calls what command
        return name;
    }

    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}