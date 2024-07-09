package org.example.birthdaybot.repository;

import org.example.birthdaybot.model.PersonData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<PersonData, Long> {
    @Query("SELECT CASE WHEN COUNT(pd) > 0 THEN true ELSE false END FROM PersonData pd WHERE pd.chatId = :chatId")
    boolean existsByChatId(@Param("chatId") Long chatId);
}
