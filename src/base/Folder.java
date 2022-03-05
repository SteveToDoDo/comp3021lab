package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Folder implements Comparable<Folder>{
	
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
	
	public void sortNotes()
	{
		Collections.sort(notes);
	}
	
	public ArrayList<Note> searchNotes(String keywords){
		ArrayList<Note> notes= new ArrayList<Note>();
		if(keywords.isEmpty())
			return notes;
		else
		{
			String[] keywordsArr = keywords.split(" ");
			ArrayList<String> keywordsList = new ArrayList<String>(Arrays.asList(keywordsArr));
			//keywordsList.remove("OR");
			//keywordsList.remove("or");
			ArrayList<ArrayList<String>> keywordsListGroup = new ArrayList<ArrayList<String>>();
			
			ArrayList<String> temp_keywordsList = new ArrayList<String>();
			int OR_flag = 1;
			for(String keyword:keywordsList)
			{
				if(keyword.equals("OR")||keyword.equals("or"))
				{
					OR_flag+=1;
				}
				else
				{
					OR_flag-=1;
				}
				if(OR_flag==0)
				{
					temp_keywordsList.add(keyword);
				}
				if(OR_flag<0)
				{
					keywordsListGroup.add(temp_keywordsList);
					temp_keywordsList = new ArrayList<String>();
					temp_keywordsList.add(keyword);
					OR_flag +=1;
				}
			}
			if(!temp_keywordsList.isEmpty())
			{
				keywordsListGroup.add(temp_keywordsList);
			}
			
			for(Note n:this.notes)
			{
				boolean failed_case = false;
				for(ArrayList<String> list:keywordsListGroup)
				{
					boolean check_flag = false;
					for(String keyword:list)
					{
						if(n instanceof TextNote)
						{
							String title_lowercase = n.getTitle().toLowerCase();
							String content_lowercase = ((TextNote) n).getContent().toLowerCase();
							String keyword_lowercase = keyword.toLowerCase();
							if((title_lowercase.indexOf(keyword_lowercase)>=0)||(content_lowercase.indexOf(keyword_lowercase)>=0))
								check_flag=true;
						}
						if(n instanceof ImageNote)
						{
							String title_lowercase = n.getTitle().toLowerCase();
							String keyword_lowercase = keyword.toLowerCase();
							if(title_lowercase.indexOf(keyword_lowercase)>=0)
								check_flag=true;
						}
					}
					if(check_flag == false)
					{
						failed_case =true;
					}
				}
				if(!failed_case)
				{
					notes.add(n);
				}
			}
			return notes;
		}
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
	@Override 
	public int compareTo(Folder o)
	{
		return this.name.compareTo(o.name);
	}
	
}
