package com.project.javacrm.offer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Offer {

    private int id;
    private String external_id;
    private LocalDateTime sent_at;
    private String source_type;
    private Integer source_id;
    private Integer client_id;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

}
