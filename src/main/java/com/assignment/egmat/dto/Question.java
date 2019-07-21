package com.assignment.egmat.dto;

import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

public class Question {
    public enum Tag {
        TAG1("Tag1"),
        TAG2("Tag2"),
        TAG3("Tag3"),
        TAG4("Tag4"),
        TAG5("Tag5"),
        TAG6("Tag6");

        public final String tagCode;

        Tag(String tagCode) {
            this.tagCode = tagCode;
        }
    }

    public enum Difficulty {
        HARD("HARD"),
        MEDIUM("MEDIUM"),
        EASY("EASY");

        public final String difficultyLevel;

        Difficulty(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }
    }

    @Parsed(index = 0)
    @Trim
    private String question;

    @Parsed(index = 1)
    @Trim
    @EnumOptions(customElement = "difficultyLevel")
    private Difficulty difficulty;

    @Parsed(index = 2)
    @Trim
    @EnumOptions(customElement = "tagCode")
    private Tag tag;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
