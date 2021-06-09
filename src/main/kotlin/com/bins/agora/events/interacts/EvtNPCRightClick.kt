package com.bins.agora.events.interacts

import com.bins.agora.Agora.Companion.goldRush
import net.citizensnpcs.api.event.NPCRightClickEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class EvtNPCRightClick: Listener {
    @EventHandler
    fun event(e: NPCRightClickEvent){
        val p = e.clicker
        when {
            e.npc.name == "골드러시" -> {
                p.teleportAsync(Location(Bukkit.getWorld("world"),4025.5, 202.0, 4143.5))
                if(!goldRush.waiters.contains(p))
                    goldRush.waiters.add(p)
            }
        }
    }
}