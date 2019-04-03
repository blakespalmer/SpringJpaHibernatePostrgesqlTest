/*
 * @Copyright 2019 ABB. All rights reserved.
 */

package com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.entity.ChildEntity;

public interface ChildRepository extends JpaRepository<ChildEntity, Integer> {
}
