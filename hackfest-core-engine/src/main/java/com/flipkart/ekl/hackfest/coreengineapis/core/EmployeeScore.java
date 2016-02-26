package com.flipkart.ekl.hackfest.coreengineapis.core;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Entity
@Table(name = "employee_score")
@Data
public class EmployeeScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public enum ScoreType {
        TECH_FORUM,
        STRETCH_ASSIGNMENT,
        OVERALL_SCORE,
        PERCENTILE, PRODUCTIVITY,SKILLS

    }

    private String score_type;

    private Float score_value;

    @Getter
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
