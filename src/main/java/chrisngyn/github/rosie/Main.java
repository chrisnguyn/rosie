package chrisngyn.github.rosie;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
    public static void main(String[] args) {
        Dotenv env = Dotenv.load();

        try {
            JDA bot = new JDABuilder((env.get("DISCORD_TOKEN"))).build();  // turn on bot + configure handler
            bot.addEventListener(new CommandsHandler());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}