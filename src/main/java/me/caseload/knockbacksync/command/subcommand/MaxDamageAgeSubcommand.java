package me.caseload.knockbacksync.command.subcommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.ChatColor;

public class MaxDamageAgeSubcommand {

    public CommandAPICommand getCommand() {
        return new CommandAPICommand("maxdamageage")
                .withPermission("knockbacksync.maxdamageage")
                .withArguments(new IntegerArgument("maxdamageage"))
                .executes((sender, args) -> {
                    Integer ms = (Integer) args.get("maxdamageage");
                    assert ms != null;

                    KnockbackSync.getInstance().getConfig().set("max_damage_age_milliseconds", ms);
                    KnockbackSync.getInstance().saveConfig();

                    String message = ChatColor.translateAlternateColorCodes('&',
                            KnockbackSync.getInstance().getConfig().getString("configure_max_damage_age_message", "&aSuccessfully configured the maximum damage age.")
                    );

                    sender.sendMessage(message);
                });
    }

}
