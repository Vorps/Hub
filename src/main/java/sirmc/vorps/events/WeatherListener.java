package sirmc.vorps.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Vorps on 01/02/2016.
 */
public class WeatherListener implements Listener{

    @EventHandler
    public void onWeatherEvent(WeatherChangeEvent e){
        e.setCancelled(true);
    }
}
