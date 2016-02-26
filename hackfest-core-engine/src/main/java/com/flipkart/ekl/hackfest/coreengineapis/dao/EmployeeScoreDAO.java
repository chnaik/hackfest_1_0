package com.flipkart.ekl.hackfest.coreengineapis.dao;

import com.flipkart.ekl.hackfest.coreengineapis.core.EmployeeScore;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
    
    
}
