package com.flipkart.ekl.hackfest.service;

import com.flipkart.ekl.hackfest.client.ApacheHttpClient;
import com.flipkart.ekl.hackfest.core.Employee;
import com.flipkart.ekl.hackfest.core.Leader;
import com.flipkart.ekl.hackfest.core.Skill;
import com.flipkart.ekl.hackfest.core.StretchAssignment;
import com.flipkart.ekl.hackfest.util.HttpClientUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
public class EmployeeService {

    private static final String CORE_HOST_URL_PREFIX = "http://localhost:9090";

    private static final String CORE_GET_SKILLS_URI = "/employee/score/%s/lms";
    private static final String CORE_GET_LEADERS_URI = "/employee/score/get/leaders";
    private static final String CORE_GET_EM_DETAILS_URI = "/employee/score/%s/get";

    private static final String TF_HOST_URL_PREFIX = "http://ekl-lm-app-001-stage.ch.flipkart.com";

    private static final String TF_GET_STRETCH_ASSIGNMENTS_URI = "/fetch_stretch_assgn.php?tags[]=java";
    private static final String TF_GET_QUESTIONS_URI = "/fetch_question_by_tags.php?tags[]=ruby";

    public List<Skill> getSkillListForUser(String emailId) throws Exception {
        String url = String.format(CORE_HOST_URL_PREFIX + CORE_GET_SKILLS_URI, emailId);
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting skills");
        JSONArray jsonArray = new JSONArray(response);
        List<Skill> skills = new ArrayList<Skill>();
        for(Object jsonObject: jsonArray) {
            Skill skill = new Skill(((JSONObject) jsonObject).get("skill").toString(),
                    Float.parseFloat(((JSONObject) jsonObject).get("level").toString()));
            skills.add(skill);
        }
        return skills;
    }

    public List<StretchAssignment> getStretchAssignmentList(String[] tags) throws Exception {
        StringBuilder tagsParams = new StringBuilder();
        for(String tag: tags) {
            tagsParams.append("tag[]=" + tag + "&");
        }
        String tagsAsString = tagsParams.toString();
        tagsAsString = tagsAsString.substring(0, tagsAsString.length() - 1);
        String url = TF_HOST_URL_PREFIX + TF_GET_STRETCH_ASSIGNMENTS_URI;

        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting stretch assignments");
        JSONArray jsonArray = new JSONArray(response);
        List<StretchAssignment> stretchAssignments = new ArrayList<StretchAssignment>();
        for(Object jsonObject: jsonArray) {
            String res = "";
            for (String str : ((JSONObject) jsonObject).get("a_tags").toString().substring(1, ((JSONObject) jsonObject).get("a_tags").toString().length() - 1).split(",")) {
                res += str.substring(1,str.length()-1) + ", ";

            }
            res = res.substring(0,res.length()-2);

            StretchAssignment stretchAssignment = new StretchAssignment(((JSONObject) jsonObject).get("title").toString(),
                    res);

            stretchAssignments.add(stretchAssignment);
        }
        return stretchAssignments;
    }

    public List<String> getQuestions(String... tags) throws Exception {
        StringBuilder tagParams = new StringBuilder();
        for(String tag: tags) {
            tagParams.append("tag[]=" + tag + "&");
        }
        String tagsAsString = tagParams.toString();
        tagsAsString = tagsAsString.substring(0, tagsAsString.length() - 1);
        String url = TF_HOST_URL_PREFIX + TF_GET_QUESTIONS_URI;
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting stretch assignments");
        JSONArray jsonArray = new JSONArray(response);
        List<String> questions = new ArrayList<String>();
        for(Object jsonObject: jsonArray) {
            questions.add(((JSONObject) jsonObject).get("title").toString());
        }
        return questions;
    }


    public List<Leader> getAllLeaders() throws Exception {
        String url = CORE_HOST_URL_PREFIX + CORE_GET_LEADERS_URI;
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting skills");
        JSONArray jsonArray = new JSONArray(response);
        List<Leader> leaders = new ArrayList<Leader>();
        for(Object jsonObject: jsonArray) {
            Leader leader = new Leader(((JSONObject) jsonObject).get("name").toString(),
                    Float.parseFloat(((JSONObject) jsonObject).get("stars").toString()));
            leaders.add(leader);
        }
        Collections.sort(leaders);
        return leaders;
    }

    public Employee getEmployeeDetails(String emailId) throws Exception {
        String url = String.format(CORE_HOST_URL_PREFIX + CORE_GET_EM_DETAILS_URI, emailId);
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting skills");
        JSONObject jsonObject = new JSONObject(response);
        Employee employee = new Employee();
        employee.setName(jsonObject.get("name").toString());
        employee.setScore(Double.parseDouble(jsonObject.get("score").toString()));
        employee.setPercentile(Double.parseDouble(jsonObject.get("percentile").toString()));
        return employee;
    }

}
