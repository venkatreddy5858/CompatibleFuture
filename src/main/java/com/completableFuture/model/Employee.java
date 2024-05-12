package com.completableFuture.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(generator = "custom-uuid")
	private String id;
	private String first_name;
	private String last_name;
	private String email;
	private String gender;


}
