package core;

import java.awt.*;

public class Level {
    int levelID;
    int gameNumber;
    int levelNumber;
    Character[][] level;
    int maxX;
    int maxY;

    public Level(int levelID, int gameNumber, int levelNumber, String levelString){
        this.levelID = levelID;
        this.gameNumber = gameNumber;
        this.levelNumber = levelNumber;
        int x = 1;
        int y = 0;
        System.out.print("Length: " + levelString.length() + "\n");
        for(int i = 0; i < levelString.length(); i++){
            if(levelString.charAt(i) == '\n'){
                x++;
            }
            if(levelString.charAt(i) != '\n' && x == 1){
                y++;
            }

        }
        y--;// final enter
        Character[][] level = new Character[x][y];
        int c = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                while(levelString.charAt(c)!= 'W' &&  levelString.charAt(c)!= 'P' && levelString.charAt(c)!= 'B' && levelString.charAt(c)!= ' ' && levelString.charAt(c)!= 'X' && levelString.charAt(c)!= '0'){
                        c++;
                }
                level[i][j] = levelString.charAt(c);
                c++;
            }
        }
        this.level = level;
        this.maxX = y;
        this.maxY = x;
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

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }


    public Character[][] getLevel() {
        return level;
    }

    public boolean checkWin(){
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
               if(level[i][j].equals('B'))
                   return false;
            }
        }
        return true;
    }

    public void render(){
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }

    public Point locatePlayer(){
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                if (level[i][j] == 'P')
                    return new Point(j,i);
            }
        }
        return new Point(-1,-1);
    }

    public boolean move(Point from, char direction) {
        boolean playerX = false;
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
                }
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
            level[from.y][from.x] = 'X';
            level[to.y][to.x] = 'B';
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

    public int getMaxBox(){
        int max = 0;
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                if (level[i][j] == 'B')
                    max++;
            }
        }
        return max;
    }

    public Point locateBox(int n){
        if (n+1>=getMaxBox()){
            return new Point(-1,-1);
        }
        else{
            int c = 0;
            for(int i = 0; i < maxY; i++){
                for(int j = 0; j < maxX; j++){
                    if (level[i][j] == 'B')
                        if(c!=n) c++;
                        else return new Point(j,i);
                }
            }
        }
        return new Point(-1,-1);
    }

    public boolean checkDeadlock(){
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
}

