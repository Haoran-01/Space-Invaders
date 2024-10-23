package data;

import ucd.comp2011j.engine.Score;
import java.io.*;
import java.util.Scanner;

/**
 * A class to reading and recording player results
 */
public class ScoreChange implements ucd.comp2011j.engine.ScoreKeeper {

    private Score[] scores;

    public ScoreChange() {
        scores = new Score[10];
        readDate();
    }

    public void readDate(){
        try (BufferedReader br = new BufferedReader(new FileReader("scores1.txt"))){
            String line = br.readLine();
            int i = 0;
            while (line != null && i < 10){
                Scanner sc = new Scanner(line);
                int score = sc.nextInt();
                String name = sc.nextLine().trim();
                scores[i] = new Score(name,score);
                line = br.readLine();
                i++;
                sc.close();
            }
        } catch (IOException e){
            System.err.println("Read file error!");
        }
    }

    @Override
    public void addScore(String name, int score) {
        scores[0] = new Score(name, score);
        scores = selectionSortScore(scores);
    }

    @Override
    public void saveScores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("scores1.txt"))){
            for (int i = 0; i < scores.length; i++){
                bw.write(scores[i].getScore() + " " + scores[i].getName() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Write file error!");
        }
    }

    @Override
    public Score[] getScores() {
        Score[] newScores = new Score[10];
        for (int i = 0; i < scores.length; i++){
            newScores[i] = scores[i];
        }
        return newScores;
    }

    @Override
    public int getLowestScore() {
        return scores[0].getScore();
    }

    /**
     * use selection sort method to sort the Score[]
     * @param scores the list that need be sorted
     * @return the sorted list
     */
    public Score[] selectionSortScore(Score[] scores) {
        Score[] sortedArray = scores.clone();
        int times = 0;
        for (int i = 0; i < scores.length; i++) {
            int theIndex = 0;
            int largest = sortedArray[0].getScore();
            for (int j = 0; j < scores.length - times; j++) {
                if (largest < sortedArray[j].getScore()) {
                    largest = sortedArray[j].getScore();
                    theIndex = j;
                }
            }
            Score exchangeElement = sortedArray[scores.length-times-1];
            sortedArray[scores.length - times-1] = sortedArray[theIndex];
            sortedArray[theIndex] = exchangeElement;
            times++;
        }

        return sortedArray;
    }
}
