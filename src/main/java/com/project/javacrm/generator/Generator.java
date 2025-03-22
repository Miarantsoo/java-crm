package com.project.javacrm.generator;

public interface Generator {

    public void generateModel(GeneratorElements generatorElements);
    public void generateController(GeneratorElements generatorElements);
    public void generateService(GeneratorElements generatorElements);
    public void generateRepository(GeneratorElements generatorElements);

}
