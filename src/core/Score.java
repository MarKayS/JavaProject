package core;

/**
 * Created by Matouš on 26. 3. 2015.
 */
public class Score {

    private int moves, time;
    private String levelName,nickname;

    public Score(int time, int moves, String levelName, String nickname) {
        this.time = time;
        this.moves = moves;
        this.levelName = levelName;
        this.nickname = nickname;
    }

    public int getMoves() {
        return moves;
    }

    public int getTime() {
        return time;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getNickname() {
        return nickname;
    }
}
