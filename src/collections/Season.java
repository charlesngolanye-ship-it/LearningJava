package collections;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Season {
    SPRING (65),
    SUMMER (85),
    FALL(55),
    WINTER (30);

    private final int averageTemperature;

    Season(int averageTemperature) {
        this.averageTemperature = averageTemperature;
    }


    public int getAverageTemperature() {
        return averageTemperature;
    }
}
