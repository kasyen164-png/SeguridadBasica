package com.miplugin.seguridad;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SeguridadBasica extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("¡Plugin de Seguridad Pro activado!");
    }

    // Detección de Reach (Golpear desde muy lejos)
    @EventHandler
    public void alAtacar(EntityDamageByEntityEvent evento) {
        if (evento.getDamager() instanceof Player) {
            Player atacante = (Player) evento.getDamager();
            Entity victima = evento.getEntity();
            
            double distancia = atacante.getLocation().distance(victima.getLocation());
            
            if (distancia > 4.5) { // Más de 4.5 bloques es casi imposible sin hacks
                atacante.kickPlayer("§c¡Hacks detectados! Reach no permitido.");
                Bukkit.broadcastMessage("§c[Seguridad] " + atacante.getName() + " fue expulsado por Reach.");
            }
        }
    }

    // Detección básica de Aimbot (giros instantáneos)
    @EventHandler
    public void alMoverse(PlayerMoveEvent evento) {
        float yawChange = Math.abs(evento.getTo().getYaw() - evento.getFrom().getYaw());
        if (yawChange > 100) { // Un giro demasiado rápido para un humano
            getLogger().warning("¡POSIBLE AIMBOT DETECTADO EN: " + evento.getPlayer().getName() + "!");
        }
    }
}
