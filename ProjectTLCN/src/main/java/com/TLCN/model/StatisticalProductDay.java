package com.TLCN.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class StatisticalProductDay {
	@Id
	private String code;
	private String name;
	private int price;
	private int quantity;
	private long selled;
}
