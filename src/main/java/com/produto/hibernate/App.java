package com.produto.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.produto.hibernate.model.Reminder;

public class App {

	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
		insert();
	}

	public static void insert() {

		Reminder reminder = new Reminder();
		reminder.setTitle("Comprar leite");
		reminder.setDescription("Hoje, 10:30");

		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(reminder);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("INSERT: " + e.getMessage());
		} finally {
			em.close();
		}
	}
}
