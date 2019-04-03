/*
 * @Copyright 2019 ABB. All rights reserved.
 */

package com.example.transactional.ExampleSpringJpaHibernatePostrgesqlTest.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parent")
@Access(AccessType.FIELD)
public class ParentEntity implements Serializable {
    private static final long serialVersionUID = -2032547860362677797L;

    private static final String ID_SEQ = "parent_id_seq";
    @Id
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_SEQ)
    @Column
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_parent", referencedColumnName = "id")
    private Set<ChildEntity> children;

    public ParentEntity() {
    }

    public ParentEntity(String name) {
        this.name = name;
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

    public Set<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildEntity> children) {
        this.children = children;
    }
}
