package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    private final String commandName;

    public Command(String name) {
        this.commandName = name;
    }

    public String getName() {
        return commandName;
    }

    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}