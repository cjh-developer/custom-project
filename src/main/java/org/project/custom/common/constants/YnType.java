package org.project.custom.common.constants;

public enum YnType {

    Y("true"),
    N("false")
    ;

    private String boolString;

    private YnType(String boolString) {
        this.boolString = boolString;
    }

    public boolean isEqual(String value){
        if(value == null){
            return false;
        }

        if(boolString.equalsIgnoreCase(value)){
            return true;
        }

        return this.name().equalsIgnoreCase(value);
    }

    public boolean isTrue(){
        return this == Y;
    }

    public String getValue(){
        return this.name();
    }

    public String getBoolString(){
        return boolString;
    }
}