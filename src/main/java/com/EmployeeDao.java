package com;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public class EmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveEmployee(Employee employee) {
       sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Transactional
    public List<Employee> getEmployees() {
        @SuppressWarnings("unchecked")
        List<Employee> listUser = (List<Employee>) sessionFactory.getCurrentSession()
                .createCriteria(Employee.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listUser;
    }

    @Transactional
    public void deleteEmployee(int id) {
        Employee employee = new Employee();
        employee.setId(id);
        sessionFactory.getCurrentSession().delete(employee);
    }

    @Transactional
    public Employee get(int id) {
        String hql = "from Employee where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        @SuppressWarnings("unchecked")
        List<Employee> listUser = (List<Employee>) query.list();

        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }
        return null;
    }
}
