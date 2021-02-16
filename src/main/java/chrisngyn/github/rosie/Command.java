package chrisngyn.github.rosie;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    private final String triggerWord;

    public Command(String name) {
        this.triggerWord = name;
    }

    public String getName() {
        return triggerWord;
    }

    public abstract void execute(GuildMessageReceivedEvent event, String[] args);
}