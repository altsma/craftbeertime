package org.wahlzeit.utils;

public @interface Pattern {
    String name() default "PatternName";
    String[] participants() default {"PatternParticipant"};
}
