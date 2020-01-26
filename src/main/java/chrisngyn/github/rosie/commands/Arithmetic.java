package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Arithmetic extends Command {

    public Arithmetic() {
        super("Calculate");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) { // !calculate 10 + 10
        if (args.length != 4) {
            event.getChannel().sendMessage("Improper use of command. Please consult !help for documentation.").queue();
        } else {
            Double num1 = Double.parseDouble(args[1]);
            Double num2 = Double.parseDouble(args[3]);

            switch (args[2]) {
                case "+":
                    event.getChannel().sendMessage("Result: " + (num1 + num2)).queue();
                    break;
                case "-":
                    event.getChannel().sendMessage("Result: " + (num1 - num2)).queue();
                    break;
                case "*":
                    event.getChannel().sendMessage("Result: " + (num1 * num2)).queue();
                    break;
                case "/":
                    event.getChannel().sendMessage("Result: " + (num1 / num2)).queue();
                    break;
                case "%":
                    event.getChannel().sendMessage("Result: " + (num1 % num2)).queue();
                    break;
                case "^":
                    event.getChannel().sendMessage("Result: " + Math.pow(num1, num2)).queue();
                    break;
                default:
                    event.getChannel().sendMessage("Unknown operation. Please consult !help for documentation.").queue();
            }
        }
    }
}
