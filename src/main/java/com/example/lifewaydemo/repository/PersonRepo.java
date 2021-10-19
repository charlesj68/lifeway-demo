package com.example.lifewaydemo.repository;

import com.example.lifewaydemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
}
