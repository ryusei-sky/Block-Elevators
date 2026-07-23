package jp.havenmc.blockelevators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class BlockElevators extends JavaPlugin implements Listener {

    private final Set<Material> materials = new HashSet<>();
    private final Set<String> worlds = new HashSet<>();
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    private int maxRange;
    private int cooldownSeconds;

    private boolean soundEnable;
    private Sound sound;
    private float volume;
    private float jumpPitch;
    private float sneakPitch;

    private enum Direction {
        UP(1),
        DOWN(-1);

        private final int value;

        Direction(int value) {
            this.value = value;
        }
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(this, this);

        if (getCommand("block-elevators-reload") != null) {
            getCommand("block-elevators-reload").setExecutor(this);
        }
    }

    private void loadConfig() {
        reloadConfig();

        materials.clear();
        for (String name : getConfig().getStringList("materials")) {
            Material material = Material.matchMaterial(name);
            if (material != null) {
                materials.add(material);
            }
        }

        worlds.clear();
        worlds.addAll(getConfig().getStringList("worlds"));

        maxRange = getConfig().getInt("max-search-range", 64);
        cooldownSeconds = getConfig().getInt("cooldown", 0);

        soundEnable = getConfig().getBoolean("sound.enable", true);

        String soundName = getConfig().getString(
                "sound.name",
                "ENTITY_ENDERMAN_TELEPORT"
        );

        Sound loadedSound = Registry.SOUNDS.get(
                NamespacedKey.minecraft(soundName.toLowerCase())
        );

        sound = loadedSound != null
                ? loadedSound
                : Sound.ENTITY_ENDERMAN_TELEPORT;

        volume = (float) getConfig().getDouble("sound.volume", 1.0);
        jumpPitch = (float) getConfig().getDouble("sound.jump-pitch", 1.2);
        sneakPitch = (float) getConfig().getDouble("sound.sneak-pitch", 0.8);
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!command.getName().equalsIgnoreCase("block-elevators-reload")) {
            return false;
        }

        if (!sender.hasPermission("blockelevators.reload")) {
            return false;
        }

        loadConfig();
        sender.sendMessage("§aBlock Elevators reloaded");
        return true;
    }

    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        if (event.getTo() == null) return;

        double yDiff = event.getTo().getY() - event.getFrom().getY();
        Player player = event.getPlayer();

        int jumpBoostLevel = 0;
        if (player.hasPotionEffect(org.bukkit.potion.PotionEffectType.JUMP_BOOST)) {
            var effect = player.getPotionEffect(org.bukkit.potion.PotionEffectType.JUMP_BOOST);
            if (effect != null) {
                jumpBoostLevel = effect.getAmplifier() + 1;
            }
        }

        double minJumpVelocity = 0.41 + 0.1 * jumpBoostLevel;

        if (yDiff < minJumpVelocity || yDiff > (minJumpVelocity + 0.1)) return;

        useElevator(
                player,
                Direction.UP,
                jumpPitch
        );
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (!event.isSneaking()) return;

        useElevator(
                event.getPlayer(),
                Direction.DOWN,
                sneakPitch
        );
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        cooldowns.remove(event.getPlayer().getUniqueId());
    }

    private void useElevator(
            Player player,
            Direction direction,
            float pitch
    ) {
        if (!player.hasPermission("blockelevators.use")) return;

        if (!worlds.isEmpty() && !worlds.contains(player.getWorld().getName())) {
            return;
        }

        if (hasCooldown(player)) return;

        Block start = player.getLocation()
                .subtract(0, 0.1, 0)
                .getBlock();

        if (!materials.contains(start.getType())) return;

        Location destination = findDestination(start, direction);
        if (destination == null) return;

        applyCooldown(player);

        destination.setYaw(player.getLocation().getYaw());
        destination.setPitch(player.getLocation().getPitch());

        player.teleport(destination);
        player.setFallDistance(0);

        if (soundEnable) {
            player.playSound(
                    destination,
                    sound,
                    volume,
                    pitch
            );
        }
    }

    private Location findDestination(
            Block start,
            Direction direction
    ) {
        int min = start.getWorld().getMinHeight();
        int max = start.getWorld().getMaxHeight();

        int limit;
        if (maxRange <= 0) {
            limit = (direction == Direction.UP) ? max : min;
        } else {
            limit = (direction == Direction.UP) 
                    ? start.getY() + maxRange 
                    : start.getY() - maxRange;
        }

        limit = Math.max(min, Math.min(max, limit));

        for (
                int y = start.getY() + direction.value;
                direction == Direction.UP ? y <= limit : y >= limit;
                y += direction.value
        ) {
            Block block = start.getWorld().getBlockAt(
                    start.getX(),
                    y,
                    start.getZ()
            );

            if (!materials.contains(block.getType())) continue;

            Location location = block.getLocation().add(0.5, 1, 0.5);

            if (isSafe(location)) {
                return location;
            }
        }

        return null;
    }

    private boolean isSafe(Location location) {
        Block feet = location.getBlock();
        Block head = location.clone().add(0, 1, 0).getBlock();

        return !feet.getType().isOccluding()
                && !head.getType().isOccluding();
    }

    private boolean hasCooldown(Player player) {
        if (cooldownSeconds <= 0) return false;

        Long expire = cooldowns.get(player.getUniqueId());
        return expire != null && System.currentTimeMillis() < expire;
    }

    private void applyCooldown(Player player) {
        if (cooldownSeconds <= 0) return;

        cooldowns.put(
                player.getUniqueId(),
                System.currentTimeMillis() + cooldownSeconds * 1000L
        );
    }
}