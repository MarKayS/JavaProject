package core;

import java.awt.*;

public class Level {
    String levelName = "";
    int levelID;
    boolean playerX = false;
    int gameNumber;
    int levelNumber;
    Character[][] level;
    int maxX = 0;
    int maxY = 1;

    public Level(Character[][] level, int maxX, int maxY) {
        this.level = level;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Level(int levelID, int gameNumber, int levelNumber, String levelString, String levelName) {
        this.levelID = levelID;
        this.gameNumber = gameNumber;
        this.levelNumber = levelNumber;
        this.levelName = levelName;
        int y;
        int x = 0;
        int c;
        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == '\n') {
                maxY++;
                if (x > maxX) maxX = x;
                x = 0;
            } else if (levelString.charAt(i) == 'W' || levelString.charAt(i) == 'P' || levelString.charAt(i) == 'B' || levelString.charAt(i) == ' ' || levelString.charAt(i) == 'X' || levelString.charAt(i) == '0') {
                x++;
            }
        }
        level = new Character[maxY][maxX];
        c = x = y = 0;
        while (c < levelString.length()) {
            if (levelString.charAt(c) == '\n') {
                while (x + 1 < maxX) {
                    x++;
                    level[y][x] = ' ';
                }
                x = 0;
                y++;
            } else if (levelString.charAt(c) == 'W' || levelString.charAt(c) == 'P' || levelString.charAt(c) == 'B' || levelString.charAt(c) == ' ' || levelString.charAt(c) == 'X' || levelString.charAt(c) == '0') {
                level[y][x] = levelString.charAt(c);
                x++;
            }
            c++;
        }
    }

    public int getLevelID() {
        return levelID;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getLevelName() {
        return levelName;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public Character[][] getLevel() {
        return level;
    }

    public boolean checkWin() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (level[i][j].equals('B'))
                    return false;
            }
        }
        return true;
    }

    public void render() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }

    public Point locatePlayer() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (level[i][j] == 'P')
                    return new Point(j, i);
            }
        }
        return new Point(-1, -1);
    }

    public boolean move(Point from, char direction) {
        int x = 0, y = 0;

        if (direction == 'w') {
            y = -1;
        } else if (direction == 's') {
            y = +1;
        } else if (direction == 'a') {
            x = -1;
        } else if (direction == 'd') {
            x = +1;
        }
        Point to = new Point(from.x + x, from.y + y);

        if (level[to.y][to.x] == 'W') {
            return false;
        }
        if (level[from.y][from.x] == 'P') {
            if (level[to.y][to.x] == 'B' || level[to.y][to.x] == '0') {
                if (!move(to, direction)) {
                    return false;
                    //assfsf
                }
                //asdasdad
            }
            if (playerX) {
                level[from.y][from.x] = 'X';
                playerX = false;
            } else {
                level[from.y][from.x] = ' ';
            }
            if (level[to.y][to.x] == 'X') {
                playerX = true;
            }
            level[to.y][to.x] = 'P';
        } else if (level[from.y][from.x] == '0') {
            if(level[to.y][to.x]=='B'||level[to.y][to.x]=='0'){
                return false;
            }
            else if(level[to.y][to.x] != 'X') {
                level[from.y][from.x] = 'X';
                level[to.y][to.x] = 'B';
            }
            else{
                level[from.y][from.x] = 'X';
                level[to.y][to.x] = '0';
            }
        } else if (level[from.y][from.x] == 'B') {
            if (level[to.y][to.x] == 'B' || level[to.y][to.x] == '0')
                return false;
            else if (level[to.y][to.x] == 'X') {
                level[to.y][to.x] = '0';
            } else {
                level[to.y][to.x] = 'B';
            }
            level[from.y][from.x] = ' ';
        }
        return true;
    }
}

   /* public int getMaxBox() {
        int max = 0;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (level[i][j] == 'B')
                    max++;
            }
        }
        return max;
    }

    public Point locateBox(int n) {
        if (n + 1 >= getMaxBox()) {
            return new Point(-1, -1);
        } else {
            int c = 0;
            for (int i = 0; i < maxY; i++) {
                for (int j = 0; j < maxX; j++) {
                    if (level[i][j] == 'B')
                        if (c != n) c++;
                        else return new Point(j, i);
                }
            }
        }
        return new Point(-1, -1);
    }

  *//*  public int[][] targetmapper(Point target) {
        return new int[][]{{0}};
    }

    public boolean isSafe(Point target) {
        if (level[target.y][target.x] == 'X' || level[target.y][target.x] == ' ' || level[target.y][target.x] == 'P')
            return true;
        return false;
    }

    public boolean solve(int i, int j, Point target) {

        if (i < 0 || i > maxY) return false;
        if (j < 0 || j > maxX) return false;

        if (!isSafe(new Point(j, i))) {
            visited[i][j] = '0';
            return false;
        }
        if (visited[i][j] != '2') return false;// null character
        visited[i][j] = '1';
        return i == target.y && j == target.x || (solve(i - 1, j, target) || solve(i, j - 1, target) || solve(i, j + 1, target) || solve(i + 1, j, target));

    }

    public boolean isReachable(Point target) {
        Point player = locatePlayer();
        if (!isSafe(target)) return false; // check is target is walkable
        return solve(player.y, player.x, target);
    }

    public boolean checkDeadlock() {
        //corner
        for (int i = 0; i < getMaxBox(); i++) {
            Point cur = locateBox(i);
            boolean bx = false;
            boolean by = false;
            if (level[cur.y + 1][cur.x] == 'W' || level[cur.y + 1][cur.x] == 'B') by = true;
            if (level[cur.y - 1][cur.x] == 'W' || level[cur.y - 1][cur.x] == 'B') by = true;
            if (level[cur.y][cur.x + 1] == 'W' || level[cur.y][cur.x + 1] == 'B') bx = true;
            if (level[cur.y][cur.x - 1] == 'W' || level[cur.y][cur.x - 1] == 'B') bx = true;
            if (bx && by) return true;
        }
        return false;
    }
}*/

