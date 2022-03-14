package base;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Collections;

public class NoteBook implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;

	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	public NoteBook(String file) throws IOException, ClassNotFoundException{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		fis = new FileInputStream(file);
		in = new ObjectInputStream(fis);
		NoteBook n = (NoteBook) in.readObject();
		this.folders = n.getFolders();
		in.close();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}


	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title,content);
		return insertNote(folderName,note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);

	}
	public ArrayList<Folder> getFolders() {
		return folders;
	} 
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1 : folders) {
	       if(f1.getName()==folderName) {
	    	   f=f1;
	    	   break;
	       }
		}

		if (f == null) {
			
			f= new Folder(folderName);
			folders.add(f);
        }
		for (Note n : f.getNotes()) {
			
           if(n.equals(note))
           {
        	   System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
        	   return false;
           }
		}
		f.addNote(note);
		return true;

	}
	
	public void sortFolders()
	{
		for(Folder f: folders)
			f.sortNotes();
		Collections.sort(folders);
	}
	
	public ArrayList<Note> searchNotes(String keywords)
	{
		ArrayList<Note> notes= new ArrayList<Note>();
		for(Folder f:folders)
		{
			notes.addAll(f.searchNotes(keywords));
		}
		return notes;
	}
	
	public boolean save(String file)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
			
		return true;

	}
}

