package com.example.lifewaydemo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class PersonTest {
    @Test
    public void should_create_person() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment env = context.getEnvironment();
        Person person = new Person("Charlie", "Brown", "cbrown@peanuts.org", "505-247-1234");
        assertThat(person).hasFieldOrPropertyWithValue("firstName", "Charlie");
        assertThat(person).hasFieldOrPropertyWithValue("lastName", "Brown");
        assertThat(person).hasFieldOrPropertyWithValue("email", "cbrown@peanuts.org");
        assertThat(person).hasFieldOrPropertyWithValue("phone", "505-247-1234");
    }

    @Test
    public void should_fail_on_missing_name() {
        assertThrows(IllegalArgumentException.class, () -> new Person("", "Brown", "cbrown@peanuts.org", "505-247-1234"));
        assertThrows(IllegalArgumentException.class, () -> new Person("Charlie", "", "cbrown@peanuts.org", "505-247-1234"));
    }

    @Test
    public void should_trim_spaces_on_data() {
        String inputFirstName = "\tCharlie  ";
        String inputLastName = "         Brown  ";
        String inputEmail = "\n\tcbrown@peanuts.org   \n";
        String inputPhone = "\t\t\n   505-247-1234\t\n";

        Person person = new Person(inputFirstName, inputLastName, inputEmail, inputPhone);
        assertThat(person).hasFieldOrPropertyWithValue("firstName", inputFirstName.trim());
        assertThat(person).hasFieldOrPropertyWithValue("lastName", inputLastName.trim());
        assertThat(person).hasFieldOrPropertyWithValue("email", inputEmail.trim());
        assertThat(person).hasFieldOrPropertyWithValue("phone", inputPhone.trim());
    }

    @Test
    public void should_fail_on_bad_email() {
        assertThrows(IllegalArgumentException.class, () -> new Person("Charlie", "Brown", "peanuts.org", "505-247-1234"));
    }
}
