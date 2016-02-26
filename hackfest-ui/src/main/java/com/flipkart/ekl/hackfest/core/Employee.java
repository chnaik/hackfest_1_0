package com.flipkart.ekl.hackfest.core;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by chaitanya.naik on 25/02/16.
 */
@Data
public class Employee {
    private List<Skill> skills;
    private List<Skill> recommendedSkills;
    private Map<String, String> productivityHistoryMap;
}
