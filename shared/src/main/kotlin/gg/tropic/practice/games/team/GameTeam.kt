package gg.tropic.practice.games.team

import org.bukkit.Bukkit
import java.util.UUID

/**
 * @author GrowlyX
 * @since 8/9/2022
 */
class GameTeam(
    val side: GameTeamSide,
    val players: List<UUID>
)
{
    fun nonSpectators() = this.toBukkitPlayers()
        .filterNotNull()
        .filter {
            !it.hasMetadata("spectator")
        }

    fun toBukkitPlayers() = this.players
        .map {
            Bukkit.getPlayer(it) ?: null
        }
}