package brawl.snaxv2.brawlEssentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffChat extends Command{
	public staffChat() {
		super("sc");
	}
    public Connection connection;
    public String host, database, username, password;
    public int port;
    
    public void openConnection() throws SQLException, ClassNotFoundException {
	    if (connection != null && !connection.isClosed()) {
	        return;
	    }
	 
	    synchronized (this) {
	        if (connection != null && !connection.isClosed()) {
	            return;
	        }
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
	    }
    }
	@Override
	public void execute(CommandSender arg0, String[] arg1) {
        host = "localhost";
        port = 3306;
        database = "brawl";
        username = "root";
        password = "haxor";  
		ProxiedPlayer player = BungeeCord.getInstance().getPlayer(arg0.getName());
	      try {
		         openConnection();
			     Statement statement = connection.createStatement();
		         ResultSet result = statement.executeQuery("SELECT * FROM rank WHERE UUID = '" +player.getUniqueId().toString() + "';");
		         if (result != null) {
		        	 while(result.next()) {
		        		 if (result.getInt("RANK") > 3) {
		        			 for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
		        			      try {
		        					     Statement statement2 = connection.createStatement();
		        				         ResultSet result2 = statement2.executeQuery("SELECT * FROM rank WHERE UUID = '" +p.getUniqueId().toString() + "';");
		        				         if (result2 != null) {
		        				        	 while(result2.next()) {
		        				        		 if (result2.getInt("RANK") > 3) {
		        				        			 String message = "";
		        				        			 for (int i = 0; i<arg1.length; i++) {
		        				        				 message += arg1[i] + " ";
		        				        			 }
		        				        			 p.sendMessage("[SC] " + player.getName() + " > " + message);
		        				        		 }
		        				        	 }
		        				         }
		        				      } catch(SQLException e) {
		        				         e.printStackTrace();
		        				      }
		        			 }
		        		 }
		        	 }
		         }
		      } catch(ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch(SQLException e) {
		         e.printStackTrace();
		      }
	}
}
