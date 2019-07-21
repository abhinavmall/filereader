package com.assignment.egmat.dto;

import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

public class Question {
    public enum Tag {
        TAG1("Tag1", 0),
        TAG2("Tag2", 1),
        TAG3("Tag3", 2),
        TAG4("Tag4", 3),
        TAG5("Tag5", 4),
        TAG6("Tag6", 5);

        public final String tagCode;
        public final int tagIndex;
        Tag(String tagCode, int tagIndex) {
            this.tagCode = tagCode;
            this.tagIndex = tagIndex;
        }
    }

    public enum Difficulty {
        HARD("HARD", 0),
        MEDIUM("MEDIUM", 1),
        EASY("EASY", 2);

        public final String difficultyLevel;
        public final int difficultyIndex;

        Difficulty(String difficultyLevel, int difficultyIndex) {
            this.difficultyLevel = difficultyLevel;
            this.difficultyIndex = difficultyIndex;
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
