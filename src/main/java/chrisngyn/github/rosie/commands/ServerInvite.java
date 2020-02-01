package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class ServerInvite extends Command {

    public ServerInvite() {
        super("ServerInvite");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("This link will expire in 10 minutes: " + event.getChannel().createInvite().setMaxAge(600).complete().getURL()).queue();
    }

}