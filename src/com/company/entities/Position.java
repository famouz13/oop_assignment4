package com.company.entities;


//enum to implement position employee field from database
public enum Position {
    Developer, TeamLead, ProjectManager, Tester, Architect, Engineer;
    //converting integer to this enum for convenience
    public static Position fromInteger(int x) {
        switch (x) {
            case 0:
                return Developer;
            case 1:
                return TeamLead;
            case 2:
                return ProjectManager;
            case 3:
                return Tester;
            case 4:
                return Architect;
            case 5:
                return Engineer;
        }
        return null;
    }
}
