package me.vorps.hub.shop;

import lombok.Getter;
import me.vorps.hub.menu.Menu;
import org.bukkit.inventory.Inventory;
import me.vorps.hub.utils.Purchase;

/**
 * Project Hub Created by Vorps on 04/03/2016 at 20:48.
 */
public abstract class Shop extends Menu {

    private @Getter Purchase purchase;

    public Shop(byte[] ids, Inventory menu, int[][] model, String namePlayer, String nameProduct){
        super(ids, menu, model);
        purchase = new Purchase(namePlayer, nameProduct);
    }

}
