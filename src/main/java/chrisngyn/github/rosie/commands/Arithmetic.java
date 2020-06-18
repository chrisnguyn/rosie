package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Arithmetic extends Command {
    // "\*" is not an escape character in Java, so we do \\* to get \* to get * ...get it?
    protected String documentation = "**!calculate** [number1] [**+**, **-**, **\\***, **/**, **%**, **^**] [number2] - basic computation.";

    public Arithmetic() {
        super("calculate");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (args.length != 4) {
            event.getChannel().sendMessage("Improper use of command. Please consult !help for documentation.").queue();
        } else {
            double num1 = Double.parseDouble(args[1]);
            double num2 = Double.parseDouble(args[3]);
            double ret;

            switch (args[2]) {
                case "+":
                    ret = num1 + num2;
                    break;

                case "-":
                    ret = num1 - num2;
                    break;

                case "*":
                    ret = num1 * num2;
                    break;

                case "/":
                    ret = num1 / num2;
                    break;

                case "%":
                    ret = num1 % num2;
                    break;

                case "^":
                    ret = Math.pow(num1, num2);
                    break;

                default:
                    event.getChannel().sendMessage("Unknown operation. Please consult !help for documentation.").queue();
                    return;
            }

            event.getChannel().sendMessage("Result: " + "**" + ret + "**").queue();
        }
    }
}