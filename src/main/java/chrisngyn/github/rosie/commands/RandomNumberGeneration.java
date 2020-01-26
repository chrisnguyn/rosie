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
                int rand = random.nextInt(2); // 0 or 1.
                String headsOrTails = (rand == 0 ? "HEADS" : "TAILS");
                event.getChannel().sendMessage("Result: " + headsOrTails).queue();
                break;

            case "dice":
                // ...
                break;
            case "card":
                // ...
                break;
            case "choose":
                // ...
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
