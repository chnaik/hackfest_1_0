package com.flipkart.ekl.hackfest.coreengineapis.dao;

import com.flipkart.ekl.hackfest.coreengineapis.core.Employee;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
public class EmployeeDAO extends AbstractDAO<Employee> {

    public EmployeeDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }


    public Employee getByEmailId(String emailId) {
        Criteria criteria = currentSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("email_id", emailId));
        return uniqueResult(criteria);
    }
}