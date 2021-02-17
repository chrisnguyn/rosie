package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import java.util.Random;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class EightBall extends Command {  // https://en.wikipedia.org/wiki/Magic_8-Ball#Possible_answers
    Random randNumGen = new Random();
    protected String documentation = "**!8ball** [anything] - get a random Magic 8-Ball response!";
    private final String[] responses = { "It is certain.", "It is decidedly so.", "Without a doubt.", "Yes - definitely.", "You may rely on it.",
            "As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Signs point to yes.",
            "Reply hazy, try again.", "Ask again later.", "Better not tell you now.", "Cannot predict now.", "Concentrate and ask again.",
            "Don't count on it.", "My reply is no.", "My sources say no.", "Outlook not so good.", "Very doubtful." };

    public EightBall() {
        super("8Ball");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        int index = randNumGen.nextInt(responses.length);
        event.getChannel().sendMessage("**" + responses[index] + "**").queue();
    }
}