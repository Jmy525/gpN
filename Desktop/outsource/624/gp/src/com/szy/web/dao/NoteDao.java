package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.szy.web.model.Language;
import com.szy.web.model.Note;
/**
 * 个人笔记
 * 包括添加笔记
 * 查看笔记
 * @author Administrator
 * 
 */
public class NoteDao {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public NoteDao() throws IOException, ClassNotFoundException {
		manager = SqlManager.createInstance();
	}//添加笔记
	public boolean addNote(String userid,String english)throws SQLException{
		sql="insert into notes(userid,english)values(?,?)";
		Object[] params = new Object[]{userid,english};
		manager.connectDB();
		//System.out.println(sql);
		boolean b=manager.executeSave(sql, params);
		manager.closeDB();
		return b;
	}//查询笔记
	public List<Note> getNote(String userid)throws SQLException{
		List<Note> list=new ArrayList<Note>();
		sql="select * from notes where userid like ?";
		Object[] params = new Object[]{userid};
		manager.connectDB();
		//System.out.println(sql);
		rs = manager.executeQuery(sql, params);
		while(rs.next()){
			Note note=new Note();
			note.setId(rs.getString("id"));
			note.setUserid(rs.getString("userid"));
			note.setEnglish(rs.getString("english"));
			list.add(note);
		}
		manager.closeDB();
		return list;
	} 
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		NoteDao dao=new NoteDao();
		boolean l=dao.addNote("123", "abc");
		System.out.println(l);
		List<Note> list=dao.getNote("123");
		System.out.println("dsad"+Arrays.toString(list.toArray()));
	}
}
