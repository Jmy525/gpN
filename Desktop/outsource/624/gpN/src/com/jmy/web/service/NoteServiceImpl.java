package com.jmy.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.jmy.web.dao.NoteDao;
import com.jmy.web.dao.UserDao;
import com.jmy.web.entity.JObj;
import com.jmy.web.entity.Note;
import com.jmy.web.entity.User;
@Service
public class NoteServiceImpl implements NoteService{
	@Autowired
	private NoteDao noteDao;
	@Autowired
	private UserDao userDao;
	@Override
	public List<Note> findAllNote(String userid) {
		if(null==userid&&"".equals(userid)){
			return null;
		}
		return noteDao.findAllNote(userid);
	}
	@Override
	public int addNote(Note note) {
		if(userDao.findUserByUserID(note.getUserid())!=null){
			if(noteDao.findNoteByEnglish(note.getEnglish(),note.getUserid())==null){
				return noteDao.addNote(note);
			}
		}
		return 0;
	}
	@Override
	public int deleteNote(String noteid) {
		return noteDao.deleteNote(noteid);
	}
}
