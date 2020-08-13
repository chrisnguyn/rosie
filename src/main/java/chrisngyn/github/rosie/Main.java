package chrisngyn.github.rosie;

import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
    public static void main(String[] args) {
        Dotenv env = Dotenv.load();

        // tl;dr: run -> Main -> CommandsHandler -> make cmd objects -> put into map -> retrieve and execute
        try {
            // read token and build bot instance, attach event listener and transmit data to CommandsHandler
            JDA bot = new JDABuilder((env.get("DISCORD_TOKEN"))).build();
            bot.addEventListener(new CommandsHandler());
        } catch (Exception e) {
            // when catching specific errors you have code to handle it, but in this case for any error just terminate
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}