package org.example.threads;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class FootballGame {
    int firstTeamScore;
    int secondTeamScore;
    static String team1Name =  "Dynamo";
    static String team2Name = "Obolon";
    public static void main(String[] args) throws InterruptedException {
        System.out.println("game started");
        Ball ball = new Ball();
        TeamScore teamScore = new TeamScore();
        Thread thread1 = new Thread(new TeamGame(team1Name,ball, Team.FIRST, teamScore));
        Thread thread2 = new Thread(new TeamGame(team2Name, ball, Team.SECOND, teamScore));
        thread1.start();
        thread2.start();
        Thread.sleep(30000);
        thread1.stop();
        thread2.stop();
        System.out.println("game over");
        doResults(teamScore,team1Name,team2Name);
    }

    private static void doResults(TeamScore teamScore, String team1Name, String team2Name) {
        if (teamScore.scoreMap.get(Team.FIRST) == teamScore.scoreMap.get(Team.SECOND)) {
            System.out.println("Draw");
        }

        if (teamScore.scoreMap.get(Team.FIRST) > teamScore.scoreMap.get(Team.SECOND)) {
            System.out.println(team1Name + "is winner");
        }

        if (teamScore.scoreMap.get(Team.FIRST) < teamScore.scoreMap.get(Team.SECOND)) {
            System.out.println(team2Name + " is winner");
        }
    }

}

class TeamGame implements Runnable {
    String teamName;
    Ball ball;
    Team team;
    TeamScore teamScore;
    public TeamGame(String teamName, Ball ball, Team team, TeamScore teamScore) {
        this.teamName = teamName;
        this.ball = ball;
        this.team = team;
        this.teamScore = teamScore;
    }

    public Team changeTeam() {
        System.out.println("team changes");
        if (this.team.equals(Team.FIRST)) {
            return Team.SECOND;
        } else return Team.FIRST;
    }

    public  boolean isCurrentTeam() {
        if (this.team.equals(this.ball.team)) {
            return true;
        }
        return false;

    }


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int player = 1;
        int min = 0;
        int max = 12;
        synchronized (ball) {
        while (player != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Random rand = new Random();
            player = rand.nextInt(max - min + 1) + min;
            System.out.println("Team = " + this.teamName + " player "  + player);
            if (player == 12) {
              this.teamScore.changeTeamScore(this.team);
              this.teamScore.printScore();
            }
        }
            try {
                ball.team = changeTeam();
                while (!isCurrentTeam() ) {
                    ball.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ball.notify();
        }
    }
}

class Ball {
    Team team;
    public Ball() {
        team = Team.FIRST;
    }
}
enum Team {
    FIRST,
    SECOND
}

class TeamScore {
    Map<Team, Integer> scoreMap;
    public TeamScore() {
        scoreMap = new ConcurrentHashMap<>();
        scoreMap.put(Team.FIRST, 0);
        scoreMap.put(Team.SECOND,0);
    }
    public  void changeTeamScore(Team team) {
        Integer current = scoreMap.get(team);
        current++;
        scoreMap.put(team,current);
    }
    public void printScore() {
        System.out.println("score :" + this.scoreMap);
    }
}