package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.util.Arrays;
import java.util.Random;

public class RandomNumberGeneration extends Command {

    String documentation = "**!random coin** - flip a coin.\n" +
            "> **!random dice** - roll a die.\n" +
            "> **!random card** - draw a card.\n" +
            "> **!random choose** option1 | option2 | option3 | ... - choose an option.\n" +
            "> **!random between** [number1] [number2] - get a number in that range (inclusive).";

    Random random = new Random(); // doesn't need to be inside of execute, why create new object every call?
    String[] value = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; // 0-13
    String[] suit = {"Clubs", "Spades", "Hearts", "Diamonds"}; // 0-3

    public RandomNumberGeneration() {
        super("random");
    }

    // compiler angry if we try to Override a method that DOESN'T exist in superclass
    // if we have "public void myMethod()" with an Override tag, it'll get angry because there isn't one in super called that
    // no @Override tag on a method that exists in super - fine, whatever, but bad practise
    // no @Override tag on a method that doesn't exist in super - it'll allow it even though you expect it to be a super method
    // maybe you typed it wrong. for example, imagine you called it GuildMessageRecIEvedEvent (misspelled), it'll allow it if no tag

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {

        // NOTE: !random card works, but !random CARD will not. this IS case sensitive
        switch (args[1].toLowerCase()) {
            case "coin":
                int coin = random.nextInt(2); // 0 or 1.
                String headsOrTails = (coin == 0 ? "HEADS" : "TAILS");
                event.getChannel().sendMessage("Result of your coin flip: **" + headsOrTails + "**").queue();
                break;

            case "dice":
                int dice = random.nextInt(6) + 1; // 0-5, +1, 1-6 instead of 0-6 by making bound 7
                event.getChannel().sendMessage("Result of your dice roll: **" + dice + "**").queue();
                break;

            case "card": // 2 3 4 5 6 7 8 9 10 J K Q A, Clubs Spades Diamonds Hearts
                int v = random.nextInt(14); // 0 - 13
                int s = random.nextInt(4); // 0 - 3 [val] of [suit]
                String ret = "Result: **" + value[v] + " of " + suit[s] + "**";
                event.getChannel().sendMessage(ret).queue();
                break;

            case "choose": // !random choose [arg1] | [arg2]
                String content = event.getMessage().getContentRaw().substring((args[0] + args[1]).length() + 2); // gives "[arg1] | [args2]"
                String[] contentSpliced = content.split(" \\| "); // gives an array of [arg1, arg2]; currently count 2
                int choose = random.nextInt(contentSpliced.length); // length = 2, bound = 2, range 0-1.
                System.out.println(Arrays.toString(contentSpliced));
                event.getChannel().sendMessage("I choose: **" + contentSpliced[choose] + "**").queue();
                break;

            case "between": // !random between 10 20
                if (Integer.parseInt(args[2]) > Integer.parseInt(args[3])) {
                    event.getChannel().sendMessage("Your lower bound can't be less than or equal to your upper bound! :angry:").queue();
                }
                else {
                    int max = Integer.parseInt(args[3]);
                    int min = Integer.parseInt(args[2]);
                    int result = random.nextInt((max - min) + 1) + min; // algorithm to find numbers inclusive
                    event.getChannel().sendMessage("Result: " + result).queue();
                }
                break;

            default:
                event.getChannel().sendMessage("Improper use of command. Please type !help for documentation.").queue();
        }
    }
}