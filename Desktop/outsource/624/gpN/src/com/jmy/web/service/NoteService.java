package com.jmy.web.service;

import java.util.List;

import com.jmy.web.entity.Note;

public interface NoteService {
	/**
	 * search notes
	 * @param userid
	 * @return 
	 */
	public List<Note> findAllNote(String userid);
	/**
	 * add note
	 * @param note
	 * @return 
	 */
	public int addNote(Note note);
	/**
	 * delete note
	 * @param noteid 
	 * @return 
	 */
	public int deleteNote(String noteid);

}
