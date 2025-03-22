package com.project.javacrm.generator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class GeneratorElements {

    private Model model;
    private Repository repo;
    private Service service;
    private Controller controller;

    @Getter
    @Setter
    @ToString
    public static class Model {
        private String imports;
        private String annotations;
        private String fields;
    }

    @Getter
    @Setter
    @ToString
    public static class Repository {
        private String imports;
        private String annotations;
        private String methods;
    }

    @Getter
    @Setter
    @ToString
    public static class Service {
        private String imports;
        private String annotations;
        private String autowired;
        private String methods;
    }

    @Getter
    @Setter
    @ToString
    public static class Controller {
        private String imports;
        private String annotations;
        private String autowired;
        private String methods;
    }

}
