package eu.chargetime.ocpp.model.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class TestModel {
    private String stringTest;
    private Calendar calendarTest;
    private Integer integerTest;
    private int intTest;
    private Long longTest;
    private long genericLongTest;
    private Double doubleTest;
    private double genericDoubleTest;
    private Boolean booleanTest;
    private boolean genericBoleanTest;
    private TestModel objectTest;

    public TestModel getObjectTest() {
        return objectTest;
    }

    public void setObjectTest(TestModel objectTest) {
        this.objectTest = objectTest;
    }

    public boolean isGenericBoleanTest() {
        return genericBoleanTest;
    }

    public void setGenericBoleanTest(boolean genericBoleanTest) {
        this.genericBoleanTest = genericBoleanTest;
    }

    public Boolean getBooleanTest() {
        return booleanTest;
    }

    public void setBooleanTest(Boolean booleanTest) {
        this.booleanTest = booleanTest;
    }

    public double getGenericDoubleTest() {
        return genericDoubleTest;
    }

    public void setGenericDoubleTest(double genericDoubleTest) {
        this.genericDoubleTest = genericDoubleTest;
    }

    public Double getDoubleTest() {
        return doubleTest;
    }

    public void setDoubleTest(Double doubleTest) {
        this.doubleTest = doubleTest;
    }

    public long getGenericLongTest() {
        return genericLongTest;
    }

    public void setGenericLongTest(long genericLongTest) {
        this.genericLongTest = genericLongTest;
    }

    public Long getLongTest() {
        return longTest;
    }

    public void setLongTest(Long longTest) {
        this.longTest = longTest;
    }

    public int getIntTest() {
        return intTest;
    }

    public void setIntTest(int intTest) {
        this.intTest = intTest;
    }

    public Integer getIntegerTest() {
        return integerTest;
    }

    public void setIntegerTest(Integer integerTest) {
        this.integerTest = integerTest;
    }

    public Calendar getCalendarTest() {
        return calendarTest;
    }

    public void setCalendarTest(Calendar calendarTest) {
        this.calendarTest = calendarTest;
    }

    public String getStringTest() {
        return stringTest;
    }

    public void setStringTest(String stringTest) {
        this.stringTest = stringTest;
    }
}
