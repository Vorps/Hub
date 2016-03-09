package me.vorps.hub.Object;

import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class BookHelp {
    private static HashMap<String, BookHelp> bookHelpList = new HashMap<>();
    private ItemStack book;

    public static BookHelp getBookHelp(String nameBook){
        return bookHelpList.get(nameBook);
    }

    public BookHelp(ResultSet result) throws SqlException {
        ArrayList<String> pages = new ArrayList<>();
        book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setTitle(Database.FORTYCUBE.getDatabase().getString(result, 1));
        meta.setAuthor(Database.FORTYCUBE.getDatabase().getString(result, 2));
        System.out.println("ok");
        ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM book_setting WHERE bs_book = '"+Database.FORTYCUBE.getDatabase().getString(result, 1)+"' ");
        try {
            while (results.next()){
                pages.add(Database.FORTYCUBE.getDatabase().getString(results, 3));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("ok");
        meta.setPages(pages);
        pages.clear();
        pages.add("§aClique gauche pour retirer l'aide");
        meta.setLore(pages);
        book.setItemMeta(meta);
        System.out.println("ok");
        System.out.println(Database.FORTYCUBE.getDatabase().getString(result, 1));
        bookHelpList.put(Database.FORTYCUBE.getDatabase().getString(result, 1), this);
    }

    public ItemStack get(){
        return book;
    }
}
