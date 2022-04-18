package com.teksystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Class {

    @Column(name = "class_time")
    private String classTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;
    @Column(name = "class_name")
    private String className;
    @Column(name = "days_per_week")
    private int daysPerWeek;
    @Column(name = "tutor_name", nullable = true)
    private String tutorName;

    @OneToMany(targetEntity = Lesson.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "classId")
    private List<Lesson> classLessons = new ArrayList<>();


}
