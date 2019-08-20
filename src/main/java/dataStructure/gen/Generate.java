package dataStructure.gen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Generate {

  static ExecutorService pool = Executors.newFixedThreadPool(10);

  public static void main(String[] args) {

    int size = 1;
    int[][] fullHistory = new int[size][7];
    try {
      fullHistory = load(size);
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<Future<Long>> result = new ArrayList<>();

    for (int i = 0; i < 10; i ++) {
      result.add(pool.submit(new GenTask(fullHistory, i, size)));
    }

    for (int i = 0; i < 10; i ++) {
      result.get(0);
    }

  }


  public static int[][] load(int size) throws IOException {
    // The name of the file to open.
    String fileName = "resource/LOTTOMAX.csv";
    // This will reference one line at a time
    String line;
    int[][] fullHistory = new int[size][7];

    BufferedReader bufferedReader = null;
    try {
      // FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(fileName);

      // Always wrap FileReader in BufferedReader.
      bufferedReader = new BufferedReader(fileReader);

      int counter=0;
      while((line = bufferedReader.readLine()) != null) {
        String[] raw = line.split(",");

        int[] current = new int[7];
        for (int j = 0; j < 7; j++) {
          current[j] = Integer.valueOf(raw[j + 3]);
        }

        fullHistory[counter] = current;
        if (counter < size - 1) {
          counter++;
        } else {
          break;
        }
      }

      bufferedReader.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch(IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    } finally {
      bufferedReader.close();
    }

    return fullHistory;
  }

}
