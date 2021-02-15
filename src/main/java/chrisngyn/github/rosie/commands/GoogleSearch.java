package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.*;

public class GoogleSearch extends Command {
    Dotenv env = Dotenv.load();
    protected String documentation = "**r!googlesearch** [query] - responds with top three Google search results for that query.";
    private String error = "";
    private String[] credentials = new String[2];

    public GoogleSearch() {
        super("googlesearch");
        try {
            credentials[0] = env.get("GCSE_ENGINE_ID");
            credentials[1] = env.get("GCSE_API_KEY");
        } catch (Exception e) {
            System.err.println(e + "\n Error trying to build GoogleSearch instance.");
            this.error = "Error executing the Google Search command. Please contact the bot creator.";
        }
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (!this.error.isEmpty()) {
            event.getChannel().sendMessage(error).queue();
            return;
        }

        String query = "";
        for (int i = 1; i < args.length; i++) { query += "+" + args[i]; }  // structure query to account for spaces

        try {
            sendGETRequest(event, query);
        } catch (Exception e) {
            System.err.println(e);
            event.getChannel().sendMessage("Error trying to execute your query. Please contact the bot creator.").queue();
        }
    }

    public void sendGETRequest(GuildMessageReceivedEvent event, String query) throws Exception {
        URL url = new URL("https://www.googleapis.com/customsearch/v1?q=" + query + "&cx=" + credentials[0] + "&num=3&key=" + credentials[1]);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String readLine;

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }

            in.close();
            System.out.println(response);
            JSONObject obj = new JSONObject(response.toString()); // response from request; need to parse

            if (obj.has("items")) {
                JSONArray arr = obj.getJSONArray("items");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj2 = (JSONObject) arr.get(i);
                    event.getChannel().sendMessage((String) obj2.get("link")).queue(); // only grab links
                }
            } else {
                event.getChannel().sendMessage("Sorry, I wasn't able to find any results for that query! :(").queue();
            }
        } else {
            System.err.println("GET request failed.");
            event.getChannel().sendMessage("Error trying to execute your query. Please contact the bot creator.").queue();
        }
    }
}