package com.kmboot.english.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "ENGLISH_RECEIVER")
public class Receiver {
	@Id
	private long id;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userName;
	@Column(nullable = false)
	private String emailAddress;
	@Column
	private String emailCode;
	@Column
	private boolean emailValid;
	@Column
	private boolean available;

	@Column
	private String channel;
}
