package com.flipkart.ekl.hackfest.coreengineapis.resource;

import com.flipkart.ekl.hackfest.coreengineapis.core.Employee;
import com.flipkart.ekl.hackfest.coreengineapis.core.EmployeeScore;
import com.flipkart.ekl.hackfest.coreengineapis.core.HttpResponse;
import com.flipkart.ekl.hackfest.coreengineapis.core.LMSScore;
import com.flipkart.ekl.hackfest.coreengineapis.dao.EmployeeDAO;
import com.flipkart.ekl.hackfest.coreengineapis.dao.EmployeeScoreDAO;
import com.flipkart.ekl.hackfest.coreengineapis.dao.LMSScoreDAO;
import com.flipkart.ekl.hackfest.coreengineapis.request.LMSSkillRequest;
import com.flipkart.ekl.hackfest.coreengineapis.request.TechForumRequest;
import com.flipkart.ekl.hackfest.coreengineapis.service.ScoreEngineService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitanya.naik on 26/02/16.
 */

@Path("/employee/score")
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@Slf4j
public class EmployeeScoreResource {

    private LMSScoreDAO lmsScoreDAO;
    private EmployeeDAO employeeDAO;
    private EmployeeScoreDAO employeeScoreDAO;

    @POST
    @Path("/update/lms")
    @UnitOfWork
    public Response updateLMSSkills(List<LMSSkillRequest> lmsSkillRequestList) {

        for (LMSSkillRequest lmsSkillRequest: lmsSkillRequestList) {
            Employee employee = employeeDAO.getByEmailId(lmsSkillRequest.getEmail_id());
            if(employee == null) {
                log.warn("Employee with email id: " + lmsSkillRequest.getEmail_id() + " doesn't exist");
                continue;
            }
            LMSScore lmsScore = new LMSScore();
            lmsScore.setSkill(lmsSkillRequest.getSkill());
            lmsScore.setLevel(lmsSkillRequest.getLevel());
            lmsScore.setEmployee(employee);
            lmsScoreDAO.upsert(lmsScore);
        }
        Response.Status status = Response.Status.CREATED;
        return Response.status(status).entity(new HttpResponse(status.getStatusCode(), status.getReasonPhrase())).build();
    }

    @GET
    @Path("/{emailId}/lms")
    @UnitOfWork
    public List<LMSSkillRequest> getLMSSkills(@PathParam("emailId") String emailId) {
        List<LMSSkillRequest> lmsSkillRequests = new ArrayList<LMSSkillRequest>();
        List<LMSScore> lmsScoreList = lmsScoreDAO.getByEmailId(emailId);
        for(LMSScore lmsScore: lmsScoreList) {
            lmsSkillRequests.add(new LMSSkillRequest(lmsScore.getEmployee().getEmail_id(),
                    lmsScore.getSkill(), lmsScore.getLevel()));
        }
        return lmsSkillRequests;
    }

    @POST
    @Path("/update/tech-forum")
    @UnitOfWork
    public Response updateTechForumAndStretchAssignment(List<TechForumRequest> techForumRequestList) {

        for(TechForumRequest techForumRequest: techForumRequestList) {

            Employee employee = employeeDAO.getByEmailId(techForumRequest.getEmail_id());
            if(employee == null) {
                log.warn("Employee with email id: " + techForumRequest.getEmail_id() + " doesn't exist");
                continue;
            }
            EmployeeScore techForumEmployeeScore = new EmployeeScore();
            techForumEmployeeScore.setEmployee(employee);
            techForumEmployeeScore.setScore_type(EmployeeScore.ScoreType.TECH_FORUM.toString());
            techForumEmployeeScore.setScore_value(techForumRequest.getReputation());
            employeeScoreDAO.upsert(techForumEmployeeScore);

            EmployeeScore stretchAssignmentEmployeeScore = new EmployeeScore();
            stretchAssignmentEmployeeScore.setEmployee(employee);
            stretchAssignmentEmployeeScore.setScore_type(EmployeeScore.ScoreType.STRETCH_ASSIGNMENT.toString());
            stretchAssignmentEmployeeScore.setScore_value(techForumRequest.getNumberOfStretchAssignments() * 1.0f);
            employeeScoreDAO.upsert(stretchAssignmentEmployeeScore);
        }

        Response.Status status = Response.Status.CREATED;
        return Response.status(status).entity(new HttpResponse(status.getStatusCode(), status.getReasonPhrase())).build();
    }

    @POST
    @Path("/update/overall")
    @UnitOfWork
    public void updateOverAllScore() {
        new ScoreEngineService(employeeScoreDAO).refreshScores();
    }

}
