package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AdvancedArithmetic extends Command {
    protected String documentation = "**r!moremath quadratic** [number1] [number2] [number3] - quadratic formula, enter values to get the roots of an equation. \n" +
            "> **r!moremath pythagorean** [number1] [number2] - pythagorean theorem, enter two side lengths to get the hypotenuse. \n" +
            "> **r!moremath** [**sin**, **cos**, **tan**, **c2f**, **f2c**] [number] - sin, cos, tan, celsius to fahrenheit, and fahrenheit to celsius conversions.";

    public AdvancedArithmetic() {
        super("moremath");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String result = "";

        switch (args[1]) {
            case "quadratic":
                double a = Double.parseDouble(args[2]);
                double b = Double.parseDouble(args[3]);
                double c = Double.parseDouble(args[4]);
                double discriminant = (b * b) - (4 * a * c);
                if (discriminant > 0) {
                    double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                    double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                    result = "As your discriminant is positive, you have two roots. Your roots are: **" + root1 + "** and **" + root2 + "**";
                } else if (discriminant == 0) {
                    double root = (-b / (2 * a));
                    result = "As your discriminant is zero, you have one root. Your root is: **" + root + "**";
                } else {
                    result = "As your discriminant is negative, you have zero real roots.";
                }
                break;
            case "pythagorean":
                double num1 = Double.parseDouble(args[2]);
                double num2 = Double.parseDouble(args[3]);
                double hypotenuse = (Math.sqrt((Math.pow(num1, 2) + Math.pow(num2, 2))));
                result = "The hypontenuse of the two side length you have provided is: **" + hypotenuse + "**";
                break;
            case "sin":
                result = "Result: **" + Math.sin(Integer.parseInt(args[2])) + "**";
                break;
            case "cos":
                result = "Result: **" + Math.cos(Integer.parseInt(args[2])) + "**";
                break;
            case "tan":
                result = "Result: **" + Math.tan(Integer.parseInt(args[2])) + "**";
                break;
            case "c2f":
                double c2f = ((Double.parseDouble(args[2]) * 9) / 5) + 32;
                result = args[2] + " degrees Celsius to Fahrenheit is: **" + c2f + "**";
                break;
            case "f2c":
                double f2c = ((Double.parseDouble(args[2]) - 32) * 5) / 9;
                result = args[2] + " degrees Fahrenheit to Celsius is: **" + f2c + "**";
                break;
            default:
                event.getChannel().sendMessage("Improper use of command. Please consult \"**r!help**\" for documentation.").queue();
                return;
        }

        event.getChannel().sendMessage("Result: " + "**" + result + "**").queue();
    }
}