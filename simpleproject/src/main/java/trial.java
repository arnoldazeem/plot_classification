import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by adaboo on 3/9/18.
 */
public class trial {

        public static void main(String[] args) throws IOException {

            String fileName = "3.csv";
            String line = null;
            List<String[]> content = new ArrayList<>();

            final BufferedReader input =  new BufferedReader(new FileReader(fileName));

            while (( line = input.readLine()) != null){
               content.add(line.split(","));

            }
            int i =0;

            for(String [] list :content){
                String numberic = Arrays.toString(list);
                String num2 = numberic.replaceAll("[^\\d]","");
                System.out.println(num2);


            }



        }
}
