package core;

/**
 * Created by Matouš on 26. 3. 2015.
 */
public class Score {

    private int score, time;
    private String levelName,nickname;

    public Score(int score, int time, String levelName, String nickname) {
        this.score = score;
        this.time = time;
        this.levelName = levelName;
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
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
