package com.flipkart.ekl.hackfest.coreengineapis.core;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    private int id;
    private String name;
    private String email_id;
}
