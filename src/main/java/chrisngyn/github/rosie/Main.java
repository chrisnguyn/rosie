package chrisngyn.github.rosie;

import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main { // run --> Main --> CommandsHandler --> make cmd objects --> put into map --> retrieve and execute
    public static void main(String[] args) {
        Dotenv env = Dotenv.load(); // load .env file

        try {
            // read token and build bot instance, attach event listener and transmit data to CommandsHandler
            JDA bot = new JDABuilder((env.get("DISCORD_TOKEN"))).build();
            bot.addEventListener(new CommandsHandler());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0); // when catching specific exceptions you have behaviour to handle it, but in this case for any exception just terminate
        }
    }
}