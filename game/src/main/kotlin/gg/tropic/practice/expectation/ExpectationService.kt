package gg.tropic.practice.expectation

import gg.scala.flavor.inject.Inject
import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service
import gg.tropic.practice.PracticeGame
import gg.tropic.practice.games.GameImpl
import gg.tropic.practice.games.GameService
import gg.tropic.practice.resetAttributes
import me.lucko.helper.Events
import net.evilblock.cubed.nametag.NametagHandler
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.visibility.VisibilityHandler
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.metadata.FixedMetadataValue

/**
 * @author GrowlyX
 * @since 8/4/2022
 */
@Service
object ExpectationService
{
    @Inject
    lateinit var plugin: PracticeGame

    @Inject
    lateinit var audiences: BukkitAudiences

    @Configure
    fun configure()
    {
        Events
            .subscribe(
                AsyncPlayerPreLoginEvent::class.java,
                EventPriority.MONITOR
            )
            .handler { event ->
                val game = GameService
                    .byPlayerOrSpectator(event.uniqueId)

                if (game == null)
                {
                    event.disallow(
                        AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST,
                        "${CC.RED}You do not have a game to join!"
                    )
                }
            }
            .bindWith(plugin)

        Events
            .subscribe(
                PlayerJoinEvent::class.java,
                EventPriority.MONITOR
            )
            .handler {
                val game = GameService
                    .byPlayerOrSpectator(it.player.uniqueId)
                    ?: return@handler

                val spawnLocation = if (it.player.uniqueId !in game.expectedSpectators)
                {
                    game.map
                        .findSpawnLocationMatchingTeam(
                            game.getTeamOf(it.player).side
                        )!!
                        .toLocation(game.arenaWorld)
                } else
                {
                    game
                        .toBukkitPlayers()
                        .filterNotNull()
                        .first().location
                }

                it.player.teleport(spawnLocation)
                it.player.resetAttributes()

                if (it.player.uniqueId in game.expectedSpectators)
                {
                    it.player.setMetadata(
                        "spectator",
                        FixedMetadataValue(plugin, true)
                    )

                    NametagHandler.reloadPlayer(it.player)
                    VisibilityHandler.update(it.player)

                    it.player.allowFlight = true
                    it.player.isFlying = true

                    game.sendMessage(
                        "${CC.GREEN}${it.player.name}${CC.SEC} is now spectating the game."
                    )

                    it.player.sendMessage(
                        "${CC.B_SEC}You are now spectating the game."
                    )
                } else
                {
                    it.player.removeMetadata("spectator", plugin)
                }
            }
            .bindWith(plugin)
    }
}
