import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class featureTODO extends ListenerAdapter {

    /* template: !todoadd, !todoview, !todocomplete [id], !todoremove [id] */
    /* featuretodo(user_id, user_query, when_added, is_complete) */

    String url = "";
    String user = "";
    String password = "";
    Connection connection;

    public featureTODO() throws Exception {
        String fileName = "SQLconnector.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader((fileReader));
        this.url = bufferedReader.readLine();
        this.user = bufferedReader.readLine();
        this.password = bufferedReader.readLine();
        this.connection = createDBConnection();
        bufferedReader.close();
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String userMessage = event.getMessage().getContentRaw();
        int x = userMessage.indexOf(' ');
        String command = userMessage.substring(0, x);
        String remainder = userMessage.substring(x);
        long user_id = event.getAuthor().getIdLong();

//        String input = "hello world, this is a line of text";
//        int i = input.indexOf(' ');
//        String word = input.substring(0, i);
//        String rest = input.substring(i);

//        String[] messageSent = event.getMessage().getContentRaw().split(" "); // optimize this one later i guess

        // !todoadd take out the trash

//        String mystring = "the quick brown fox";
//        String arr[] = mystring.split(" ", 2);
//        String firstWord = arr[0];                the
//        String theRest = arr[1];                  quick brown fox

        if (command.equalsIgnoreCase("!todoadd")) { // !todoadd take out the trash
            String query = "";
        }

        else if (command.equalsIgnoreCase("!todoview")) { // !todoview [NO ARGS]

            String result = "";
            String query = String.format("SELECT FROM rosie.featuretodo WHERE user_id = 190276271488499713");
            // String query = String.format("SELECT FROM featuretodo WHERE user_id = (%d)", user_id);

            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    result += rs.getString("user_query") + "\n";
                }

            } catch (Exception e) {
                System.out.println("Error executing query!");
            }

            event.getChannel().sendMessage(result).queue();

        }

        else if (command.equalsIgnoreCase("!todocompleted")) { // !todocompleted take out the trash

        }

        else if (command.equalsIgnoreCase("todoremove")) { // !todoremove take out the trash

        }
    }

    Connection createDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            return conn;
        } catch (Exception e) {
            System.out.println("Connection failed!");
        }
        return null;
    }
}