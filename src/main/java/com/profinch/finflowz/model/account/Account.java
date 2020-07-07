package com.profinch.finflowz.model.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Account_Db")
public class Account {

	@Id
	private int id;
	private String name;
	private String branchCode;
}
