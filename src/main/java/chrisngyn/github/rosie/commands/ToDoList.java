package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoList extends Command {

    protected String documentation = "**!todo add** - add an entry to your todo list.\n" +
            "> **!todo view** - view your current todo list.\n" +
            "> **!todo complete** [existing entry] - mark an entry as completed.\n" +
            "> **!todo remove** [existing entry] - delete an entry from your to do list.";
    private String error = "";

    private String url = "";
    private String user = "";
    private String password = "";
    private Connection connection;

    public ToDoList() {
        super("todo");
        try {
            String file = "MySQLconnector.txt";
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader((reader));
            this.url = buffer.readLine();
            this.user = buffer.readLine();
            this.password = buffer.readLine();
            this.connection = createDBConnection();
            buffer.close();
            reader.close();
        } catch (Exception e) {
            this.error = "Error executing ToDo command.";
        }
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {

        if (!this.error.isEmpty()) {
            event.getChannel().sendMessage("Error executing this command. Please contact the bot creator!").queue();
            return; // if the try catch caught an exception, something went wrong, if the error string isn't empty, this returns.
        }

        long user_id = event.getAuthor().getIdLong();
        Date date = new Date(); // Sun Dec 22 18:53:32 EST 2019, footer
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // 22/12/2019 19:21:42, how when_added is formatted

        if (args.length == 1) {
            event.getChannel().sendMessage("Improper use of command. Please type !help for documentation.").queue();
            return;
        }

        switch (args[1]) {
            case "add":
                String add_content = "";
                for (int i = 2; i < args.length; i++) { add_content += args[i] + " "; }
                String add_query = "INSERT INTO rosie.featuretodo(user_id, user_query, when_added, is_completed) values (?, ?, ?, ?)";

                try {
                    PreparedStatement pstmt = this.connection.prepareStatement(add_query);
                    pstmt.setLong(1, user_id);
                    pstmt.setString(2, add_content);
                    pstmt.setString(3, formatter.format(date));
                    pstmt.setString(4, "No");
                    pstmt.execute();
                    event.getChannel().sendMessage("Your entry has been added to your to do list!").queue();
                } catch (Exception e) {
                    System.out.println("Error executing query for TODO_ADD.");
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }
                break;

            case "view":
                String view_query = String.format("SELECT featuretodo.user_query, featuretodo.when_added, featuretodo.is_completed FROM rosie.featuretodo WHERE featuretodo.user_id = (%d)", user_id);

                try {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(view_query);
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
                    // eb.setThumbnail("https://www.calltrackingmetrics.com/wp-content/uploads/2017/11/shopify_glyph.png"); ):
                    eb.setColor(9168790); // or Color.(anything), or www.shodor.org/stella2java/rgbint.html
                    eb.setFooter("Request was made at: " + date, null); // date or formatter.format(date);
                    event.getChannel().sendMessage(eb.build()).queue();
                } catch (Exception e) {
                    System.out.println("Error executing query for TODO_VIEW.");
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }
                break;

            case "complete":
                String complete_content = "";
                for (int i = 2; i < args.length; i++) { complete_content += args[i] + " "; }
                String complete_query = "UPDATE rosie.featuretodo SET featuretodo.is_completed = \"Yes\" WHERE featuretodo.user_id = ? AND user_query = ?";

                try {
                    PreparedStatement pstmt = connection.prepareStatement(complete_query);
                    pstmt.setLong(1, user_id);
                    pstmt.setString(2, complete_content);
                    pstmt.execute();
                    event.getChannel().sendMessage("Your entry has been updated in your to do list!").queue();
                } catch (Exception e) {
                    System.out.println("Error executing query for TODO_COMPLETE.");
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }
                break;

            // if user tries to delete an entry that doesn't exist, it'll still give confirmation because technically the query WAS a success.
            // need a way to check first if it exists.
            case "remove":
                String remove_content = "";
                for (int i = 2; i < args.length; i++) { remove_content += args[i] + " "; }
                String remove_query = "DELETE FROM rosie.featuretodo WHERE featuretodo.user_id = ? AND user_query = ?";

                try {
                    PreparedStatement pstmt = connection.prepareStatement(remove_query);
                    pstmt.setLong(1, user_id);
                    pstmt.setString(2, remove_content);
                    pstmt.execute();
                    event.getChannel().sendMessage("Your entry has been deleted in your to do list!").queue();
                } catch (Exception e) {
                    System.out.println("Error executing query for TODO_REMOVE!");
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }
                break;

            case "default":
                event.getChannel().sendMessage("Improper use of command. Please type **!help** for documentation.").queue();
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