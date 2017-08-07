package com.jmy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jmy.web.entity.Note;

public interface NoteDao {
	/**
	 * select  notes by userid
	 * @param userid
	 * @return 
	 */
	List<Note> findAllNote(String userid);
	/**
	 * add note
	 * @param note
	 * @return 
	 */
	int addNote(Note note);
	/**
	 * delete note
	 * @param noteid 
	 * @return true or false
	 */
	int deleteNote(String noteid);
	/**
	 * 
	 * @return
	 */
	Note findNoteByEnglish(@Param("english")String english,@Param("userid")String userid);
}
