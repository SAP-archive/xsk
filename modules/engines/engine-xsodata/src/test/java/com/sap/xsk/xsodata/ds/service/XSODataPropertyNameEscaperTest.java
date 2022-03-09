package com.sap.xsk.xsodata.ds.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class XSODataPropertyNameEscaperTest {

    private XSODataPropertyNameEscaper escaper;

    @Before
    public void setUp() {
        this.escaper = new XSODataPropertyNameEscaper();
    }

    @Test
    public void testEscapeWithDots() {
        String propertyName = "Property.Name";
        assertEquals("Unexpected property name", propertyName, escaper.escape(propertyName));
    }

    @Test
    public void testEscapeWithUnderscore() {
        String propertyName = "Property_Name";
        assertEquals("Unexpected property name", propertyName, escaper.escape(propertyName));
    }

}