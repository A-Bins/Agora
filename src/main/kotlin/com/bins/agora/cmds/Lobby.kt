package com.bins.agora.cmds

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandException
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Lobby : CommandExecutor{
    override fun onCommand(p: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(p is Player) p.teleportAsync(Location(Bukkit.getWorld("world"), 10004.5, 40.0, 10000.5, 90F, 1F))
        return false
    }
}