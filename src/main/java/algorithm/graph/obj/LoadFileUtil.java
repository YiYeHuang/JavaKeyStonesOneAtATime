package algorithm.graph.obj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFileUtil {


  public static List<Integer> loadInversions(String filePath) throws IOException {
    // The name of the file to open.
    String fileName = filePath;
    // This will reference one line at a time
    String line = null;
    List<Integer> result = new ArrayList<>();

    BufferedReader bufferedReader = null;
    try {
      // FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(fileName);

      // Always wrap FileReader in BufferedReader.
      bufferedReader = new BufferedReader(fileReader);

      int index = 0;
      while((line = bufferedReader.readLine()) != null) {
        result.add(Integer.valueOf(line));
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

    int[] finalResult = new int[result.size()];

//        for (int i =0; i < result.size();i++) {
//            finalResult[i] = result.get(i);
//        }

    return result;
  }

}
