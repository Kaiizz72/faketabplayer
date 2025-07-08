package com.gumbal.fakeplayers;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Action;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Entry;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.GameType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class PersistentFakeTabPlayers extends JavaPlugin {

    private final Map<UUID, GameProfile> fakeProfiles = new LinkedHashMap<>();

    @Override
    public void onEnable() {
        createFakeNames();
        Bukkit.getScheduler().runTaskTimer(this, this::sendToAllPlayers, 0L, 20L * 30);

        getServer().getPluginManager().registerEvents(new org.bukkit.event.Listener() {
            @org.bukkit.event.EventHandler
            public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
                Bukkit.getScheduler().runTaskLater(PersistentFakeTabPlayers.this, () -> {
                    sendTo(event.getPlayer());
                }, 40L);
            }
        }, this);

        getLogger().info("PersistentFakeTabPlayers by GumbalMC đã bật.");
    }

    private void createFakeNames() {
        String[] names = {
            "gumbal", "nhicute", "longdz", "haihamtai", "shadowvn", "dragonboy123", "panda_girl", "tiendzpro",
            "darklordvn", "cutegirl_2007", "hackerNo1", "phongdzvip", "gaucon123", "xXThanhXx",
            "trai_dep_vjp", "cobegaucute", "admin_fake", "oanhvip99", "sonytroll", "mrbeanvn",
            "nobita_afk", "trumcuoivl", "yeuemgame", "conmeoden", "linhcutee", "namvippro",
            "hungbadboy", "trollkid69", "ntmnopro", "boycuoingu", "nguoitoilaai", "jetkidvn",
            "kimchi_xx", "lucifervn", "quynhhoa123", "trungvip04", "bonboncute", "gauvangvjp",
            "phamthienan", "lanxinhgai", "quanhero", "nguyenvinh", "thaongoxd", "cau_be_mua",
            "hoangz0r", "boyhacker", "congchuatroi", "ducnp69", "congvn007", "ghostvn123", "michan2008"
        };

        for (String name : names) {
            UUID uuid = UUID.nameUUIDFromBytes(("Fake-" + name).getBytes());
            GameProfile profile = new GameProfile(uuid, name);
            fakeProfiles.put(uuid, profile);
        }
    }

    private void sendToAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTo(player);
        }
    }

    private void sendTo(Player player) {
        ServerGamePacketListenerImpl connection =
            ((org.bukkit.craftbukkit.v1_21_R1.entity.CraftPlayer) player).getHandle().connection;

        List<Entry> entries = new ArrayList<>();
        for (Map.Entry<UUID, GameProfile> e : fakeProfiles.entrySet()) {
            entries.add(new Entry(
                e.getKey(),
                e.getValue(),
                false,
                100,
                GameType.SURVIVAL,
                null,
                null
            ));
        }

        ClientboundPlayerInfoUpdatePacket packet = new ClientboundPlayerInfoUpdatePacket(Action.ADD_PLAYER, entries);
        connection.send(packet);
    }
}
