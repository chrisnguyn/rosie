import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeatureTODO extends ListenerAdapter {

    // How to call: !todoadd [query], !todoview, !todocomplete [existing entry], !todoremove [existing entry]

    String url = "";
    String user = "";
    String password = "";
    Connection connection;

    public FeatureTODO() throws Exception {
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

        Date date = new Date(); // Sun Dec 22 18:53:32 EST 2019, footer
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // 22/12/2019 19:21:42, how when_added is formatted

        // possible optimization? only works if someone puts TWO words minimum though, or "command" doesn't catch the first word (because no space)
        // int x = userMessage.indexOf(' ');
        // String command = userMessage.substring(0, x);
        // String remainder = userMessage.substring(x);

        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        String restOfMessage = "";
        long user_id = event.getAuthor().getIdLong();

        if (messageSent[0].equalsIgnoreCase("!todoadd")) {

            for (int i = 1; i < messageSent.length; i++) {
                restOfMessage += messageSent[i] + " ";
            }

            Connection connection = createDBConnection();
            String query = "INSERT INTO rosie.featuretodo(user_id, user_query, when_added, is_completed) values (?, ?, ?, ?)";

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setLong(1, user_id);
                pstmt.setString(2, restOfMessage);
                pstmt.setString(3, formatter.format(date));
                pstmt.setString(4, "No");
                pstmt.execute();
                event.getChannel().sendMessage("Your entry has been added to your to do list!").queue();

            } catch (Exception e) {
                System.out.println("Error executing query for todoadd!");
                event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
            }
        }

        else if (messageSent[0].equalsIgnoreCase("!todoview")) {

            Connection connection = createDBConnection();
            String query = String.format("SELECT featuretodo.user_query, featuretodo.when_added, featuretodo.is_completed FROM rosie.featuretodo WHERE featuretodo.user_id = (%d)", user_id);

            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String entries = "";
                String added = "";
                String completed = "";

                while (rs.next()) {
                    entries += rs.getString("featuretodo.user_query") + "\n\n";
                    added += rs.getString("featuretodo.when_added") + "\n\n";
                    completed += rs.getString("featuretodo.is_completed") + "\n\n";
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(event.getAuthor().getName() + "'s to do list:");
                eb.addField("Your Entries", entries, true);
                eb.addField("Added When", added, true);
                eb.addField("Is Completed", completed, true);
                eb.setThumbnail("https://www.calltrackingmetrics.com/wp-content/uploads/2017/11/shopify_glyph.png"); // hehe
                eb.setColor(9168790); // or Color.(anything), or www.shodor.org/stella2java/rgbint.html
                eb.setFooter("Request was made at: " + date, null); // date or formatter.format(date);
                event.getChannel().sendMessage(eb.build()).queue();

            } catch (Exception e) {
                System.out.println("Error executing query for todoview!");
                event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
            }
        }

        else if (messageSent[0].equalsIgnoreCase("!todocomplete")) {

            for (int i = 1; i < messageSent.length; i++) {
                restOfMessage += messageSent[i] + " ";
            }

            Connection connection = createDBConnection();
            String query = "UPDATE rosie.featuretodo SET featuretodo.is_completed = \"Yes\" WHERE featuretodo.user_id = ? AND user_query = ?";

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setLong(1, user_id);
                pstmt.setString(2, restOfMessage);
                pstmt.execute();
                event.getChannel().sendMessage("Your entry has been updated in your to do list!").queue();

            } catch (Exception e) {
                System.out.println("Error executing query for todocomplete!");
                event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
            }

        }

        // if user tries to delete an entry that doesn't exist, it'll still give confirmation because technically the query WAS a success.
        // need a way to check first if it exists.
        else if (messageSent[0].equalsIgnoreCase("!todoremove")) {

            for (int i = 1; i < messageSent.length; i++) {
                restOfMessage += messageSent[i] + " ";
            }

            Connection connection = createDBConnection();
            String query = "DELETE FROM rosie.featuretodo WHERE featuretodo.user_id = ? AND user_query = ?";

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setLong(1, user_id);
                pstmt.setString(2, restOfMessage);
                pstmt.execute();
                event.getChannel().sendMessage("Your entry has been deleted in your to do list!").queue();

            } catch (Exception e) {
                System.out.println("Error executing query for todoremove!");
                event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
            }
        }
    }

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