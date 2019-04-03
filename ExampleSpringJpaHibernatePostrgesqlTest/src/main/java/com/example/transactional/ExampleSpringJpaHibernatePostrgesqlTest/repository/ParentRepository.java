/*
 * @Copyright 2019 ABB. All rights reserved.
 */

package com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.entity.ParentEntity;

public interface ParentRepository extends JpaRepository<ParentEntity, Integer> {
}
