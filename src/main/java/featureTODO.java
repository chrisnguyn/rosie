import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class featureTODO extends ListenerAdapter {

    /* template: !todoadd, !todoview, !todocomplete [id], !todoremove [id] */
    /* prepared statements when? */

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

        Date date = new Date(); // Sun Dec 22 18:53:32 EST 2019

        String[] messageSent = event.getMessage().getContentRaw().split(" ");
//        int x = userMessage.indexOf(' ');
//        String command = userMessage.substring(0, x);
//        String remainder = userMessage.substring(x);
        long user_id = event.getAuthor().getIdLong();

//        if (messageSent[0].equalsIgnoreCase("testing")) {
//            EmbedBuilder eb = new EmbedBuilder();
//            eb.setTitle(event.getAuthor().getName() + "'s to do list");
//            eb.addField("Name", "Chris", true);
//            eb.setThumbnail("https://www.calltrackingmetrics.com/wp-content/uploads/2017/11/shopify_glyph.png");
//            eb.setColor(9168790); // or Color.[w/e], or www.shodor.org/stella2java/rgbint.html
//            eb.setFooter("Request was made at: " + date, null);
//            event.getChannel().sendMessage(eb.build()).queue();
//        }


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

        if (messageSent[0].equalsIgnoreCase("!eggs")) {
            event.getChannel().sendMessage("works").queue();
        }

        if (messageSent[0].equalsIgnoreCase("!todoadd")) { // !todoadd take out the trash
            String query = "";
        }

        else if (messageSent[0].equalsIgnoreCase("!todoview")) {

            // THIS WORKS, ADDING THE OTHER SHIT DOES NOT

            Connection connection = createDBConnection();

            String result = "";
            String query = String.format("SELECT featuretodo.user_query FROM rosie.featuretodo WHERE featuretodo.user_id = (%d)", user_id);

            try {

                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String entries = "";
                String added = "";
                String completed = "";

                while (rs.next()) {
                    result += rs.getString("featuretodo.user_query") + "\n";
//                    entries += rs.getString("featuretodo.user_query") + "\n";
//                    added += rs.getString("featuretodo.when_added") + "\n";
//                    completed += rs.getString("featuretodo.is_completed") + "\n";
                }

//                EmbedBuilder eb = new EmbedBuilder();
//                eb.setTitle(event.getAuthor().getName() + "'s to do list:");
//                eb.addField("Entry", "entries", true);
//                eb.addField("When", "added", true);
//                eb.addField("Completed", "completed", true);
//                eb.setThumbnail("https://www.calltrackingmetrics.com/wp-content/uploads/2017/11/shopify_glyph.png");
//                eb.setColor(9168790); // or Color.[w/e], or www.shodor.org/stella2java/rgbint.html
//                eb.setFooter("Request was made at: " + date, null);
//                event.getChannel().sendMessage(eb.build()).queue();

            } catch (Exception e) {
                System.out.println("Error executing query!");
            }

            event.getChannel().sendMessage("i got here").queue();

            event.getChannel().sendMessage(result).queue();
        }

        else if (messageSent[0].equalsIgnoreCase("!todocompleted")) { // !todocompleted take out the trash

        }

        else if (messageSent[0].equalsIgnoreCase("todoremove")) { // !todoremove take out the trash

        }
    }

//    Connection createDBConnection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rosie", "", "");
//            return conn;
//        } catch (Exception e) {
//            System.out.println("Connection failed!");
//        }
//        return null;
//    }

    Connection createDBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // com.mysql.jdbc.Driver is deprecated
            Connection con = DriverManager.getConnection(this.url, this.user, this.password);
            return con;
        } catch (Exception e){
            System.out.println("Connection failed!");
            System.out.println(e);
        }
        return null;
    }
}