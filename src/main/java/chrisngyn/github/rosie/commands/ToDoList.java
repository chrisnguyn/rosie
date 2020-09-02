package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class ToDoList extends Command {
    Dotenv env = Dotenv.load();
    protected String documentation = "**r!todo add** - add an entry to your todo list.\n" +
            "> **r!todo view** - view your current todo list.\n" +
            "> **r!todo complete** [existing entry] - mark an entry as completed.\n" +
            "> **r!todo remove** [existing entry] - delete an entry from your to do list.";
    private String error = "";
    private String url = "";
    private String user = "";
    private String password = "";
    private Connection connection;

    public ToDoList() {
        super("todo");
        try {
            this.url = env.get("MYSQL_URL");
            this.user = env.get("MYSQL_USER");
            this.password = env.get("MYSQL_PW");
            this.connection = createDBConnection();
        } catch (Exception e) {
            System.err.println(e + "\n Error trying to build ToDoList instance.");
            this.error = "Error executing todo command. Please contact bot creator.";
        }
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (!this.error.isEmpty()) {
            event.getChannel().sendMessage(error).queue();
            return;
        }

        long userId = event.getAuthor().getIdLong();
        Date date = new Date(); // sample Sun Dec 22 18:53:32 EST 2019
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // sample 22/12/2019 19:21:42

        if (args.length == 1) {
            event.getChannel().sendMessage("Improper use of command. Please consult \"**r!help**\" for documentation.").queue();
            return;
        }

        switch (args[1]) {
            case "add":
                String content = "";
                for (int i = 2; i < args.length; i++) { content += args[i] + " "; }

                if (todoAdd(content, userId, date, formatter) == 0) {
                    event.getChannel().sendMessage("Your entry has been added to your to do list!").queue();
                } else {
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }

                break;

            case "view":
                String author = event.getAuthor().getName();
                EmbedBuilder response = todoView(author, userId, date, formatter);

                if (response == null) {
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                } else {
                    event.getChannel().sendMessage(response.build()).queue();
                }

                break;

            case "complete":
                String complete_content = "";
                for (int i = 2; i < args.length; i++) { complete_content += args[i] + " "; }
                String complete_query = "UPDATE rosie.featuretodo SET featuretodo.is_completed = \"Yes\" WHERE featuretodo.user_id = ? AND user_query = ?";

                try {
                    PreparedStatement pstmt = connection.prepareStatement(complete_query);
                    pstmt.setLong(1, userId);
                    pstmt.setString(2, complete_content);
                    pstmt.execute();
                    event.getChannel().sendMessage("Your entry has been updated in your to do list!").queue();
                } catch (Exception e) {
                    System.err.println("Error executing query for TODO_COMPLETE.");
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }
                break;

            // if user tries to delete an entry that doesn't exist, it'll still give confirmation; need a way to check first if it exists.
            case "remove":
                String remove_content = "";
                for (int i = 2; i < args.length; i++) { remove_content += args[i] + " "; }
                String remove_query = "DELETE FROM rosie.featuretodo WHERE featuretodo.user_id = ? AND user_query = ?";

                try {
                    PreparedStatement pstmt = connection.prepareStatement(remove_query);
                    pstmt.setLong(1, userId);
                    pstmt.setString(2, remove_content);
                    pstmt.execute();
                    event.getChannel().sendMessage("Your entry has been deleted in your to do list!").queue();
                } catch (Exception e) {
                    System.err.println("Error executing query for TODO_REMOVE!");
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }
                break;

            default:
                event.getChannel().sendMessage("Improper use of command. Please type **!help** for documentation.").queue();
        }
    }

    private Connection createDBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // com.mysql.jdbc.Driver is deprecated
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e){
            System.err.println(e + "\n Unable to create DB connection!");
            return null;
        }
    }

    private int todoAdd(String content, long userId, Date date, SimpleDateFormat formatter) { // return 0 if successful, -1 otherwise
        String query = "INSERT INTO rosie.featuretodo(user_id, user_query, when_added, is_completed) values (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setLong(1, userId);
            pstmt.setString(2, content);
            pstmt.setString(3, formatter.format(date));
            pstmt.setString(4, "No");
            pstmt.execute();
            return 0;
        } catch (Exception e) {
            System.err.println("Error executing query for todoAdd.");
            System.err.println(e);
            return -1;
        }
    }

    private EmbedBuilder todoView(String author, long userId, Date date, SimpleDateFormat formatter) {
        String query = String.format("SELECT featuretodo.user_query, featuretodo.when_added, featuretodo.is_completed FROM rosie.featuretodo WHERE featuretodo.user_id = (%d)", userId);

        try {
            Statement stmt = this.connection.createStatement();
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
            eb.setTitle(author + "'s to do list:");
            eb.addField("Your Entries", entries, true);
            eb.addField("Added When", added, true);
            eb.addField("Is Completed", completed, true);
            eb.setThumbnail("https://www.calltrackingmetrics.com/wp-content/uploads/2017/11/shopify_glyph.png");
            eb.setColor(9168790); // or Color.(anything), or www.shodor.org/stella2java/rgbint.html
            eb.setFooter("Request was made at: " + date, null); // date or formatter.format(date);
            return eb;
        } catch (Exception e) {
            System.err.println("Error executing query for todoView.");
            System.err.println(e);
            return null;
        }
    }

    private void todoComplete() {

    }

    private void todoRemove() {

    }
}