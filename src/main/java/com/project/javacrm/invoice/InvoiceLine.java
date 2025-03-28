package com.project.javacrm.invoice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class InvoiceLine {

    private int id;
    private String external_id;
    private String title;
    private String comment;
    private int price;
    private int invoice_id;
    private int offer_id;
    private String type;
    private int quantity;
    private int product_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

}
