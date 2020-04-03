import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Libary {

    private Set<Book> availableBooks;
    private Map<Book,String> lentBooks;

    public Libary() {
        this.availableBooks=new HashSet<>();
        this .lentBooks=new HashMap<>();
    }
}
