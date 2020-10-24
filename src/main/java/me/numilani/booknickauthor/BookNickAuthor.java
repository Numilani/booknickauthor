package me.numilani.booknickauthor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BookNickAuthor extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BookListener(), this);

        Logger log = getLogger();
        log.info("BookNickAuthor Enabled.");
    }

    @Override
    public void onDisable() {
        Logger log = getLogger();
        log.info("BookNickAuthor Disabled.");
    }
}
