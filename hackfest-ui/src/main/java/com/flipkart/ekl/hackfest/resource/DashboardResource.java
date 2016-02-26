package com.flipkart.ekl.hackfest.resource;

/**
 * Created by chaitanya.naik on 25/02/16.
 */

import com.flipkart.ekl.hackfest.core.Skill;
import com.flipkart.ekl.hackfest.core.StretchAssignment;
import com.flipkart.ekl.hackfest.service.EmployeeService;
import com.flipkart.ekl.hackfest.views.DashboardView;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    String emailId = "rahul.jain@flipkart.com";

    @GET
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public DashboardView getDashboardView()
    {
        EmployeeService employeeService = new EmployeeService();
        List<Skill> allSkills = new ArrayList<Skill>();
        List<StretchAssignment> stretchAssignments = new ArrayList<StretchAssignment>();
        List<String> questions = new ArrayList<String>();
        try {
            allSkills = employeeService.getSkillListForUser(emailId);
            String[] skillNames = new String[allSkills.size()+1];
            int counter = 0;
            for (Skill skill: allSkills) {
                skillNames[counter++] = skill.getName();
            }
            skillNames[allSkills.size()] = "java";
            stretchAssignments = employeeService.getStretchAssignmentList(skillNames);
            questions = employeeService.getQuestions(skillNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DashboardView(allSkills, stretchAssignments, questions);
    }

}