package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ToDoList extends Command {
    Dotenv env = Dotenv.load();
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
            this.url = env.get("MYSQL_URL");
            this.user = env.get("MYSQL_USER");
            this.password = env.get("MYSQL_PW");
            this.connection = createDBConnection();
        } catch (Exception e) {
            System.err.println(e);
            this.error = "Error executing todo command. Please contact bot creator.";
        }
    }

    @Override
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
                String addContent = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                if (todoAdd(addContent, userId, date, formatter) == 0) {
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

            case "complete": // if you try to 'complete' something that doesn't exist it'll still give confirmation :(
                String completeContent = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                if (todoComplete(completeContent, userId) == 0) {
                    event.getChannel().sendMessage("Your entry has been updated in your to do list!").queue();
                } else {
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }

                break;

            case "remove": // if you try to 'remove' something that doesn't exist it'll still give confirmation :(
                String removeContent = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                if (todoRemove(removeContent, userId) == 0) {
                    event.getChannel().sendMessage("Your entry has been deleted in your to do list!").queue();
                } else {
                    event.getChannel().sendMessage("Sorry, but something went wrong. Please alert the bot creator!").queue();
                }

                break;

            default:
                event.getChannel().sendMessage("Improper use of command. Please type **!help** for documentation.").queue();
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
            System.err.println(e + "\n Error executing query for todoAdd.");
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
            System.err.println(e + "\n Error executing query for todoView.");
            return null;
        }
    }

    private int todoComplete(String content, long userId) {
        String query = "UPDATE rosie.featuretodo SET featuretodo.is_completed = \"Yes\" WHERE featuretodo.user_id = ? AND user_query = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(1, userId);
            pstmt.setString(2, content);
            pstmt.execute();
            return 0;
        } catch (Exception e) {
            System.err.println(e + "\n Error executing query for todoComplete.");
            return -1;
        }
    }

    private int todoRemove(String content, long userId) {
        String query = "DELETE FROM rosie.featuretodo WHERE featuretodo.user_id = ? AND user_query = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(1, userId);
            pstmt.setString(2, content);
            pstmt.execute();
            return 0;
        } catch (Exception e) {
            System.err.println(e + "\n Error executing query for todoRemove.");
            return -1;
        }
    }

    private Connection createDBConnection(){
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e){
            System.err.println(e + "\n Unable to create DB connection!");
            return null;
        }
    }
}