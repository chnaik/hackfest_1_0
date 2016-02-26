package com.flipkart.ekl.hackfest.coreengineapis.dao;

import com.flipkart.ekl.hackfest.coreengineapis.core.EmployeeScore;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
public class EmployeeScoreDAO extends AbstractDAO<EmployeeScore> {

    public EmployeeScoreDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void upsert(EmployeeScore employeeScore) {

        Criteria criteria = currentSession().createCriteria(EmployeeScore.class);
        criteria.add(Restrictions.eq("score_type", employeeScore.getScore_type()));
        Criteria metadataCriteria = criteria.createCriteria("employee");
        metadataCriteria.add(Restrictions.eq("id", employeeScore.getEmployee().getId()));
        EmployeeScore employeeScoreOld = uniqueResult(criteria);

        if (employeeScoreOld == null) {
            persist(employeeScore);
        } else {
            employeeScore.setId(employeeScoreOld.getId());
            currentSession().buildLockRequest(LockOptions.UPGRADE).setTimeOut(100).lock(employeeScoreOld);
            currentSession().merge(employeeScore);
        }
    }


    public List<EmployeeScore> getByScoreType(String scoreType) {

        Criteria criteria = currentSession().createCriteria(EmployeeScore.class);
        criteria.add(Restrictions.eq("score_type", scoreType));
        return list(criteria);

    }


    public EmployeeScore getByScoreTypeAndEmployeeId(String scoreType, int employeeId) {

        Criteria criteria = currentSession().createCriteria(EmployeeScore.class);
        criteria.add(Restrictions.eq("score_type", scoreType));
        Criteria metadataCriteria = criteria.createCriteria("employee");
        metadataCriteria.add(Restrictions.eq("id", employeeId));
        return uniqueResult(criteria);

    }
    public void upsert() {
        EmployeeScore employeeScore = null;
        HashMap<Integer, EmployeeScore> hm = new HashMap<Integer, EmployeeScore>();
        Criteria criteria = criteria();
        criteria.add(Restrictions.ne("score_type", EmployeeScore.ScoreType.OVERALL_SCORE.toString()));
        List<EmployeeScore> results = list(criteria);
        for (EmployeeScore result : results) {
            EmployeeScore result2 = null;
            if (hm.containsKey(result.getEmployee().getId())) {
                result2 = hm.get(result.getEmployee().getId());
            }else {
                Criteria criteria2 = criteria();
                criteria2.add(Restrictions.eq("score_type", EmployeeScore.ScoreType.OVERALL_SCORE.toString()));
                criteria2.add(Restrictions.eq("employee.id", result.getEmployee().getId()));
                result2 = uniqueResult(criteria2);
                if (result2 == null) {
                    result2 = new EmployeeScore();
                    result2.setEmployee(result.getEmployee());
                    result2.setScore_value(0.0F);
                    result2.setScore_type(EmployeeScore.ScoreType.OVERALL_SCORE.toString());
                    
                }
                hm.put(result.getEmployee().getId(), result2);
            }
            result2.setScore_value(0.0F);
            if (EmployeeScore.ScoreType.PRODUCTIVITY.toString().equals(result.getScore_type())) {
                result2.setScore_value(result2.getScore_value() + result.getScore_value() * 10);
            } else if (EmployeeScore.ScoreType.SKILLS.toString().equals(result.getScore_type())) {
                result2.setScore_value(result2.getScore_value() + result.getScore_value() * 20);
            } else if (EmployeeScore.ScoreType.STRETCH_ASSIGNMENT.toString().equals(result.getScore_type())) {
                result2.setScore_value(result2.getScore_value() + result.getScore_value() * 50);
            } else if (EmployeeScore.ScoreType.TECH_FORUM.toString().equals(result.getScore_type())) {
                result2.setScore_value(result2.getScore_value() + result.getScore_value() * 20);
            }
        }
        for (EmployeeScore score : hm.values()) {
            persist(score);
        }
    }


}
