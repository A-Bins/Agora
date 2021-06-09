package com.bins.agora.structure.objects

import com.bins.agora.Agora
import com.bins.agora.Agora.Companion.goldRush
import com.bins.agora.Agora.Companion.ins
import com.bins.ruins.utilities.Receiver.bb
import net.minecraft.server.v1_16_R3.Items.it
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//        if(!isStarting) return false
//        if(waiters.size != 8) return false
class GoldRush {
    enum class Team{
        SHERIFF, OUTLAW, NOTHING
    }
    companion object{
        private val teams: HashMap<UUID, Team> = HashMap()
        var Player.team: Team
            get() { teams[uniqueId] }
            private set(value) { teams[uniqueId] = value }
    }
    val waiters: ArrayList<Player> = arrayListOf()
    var isStarting = false
    fun start(): Boolean{
        fun startTimer(timer: Int, isCancelled: (Int) -> Boolean, title: (Int) -> String, end: () -> Any) {
            ins.server.scheduler.runTaskLater(ins, Runnable {
                if(isCancelled(waiters.size)){
                    goldRush.waiters.forEach {
                        it.playSound(it.location, Sound.ENTITY_VILLAGER_NO, 1F, 1F)
                        it.sendTitle("","§c시작까지 필요한 인원이 부족합니다!", 20, 20, 20)
                    }
                    return@Runnable
                }
                if(timer == 10 || (5 downTo 1).contains(timer)) goldRush.waiters.forEach {

                    it.playSound(it.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 2F)
                    it.sendTitle("","시작까지 ${title(timer)}${timer}초 남았습니다..", 20, 20, 20)
                }
                if(timer != 0) startTimer(timer-1, isCancelled, title, end)
                else end()
            }, 20)
        }
        startTimer(10,
            {
                return@startTimer it != 8
            },
            {
                return@startTimer when {
                    it == 10 -> "§a"
                    (5 downTo 4).contains(it) -> "§6"
                    it == 3 -> "§e"
                    else -> "§c"
                }
            },
            {
                isStarting = true
                Bukkit.broadcastMessage("끝!")
            })
            return true
        }
    fun respawn(p: Player){
        fun respawnTimer(timer: Int, end: () -> Any){
            ins.server.scheduler.runTaskLater(ins, Runnable {
                goldRush.waiters.forEach {
                    it.playSound(it.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 2F)
                    it.sendTitle("","시작까지 ${timer}초 남았습니다..", 20, 20, 20)
                }
                if(timer != 0) respawnTimer(timer-1, end)
                else end()
            }, 20)
        }



    }
}