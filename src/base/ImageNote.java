package base;

import java.nio.file.Files;


public class ImageNote extends Note  {
	private static final long serialVersionUID = 1L;
	public Files image;
	
	public ImageNote(String title){
		super(title);
	}

}
