package org.example.birthdaybot.repository;

import org.example.birthdaybot.model.PersonData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<PersonData, Long> {
}
