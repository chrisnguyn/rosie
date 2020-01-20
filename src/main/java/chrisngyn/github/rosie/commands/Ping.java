package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Ping extends Command {

    public Ping() {
        super("Ping");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Pong!").queue();
    }
}