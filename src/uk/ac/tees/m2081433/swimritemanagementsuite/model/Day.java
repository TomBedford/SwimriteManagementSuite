package uk.ac.tees.m2081433.swimritemanagementsuite.model;

/**
 * Enum for the different days of the week lessons can be on.
 */
public enum Day {
    /**
     * Monday the first day of the week.
     */
    MONDAY {
                @Override
                public String toString() {
                    return "Monday";
                }
            },
    
    /**
     * Tuesday the second day of the week.
     */
    TUESDAY {
                @Override
                public String toString() {
                    return "Tuesday";
                }
            }, 
    
    /**
     * Wednesday the third day of the week.
     */
    WEDNESDAY {
                @Override
                public String toString() {
                    return "Wednesday";
                }
            }, 
    
    /**
     * Thursday the fourth day of the week.
     */
    THURSDAY {
                @Override
                public String toString() {
                    return "Thursday";
                }
            }, 
    
    /**
     * Friday the fifth day of the week.
     */
    FRIDAY {
                @Override
                public String toString() {
                    return "Friday";
                }
            }, 
    
    /**
     * Saturday the sixth day of the week.
     */
    SATURDAY {
                @Override
                public String toString() {
                    return "Saturday";
                }
            },
    
    /**
     * Sunday the seventh day of the week.
     */
    SUNDAY {
                @Override
                public String toString() {
                    return "Sunday";
                }
            },
}
