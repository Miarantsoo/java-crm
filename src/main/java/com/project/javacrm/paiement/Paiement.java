package com.project.javacrm.paiement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Paiement {

    private int id;
    private String external_id;
    private double amount;
    private String description;
    private String payment_source;
    private LocalDateTime payment_date;
    private String integration_payment_id;
    private String integration_type;
    private int invoice_id;
    private LocalDateTime deleted_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @JsonIgnore
    private String color;

    public void setColorByPrice() {
        if ((amount/100) > 1000) {
            this.color = "light";
        } else {
            this.color = "danger";
        }

    }

}
