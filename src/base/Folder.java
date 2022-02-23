package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {
	
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public String getName(){
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	//<Folder Name> : <Number of Text Notes> : <Number of Image Notes>
	public String toString()
	{
		int nText = 0;
		int nImage = 0;
		//TODO
		for (Note note:notes)
		{
			if(note instanceof TextNote)
			{
				nText +=1;
			}
			if(note instanceof ImageNote)
			{
				nImage +=1;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
}