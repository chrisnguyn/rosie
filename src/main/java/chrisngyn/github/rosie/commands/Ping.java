package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Ping extends Command {

    // Basic command to test if the bot is up and running, !ping should return "Pong!"

    String documentation = "**!ping** - basic command to test to see if the bot is up and running.";

    public Ping() {
        super("ping");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Pong!").queue();
    }
}