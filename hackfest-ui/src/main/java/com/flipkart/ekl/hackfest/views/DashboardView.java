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

    public DashboardView(List<Skill> allSkills, List<StretchAssignment> stretchAssignmentList, List<String> questions) {
        super("dashboard-view.ftl");
        init(allSkills);
        this.stretchAssignmentList.addAll(stretchAssignmentList);
        this.questions.addAll(questions);
    }

    private void init(List<Skill> allSkills) {
        employee = new Employee();
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

//        stretchAssignmentList.add(new StretchAssignment("Studio 34 App", "Android"));
//        stretchAssignmentList.add(new StretchAssignment("Hackfest Site", "JS"));
//        stretchAssignmentList.add(new StretchAssignment("Cron", "Ruby"));

        leaders.add(new Leader("Chaitanya", 3.7f));
        leaders.add(new Leader("Shashank", 3.7f));
        leaders.add(new Leader("Rahul", 3.7f));
        leaders.add(new Leader("Test", 5f));
    }
}
