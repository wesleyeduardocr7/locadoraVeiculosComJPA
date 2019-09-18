package main;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("wesley.laboratorio05.jpa");
        EntityManager manager = factory.createEntityManager();

        manager.close();
        factory.close();

    }

}
