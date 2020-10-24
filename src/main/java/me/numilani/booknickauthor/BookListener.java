package me.numilani.booknickauthor;

import com.earth2me.essentials.User;
import com.earth2me.essentials.UserMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

public class BookListener implements Listener {

    @EventHandler
    public void onBookSign(PlayerEditBookEvent event)
    {
        Bukkit.getLogger().info("test");
        if (event.isSigning())
        {
            String nick = event.getPlayer().getDisplayName();

            BookMeta book = event.getNewBookMeta();
            book.setAuthor(nick);
            event.setNewBookMeta(book);
        }
    }
}
