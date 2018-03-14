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

public class playerMessages extends Command {
	public playerMessages () {
		super ("msg");
	}

	@Override
	public void execute(CommandSender arg0, String[] arg1) {

			ProxiedPlayer og = BungeeCord.getInstance().getPlayer(arg0.getName());
			ProxiedPlayer target = BungeeCord.getInstance().getPlayer(arg1[0]);
			if (target!=null) {
    			 String message = "";
    			 for (int i = 0; i<arg1.length-1; i++) {
    				 message += arg1[i+1] + " ";
    			 }
				og.sendMessage("[To] " + target.getName() + " > " + message);
				target.sendMessage("[From] " + og.getName() + " > " + message);
			}
	}
}