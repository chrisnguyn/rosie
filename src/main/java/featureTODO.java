import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

        Date date = new Date(); // Sun Dec 22 18:53:32 EST 2019, footer
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // 22/12/2019 19:21:42, how when_added is formatted

        // possible optimization? only works if someone puts TWO words minimum though, or "command" doesn't catch the first word (because no space)
        // int x = userMessage.indexOf(' ');
        // String command = userMessage.substring(0, x);
        // String remainder = userMessage.substring(x);

        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        long user_id = event.getAuthor().getIdLong();

        if (messageSent[0].equalsIgnoreCase("!todoadd")) { // !todoadd take out the trash

            Connection connection = createDBConnection();
            String query = String.format("");
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
                    entries += rs.getString("featuretodo.user_query") + "\n";
                    added += rs.getString("featuretodo.when_added") + "\n";
                    completed += rs.getString("featuretodo.is_completed") + "\n";
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(event.getAuthor().getName() + "'s to do list:");
                eb.addField("Entry", entries, true);
                eb.addField("When", added, true);
                eb.addField("Completed", completed, true);
                eb.setThumbnail("https://www.calltrackingmetrics.com/wp-content/uploads/2017/11/shopify_glyph.png");
                eb.setColor(9168790); // or Color.[w/e], or www.shodor.org/stella2java/rgbint.html
                eb.setFooter("Request was made at: " + formatter.format(date), null); // date, format.format(date);
                event.getChannel().sendMessage(eb.build()).queue();

            } catch (Exception e) {
                System.out.println("Error executing query!");
            }

            event.getChannel().sendMessage("i got here").queue();

//            event.getChannel().sendMessage(result).queue();
        }

        else if (messageSent[0].equalsIgnoreCase("!todocompleted")) { // !todocompleted take out the trash
            Connection connection = createDBConnection();
            String query = String.format("");
        }

        else if (messageSent[0].equalsIgnoreCase("todoremove")) { // !todoremove take out the trash
            Connection connection = createDBConnection();
            String query = String.format("");
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