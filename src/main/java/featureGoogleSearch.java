import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class featureGoogleSearch extends ListenerAdapter {

    // !googlesearch [query], return top 3 links in google
    // GET https://www.googleapis.com/customsearch/v1?q=[QUERY_HERE]&cx=[SEARCH_ENGINE_ID_HERE]&num=3&key=[API_KEY_HERE]

    public static String[] getGoogleCredentials() throws Exception {
        String fileName = "googleCredentials.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String searchEngineID = bufferedReader.readLine();
        String APIKey = bufferedReader.readLine();
        bufferedReader.close();

        String credentials[] = {searchEngineID, APIKey};
        return credentials;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!googlesearch")) {

            String query = messageSent[1];

            for (int i = 2; i < messageSent.length; i++) {
                query += "+" + messageSent[i];
            }

            try {
                MyGETRequest(event, query);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void MyGETRequest(GuildMessageReceivedEvent event, String query) throws Exception {

        // My search URL. Query is the custom word we feed it, limit 3 "num=3"
        URL urlForGetRequest = new URL("https://www.googleapis.com/customsearch/v1?q=" + query + "&cx=" + getGoogleCredentials()[0] + "&num=3&key=" + getGoogleCredentials()[1]);
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

            System.out.println(response); // testing purposes.

            // Parsing JSON object
            JSONObject obj = new JSONObject(response.toString());
            if (obj.has("items")) {
                JSONArray arr = obj.getJSONArray("items");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj2 = (JSONObject) arr.get(i);

                    event.getChannel().sendMessage("`" + (String) obj2.get("link") + "`").queue();
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

/*
    JSONObject obj = new JSONObject(" .... ");
    String pageName = obj.getJSONObject("pageInfo").getString("pageName");

    JSONArray arr = obj.getJSONArray("posts");
for (int i = 0; i < arr.length(); i++)
        {
        String post_id = arr.getJSONObject(i).getString("post_id");
        ......
        }
*/