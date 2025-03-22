package com.project.javacrm.generator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ColumnDetails {

    private String name;
    private String type;
    private boolean primary;
    private boolean foreign;
    private String referencedTable;
    private String referencedColumn;
    private String referencedColumnType;

}
