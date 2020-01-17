package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Ping extends Commands {

    public Ping() {
        super("ping");
        System.out.println("Called ping constructor.");
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Called ping execute.");
    }
}