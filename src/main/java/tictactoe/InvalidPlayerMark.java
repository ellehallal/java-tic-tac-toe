package tictactoe;

public enum InvalidPlayerMark {
    DIGIT("\\d+"),
    WHITESPACE(" "),
    EMPTY("");

    public final String mark;

    InvalidPlayerMark(String mark) {
        this.mark = mark;
    }
}
