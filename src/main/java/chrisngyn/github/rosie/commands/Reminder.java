package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Reminder extends Command {

    public Reminder() {
        super("Remindme"); // !remindme 20 minutes take the pizza out of the oven
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {

    }

}