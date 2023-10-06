package ru.netology.entity;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

import javax.persistence.EmbeddedId;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person implements Serializable {

	@EmbeddedId
	private PersonId personId;

	private String phone;

	private String city;
}
