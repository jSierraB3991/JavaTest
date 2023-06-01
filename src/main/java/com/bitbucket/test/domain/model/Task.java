package com.bitbucket.test.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
