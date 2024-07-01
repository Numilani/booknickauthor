package me.numilani.booknickauthor;

import me.numilani.booknickauthor.commands.WriteNoteCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.bukkit.CloudBukkitCapabilities;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.meta.CommandMeta;
import org.incendo.cloud.paper.LegacyPaperCommandManager;

public final class BookNickAuthor extends JavaPlugin {
//    CloudSimpleHandler handler = new CloudSimpleHandler();
    public LegacyPaperCommandManager<CommandSender> manager;
    public AnnotationParser<CommandSender> annotationParser;


    @Override
    public void onEnable() {
        try {
            manager = LegacyPaperCommandManager.createNative(this, ExecutionCoordinator.simpleCoordinator());
            if (manager.hasCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER)) {
                // Register Brigadier mappings for rich completions
                manager.registerBrigadier();
            } else if (manager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
                // Use Paper async completions API (see Javadoc for why we don't use this with Brigadier)
                manager.registerAsynchronousCompletions();
            }
            annotationParser = new AnnotationParser(manager, CommandSender.class, parameters -> CommandMeta.empty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServer().getPluginManager().registerEvents(new BookListener(), this);
        annotationParser.parse(new WriteNoteCommand(this));
    }

    @Override
    public void onDisable() {
    }

}
