package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Arithmetic extends Command {

    public Arithmetic() {
        super("Arithmetic");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) { // !calculate 10 + 10

    }
}