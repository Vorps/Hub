package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.utils.Lang;
import me.vorps.fortycube.utils.LangSetting;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class BookHelp{
    private static @Getter HashMap<String, BookHelp> bookHelpList = new HashMap<>();

    private static @Getter TreeMap<String ,BookHelp> trieBookHelp = new TreeMap<>(new ComparatorBookHelp(bookHelpList));

    private ItemStack book;
    private @Getter HashMap<String, me.vorps.fortycube.menu.Item> item;
    private @Getter int level;
    private HashMap<String, ArrayList<String>> values;


    public static void trieBookHelp(){
        trieBookHelp.clear();
        trieBookHelp.putAll(bookHelpList);
    }


    private static class ComparatorBookHelp implements Comparator<String> {
        Map<String, BookHelp> base;
        private ComparatorBookHelp(Map<String, BookHelp> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            return base.get(a).level >= base.get(b).level ? 1 : -1;
        }
    }

    public static BookHelp getBookHelp(String nameBook){
        return bookHelpList.get(nameBook);
    }

    public BookHelp(ResultSet result) throws SqlException{
        item = new HashMap<>();
        values = new HashMap<>();
        book = new ItemStack(Material.WRITTEN_BOOK, 1);
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            item.put(langSetting.getName(), Item.getItem(Database.FORTYCUBE.getDatabase().getString(result, 3), langSetting.getName()));
        }
        level = Database.FORTYCUBE.getDatabase().getInt(result, 4);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setAuthor(Database.FORTYCUBE.getDatabase().getString(result, 2));
        book.setItemMeta(meta);
        ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM book_setting WHERE bs_book = '"+Database.FORTYCUBE.getDatabase().getString(result, 1)+"' ");
        try {
            for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
                ArrayList<String> pages = new ArrayList<>();
                while(results.next()){
                    pages.add(Lang.getMessage(Database.FORTYCUBE.getDatabase().getString(results, 3), langSetting.getName()));
                }
                values.put(langSetting.getName(), pages);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            bookHelpList.put(item.get(langSetting.getName()).get().getItemMeta().getDisplayName(), this);
        }
    }


    public ItemStack getBook(String lang){
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setPages(values.get(lang));
        meta.setTitle(item.get(lang).get().getItemMeta().getDisplayName());
        book.setItemMeta(meta);
        return book;
    }

    public static void clear(){
        bookHelpList.clear();
    }
}
