package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 03/03/2016 at 06:09.
 */
public abstract class Menu {
    protected int[][] model;
    private ItemStack[] items;
    protected byte[] ids;
    protected Inventory menu;

    protected Menu(byte[] ids, Inventory menu, int[][] model){
        this.menu = menu;
        this.model = model;
        this.ids = ids;
        if(ids != null){
            init();
            if(menu != null && model != null){
                constructModel();
            }
        }
    }

    protected void init(){
        items = new ItemStack[ids.length];
        for(int i = 0; i < ids.length; i++){
            items[i] = new Item(160).withData(ids[i]).withName(" ").get();
        }
    }
    protected void constructModel(){
        for(int i = 0; i < model.length; i++){
            menu.setItem(model[i][0], items[model[i][1]]);
        }
    }

    protected void list(int pos, int nbr, int index, int coefficient, ArrayList<String> list, String[] des) {
        switch (nbr) {
            case 0:
                break;
            case 1:
                menu.setItem(pos+3 + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                break;
            case 2:
                menu.setItem(pos+2 + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 1)).withName(list.get(index + 1)).withLore(des).get());
                break;
            case 3:
                menu.setItem(pos+2 + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 1)).withName(list.get(index + 1)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 2)).withName(list.get(index + 2)).withLore(des).get());
                break;
            case 4:
                menu.setItem(pos+1 + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                menu.setItem(pos+2 + coefficient, new Item(list.get(index + 1)).withName(list.get(index + 1)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 2)).withName(list.get(index + 2)).withLore(des).get());
                menu.setItem(pos+5 + coefficient, new Item(list.get(index + 3)).withName(list.get(index + 3)).withLore(des).get());
                break;
            case 5:
                menu.setItem(pos+1 + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                menu.setItem(pos+2 + coefficient, new Item(list.get(index + 1)).withName(list.get(index + 1)).withLore(des).get());
                menu.setItem(pos+3 + coefficient, new Item(list.get(index + 2)).withName(list.get(index + 2)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 3)).withName(list.get(index + 3)).withLore(des).get());
                menu.setItem(pos+5 + coefficient, new Item(list.get(index + 4)).withName(list.get(index + 4)).withLore(des).get());
                break;
            case 6:
                menu.setItem(pos + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                menu.setItem(pos+1 + coefficient, new Item(list.get(index + 1)).withName(list.get(index + 1)).withLore(des).get());
                menu.setItem(pos+2 + coefficient, new Item(list.get(index + 2)).withName(list.get(index + 2)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 3)).withName(list.get(index + 3)).withLore(des).get());
                menu.setItem(pos+5 + coefficient, new Item(list.get(index + 4)).withName(list.get(index + 4)).withLore(des).get());
                menu.setItem(pos+6 + coefficient, new Item(list.get(index + 5)).withName(list.get(index + 5)).withLore(des).get());
                break;
            case 7:
                menu.setItem(pos + coefficient, new Item(list.get(index)).withName(list.get(index)).withLore(des).get());
                menu.setItem(pos+1 + coefficient, new Item(list.get(index + 1)).withName(list.get(index + 1)).withLore(des).get());
                menu.setItem(pos+2 + coefficient, new Item(list.get(index + 2)).withName(list.get(index + 2)).withLore(des).get());
                menu.setItem(pos+3 + coefficient, new Item(list.get(index + 3)).withName(list.get(index + 3)).withLore(des).get());
                menu.setItem(pos+4 + coefficient, new Item(list.get(index + 4)).withName(list.get(index + 4)).withLore(des).get());
                menu.setItem(pos+5 + coefficient, new Item(list.get(index + 5)).withName(list.get(index + 5)).withLore(des).get());
                menu.setItem(pos+6 + coefficient, new Item(list.get(index + 6)).withName(list.get(index + 6)).withLore(des).get());
                break;
            default:
                if(nbr <= 14) {
                    list(pos, 7,index, 0, list, des);
                    list(pos, nbr - 7, index + 7, -9, list, des);
                } else {
                    list(pos, 7, index, 0, list, des);
                    list(pos, 7, index + 7, -9, list, des);
                    list(pos, nbr - 14, index + 14, 9, list, des);
                }
                break;
        }
    }
}
