package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Arithmetic extends Command {

    public Arithmetic() {
        super("Arithmetic");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) { // !calculate 10 + 10

        int num1 = Integer.parseInt(args[1]);
        int num2 = Integer.parseInt(args[2]);

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