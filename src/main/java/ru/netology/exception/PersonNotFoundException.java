package ru.netology.exception;

import ru.netology.entity.PersonId;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {
    super("Could not find person ");
  }

  public PersonNotFoundException(PersonId personId) {
    super("Could not find person " + personId.toString());
  }
}
