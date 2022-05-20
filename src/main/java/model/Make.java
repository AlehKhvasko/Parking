package model;

public enum Make {
    Lada("лада"),
    Audi("Audi Automobilwerke"),
    BMW("Bavarian Motor Works");

    private final String description;

    private Make(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
