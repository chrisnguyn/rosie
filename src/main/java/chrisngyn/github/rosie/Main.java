package chrisngyn.github.rosie;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) {
        Dotenv env = Dotenv.load();

        try {
            JDABuilder bot = JDABuilder.createDefault(env.get("DISCORD_TOKEN"));  // turn on bot + configure handler
            bot.addEventListeners(new CommandsHandler());
            bot.build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}