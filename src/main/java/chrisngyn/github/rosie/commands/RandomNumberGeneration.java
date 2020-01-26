package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class RandomNumberGeneration extends Command {

    public RandomNumberGeneration() {
        super("Random"); // !random coin, !random dice, !random card, !random choose [arg] | [arg]
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) { // !random coin

        Random random = new Random();

        switch (args[1]) {

            case "coin":
                int coin = random.nextInt(2); // 0 or 1.
                String headsOrTails = (coin == 0 ? "HEADS" : "TAILS");
                event.getChannel().sendMessage("Result of your coin flip: **" + headsOrTails + "**").queue();
                break;

            case "dice":
                int dice = random.nextInt(6) + 1; // 0-5, +1, 1-6 instead of 0-6 by making bound 7
                event.getChannel().sendMessage("Result of your dice roll: **" + dice + "**").queue();
                break;

            case "card":
                // ...
                break;

                /*
      CURRENTLY DO NOT HAVE ACCESS TO MY COMPUTER BUT IT IS 12:31AM AND I NEED TO WRITE THIS BEFORE I FORGET LOL

      !choose [arg1] | [arg2]

      public void execute(Event event, String[] args) {

          event.getChannel().getContent() gives "!choose [arg1] | [arg2]"
          String t = event.getChannel().getContent().substring(args[0].length) gives "[arg1] | [arg2]"
          String[] x = t.split(" | "); gives [arg1, arg2]

          make a random obj
          use it to decide one or the other
          gg

          t!choose arg1 | arg2 | arg3 | arg4 | arg5 - tatsu can choose MULTIPLE ARGS

          so after you split on (" | ") count how long it is then make the random obj. choose accordingly
      }
  */

            case "choose": // !random choose [arg1] | [arg2]
                String content = event.getMessage().getContentRaw().substring(args[0].length());    // gives "[arg1] | [args2]"
                String[] contentSpliced = content.split(" | ");                              // gives an array of [arg1, arg2]; currently count 2
                int choose = random.nextInt(contentSpliced.length);                                // length = 2, bound = 2, range 0-1.
                event.getChannel().sendMessage("").queue();
                break;

            case "between":
                // ...
                break;
            default:
                event.getChannel().sendMessage("Improper use of command. Please type !help for documentation.").queue();
        }

    }

    /*
      CURRENTLY DO NOT HAVE ACCESS TO MY COMPUTER BUT IT IS 12:31AM AND I NEED TO WRITE THIS BEFORE I FORGET LOL
      
      !choose [arg1] | [arg2]
      
      public void execute(Event event, String[] args) {
      
          event.getChannel().getContent() gives "!choose [arg1] | [arg2]"
          String t = event.getChannel().getContent().substring(args[0].length) gives "[arg1] | [arg2]"
          String[] x = t.split(" | "); gives [arg1, arg2]
          
          make a random obj
          use it to decide one or the other
          gg
          
          t!choose arg1 | arg2 | arg3 | arg4 | arg5 - tatsu can choose MULTIPLE ARGS
          
          so after you split on (" | ") count how long it is then make the random obj. choose accordingly
      }
  */
  
}
