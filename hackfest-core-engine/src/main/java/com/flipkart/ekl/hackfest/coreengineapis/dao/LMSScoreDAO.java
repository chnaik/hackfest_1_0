package com.flipkart.ekl.hackfest.coreengineapis.dao;

import com.flipkart.ekl.hackfest.coreengineapis.core.LMSScore;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
public class LMSScoreDAO extends AbstractDAO<LMSScore> {

    public LMSScoreDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void upsert(LMSScore lmsScore) {
        Criteria criteria = currentSession().createCriteria(LMSScore.class);
        criteria.add(Restrictions.eq("skill", lmsScore.getSkill()));
        Criteria metadataCriteria = criteria.createCriteria("employee");
        metadataCriteria.add(Restrictions.eq("id", lmsScore.getEmployee().getId()));
        LMSScore lmsScoreOld = uniqueResult(criteria);

        if (lmsScoreOld == null) {
            persist(lmsScore);
        } else {
            lmsScore.setId(lmsScoreOld.getId());
            currentSession().buildLockRequest(LockOptions.UPGRADE).setTimeOut(100).lock(lmsScoreOld);
            currentSession().merge(lmsScore);
        }
    }

    public List<LMSScore> getByEmailId(String emailId) {

        Criteria criteria = currentSession().createCriteria(LMSScore.class, "lmsScore");
        Criteria metadataCriteria = criteria.createCriteria("employee");
        metadataCriteria.add(Restrictions.eq("email_id", emailId));
        return list(criteria);
    }
}

