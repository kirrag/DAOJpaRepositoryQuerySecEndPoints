package ru.netology.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class PersonId implements Serializable {

	private String name;

	private String surname;

	private int age;
}
