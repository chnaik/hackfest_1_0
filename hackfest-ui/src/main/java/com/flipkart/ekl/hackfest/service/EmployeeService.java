package com.flipkart.ekl.hackfest.service;

import com.flipkart.ekl.hackfest.client.ApacheHttpClient;
import com.flipkart.ekl.hackfest.core.Skill;
import com.flipkart.ekl.hackfest.core.StretchAssignment;
import com.flipkart.ekl.hackfest.util.HttpClientUtils;
import com.sun.javafx.binding.StringFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
public class EmployeeService {

    private static final String CORE_HOST_URL_PREFIX = "http://172.17.92.120:9090";

    private static final String CORE_GET_SKILLS_URI = "/employee/score/%s/lms";

    private static final String TF_HOST_URL_PREFIX = "http://ekl-lm-app-001-stage.ch.flipkart.com";

    private static final String TF_GET_STRETCH_ASSIGNMENTS_URI = "";
    private static final String TF_GET_QUESTIONS_URI = "";

    public List<Skill> getSkillListForUser(String emailId) throws Exception {
        String url = StringFormatter.format(CORE_HOST_URL_PREFIX + CORE_GET_SKILLS_URI, emailId).getValue();
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

    public List<StretchAssignment> getStretchAssignmentList(String... tags) throws Exception {
        String url = StringFormatter.format(TF_HOST_URL_PREFIX + TF_GET_STRETCH_ASSIGNMENTS_URI, tags).getValue();
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting stretch assignments");
        JSONArray jsonArray = new JSONArray(response);
        List<StretchAssignment> stretchAssignments = new ArrayList<StretchAssignment>();
        for(Object jsonObject: jsonArray) {
            StretchAssignment stretchAssignment = new StretchAssignment(((JSONObject) jsonObject).get("TODO").toString(),
                    ((JSONObject) jsonObject).get("//TODO").toString());
            stretchAssignments.add(stretchAssignment);
        }
        return stretchAssignments;
    }

    public List<String> getQuestions(String... tags) throws Exception {
        String url = StringFormatter.format(TF_HOST_URL_PREFIX + TF_GET_QUESTIONS_URI, tags).getValue();
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
        String response = HttpClientUtils.processResponse(apacheHttpClient.get(url, null), "Error while getting stretch assignments");
        JSONArray jsonArray = new JSONArray(response);
        List<String> questions = new ArrayList<String>();
        for(Object jsonObject: jsonArray) {
            questions.add(((JSONObject) jsonObject).get("//TODO").toString());
        }
        return questions;
    }
}
