package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.util.Arrays;
import java.util.Random;

public class RandomNumberGeneration extends Command {
    protected String documentation = "**!random coin** - flip a coin.\n" +
            "> **!random dice** - roll a die.\n" +
            "> **!random card** - draw a card.\n" +
            "> **!random choose** option1 | option2 | option3 | ... - choose an option.\n" +
            "> **!random between** [number1] [number2] - get a number in that range (inclusive).";
    private final String[] value = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; // 0-13
    private final String[] suit = {"Clubs", "Spades", "Hearts", "Diamonds"}; // 0-3
    Random random = new Random(); // doesn't need to be inside of execute; why create new object every call?

    public RandomNumberGeneration() {
        super("random");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String ret = "";
        switch (args[1].toLowerCase()) {
            case "coin":
                int coin = random.nextInt(2); // 0 or 1.
                String headsOrTails = (coin == 0 ? "HEADS" : "TAILS");
                ret = "Result of your coin flip: **" + headsOrTails + "**";
                break;

            case "dice":
                int dice = random.nextInt(6) + 1; // 0-5, +1 = 1-6 instead of having 0-6 by making bound 7
                ret = "Result of your dice roll: **" + dice + "**";
                break;

            case "card":
                int val = random.nextInt(14); // 0 - 13
                int suit = random.nextInt(4); // 0 - 3 [val] of [suit]
                ret = "Result: **" + this.value[val] + " of " + this.suit[suit] + "**"; // this.suit to denote our global variable and not the integer we made
                break;

            case "choose":
                String content = event.getMessage().getContentRaw().substring((args[0] + args[1]).length() + 2); // gives "[arg1] | [args2]"
                String[] contentSpliced = content.split(" \\| "); // gives an array of [arg1, arg2]; currently count 2
                int choose = random.nextInt(contentSpliced.length); // length = 2, bound = 2, range 0-1.
                System.out.println(Arrays.toString(contentSpliced));
                ret = "I choose: **" + contentSpliced[choose] + "**";
                break;

            case "between":
                if (Integer.parseInt(args[2]) > Integer.parseInt(args[3]) || Integer.parseInt(args[2]) == Integer.parseInt(args[3])) {
                    event.getChannel().sendMessage("Your lower bound can't be less than or equal to your upper bound! :angry:").queue();
                    return;
                } else {
                    int max = Integer.parseInt(args[3]);
                    int min = Integer.parseInt(args[2]);
                    int result = random.nextInt((max - min) + 1) + min; // algorithm to find numbers inclusive
                    ret = "Result: " + result;
                }
                break;

            default:
                event.getChannel().sendMessage("Improper use of command. Please type !help for documentation.").queue();
                return;
        }

        event.getChannel().sendMessage(ret).queue();
    }
}