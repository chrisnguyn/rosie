package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Arithmetic extends Command {
    protected String documentation = "**r!calculate** [number1] [**+**, **-**, **\\***, **/**, **%**, **^**] [number2] - basic computation.";

    public Arithmetic() {
        super("calculate");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (args.length != 4) {
            event.getChannel().sendMessage("Improper use of command. Please use \"**r!help**\" for documentation.").queue();
        } else {
            double num1 = Double.parseDouble(args[1]);
            double num2 = Double.parseDouble(args[3]);
            double result;

            switch (args[2]) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                case "%":
                    result = num1 % num2;
                    break;
                case "^":
                    result = Math.pow(num1, num2);
                    break;
                default:
                    event.getChannel().sendMessage("Improper use of command. Please consult \"**r!help**\" for documentation.").queue();
                    return;
            }

            event.getChannel().sendMessage("Result: " + "**" + result + "**").queue();
        }
    }
}