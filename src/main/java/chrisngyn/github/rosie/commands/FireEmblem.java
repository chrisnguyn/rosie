package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class FireEmblem extends Command {
    public FireEmblem(String name) {
        super("fe");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {  // only works when local API is running since it's not hosted anywhere lol
        return;
    }
}