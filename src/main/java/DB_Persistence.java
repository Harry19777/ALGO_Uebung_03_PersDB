import javax.persistence.*;
import java.util.Collections;
import java.util.List;
/*
 * @Author: Harald EDERER
 * @Date: 16.10.2022
 */
public class DB_Persistence {
    private static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("person");

    /**
     * Read all Person from Database
     */
    public List<Person> readAll() {
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT p FROM Person p";
        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        List<Person> pList = null;

        try {
            pList = tq.getResultList();
            Collections.sort(pList, new SortPersonById());
            return pList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return pList;
    }


    /**
     * Add one Person to DB
     *
     * @Param Person
     */
    public void addPerson(Person p) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(p);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        } finally {
            em.close();
        }
    }


    /**
     * Read one Person from DB by search
     *
     * @param nr
     */
    public List<Person> readPerson(int nr) {
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.nr = :custNr";
        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        tq.setParameter("custNr", nr);
        List<Person> pList = null;

        try {
            pList = tq.getResultList();
            return pList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return pList;
    }

    /**
     * Update one Person
     * @param person
     */
    public void updatePerson(Person person) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;
        Person p = null;

        try {
            et = em.getTransaction();
            et.begin();

            p = em.find(Person.class, person.getNr());
            p.setVname(person.getVname());
            p.setNname(person.getNname());
            p.setGehalt(person.getGehalt());

            em.persist(p);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Delete Person Search by Nr
     *
     * @param nr
     */
    public void deletePerson(int nr) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;
        Person p = null;
        try {
            et = em.getTransaction();
            et.begin();;
            p = em.find(Person.class, nr);
            em.remove(p);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        } finally {
            em.close();
        }
    }


}
