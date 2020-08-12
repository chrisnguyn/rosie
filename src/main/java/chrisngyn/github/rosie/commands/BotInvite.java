package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class BotInvite extends Command {
    protected String documentation = "**r!botinvite** - generate a link to invite Rosie to move servers.";
    private String inviteURL = "https://discordapp.com/api/oauth2/authorize?client_id=646757636221435924&permissions=0&scope=bot";

    public BotInvite() {
        super("botinvite");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Link to invite Rosie to more servers: " + inviteURL).queue();
    }
}