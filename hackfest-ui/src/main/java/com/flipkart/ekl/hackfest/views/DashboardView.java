package com.flipkart.ekl.hackfest.views;

import com.flipkart.ekl.hackfest.core.Employee;
import com.flipkart.ekl.hackfest.core.Leader;
import com.flipkart.ekl.hackfest.core.Skill;
import com.flipkart.ekl.hackfest.core.StretchAssignment;
import io.dropwizard.views.View;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chaitanya.naik on 25/02/16.
 */
@Getter
public class DashboardView  extends View {

    private Employee employee;
    private List<StretchAssignment> stretchAssignmentList = new ArrayList<StretchAssignment>();
    private List<Leader> leaders = new ArrayList<Leader>();
    private List<String> questions = new ArrayList<String>();

    public DashboardView(List<Skill> allSkills, List<StretchAssignment> stretchAssignmentList, List<String> questions,
                         List<Leader> leaders, Employee employee) {
        super("dashboard-view.ftl");
        this.stretchAssignmentList.addAll(stretchAssignmentList);
        this.questions.addAll(questions);
        this.leaders.addAll(leaders);
        this.employee = employee;
        init(allSkills);
    }

    private void init(List<Skill> allSkills) {
        int size = allSkills.size();
        Collections.sort(allSkills);
        List<Skill> skills = new ArrayList<Skill>();
        if (size > 0) skills.add(allSkills.get(0));
        if (size > 1) skills.add(allSkills.get(1));
        if (size > 2) skills.add(allSkills.get(2));
        employee.setSkills(skills);
        Collections.reverse(allSkills);
        skills = new ArrayList<Skill>();
        if (size > 0) skills.add(allSkills.get(0));
        if (size > 1) skills.add(allSkills.get(1));
        if (size > 2) skills.add(allSkills.get(2));
        employee.setRecommendedSkills(skills);

    }
}
