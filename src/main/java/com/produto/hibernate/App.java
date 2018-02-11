package com.produto.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.produto.hibernate.model.Reminder;

public class App {

	private static EntityManagerFactory entityManagerFactory;
	 

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");		
		insert();
		listAll();
		findBy();
		update();
		delete();
	}

	public static void insert() {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Reminder reminder = new Reminder();
		reminder.setTitle("Comprar leite");
		reminder.setDescription("Hoje, 10:30");

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

	private static void listAll() {

		List<Reminder> remindes = null;
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			remindes = em.createQuery("from Reminder").getResultList();
		} catch (Exception e) {
			System.out.println("LIST ALL: " + e.getMessage());
		} finally {
			em.close();
		}

		if (remindes != null) {
			remindes.forEach(System.out::println);
		}
	}

	private static void findBy() {

		Reminder reminder;
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			reminder = em.find(Reminder.class, 1L);
		} finally {
			em.close();
		}
		System.out.println(reminder);
	}

	private static void update() {

		Reminder reminder;
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			reminder = em.find(Reminder.class, 1L);
			reminder.setTitle("Comprar café");
			reminder.setDescription("Hoje, 8h22");

			em.getTransaction().begin();
			em.merge(reminder);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("UPDATE: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	private static void delete() {

		Reminder reminder;
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			em.getTransaction().begin();
			reminder = em.find(Reminder.class, 1l);
			em.remove(reminder);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("DELETE: " + e.getMessage());
		} finally {
			em.close();
		}
	}
}
