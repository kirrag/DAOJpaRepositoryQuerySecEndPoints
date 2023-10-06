package ru.netology.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.netology.repository.DAOJpaRepository;
import ru.netology.entity.Person;
import ru.netology.entity.PersonId;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(DAOJpaRepository repository) {
		// Load Data for testing

		repository.deleteAll();

		return args -> {

			log.info("Preloading "
					+ repository.save(new Person(new PersonId("Иван", "Петров", 26), "+79261234567", "Москва")));
			log.info("Preloading "
					+ repository.save(new Person(new PersonId("Сергей", "Соколов", 25), "+79201234567", "Москва")));
			log.info("Preloading "
					+ repository.save(new Person(new PersonId("Илья", "Воловиков", 23), "+79251234567", "Санкт-Петербург")));
			log.info("Preloading "
					+ repository.save(new Person(new PersonId("Анна", "Соколова", 24), "+79101234567", "Нововсибирск")));
			log.info("Preloading "
					+ repository.save(new Person(new PersonId("Анна", "Иавнова", 25), "+79161234567", "Волгоград")));
		};
	}
}
