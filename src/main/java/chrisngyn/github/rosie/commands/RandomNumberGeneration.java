package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import java.util.Arrays;
import java.util.Random;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class RandomNumberGeneration extends Command {
    Random randNumGen = new Random();
    protected String documentation = "**!random coin** - flip a coin.\n" +
            "> **!random dice** - roll a die.\n" +
            "> **!random card** - draw a card.\n" +
            "> **!random choose** option1 | option2 | option3 | ... - choose an option.\n" +
            "> **!random between** [number1] [number2] - get a number in that range (inclusive).";
    private final String[] value = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private final String[] suit = {"Clubs", "Spades", "Hearts", "Diamonds"};

    public RandomNumberGeneration() {
        super("random");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String result = "";

        switch (args[1].toLowerCase()) {
            case "coin":
                int coin = randNumGen.nextInt(2);
                String headsOrTails = (coin == 0 ? "HEADS" : "TAILS");
                result = "Result of your coin flip: **" + headsOrTails + "**";
                break;
            case "dice":
                int dice = randNumGen.nextInt(6) + 1;
                result = "Result of your dice roll: **" + dice + "**";
                break;
            case "card":
                int val = randNumGen.nextInt(14);
                int suit = randNumGen.nextInt(4);
                result = "Result: **" + this.value[val] + " of " + this.suit[suit] + "**";
                break;
            case "choose":
                String content = event.getMessage().getContentRaw().substring((args[0] + args[1]).length() + 2);
                String[] contentSpliced = content.split(" \\| ");
                int choose = randNumGen.nextInt(contentSpliced.length);
                System.out.println(Arrays.toString(contentSpliced));
                result = "I choose: **" + contentSpliced[choose] + "**!";
                break;
            case "between":
                if (Integer.parseInt(args[2]) > Integer.parseInt(args[3]) || Integer.parseInt(args[2]) == Integer.parseInt(args[3])) {
                    event.getChannel().sendMessage("Your lower bound can't be less than or equal to your upper bound! :angry:").queue();
                    return;
                } else {
                    int max = Integer.parseInt(args[3]);
                    int min = Integer.parseInt(args[2]);
                    int gen = randNumGen.nextInt((max - min) + 1) + min;
                    result = "Result: **" + gen + "**";
                }
                break;
            default:
                event.getChannel().sendMessage("Improper use of command. Please consult \"**r!help**\" for documentation.").queue();
                return;
        }

        event.getChannel().sendMessage(result).queue();
    }
}