package de.seekyml.ninetyonetoolsbotlite;

import de.seekyml.ninetyonetoolsbotlite.events.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Plugin;

import javax.security.auth.login.LoginException;
import java.util.concurrent.TimeUnit;

import static de.seekyml.ninetyonetoolsbotlite.util.Values.*;

public class Main extends Plugin {
    private static Plugin plugin;
    private static JDA jda;
    private int players = 0;
    private int playersold = 1;
    private boolean mainserver = false;
    private boolean queue = false;
    private String already = "";

    public void onEnable() {
        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§6Starting 91bot").create());
        plugin = this;
        // PREPARE FOR DISCORD EVENTS TODO: ADD THEM
        try {
            jda = JDABuilder.createDefault(token).addEventListeners(new Object[] { new Events() }).build();
        } catch (LoginException e) {
           System.exit(0);
        }
        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§a91bot started successfully").create());
            ProxyServer.getInstance().getScheduler().schedule(Main.getPlugin(), () -> {
                try {
                    pingQueue();
                    pingServer();
                    playercount();
                    //  BOTH ONLINE
                    if(mainserver && queue){
                        if (!already.equals("both") || !(playersold ==players)){
                        Main.getJDA().getTextChannelById(getstatuschannel).editMessageById(msgid,
                                "```prolog\n" +
                                "                  █▀█ ▄█ ▀█▀\n" +
                                "                  ▀▀█  █  █\n" +
                                "                 SERVER STATUS\n" +
                                "\n" +
                                "  █▀█ █ █ █▀▀ █ █ █▀▀   ▀   █▀█ █▄ █ █   █ █▄ █ █▀▀\n" +
                                "  ▀▀█ █▄█ ██▄ █▄█ ██▄   ▄   █▄█ █ ▀█ █▄▄ █ █ ▀█ ██▄\n" +
                                "\n" +
                                "  █▀▄▀█ ▄▀█ █ █▄ █   ▀   █▀█ █▄ █ █   █ █▄ █ █▀▀\n" +
                                "  █ ▀ █ █▀█ █ █ ▀█   ▄   █▄█ █ ▀█ █▄▄ █ █ ▀█ ██▄\n" +
                                "\n" +
                                "                  " + players + "/91 Players\n" +
                                "                 Proxy: Online\n" +
                                "```").queue();
                            already = "both";
                            playersold = players;
                            ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§6Updated Server Status").create());
                        }
                    }
                    // ONLY MAIN OFF
                    if(!mainserver && queue){
                        if (!already.equals("main") || !(playersold ==players)){
                        Main.getJDA().getTextChannelById(getstatuschannel).editMessageById(msgid,
                                "```prolog\n" +
                                "                  █▀█ ▄█ ▀█▀\n" +
                                "                  ▀▀█  █  █\n" +
                                "                 SERVER STATUS\n" +
                                "\n" +
                                "  █▀█ █ █ █▀▀ █ █ █▀▀   ▀   █▀█ █▄ █ █   █ █▄ █ █▀▀\n" +
                                "  ▀▀█ █▄█ ██▄ █▄█ ██▄   ▄   █▄█ █ ▀█ █▄▄ █ █ ▀█ ██▄\n" +
                                "\n" +
                                "  █▀▄▀█ ▄▀█ █ █▄ █   ▀   █▀█ █▀▀ █▀▀ █   █ █▄ █ █▀▀\n" +
                                "  █ ▀ █ █▀█ █ █ ▀█   ▄   █▄█ █▀  █▀  █▄▄ █ █ ▀█ ██▄\n" +
                                "\n" +
                                "                  " + players + "/91 Players\n" +
                                "                 Proxy: Online\n" +
                                "```").queue();
                        already = "main";
                        playersold = players;
                        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§6Updated Server Status").create());
                        }
                    }
                    //BOTH OFF
                    if(!mainserver && !queue){
                        if(!already.equals("bothoff") || !(playersold ==players)){
                        Main.getJDA().getTextChannelById(getstatuschannel).editMessageById(msgid,
                                "```prolog\n" +
                                "                  █▀█ ▄█ ▀█▀\n" +
                                "                  ▀▀█  █  █\n" +
                                "                 SERVER STATUS\n" +
                                "\n" +
                                "  █▀█ █ █ █▀▀ █ █ █▀▀   ▀   █▀█ █▀▀ █▀▀ █   █ █▄ █ █▀▀\n" +
                                "  ▀▀█ █▄█ ██▄ █▄█ ██▄   ▄   █▄█ █▀  █▀  █▄▄ █ █ ▀█ ██▄\n" +
                                "\n" +
                                "  █▀▄▀█ ▄▀█ █ █▄ █   ▀   █▀█ █▀▀ █▀▀ █   █ █▄ █ █▀▀\n" +
                                "  █ ▀ █ █▀█ █ █ ▀█   ▄   █▄█ █▀  █▀  █▄▄ █ █ ▀█ ██▄\n" +
                                "\n" +
                                "                  " + players + "/91 Players\n" +
                                "                 Proxy: Online\n" +
                                "```").queue();
                        already = "bothoff";
                        playersold = players;
                        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§6Updated Server Status").create());
                        }
                    }
                    // ONLY QUEUE OFF
                    if(mainserver && !queue) {
                        if (!already.equals("queue") || !(playersold ==players)) {
                            Main.getJDA().getTextChannelById(getstatuschannel).editMessageById(msgid,
                                    "```prolog\n" +
                                            "                  █▀█ ▄█ ▀█▀\n" +
                                            "                  ▀▀█  █  █\n" +
                                            "                 SERVER STATUS\n" +
                                            "\n" +
                                            "  █▀█ █ █ █▀▀ █ █ █▀▀   ▀   █▀█ █▀▀ █▀▀ █   █ █▄ █ █▀▀\n" +
                                            "  ▀▀█ █▄█ ██▄ █▄█ ██▄   ▄   █▄█ █▀  █▀  █▄▄ █ █ ▀█ ██▄\n" +
                                            "\n" +
                                            "  █▀▄▀█ ▄▀█ █ █▄ █   ▀   █▀█ █▄ █ █   █ █▄ █ █▀▀\n" +
                                            "  █ ▀ █ █▀█ █ █ ▀█   ▄   █▄█ █ ▀█ █▄▄ █ █ ▀█ ██▄\n" +
                                            "\n" +
                                            "                  " + players + "/91 Players\n" +
                                            "                 Proxy: Online\n" +
                                            "```").queue();
                            already = "queue";
                            playersold = players;
                            ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§6Updated Server Status").create());
                        }
                    }
                } catch (NullPointerException ignored) {}
            }, 1, 5, TimeUnit.SECONDS);
    }

    public void onDisable() {
        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder("§6Disabling 91bot").create());
        already = null;
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static JDA getJDA() {
        return jda;
    }

    // Ping Main Server and check if its available
    public void pingServer(){
        getProxy().getServers().get("server").ping((result, error) -> {
            if(error!=null){
                mainserver = false;
                // Not reachable --> Offline
            }else{
                mainserver = true;
                // Found --> Online
            }

        });
    }
    // Ping Queue Server and check if its available
    public void pingQueue(){
        getProxy().getServers().get("queue").ping((result, error) -> {
            if(error!=null){
                queue = false;
                // Not reachable --> Offline
            }else{
                queue = true;
                // Found --> Online
            }

        });
    }
    // Get Playercount, count it together
    public void playercount(){
        int serverplayers = getProxy().getServerInfo("server").getPlayers().size();
        int queueplayers = getProxy().getServerInfo("queue").getPlayers().size();
        players = serverplayers + queueplayers;
    }
}
