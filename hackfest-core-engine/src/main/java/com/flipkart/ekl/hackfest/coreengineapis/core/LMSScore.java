package com.flipkart.ekl.hackfest.coreengineapis.core;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Entity
@Table(name = "lms_score")
@Data
public class LMSScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skill;
    private Float level;

    @Getter
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
