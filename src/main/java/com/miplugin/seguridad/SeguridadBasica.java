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
        getLogger().info("¡Seguridad Pro Activada!");
    }

    // Detección de Reach (Golpear desde más de 4.5 bloques)
    @EventHandler
    public void alAtacar(EntityDamageByEntityEvent evento) {
        if (evento.getDamager() instanceof Player) {
            Player atacante = (Player) evento.getDamager();
            Entity victima = evento.getEntity();
            
            double distancia = atacante.getLocation().distance(victima.getLocation());
            
            if (distancia > 4.5) { 
                atacante.kickPlayer("§c¡Hacks detectados! (Reach)");
                Bukkit.broadcastMessage("§c[Seguridad] " + atacante.getName() + " expulsado por Reach.");
            }
        }
    }

    // Detección de Aimbot (Giros bruscos de cámara)
    @EventHandler
    public void alMoverse(PlayerMoveEvent evento) {
        float giro = Math.abs(evento.getTo().getYaw() - evento.getFrom().getYaw());
        if (giro > 100) { 
            getLogger().warning("¡POSIBLE AIMBOT DETECTADO: " + evento.getPlayer().getName() + "!");
        }
    }
}
