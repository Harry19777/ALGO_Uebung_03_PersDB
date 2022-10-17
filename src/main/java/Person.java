import javax.persistence.*;

/**
 * @AUTHOR: Harald EDERER
 * @Date: 14.10.2022
 */
@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr", updatable = false, nullable = false)
    private int nr;
    @Column(name = "vname", nullable = false)
    private String vname;
    @Column(name = "nname", nullable = false)
    private String nname;
    @Column(name = "gehalt", nullable = false)
    private int gehalt;


    public Person() {
    }

    public Person(String vname, String nname, int gehalt) {
        this.vname = vname;
        this.nname = nname;
        this.gehalt = gehalt;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public int getGehalt() {
        return gehalt;
    }

    public void setGehalt(int gehalt) {
        this.gehalt = gehalt;
    }

    @Override
    public String toString() {
        return "\nNr: " + getNr() +
                ", VName: " + getVname() +
                ", NName: " + getNname() +
                ", Gehalt: " + getGehalt();
    }
}
