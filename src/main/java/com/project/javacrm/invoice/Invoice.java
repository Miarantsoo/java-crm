package com.project.javacrm.invoice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Invoice {
    private Integer id, invoice_number, source_id, client_id, offer_id;
    private String external_id, status, integration_invoice_id, integration_type, source_type, sent_at;
    private LocalDateTime due_at, deleted_at, created_at, updated_at;
}
