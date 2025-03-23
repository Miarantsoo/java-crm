package com.project.javacrm.dashboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ChartTask {

    private List<TaskElement> data;
    private int total;

    @Getter
    @Setter
    public static class TaskElement {
        private String status;
        private int nbr;
    }

}
