package com.semantic.api.repository;

import com.semantic.api.domain.Person;
import org.junit.Test;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

import javax.xml.datatype.DatatypeConfigurationException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonBuilderTest {

    @Test
    public void testBuildShouldAddPerson() throws Exception {
        BindingSet bindingSet = mock(BindingSet.class);
        Value nameValue = mock(Value.class);

        when(bindingSet.getValue("person")).thenReturn(nameValue);
        when(nameValue.stringValue()).thenReturn("TEST NAME");

        PersonBuilder personBuilder = new PersonBuilder();

        String actual = personBuilder.build(bindingSet).getName();

        assertEquals("TEST NAME", actual);
    }

    @Test
    public void testBuildShouldAddPlaceOfBirth() throws Exception {
        BindingSet bindingSet = mock(BindingSet.class);
        Value birthPlaceValue = mock(Value.class);

        when(bindingSet.getValue("birthPlace")).thenReturn(birthPlaceValue);
        when(birthPlaceValue.stringValue()).thenReturn("PLACE");

        PersonBuilder personBuilder = new PersonBuilder();

        String actual = personBuilder.build(bindingSet).getPalceOfBirth();

        assertEquals("PLACE", actual);
    }

    @Test
    public void testBuildShouldAddAge() throws Exception {
        BindingSet bindingSet = mock(BindingSet.class);
        Value birthDateValue = mock(Value.class);

        when(bindingSet.getValue("birthDate")).thenReturn(birthDateValue);
        when(birthDateValue.stringValue()).thenReturn("1953-05-06+02:00");

        PersonBuilder personBuilder = new PersonBuilder() {
            @Override
            Integer getCurrentYear() {
                return 1960;
            }
        };

        String actual = personBuilder.build(bindingSet).getAge();

        assertEquals("7", actual);
    }

}