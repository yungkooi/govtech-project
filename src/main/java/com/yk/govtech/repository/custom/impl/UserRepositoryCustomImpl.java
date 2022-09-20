package com.yk.govtech.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yk.govtech.entity.User;
import com.yk.govtech.repository.custom.UserRepositoryCustom;

@Service
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> findByRequestParam(double min, double max, int offset, Integer limit, String sort) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        
        Root<User> user = query.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(cb.greaterThanOrEqualTo(user.get("salary"), min));
        predicates.add(cb.lessThanOrEqualTo(user.get("salary"), max));
        
        query.select(user).where(predicates.toArray(new Predicate[0]));
        
        if (StringUtils.hasText(sort)) {
        	query.orderBy(cb.asc(user.get(sort.toLowerCase())));
        }
        
        TypedQuery<User> entityManagerAppend = entityManager.createQuery(query);
        
        if (offset != 0) {
        	entityManagerAppend.setFirstResult(offset);
        }
        
        if (limit != null && limit != 0) {
        	entityManagerAppend.setMaxResults(limit);
        }
        
        return entityManagerAppend.getResultList();
	}

}
