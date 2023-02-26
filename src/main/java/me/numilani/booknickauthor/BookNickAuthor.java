package me.numilani.booknickauthor;

import com.bergerkiller.bukkit.common.PluginBase;
import me.numilani.booknickauthor.commands.WriteNoteCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.bergerkiller.bukkit.common.cloud.CloudSimpleHandler;


import java.util.logging.Logger;

public final class BookNickAuthor extends PluginBase {
    CloudSimpleHandler handler = new CloudSimpleHandler();

    @Override
    public void enable() {
        handler.enable(this);
        handler.getParser().parse(new WriteNoteCommand(this));
        getServer().getPluginManager().registerEvents(new BookListener(), this);
//        this.getCommand("writenote").setExecutor(new WriteNoteCommand(this));
    }

    @Override
    public void disable() {
    }

    @Override
    public int getMinimumLibVersion() {
        return 0;
    }

    @Override
    public boolean command(CommandSender sender, String command, String[] args) {
        return true;
    }
}
