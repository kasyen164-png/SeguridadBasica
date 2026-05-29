package com.miplugin.seguridad;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SeguridadBasica extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("¡Plugin de Seguridad Activado!");
    }

    @EventHandler
    public void alUsarComando(PlayerCommandPreprocessEvent evento) {
        String comando = evento.getMessage().toLowerCase();
        
        // Bloqueamos comandos prohibidos
        if (comando.startsWith("/op") || comando.startsWith("/gamemode")) {
            evento.setCancelled(true);
            evento.getPlayer().sendMessage("§c¡Comando prohibido por seguridad!");
        }
    }
}
