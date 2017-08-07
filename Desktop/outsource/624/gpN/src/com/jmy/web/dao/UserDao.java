package com.jmy.web.dao;

import java.util.List;

import com.jmy.web.entity.Note;
import com.jmy.web.entity.User;

public interface UserDao {
	/**
	 * select  user by userid
	 * @param userid
	 * @return 
	 */
	User findUserByUserID(String userid);
}
