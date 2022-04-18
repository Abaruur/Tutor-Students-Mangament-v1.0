package com.teksystems.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tutorId;
    @NotBlank
    private String tutorName;
    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @OneToMany(targetEntity = Class.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id", referencedColumnName = "tutorId")
    private List<Class> tutorCreatedClasses = new ArrayList<>();

}
