package com.an.test.personalitytestapp.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="answers")
public class Answer {

    @Id
    @SequenceGenerator(
            name = "answer_seq",
            sequenceName = "answer_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "answer_seq",
            strategy = GenerationType.SEQUENCE)
    private long id;


    @Column(nullable = false)
    private long questionId;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String answer;

    public Answer(long questionId, long userId, String answer) {
        this.questionId = questionId;
        this.userId = userId;
        this.answer = answer;
    }
}
