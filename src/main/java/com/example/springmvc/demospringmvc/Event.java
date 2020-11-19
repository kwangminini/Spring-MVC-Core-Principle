package com.example.springmvc.demospringmvc;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String name;
    private int limitOfEnrollment;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
