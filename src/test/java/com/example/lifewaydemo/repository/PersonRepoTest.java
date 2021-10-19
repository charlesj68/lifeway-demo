package com.example.lifewaydemo.repository;

import com.example.lifewaydemo.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepoTest {

    @Autowired
    PersonRepo repository;

    @Test
    public void should_create_new_person() {
        Person person = repository.save(new Person("Charlie", "Brown", "cbrown@peanuts.org", "505-247-1234"));
        assertThat(person).hasFieldOrPropertyWithValue("firstName", "Charlie");
        assertThat(person).hasFieldOrPropertyWithValue("lastName", "Brown");
        assertThat(person).hasFieldOrPropertyWithValue("email", "cbrown@peanuts.org");
        assertThat(person).hasFieldOrPropertyWithValue("phone", "505-247-1234");
    }

    @Test
    public void should_update_person() {
        Person person = repository.save(new Person("Charlie", "Brown", "cbrown@peanuts.org", "505-247-1234"));
        assertThat(person).hasFieldOrPropertyWithValue("firstName", "Charlie");
        assertThat(person).hasFieldOrPropertyWithValue("lastName", "Brown");
        assertThat(person).hasFieldOrPropertyWithValue("email", "cbrown@peanuts.org");
        assertThat(person).hasFieldOrPropertyWithValue("phone", "505-247-1234");
        person.setPhone("303-994-1234");
        person = repository.save(person);
        assertThat(person).hasFieldOrPropertyWithValue("phone", "303-994-1234");
    }

    @Test
    public void should_find_user_by_id() {
        Person person = repository.save(new Person("Charlie", "Brown", "cbrown@peanuts.org", "505-247-1234"));
        Person found = repository.getById(person.getId());
        assertSame(person, found, "Find by ID did not return the expected data");
    }

    @Test
    public void should_delete_person() {
        Person person1 = repository.save(new Person("Charlie", "Brown", "cbrown@peanuts.org", "505-247-1234"));
        repository.save(new Person("Lucy", "Van Pelt", "lvanpelt@peanuts.org", "505-296-9876"));
        repository.deleteById(person1.getId());
        assertThrows(JpaObjectRetrievalFailureException.class, () -> repository.getById(person1.getId()));
    }

    @Test
    public void should_list_all_users() {
        repository.save(new Person("Charlie", "Brown", "cbrown@peanuts.org", "505-247-1234"));
        repository.save(new Person("Lucy", "Van Pelt", "lvanpelt@peanuts.org", "505-296-9876"));
        repository.save(new Person("Peppermint", "Patty", "ppatty@peanuts.org", "505-493-4567"));
        List<Person> all = repository.findAll();
        assertThat(all).hasSize(3);
    }

}
