package me.numilani.booknickauthor;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import com.bergerkiller.bukkit.common.PluginBase;
import me.numilani.booknickauthor.commands.WriteNoteCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.bergerkiller.bukkit.common.cloud.CloudSimpleHandler;


import java.util.function.Function;
import java.util.logging.Logger;

public final class BookNickAuthor extends JavaPlugin {
//    CloudSimpleHandler handler = new CloudSimpleHandler();
    public PaperCommandManager<CommandSender> cmdHandler;
    public AnnotationParser<CommandSender> cmdParser;


    @Override
    public void onEnable() {
        try {
            cmdHandler = new PaperCommandManager<>(this, CommandExecutionCoordinator.simpleCoordinator(), Function.identity(), Function.identity());
            cmdParser = new AnnotationParser<>(cmdHandler, CommandSender.class, parserParameters -> SimpleCommandMeta.empty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServer().getPluginManager().registerEvents(new BookListener(), this);
//        this.getCommand("writenote").setExecutor(new WriteNoteCommand(this));
    }

    @Override
    public void onDisable() {
    }

}
