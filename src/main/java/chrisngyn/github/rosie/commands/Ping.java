package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Ping extends Command {
    protected String documentation = "**!ping** - basic command to test to see if the bot is up and running.";

    public Ping() {
        super("ping"); // assign name "ping" to object Ping upon creation. "ping" is our name; view constructor + field in Command.java
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) { // when you call Ping.execute(), it'll do this; behaviour is different from Help.execute(), etc.
        event.getChannel().sendMessage("Pong!").queue();
    }
}