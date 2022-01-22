package com.pragra.learning.jdbctemplate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder //design pattern
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String course;
    private Instant createDate;
    private Instant updateDate;
}
