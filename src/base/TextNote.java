package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note {
	private static final long serialVersionUID = 1L;
	public String content;
	
	public TextNote(String title){
		super(title);
	}
	public TextNote(File f) throws IOException {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}
	private String getTextFromFile(String absolutePath) throws IOException {
		String result = "";
		
        InputStreamReader reader = new InputStreamReader(new FileInputStream(absolutePath));
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        while ((line=br.readLine()) != null)
        {
        	result.concat(line);
        	result.concat("\n");
        }
		
        br.close();
		return result;
	}
	public void exportTextToFile(String pathFolder) throws IOException {
		
		String fileTitle = this.getTitle();
		fileTitle=fileTitle.replaceAll(" ", "_");
		BufferedWriter bw = null;
		FileWriter fw = null;
		File file = null;
		if(pathFolder.equals(""))
		{
			file = new File( fileTitle+ ".txt");
		}
		else
		{
			file = new File( pathFolder + File.separator +fileTitle+ ".txt");
		}

		fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write(this.content);
        bw.close();
	}

}
