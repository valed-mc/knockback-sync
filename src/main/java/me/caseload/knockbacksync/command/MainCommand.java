package me.caseload.knockbacksync.command;

import dev.jorel.commandapi.CommandAPICommand;
import me.caseload.knockbacksync.command.subcommand.MaxDamageAgeSubcommand;
import me.caseload.knockbacksync.command.subcommand.OffsetSubcommand;
import me.caseload.knockbacksync.command.subcommand.PingSubcommand;
import me.caseload.knockbacksync.command.subcommand.ToggleSubcommand;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

public class MainCommand {

    public void register() {
        new CommandAPICommand("knockbacksync")
                .withAliases("kbsync")
                .withSubcommand(new PingSubcommand().getCommand())
                .withSubcommand(new ToggleSubcommand().getCommand())
                .withSubcommand(new OffsetSubcommand().getCommand())
                .withSubcommand(new MaxDamageAgeSubcommand().getCommand())
                .executes((sender, args) -> {
                    sender.sendMessage(
                            ChatColor.translateAlternateColorCodes(
                                    '&',
                                    "&eThis server is running &6ValedKnockbackSync&e.\n&eA fork of &6KnockbackSync &eby &6CASELOAD7000&e,\n&emade by the &6Valed Development Team&e.\n\n&fhttps://github.com/valed-mc/knockback-sync"
                            ));
                })
                .register();
    }
}