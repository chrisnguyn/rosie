package chrisngyn.github.rosie;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() { // AFAIK, this is unused this far? What did we originally have this for?
        return name;
    }

    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}