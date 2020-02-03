package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class GoogleSearch extends Command {

    protected String documentation = "**!googlesearch** [query] - responds with top three Google search results for that query.";

    private String[] credentials = new String[2];

    public GoogleSearch() {
        super("googlesearch");
        try {
            String file = "googlecredentials.txt";
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            credentials[0] = buffer.readLine();
            credentials[1] = buffer.readLine();
            buffer.close();
            reader.close();
        } catch (Exception e) {
            //
        }
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) { // !googlesearch how to make a discord bot

        String query = "";
        for (int i = 1; i < args.length; i++) { query += "+" + args[i]; } // query formatting, was broken before because i was appending with spaces

        try {
            MyGETRequest(event, query);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void MyGETRequest(GuildMessageReceivedEvent event, String query) throws Exception {

        // put google links and link to where i got this from
        // my search URL, query is the custom string we feed it, limit 3 "num=3"
        URL urlForGetRequest = new URL("https://www.googleapis.com/customsearch/v1?q=" + query + "&cx=" + credentials[0] + "&num=3&key=" + credentials[1]);
        String readLine;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            while ((readLine = in.readLine()) != null) {
                response.append(readLine); // response is my JSON
            }
            in.close();

            System.out.println(response); // testing purposes

            JSONObject obj = new JSONObject(response.toString()); // parsing my JSON object
            if (obj.has("items")) {
                JSONArray arr = obj.getJSONArray("items");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj2 = (JSONObject) arr.get(i);
                    event.getChannel().sendMessage((String) obj2.get("link")).queue();
                }
            }
            else {
                event.getChannel().sendMessage("Sorry, I wasn't able to find any results for that query. :(").queue();
            }
        }
        else {
            System.err.println("GET request failed.");
        }
    }
}

// JSONObject obj = new JSONObject(" ... ");
// String pageName = obj.getJSONObject("pageInfo").getString("pageName");
// JSONArray arr = obj.getJSONArray("posts");
// for (int i = 0; i < arr.length(); i++) {
//     String post_id = arr.getJSONObject(i).getString("post_id");
// }