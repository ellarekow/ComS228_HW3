package homework3;

import java.io.*;
import java.util.*;
/**
 * 
 * @author Ella Rekow
 *
 */
public class Homework3 {
	public static void main(String[] args) throws IOException {
		try {
			//files for project
			File input = new File("input.txt");
			FileOutputStream output = new FileOutputStream("output.txt");
			
			//scanners and writers
			Scanner reader = new Scanner(input);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

			//goes through each line and does the conversion 
			while(reader.hasNextLine()) {
				String in = reader.nextLine();
				InToPost postCreator = new InToPost(in);
				
				//writes to file 
				writer.write(postCreator.getPostFix() + "\n");
			}
			
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
////		
//		InToPost post = new InToPost("2 * 3 / 12 - -16");
//		System.out.println(post.getPostFix());
		
	}
}
