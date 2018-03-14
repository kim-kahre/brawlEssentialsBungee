package brawl.snaxv2.brawlEssentials;

import net.md_5.bungee.api.plugin.Plugin;

public class main extends Plugin {

    @Override
    public void onEnable() {
    	getProxy().getPluginManager().registerCommand(this, new staffChat());
    	getProxy().getPluginManager().registerCommand(this, new playerMessages());
    }
}