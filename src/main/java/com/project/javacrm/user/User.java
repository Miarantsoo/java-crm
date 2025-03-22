package com.project.javacrm.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {

    private String external_id;
    private String name;
    private String email;
    private String address;
    private int primary_number;
    private int secondary_number;
    private String language;
    private LocalDateTime deleted_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String avatar;

}
