package org.example.birthdaybot.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "birthdayDB")
public class PersonData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long chatId;

    @Nonnull
    @Column(nullable = false, name = "person_name")
    private String fullName;

    @Nonnull
    @Column(nullable = false, name = "date_of_birth")
    private Date birthday;

    public PersonData(Long chatId, @NotNull String fullName, @NotNull Date birthday) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.birthday = birthday;
    }
}
