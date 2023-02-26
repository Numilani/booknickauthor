package me.numilani.booknickauthor.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.specifier.Quoted;
import me.numilani.booknickauthor.BookNickAuthor;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WriteNoteCommand {
    BookNickAuthor plugin;

    public WriteNoteCommand(BookNickAuthor plugin) {
        this.plugin = plugin;
    }

    @CommandMethod("writenote <title> <message>")
    public void WriteNote(CommandSender sender, @Quoted @Argument("title") String title, @Quoted @Argument("message") String message){
        Player player = (Player) sender;

        ItemStack heldItem = ((Player) sender).getInventory().getItem(((Player) sender).getInventory().getHeldItemSlot());
        if (heldItem == null || heldItem.getType() != Material.PAPER) {
            sender.sendMessage("You must be holding paper to write a note!");
            return;
        }

        ItemMeta tempMeta = heldItem.getItemMeta();
        tempMeta.setDisplayName(title);
        tempMeta.setLore(SplitLoreText(message));
        heldItem.setItemMeta(tempMeta);
    }

//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (args.length < 2) {
//            sender.sendMessage("Syntax: /writenote <title> <message>");
//            return true;
//        }
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//
//            ItemStack heldItem = ((Player) sender).getInventory().getItem(((Player) sender).getInventory().getHeldItemSlot());
//            if (heldItem == null || heldItem.getType() != Material.PAPER) {
//                sender.sendMessage("You must be holding paper to write a note!");
//                return true;
//            }
//
//            ItemMeta tempMeta = heldItem.getItemMeta();
//            tempMeta.setDisplayName(args[0]);
//            String[] x = (String[]) ArrayUtils.removeElement(args, 0);
//            String message = String.join(" ", x);
//            tempMeta.setLore(SplitLoreText(message));
//
//            heldItem.setItemMeta(tempMeta);
//
//            return true;
//        }
//        return false;
//    }

    private ArrayList<String> SplitLoreText(String text) {
        ArrayList<String> loretext = new ArrayList<>();
        String[] splits = text.split(" ");
        String currentLine = new String();

        for (int i = 0; i < splits.length;) {
            while (currentLine.length() < 32 && i < splits.length) {
                currentLine += " " + splits[i];
                i++;
            }
            loretext.add(currentLine);
            currentLine = new String();
        }

        return loretext;
    }
}
