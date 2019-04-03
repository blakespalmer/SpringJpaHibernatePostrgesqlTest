/*
 * @Copyright 2019 ABB. All rights reserved.
 */

package com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.entity.ChildEntity;
import com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.entity.ParentEntity;
import com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.repository.ChildRepository;
import com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.repository.ParentRepository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureEmbeddedDatabase
/* XXX With this uncommented the foreign key in the ParentEntity is not fetched
 * and 'testReadWithDependencies()' will fail. */
//@Transactional 
public class ExampleTest {

    @Autowired
    Flyway flyway;

    private static AtomicBoolean initialized = new AtomicBoolean(false);

    // XXX When @Transactional is uncommented, swap these setup methods.
    @Before
    public void nonTransactionalSetup() {
        flyway.clean();
        flyway.migrate();
    }
//    @Before
//    public void transactionalSetup() {
//        if (!initialized.getAndSet(true)) {
//            flyway.clean();
//            flyway.migrate();
//        }
//    }

    @Autowired
    ParentRepository parentRepo;
    @Autowired
    ChildRepository childRepo;

    @Test
    public void testNoData() {
        assertThat(parentRepo.findAll()).isEmpty();
        assertThat(childRepo.findAll()).isEmpty();
        ParentEntity savedParent1 = parentRepo.save(new ParentEntity("parent1"));
        childRepo.save(new ChildEntity("child1", savedParent1.getId()));
    }

    @Test
    @Transactional
    public void testReadWithDependencies() {
        assertThat(parentRepo.findAll()).isEmpty();
        assertThat(childRepo.findAll()).isEmpty();

        ParentEntity savedParent1 = parentRepo.save(new ParentEntity("parent1"));
        childRepo.save(new ChildEntity("child1", savedParent1.getId()));
        
        List<ParentEntity> allParents = parentRepo.findAll();
        assertThat(allParents).hasSize(1);
        Set<ChildEntity> acutalChildren = allParents.get(0).getChildren();

        // XXX When the tests are run with @Transactional, this assertion fails.
        assertThat(acutalChildren).isNotNull();

        assertThat(acutalChildren).hasSize(1);
        assertThat(acutalChildren.iterator().next().getName()).isEqualTo("child1");
    }

}