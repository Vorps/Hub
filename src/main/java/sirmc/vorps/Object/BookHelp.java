package sirmc.vorps.Object;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import sirmc.vorps.Hub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Vorps on 10/02/2016.
 */
public class BookHelp {

    private  ItemStack book;

    public BookHelp(ResultSet result) throws SQLException{
        ArrayList<String> pages = new ArrayList<>();
        book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setTitle(result.getString(1));
        meta.setAuthor(result.getString(4));
        ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM BookHelp WHERE nameHelp = '"+result.getString(1)+"' ");
        while (results.next()){
            pages.add(results.getString(3));
        }
        meta.setPages(pages);
        pages.clear();
        pages.add("§aClique gauche pour retirer l'aide");
        meta.setLore(pages);
        book.setItemMeta(meta);
    }

    public ItemStack get(){
        return book;
    }
}
