package ru.netology.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.netology.entity.Person;
import ru.netology.entity.PersonId;

@Repository
public interface DAOJpaRepository extends JpaRepository<Person, PersonId> {

	@Query("select p from Person p where p.personId.name = :name")
	public List<Person> getPersonByName(String name);

	@Query("select p from Person p where p.personId.surname = :surname")
	public List<Person> getPersonBySurname(String surname);

	@Query("select p from Person p where p.personId.age = :age")
	public List<Person> getPersonByAge(int age);

	@Query("select p from Person p where p.city = :city")
	public List<Person> getPersonByCity(String city);

	@Query("select p from Person p where p.phone = :phone")
	public List<Person> getPersonByPhone(String phone);

	@Query("select p from Person p where p.personId.age < :age order by p.personId.age asc")
	public List<Person> getPersonByAgeLessThanOrderByAgeAsc(int age);

	@Query("select p from Person p where p.personId.name = :name and p.personId.surname = :surname")
	public Optional<Person> getPersonByNameAndSurname(String name, String surname);
}
