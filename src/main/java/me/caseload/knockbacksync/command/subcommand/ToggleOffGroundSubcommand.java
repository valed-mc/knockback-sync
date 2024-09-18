package me.caseload.knockbacksync.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.ChatColor;

public class ToggleOffGroundSubcommand {

    public CommandAPICommand getCommand() {
        return new CommandAPICommand("toggleoffground")
                .withPermission("knockbacksync.toggleoffground")
                .executes((sender, args) -> {
                    boolean toggledState = !KnockbackSync.getInstance().getConfig().getBoolean("toggle_offground");

                    KnockbackSync.getInstance().getConfig().set("toggle_offground", toggledState);
                    KnockbackSync.getInstance().saveConfig();

                    String message = ChatColor.translateAlternateColorCodes('&',
                            toggledState ?
                                    KnockbackSync.getInstance().getConfig().getString("offground_enable_message", "&aSuccessfully enabled ValedKnockbackSync offground.") :
                                    KnockbackSync.getInstance().getConfig().getString("offground_disable_message", "&cSuccessfully disabled ValedKnockbackSync offground.")
                    );

                    sender.sendMessage(message);
                });
    }

}
