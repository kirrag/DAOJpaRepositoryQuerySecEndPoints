package ru.netology.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import ru.netology.repository.DAOJpaRepository;
import ru.netology.entity.Person;
import ru.netology.entity.PersonId;
import ru.netology.exception.PersonNotFoundException;

@RestController
public class DAOJpaController {
	private final DAOJpaRepository repository;

	public DAOJpaController(DAOJpaRepository repository) {
		this.repository = repository;
	}

	// CREATE
	@PostMapping("/persons")
	public Person newPerson(@RequestBody Person newPerson) {
		return repository.save(newPerson);
	}

	// READ
	@GetMapping("/persons")
	public List<Person> getAll() {
		return repository.findAll();
	}

	@GetMapping("/persons/by-name")
	public List<Person> getPersonsByName(@RequestParam("name") String name) {
		return repository.getPersonByName(name);
	}

	@GetMapping("/persons/by-surname")
	public List<Person> getPersonsBySurame(@RequestParam("surname") String surname) {
		return repository.getPersonBySurname(surname);
	}

	@GetMapping("/persons/by-age")
	public List<Person> getPersonsBySurame(@RequestParam("age") int age) {
		return repository.getPersonByAge(age);
	}

	@GetMapping("/persons/by-city")
	public List<Person> getPersonsByCity(@RequestParam("city") String city) {
		return repository.getPersonByCity(city);
	}

	@GetMapping("/persons/by-phone")
	public List<Person> getPersonsByPhone(@RequestParam("phone") String phone) {
		return repository.getPersonByPhone(phone);
	}

	@GetMapping("/persons/by-age-less-than")
	public List<Person> getPersonByAgeLessThen(@RequestParam("age") int age) {
		return repository.getPersonByAgeLessThanOrderByAgeAsc(age);
	}

	@GetMapping("/persons/by-fullname")
	public Person getPersonsByFullName(@RequestParam("name") String name,
			@RequestParam("surname") String surname) {
		return repository.getPersonByNameAndSurname(name, surname)
				.orElseThrow(() -> new PersonNotFoundException());
	}

	@GetMapping("/persons/by-personid")
	public Person getPersonByPersonId(@RequestBody PersonId personId) {
		return repository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
	}

	// UPDATE
	public @PutMapping("/persons")
	Person updatePerson(@RequestBody Person newPerson) {
		PersonId personId = newPerson.getPersonId();

		if (repository.existsById(personId)) {
			repository.deleteById(personId);
			return repository.save(newPerson);
		} else {
			throw new PersonNotFoundException(personId);
		}
	}

	// DELETE
	@DeleteMapping("/persons/by-personid")
	public void deletePersonByPersonId(@RequestBody PersonId personId) {
		repository.deleteById(personId);
	}

	@DeleteMapping("/persons")
	public void deleteAll() {
		repository.deleteAll();
	}
}
