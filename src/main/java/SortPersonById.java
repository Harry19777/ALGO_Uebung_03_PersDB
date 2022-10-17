import java.util.Comparator;
/*
*https://www.javatpoint.com/java-list-sort-method
 */
public class SortPersonById implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getNr() - p2.getNr();
    }
}
