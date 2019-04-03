/*
 * @Copyright 2019 ABB. All rights reserved.
 */

package com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "child")
@Access(AccessType.FIELD)
public class ChildEntity implements Serializable {
    private static final long serialVersionUID = -8554200908243086686L;

    private static final String ID_SEQ = "child_id_seq";
    @Id
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_SEQ)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column(name = "fk_parent")
    private Integer fkParent;

    public ChildEntity() {
    }

    public ChildEntity(String name, Integer fkParent) {
        this.name = name;
        this.fkParent = fkParent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFkParent() {
        return fkParent;
    }

    public void setFkParent(Integer fkParent) {
        this.fkParent = fkParent;
    }

}
