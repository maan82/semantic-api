package com.semantic.api.repository;

import com.semantic.api.domain.Person;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;

@Component
public class PersonBuilder {

    Integer getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public Person build(BindingSet bindingSet) throws DatatypeConfigurationException {
        String person = getValue(bindingSet, "person");
        String birthPlace = getValue(bindingSet, "birthPlace");
        String age = calculateAge(getValue(bindingSet, "birthDate"));
        return new Person(person, age, birthPlace);
    }

    private String calculateAge(String birthDate) throws DatatypeConfigurationException {
        String age = null;
        if (birthDate != null) {
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(birthDate);
            Integer ageDiff = getCurrentYear() - xmlGregorianCalendar.getYear();
            age = ageDiff.toString();
        }
        return age;
    }

    private String getValue(BindingSet bindingSet, String bindingName) {
        Value value = bindingSet.getValue(bindingName);
        return value != null ? value.stringValue(): null;
    }
}
