package edu.gsu.ays.gpi.inoisbatch.entity;


import com.univocity.parsers.annotations.Parsed;

public class Test {

    @Parsed(index = 0)
    private String name;

    @Parsed(field = "SSN")
    private String SSN;

    @Parsed(field = "School")
    private String school;

    @Parsed(field = "Gov_Id")
    private String govId;


    public Test(){}

    public String getName(){return this.name;}
    public String getSSN(){return this.SSN;}
    public String getSchool() {return this.school;}
    public String getGovId(){return this.govId;}

    public void setGovId(String govId) { this.govId = govId; }
    public void setName(String name) { this.name = name; }
    public void setSchool(String school) { this.school = school; }
    public void setSSN(String ssn) { this.SSN = ssn; }
}
