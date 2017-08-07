package com.jmy.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jmy.web.entity.JObj;
import com.jmy.web.entity.Note;
import com.jmy.web.service.NoteServiceImpl;


@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	NoteServiceImpl noteServiceImpl;
	@ResponseBody
	@RequestMapping("/findAllNote.do")
	public JObj getemp(
			HttpServletRequest request,
			@RequestParam("userid")String userid
			) {
		JObj obj;
		List<Note> notes=noteServiceImpl.findAllNote(userid);
		if(notes==null||notes.size()<=0){
			obj=JObj.getErrorJobj("没有查到用户笔记");
			return obj;
		}
		obj=JObj.getSuccessJobj();
		obj.setData(notes);
		return obj;
	}
	@ResponseBody
	@RequestMapping("/addNote.do")
	public JObj addNote(
			@RequestParam("userid")String userid,
			@RequestParam("english")String english
			) {
		JObj obj;
		Note note=new Note(userid,english);
		if(noteServiceImpl.addNote(note)<=0){
			obj=JObj.getErrorJobj("添加笔记失败,用户id不存在或者此笔记已经添加过!");
			return obj;
		}
		obj=JObj.getSuccessJobj("添加笔记成功！");
		return obj;
	}
	@ResponseBody
	@RequestMapping("/deleteNote.do")//deleteNote
	public JObj deleteNote(
			@RequestParam("noteid")String noteid
		) {
		JObj obj;
		if(noteServiceImpl.deleteNote(noteid)<=0){
			obj=JObj.getErrorJobj("删除笔记失败！");
			return obj;
		}
		obj=JObj.getSuccessJobj("删除笔记成功！");
		return obj;
	}
}
