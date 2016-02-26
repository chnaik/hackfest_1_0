package com.flipkart.ekl.hackfest.coreengineapis.core;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Entity
@Table(name = "productivity_score_history")
@Data
public class ProductivityScoreHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float score;

    @Getter
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    private Timestamp updated_at;
}
