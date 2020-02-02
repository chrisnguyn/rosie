package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;

public class GoogleSearch extends Command {

    public GoogleSearch() throws Exception {
        super("googlesearch");
        String file = "googlecredentials.txt";
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        String searchEngineID = buffer.readLine();
        String APIKey = buffer.readLine();
        buffer.close();
        reader.close();
        String credentials[] = {searchEngineID, APIKey};
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) { // !googlesearch tesla
        String query = "";
        for (int i = 3; i < args.length; i++) { query += args[i] + " "; }

        try {
            MyGETRequest(event, query);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

}