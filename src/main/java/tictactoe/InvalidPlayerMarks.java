package tictactoe;

public enum InvalidPlayerMarks {
    DIGIT("\\d+"),
    WHITESPACE(" "),
    EMPTY("");

    public final String mark;

    InvalidPlayerMarks(String mark) {
        this.mark = mark;
    }
}
