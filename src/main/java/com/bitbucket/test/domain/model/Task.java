package com.bitbucket.test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task implements Serializable {
    private static final long serialVersionUID = 9155821926133886403L;

    @Id
    private String code;

    private String name;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    @ManyToOne
    private User user;
}
