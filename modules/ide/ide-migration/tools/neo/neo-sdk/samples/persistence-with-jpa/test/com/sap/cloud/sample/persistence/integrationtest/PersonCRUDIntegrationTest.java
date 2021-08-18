package com.sap.cloud.sample.persistence.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sap.cloud.sample.persistence.Person;

/**
 * Integration test for Create, Read, Update and Delete operations on JPA Person entities. During the Maven build, it is
 * using a SAP HANA DB running on SAP BTP accessed via JDBC tunnel. The DB tunnel is opened by the build
 * using the SAP BTP Maven Plugin goal open-db-tunnel. If executed as plain JUnit test without first
 * opening a DB tunnel, it will use an embedded Derby DB instead.
 */
public class PersonCRUDIntegrationTest {

    private EntityManager em;

    /**
     * Create an EntityManager to be used in all test cases.
     */
    @Before
    public void setup() {
        em = createEntityManagerFactory().createEntityManager();
    }

    private EntityManagerFactory createEntityManagerFactory() {
        final Map<String, String> properties = new HashMap<String, String>();
        // Use SAP HANA DB properties set by SAP BTP Maven Plugin open-db-tunnel maven goal.
        // If no DB tunnel properties are available, fall back to embedded Derby DB.
        final String jdbcUrl = System.getProperty("dbtunnel.result.jdbcUrl", "jdbc:derby:memory:test-jpa;create=true");
        properties.put("javax.persistence.jdbc.url", jdbcUrl);
        final String dbUser = System.getProperty("dbtunnel.result.dbUser");
        if (dbUser != null) {
            properties.put("javax.persistence.jdbc.user", dbUser);
        }
        final String dbPassword = System.getProperty("dbtunnel.result.dbUserPassword");
        if (dbPassword != null) {
            properties.put("javax.persistence.jdbc.password", dbPassword);
        }
        return Persistence.createEntityManagerFactory("persistence-with-jpa", properties);
    }

    /**
     * Make sure to close the EntityManager.
     */
    @After
    public void tearDown() {
        em.close();
    }

    /**
     * Create a person entity and make sure that it has been persisted.
     */
    @Test
    public void testCreateAndReadPerson() throws Exception {
        createPersistentPerson("John", "Foo");
        final Person person = findPersonsByFirstNameInDifferentEntityManager("John").get(0);
        assertEquals("John", person.getFirstName());
        assertEquals("Foo", person.getLastName());
    }

    /**
     * Create a person entity, and make sure that the "AllPersons" query result contains this person.
     */
    @Test
    public void testAllPersonsQuery() throws Exception {
        String firstName = "Harry";
        String lastName = "Potter";
        createPersistentPerson(firstName, lastName);

        final List<Person> persons = em.createNamedQuery("AllPersons", Person.class).getResultList();
        boolean foundPerson = false;
        for (Person p : persons) {
            if (firstName.equals(p.getFirstName()) && lastName.equals(p.getLastName())) {
                foundPerson = true;
            }
        }
        assertTrue("Did not find person with firstName: " + firstName + " and lastName: " + lastName, foundPerson);
    }

    /**
     * Create a person entity, change an attribute of it and then make sure that the change has been persisted.
     */
    @Test
    public void testUpdatePerson() throws Exception {
        final Person person = createPersistentPerson("Joe", "Bar");
        em.getTransaction().begin();
        person.setLastName("Ford");
        em.flush();
        em.getTransaction().commit();
        final Person updatedPerson = findPersonsByFirstNameInDifferentEntityManager("Joe").get(0);
        assertEquals("Ford", updatedPerson.getLastName());
        assertNotSame(person, updatedPerson);
    }

    /**
     * Create a person entity, delete it and make sure it is deleted.
     */
    @Test
    public void testDeletePerson() throws Exception {
        final Person person = createPersistentPerson("Alice", "Baz");
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
        final List<Person> persons = findPersonsByFirstNameInDifferentEntityManager("Alice");
        Assert.assertEquals(0, persons.size());
    }

    private List<Person> findPersonsByFirstNameInDifferentEntityManager(final String firstName) {
        final EntityManager otherEm = createEntityManagerFactory().createEntityManager();
        try {
            return otherEm.createQuery("select p from Person p where p.firstName = :firstName", Person.class)
                    .setParameter("firstName", firstName).getResultList();
        } finally {
            otherEm.close();
        }

    }

    private Person createPersistentPerson(final String firstName, final String lastName) {
        final Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        return person;
    }
}
