package com.an.test.personalitytestapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="questions")
public class Question {

    @Id
    @SequenceGenerator(
            name = "question_seq",
            sequenceName = "question_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "question_seq",
            strategy = GenerationType.SEQUENCE)
    private long id;

    private String question;

    private String questionCategory;

    private String questionType;

    private String options;

    private String ranges;

    @Transient
    private List<String> optionList;

    @Transient
    private List<String> rangeList;

    @JsonProperty("optionList")
    public List<String> getOptionList(){
        return Arrays.asList(options.split(","));
    }

    @JsonProperty("rangeList")
    public List<String> getRangeList(){
        return Arrays.asList(ranges.split(","));
    }

    /**
     * For integration test
     * @param question
     */
    public Question(String question) {
        this.question = question;
    }
}
