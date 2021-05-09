package wsb;

import org.postgresql.util.PSQLException;
import wsb.lasttask.ClassEntity;
import wsb.lasttask.StudentsEntity;

import javax.persistence.*;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oop2020");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            student(em, 1, "John", "Smith123", 22);
            student(em, 2, "John1", "123Smith123", 21);
            student(em, 3, "John2", "asdfasfSmith", 44);
            student(em, 4, "John3", "Black", 25);
            student(em, 5, "John4", "Kogut", 40);

            newClass(em, "A", "Misie");
            newClass(em, "B", "Zuczki");

            transaction.commit();


        } catch (PersistenceException e) {
            //skip
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        TypedQuery<StudentsEntity> query = em.createNamedQuery("StudentsEntity.byLastName", StudentsEntity.class);
        query.setParameter(1, "%Smith%");
        query.getResultList().forEach(System.out::println);

        Query nativeQuery = em.createNativeQuery("SELECT COUNT(*) FROM class WHERE class.class_name=:name");
        nativeQuery.setParameter("name", "Misie");
        System.out.println(nativeQuery.getSingleResult());

        em.close();
        factory.close();
    }

    private static void student(EntityManager entityManager, Integer id, String firstName, String lastName, Integer age) {
        StudentsEntity studentsEntity = new StudentsEntity();
        studentsEntity.setId(id);
        studentsEntity.setFirstName(firstName);
        studentsEntity.setLastName(lastName);
        studentsEntity.setAge(age);

        try {
            entityManager.persist(studentsEntity);
        } catch (PersistenceException e) {
            //skip

        }
    }

    private static void newClass(EntityManager em, String id, String className) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassId(id);
        classEntity.setClassName(className);

        try {
            em.persist(classEntity);
        } catch (PersistenceException e) {
            //skip
        }
    }
}
