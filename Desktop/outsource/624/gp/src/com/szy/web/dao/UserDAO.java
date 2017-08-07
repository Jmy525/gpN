package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.szy.web.model.PageMessage;
import com.szy.web.model.Product;
import com.szy.web.model.User;
import com.szy.web.util.TextUtility;

/**
 * @author coolszy
 * @time Dec 4, 2011 2:52:09 PM
 */
public class UserDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public UserDAO() throws IOException, ClassNotFoundException, SQLException {
		manager = SqlManager.createInstance();
	}

	/**
	 * 用户登录 验证用户名和密码是否正确
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws SQLException
	 */
	public int validate(String uname, String password) throws SQLException {
		int result = 0;
		sql = "select count(userid) from user_table where username=? and password=?";
		Object[] params = new Object[] { uname, password };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = 2;
		}
		manager.closeDB();
		return result;
	}

	/**
	 * 用户名否正存在
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws SQLException
	 */
	public int isexesit(String uname, String phone) throws SQLException {
		int result = 0;
		sql = "select * from user_table where username=?";
		Object[] params = new Object[] { uname };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = 1;
		}/* else {
			manager.closeDB();
			sql = "select * from user_table where phone=?";
			Object[] params1 = new Object[] { phone };
			manager.connectDB();
			rs = manager.executeQuery(sql, params1);
			if (rs.next()) {
				result = 2;
			}
		}*/
		manager.closeDB();
		return result;
	}

	/**
	 * 根据用户名更新登录时间和状态
	 * 
	 * @param uname
	 *            用户名
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean updatelogintime(String uname, long logintime)
			throws SQLException {
		sql = "UPDATE user_table set islogin = ?,logtime = ? where username =?";
		Object[] params = new Object[] { "1", logintime, uname };
		manager.connectDB();
		boolean issuccess = manager.executeUpdate(sql, params);
		manager.closeDB();
		return issuccess;
	}

	public boolean updatelogintime(String uname, long logintime,
			long installtime, long endtime) throws SQLException {
		sql = "UPDATE user_table set islogin = ?,logtime = ?,installtime = ?,endtime = ? where username =?";
		Object[] params = new Object[] { "1", logintime, installtime, endtime,
				uname };
		manager.connectDB();
		boolean issuccess = manager.executeUpdate(sql, params);
		manager.closeDB();
		return issuccess;
	}

	/**
	 * 注册用户
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码，电话，邮箱，注册时间
	 * @return
	 * @throws SQLException
	 */
	public int addUser(String username, String password, String phone,
			String imei, long regtime, long endtime, String platform)
			throws SQLException {
		int result = 0;
		int isexesit = isexesit(username, phone);
		if (isexesit == 1) {
			// 用户名存在
			result = 1;
		} else if (isexesit == 2) {
			// 电话存在
			result = 2;
		} else {
			sql = "INSERT INTO user_table(username,password,phone,imei,regtime,endtime,platform) VALUES (?,?,?,?,?,?,?)";
			Object[] params = new Object[] { username, password, phone, imei,
					regtime, endtime, platform };
			manager.connectDB();
			boolean result1 = manager.executeUpdate(sql, params);
			if (result1) {
				result = 3;
			} else {
				result = 0;
			}
		}
		manager.closeDB();
		return result;
	}

	/**
	 * 根据用户id获取登录时间
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws SQLException
	 */
	public User getInstallTimeByUid(String userid) throws SQLException {
		User result = null;
		sql = "select * from user_table where userid = ?";
		Object[] params = new Object[] { userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = new User();
			long installtime = rs.getLong("regtime");
			long endtime = rs.getLong("endtime");
			String username = rs.getString("username");
			String phone = rs.getString("phone");
			int islogin = rs.getInt("islogin");
			int ispay = rs.getInt("ispay");
			long paytime = rs.getLong("paytime");
			String avatar = rs.getString("avatar");
			result.setInstalltime(installtime);
			result.setEndtime(endtime);
			result.setIslogin(islogin);
			result.setUserid(Long.parseLong(userid));
			result.setUsername(username);
			result.setPhone(phone);
			result.setIspay(ispay);
			result.setPaytime(paytime);
			result.setAvatar(avatar);
		}
		manager.closeDB();
		return result;
	}

	public User getUserInfoByUName(String username) throws SQLException {
		User result = null;
		sql = "select * from user_table where username = ?";
		Object[] params = new Object[] { username };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = new User();
			long regtime = rs.getLong("regtime");
			long endtime = rs.getLong("endtime");
			String phone = rs.getString("phone");
			int islogin = rs.getInt("islogin");
			int ispay = rs.getInt("ispay");
			long userid = rs.getLong("userid");
			result.setRegtime(regtime);
			result.setEndtime(endtime);
			result.setIslogin(islogin);
			result.setUserid(userid);
			result.setUsername(username);
			result.setPhone(phone);
			result.setIspay(ispay);
		}
		manager.closeDB();
		return result;
	}

	/**
	 * 更改用户姓名
	 * 
	 * @param uname
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public boolean updateUserName(String uname, String userid)
			throws SQLException {
		boolean issuccess = false;
		sql = "select * from user_table where userid = ?";
		Object[] params = new Object[] { userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			manager.closeDB();
			sql = "UPDATE user_table set username = ? where userid =?";
			Object[] params_ = new Object[] { uname, userid };
			manager.connectDB();
			issuccess = manager.executeUpdate(sql, params_);
			manager.closeDB();
			return issuccess;
		} else {
			manager.closeDB();
			return issuccess;
		}

	}

	/**
	 * 更改用户密码
	 * 
	 * @param password
	 * @param phone
	 * @return 1表示成功 2表示用户不存在
	 * @throws SQLException
	 */
	public int updateUserPassword(String password, String phone)
			throws SQLException {
		int issuccess = 2;
		if (this.queryPhone(phone)) {
			// if (this.queryUserPassword(userid, oldPassword)) {
			sql = "UPDATE user_table set password = ? where phone =?";
			Object[] params_ = new Object[] { password, phone };
			manager.connectDB();
			manager.executeUpdate(sql, params_);
			manager.closeDB();
			issuccess = 1;
			/*
			 * } else { manager.closeDB(); issuccess = 3; }
			 */
			return issuccess;
		} else {
			manager.closeDB();
			return issuccess;
		}
	}

	/**
	 * 更改用户密码
	 * 
	 * @param password
	 * @param phone
	 * @return 1表示成功 2表示用户不存在
	 * @throws SQLException
	 */
	public int updateUserToken(long userId, String token)
			throws SQLException {
		int issuccess = 2;
			sql = "UPDATE user_table set token = ? where userid =?";
			Object[] params_ = new Object[] { token, userId };
			manager.connectDB();
			manager.executeUpdate(sql, params_);
			manager.closeDB();
			issuccess = 1;
			return issuccess;
	}
	/**
	 * 记录用户对应的产品信息
	 * 
	 * @param userid
	 * @param optional_buy
	 *            1 表示自选的语言或者方言 2表示购买的
	 * @param language_type
	 *            1表示外语 2表示方言
	 * @param language_name
	 *            语言名称
	 * @return 1表示成功 2表示失败
	 * @throws SQLException
	 */
	public int recodeProductAboutUser(String userid, String language_name,
			String optional_buy, String language_type,String language_money) throws SQLException {
		int issuccess = 2;
		if (this.queryUserId(userid)) {
			String pay_table_id = this.queryPay_tableUserId(userid,language_name);
			if(pay_table_id != null && !pay_table_id.isEmpty()) {
				if(this.updatePay_table(pay_table_id, optional_buy,language_type,language_money)) {
					issuccess = 1;
				}
			} else {
				if (this.savePay_table(userid, language_name, optional_buy,language_type,language_money)) {
					issuccess = 1;
				}
			}
			return issuccess;
		} else {
			return issuccess;
		}
	}

	/**
	 * 查询pay_table表中 这个用户购买的语言信息
	 * 
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public List<Product> queryAboutProduct(String userid) throws SQLException {
		List<Product> productList = new ArrayList<Product>();
		Date startDate = new Date();
		sql = "select DATE_FORMAT(startDate,'%Y-%m-%d %H:%i:%s') startDate,languageName,languageType,optionalBuy,languageMoney from pay_table where userid = ?";
		Object[] params = new Object[] { userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		String languageNames1="俄语,德语,日语,汉语,法语,英语,西班牙语,阿拉伯语,韩语";
		String languageNames2="四川话,天津话,山东话,山西话,河北话,河南话,浙江话,粤语,陕西话,湖北话,闽南话,东北话";
		String languageNames3="英汉词典,中德,中法,中阿,中韩,中英,中日,中俄,中西";
		//英国  日本     俄  西   
		boolean isBuied=false;
		while (rs.next()) {
			Product product = new Product();
			product.setLanguageName(rs.getString("languageName"));
			if(rs.getString("languageType")==null){
				if(rs.getString("languageName")!=null){
					if(languageNames1.indexOf(rs.getString("languageName"))>-1){
						product.setLanguageType("1");
					}else if(languageNames2.indexOf(rs.getString("languageName"))>-1){
						product.setLanguageType("2");
					}else if(languageNames3.indexOf(rs.getString("languageName"))>-1){
						product.setLanguageType("3");
					}else{
						product.setLanguageType("2");
					}
				}else{
					product.setLanguageType("2");
				}
			}else{
				product.setLanguageType(rs.getString("languageType"));
			}
			product.setOptionalBuy(rs.getString("optionalBuy"));
			product.setStartDate(rs.getString("startDate"));
			if(rs.getString("languageMoney")==null){
				product.setLanguageMoney("0");
			}else{
				product.setLanguageMoney(rs.getString("languageMoney"));
			}
			
			startDate = rs.getDate("startDate");
			long between = new Date().getTime() - startDate.getTime();
			//2是购买   1是分享（自定义）
			if(product.getOptionalBuy().equals("2")){//一年后过期
				if (between > (365 * 24 * 3600000L)) {
					// 过期 默认是 1年
					product.setIsProductOverdue("1");
				} else {
					// 不过期
					product.setIsProductOverdue("0");
				}
			}else{//7天后过期
				if (between >(30*24*3600000L)) {
					// 过期7天
					product.setIsProductOverdue("1");
				} else {
					// 不过期
					product.setIsProductOverdue("0");
				}
			}
			/*if(product.getOptionalBuy().equals("2")){//一年后过期
				if (between > (365 * 24 * 3600000)) {
					// 过期 默认是 1年
					product.setIsProductOverdue("1");
				} else {
					// 不过期
					product.setIsProductOverdue("0");
				}
				isBuied=true;
				product.setIsProductOverdue("0");
			}else{//7天后过期
				if (between >(7*24*3600000)) {
					// 过期7天
					product.setIsProductOverdue("1");
				} else {
					// 不过期
					product.setIsProductOverdue("0");
				}
				if (between > (365 * 24 * 3600000)) {
					// 过期 默认是 1年
					product.setIsProductOverdue("1");
				} else {
					// 不过期
					product.setIsProductOverdue("0");
				}
			}*/
			
			productList.add(product);
		}
		if(isBuied){
			for(Product p:productList){
				p.setIsProductOverdue("0");
			}
		}
		return productList;
	}

	/**
	 * 查询pay_table表中用户是否自定制或者购买了此种语言
	 * 
	 * @param userid
	 * @param language_name
	 * @return id pay_table的id
	 * @throws SQLException
	 */
	private String queryPay_tableUserId(String userid, String language_name)
			throws SQLException {
		String issuccess = null;
		sql = "select id from pay_table where userid = ? and languageName=?";
		Object[] params = new Object[] { userid, language_name };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			issuccess = rs.getString("id");
		}
		manager.closeDB();
		return issuccess;
	}

	/**
	 * 保存pay_table表中用户跟产品的对应关系
	 * 
	 * @param userid
	 * @param language_name
	 * @param optional_buy
	 *            1 免费（自己定制） 2购买
	 * @param language_type
	 * @return
	 * @throws SQLException
	 */
	private boolean savePay_table(String userid, String language_name,
			String optional_buy, String language_type,String language_money) throws SQLException {
		sql = "INSERT INTO pay_table(userid,startDate,languageName,languageType,optionalBuy,languageMoney) VALUES (?,?,?,?,?,?)";
		Object[] params = new Object[] { userid,
				TextUtility.formatDate(new Date()), language_name,
				language_type, optional_buy,language_money };
		manager.connectDB();
		boolean result = manager.executeSave(sql, params);
		manager.closeDB();
		return result;
	}
/*	private boolean savePay_table(String userid, String language_name,
			String optional_buy, String language_type, String language_money)
			throws SQLException {
		String languageNames1="俄语,德语,日语,汉语,法语,英语,西班牙语,阿拉伯语,韩语";
		String languageNames2="四川话,天津话,山东话,山西话,河北话,河南话,浙江话,粤语,陕西话,湖北话,闽南话,东北话";
		String languageNames3="英汉词典,中德,中法,中阿,中韩,中英,中日,中俄,中西";
		//如果购买则打开所有语言  @author jmy
		boolean result = false;
		//方案1   在查询到有付费记录  则开通所有权限
		sql = "INSERT INTO pay_table(userid,startDate,languageName,languageType,optionalBuy,languageMoney) VALUES (?,?,?,?,?,?)";
		Object[] params = new Object[] { userid,
				TextUtility.formatDate(new Date()), language_name,
				language_type, optional_buy, language_money };
		manager.connectDB();
		result = manager.executeSave(sql, params);
		manager.closeDB();
		//方案2  购买一项  添加所有
		if (optional_buy.equals("1")) {
			sql = "INSERT INTO pay_table(userid,startDate,languageName,languageType,optionalBuy,languageMoney) VALUES (?,?,?,?,?,?)";
			Object[] params = new Object[] { userid,
					TextUtility.formatDate(new Date()), language_name,
					language_type, optional_buy, language_money };
			manager.connectDB();
			result = manager.executeSave(sql, params);
			manager.closeDB();
		} else if(optional_buy.equals("2")){
			sql = "INSERT INTO pay_table(userid,startDate,optionalBuy,languageMoney,languageName,languageType) VALUES ";
			//打开外语1
			String[] waiyu=languageNames1.split(",");
			for(int i=0;i<waiyu.length;i++){
				String content1="('"+userid+"','"+TextUtility.formatDate(new Date())+"','"+optional_buy+"','"+language_money+"','"+waiyu[i]+"','"+"1'"+"),";
				sql+=content1;
			}
			//打开方言2
			String[] fangyan=languageNames2.split(",");
			for(int i=0;i<fangyan.length;i++){
				String content2="('"+userid+"','"+TextUtility.formatDate(new Date())+"','"+optional_buy+"','"+language_money+"','"+fangyan[i]+"','"+"2'"+"),";
				sql+=content2;
			}
			//打开其他：
			String[] qita=languageNames3.split(",");
			for(int i=0;i<qita.length;i++){
				String content3="";
				if(i==qita.length-1){
					content3="('"+userid+"','"+TextUtility.formatDate(new Date())+"','"+optional_buy+"','"+language_money+"','"+qita[i]+"','"+"3'"+")";
				}else{
					content3="('"+userid+"','"+TextUtility.formatDate(new Date())+"','"+optional_buy+"','"+language_money+"','"+qita[i]+"','"+"3'"+"),";
				}
				sql+=content3;
			}
			Object[] params = new Object[] {};
			manager.connectDB();
			System.out.println("拼接好的"+sql);
			result = manager.executeSave(sql, params);
			manager.closeDB();
		}
		return result;
	}*/

	/**
	 * 更新pay_table表中用户跟产品的对应关系
	 * 
	 * @param userid
	 * @param wy
	 * @param fy
	 * @return issuccess 0 表示失败 1表示成功
	 * @throws SQLException
	 */
	private boolean updatePay_table(String id, String optional_buy,String language_type,String language_money)
			throws SQLException {
		boolean issuccess = false;

		sql = "UPDATE pay_table set optionalBuy=?,startDate=?,languageType=?,languageMoney=? where id =?";
		Object[] params_ = new Object[] { optional_buy,TextUtility.formatDate(new Date()),language_type,language_money,id};
		manager.connectDB();
		manager.executeUpdate(sql, params_);
		manager.closeDB();
		issuccess = true;
		return issuccess;
	}

	/**
	 * 查询对应的用户是否存在
	 * 
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	private boolean queryUserId(String userid) throws SQLException {
		boolean issuccess = false;
		sql = "select userid from user_table where userid = ?";
		Object[] params = new Object[] { userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			issuccess = true;
		}
		manager.closeDB();
		return issuccess;
	}

	/**
	 * 查询对应的用户是否存在 根据用户的电话去确认用户是否存在
	 * 
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	private boolean queryPhone(String phone) throws SQLException {
		boolean issuccess = false;
		sql = "select userid from user_table where phone = ?";
		Object[] params = new Object[] { phone };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			issuccess = true;
		}
		manager.closeDB();
		return issuccess;
	}

	/**
	 * 查询对应用户的密码是否正确
	 * 
	 * @param userid
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	private boolean queryUserPassword(String userid, String password)
			throws SQLException {
		boolean issuccess = false;
		sql = "select userid from user_table where password=? and userid=?";
		Object[] params = new Object[] { password, userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			issuccess = true;
		}
		manager.closeDB();
		return issuccess;
	}

	/**
	 * 添加用户头像位置信息
	 * 
	 * @param userid
	 * @param avatarPosition
	 * @throws SQLException
	 */
	public boolean addUserAvatar(String userid, String avatarPosition)
			throws SQLException {
		boolean issuccess = false;
		sql = "UPDATE user_table set avatar = ? where userid =?";
		Object[] params_ = new Object[] { avatarPosition, userid };
		manager.connectDB();
		issuccess = manager.executeUpdate(sql, params_);
		manager.closeDB();
		return issuccess;
	}

	/**
	 * 页面查询客户信息，管理后台，通过一个页面了解注册用户的基本信息
	 * 
	 * @param startDate
	 * @param endDate
	 * @param name
	 * @param phone
	 * @throws SQLException
	 * @return
	 */
	public List<PageMessage> queryPage(String startDate, String endDate,
			String name, String phone) throws SQLException {

		String case1 = "";
		String case2 = "";
		String case3 = "";
		if (startDate != null && endDate != null && !startDate.isEmpty()
				&& !endDate.isEmpty()) {
			case1 = "and substring_index(from_unixtime(substring(u.regtime,1,10)), '.', 1)>='"
					+ startDate
					+ "'"
					+ " and substring_index(from_unixtime(substring(u.regtime,1,10)), '.', 1)<='"
					+ endDate + "' ";
		}
		if (name != null && !name.isEmpty()) {
			case2 = " and u.username like '%" + name + "%' ";
		}
		if (phone != null && !phone.isEmpty()) {
			case3 = " and u.phone ='" + phone + "' ";
		}
		String sql = "SELECT "
				+ "u.username, "
				+ "u.phone, "
				+ "substring_index(from_unixtime(substring(u.regtime,1,10)), '.', 1)regtime, "
				+ "u.platform, "
				+ "t.languageName, "
				+ "IF(t.optionalBuy is not null and t.optionalBuy=1,'免费','') free, "
				+ "IF(t.optionalBuy=2,20,'') money, " + "t.startDate, "
				+ "DATE_ADD(t.startDate,INTERVAL 1 YEAR) endDate " + "FROM "
				+ "user_table u LEFT JOIN " + "pay_table t " + "ON "
				+ "u.userid = t.userid " + "WHERE " + "u.username is not null "
				+ case1 + case2 + case3 + "ORDER BY u.regtime DESC";
		System.out.println("查询页面信息的sql=" + sql);
		Object[] params = new Object[] {};
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		List<PageMessage> result = new ArrayList<PageMessage>();
		while (rs.next()) {
			PageMessage pageMessage = new PageMessage();
			pageMessage.setStartDate(rs.getString("startDate")==null?"":rs.getString("startDate"));
			pageMessage.setEndDate(rs.getString("endDate")==null?"":rs.getString("endDate"));
			pageMessage.setFree(rs.getString("free")==null?"":rs.getString("free"));
			pageMessage.setLanguage(rs.getString("languageName")==null?"":rs.getString("languageName"));
			pageMessage.setName(rs.getString("username")==null?"":rs.getString("username"));
			pageMessage.setPayMoney(rs.getString("money")==null?"":rs.getString("money"));
			pageMessage.setPhone(rs.getString("phone")==null?"":rs.getString("phone"));
			pageMessage.setPlatform(rs.getString("platform")==null?"":rs.getString("platform"));
			pageMessage.setRegisterDate(rs.getString("regtime")==null?"":rs.getString("regtime"));
			result.add(pageMessage);
		}
		manager.closeDB();
		return result;
	}
	
	public User getUserInfoByOpenid(String openid) throws SQLException {
		User result = null;
		sql = "select * from user_table where openid = ?";
		Object[] params = new Object[] { openid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = new User();
			long regtime = rs.getLong("regtime");
			long endtime = rs.getLong("endtime");
			String phone = rs.getString("phone");
			int islogin = rs.getInt("islogin");
			int ispay = rs.getInt("ispay");
			long userid = rs.getLong("userid");
			result.setRegtime(regtime);
			result.setEndtime(endtime);
			result.setIslogin(islogin);
			result.setUserid(userid);
			result.setUsername(rs.getString("username"));
			result.setPhone(phone);
			result.setIspay(ispay);
		}
		manager.closeDB();
		return result;
	}
	
	
	public User getUserInfoByUserid(String userid) throws SQLException {
		User result = null;
		sql = "select * from user_table where userid = ?";
		Object[] params = new Object[] { userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = new User();
			long regtime = rs.getLong("regtime");
			long endtime = rs.getLong("endtime");
			String phone = rs.getString("phone");
			int islogin = rs.getInt("islogin");
			int ispay = rs.getInt("ispay");
			result.setRegtime(regtime);
			result.setEndtime(endtime);
			result.setIslogin(islogin);
			result.setUsername(rs.getString("username"));
			result.setPhone(phone);
			result.setIspay(ispay);
			result.setToken(rs.getString("token"));
		}
		manager.closeDB();
		return result;
	}
	/**
	 * 用户登录 验证openid是否正确
	 * 
	 * @param openid
	 *           微信公众帐号
	 * @return
	 * @throws SQLException
	 */
	public int saveOrUpdate(String openid,String username,String loginType) throws SQLException {
		int result = 0;
		sql = "select count(userid) as c from user_table where openid=?";
		Object[] params = new Object[] {openid};
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			if(rs.getInt("c")!=0){
				result = 2;
			}else{
				sql = "INSERT INTO user_table(openid,username,regtime,endtime) VALUES (?,?,?,?)";
				long regtime = System.currentTimeMillis();
				params = new Object[] {openid,username,regtime,regtime};
				boolean result1 = manager.executeUpdate(sql, params);
				if (result1) {
					result = 2;
				} else {
					result = 0;
				}
			}
		}else{
			sql = "INSERT INTO user_table(openid,username,regtime,endtime) VALUES (?,?,?,?)";
			long regtime = System.currentTimeMillis();
			params = new Object[] {openid,username,regtime,regtime};
			boolean result1 = manager.executeUpdate(sql, params);
			if (result1) {
				result = 2;
			} else {
				result = 0;
			}
		}
		manager.closeDB();
		return result;
	}
}
