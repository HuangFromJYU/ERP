package edu.jyu.erp.auth.emp.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.jyu.erp.auth.dep.vo.DepModel;
import edu.jyu.erp.auth.role.vo.RoleModel;
import edu.jyu.erp.util.format.FormatUtil;

/**
 * 员工，也就是用户了
 * 
 * @author Jason
 *
 */
public class EmpModel {
	public static final String EMP_LOGIN_USER_OBJECT_NAME = "loginEm";

	// 数据结构思想应用
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;

	public static final String EMP_GENDER_OF_MAN_VIEW = "男";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "女";

	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	static {
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	private Long uuid;
	/** 用户名 */
	private String userName;
	/** 员工姓名 */
	private String name;
	/** 密码 */
	private String pwd;
	/** 邮箱 */
	private String email;
	/** 电话 */
	private String tele;
	/** 地址 */
	private String address;

	/** 最后登录IP地址 */
	private String lastLoginIp;

	/** 登录总次数 */
	private Integer loginTimes;
	/** 最后登录时间 */
	private Long lastLoginTime;
	/** 性别 */
	private Integer gender;
	/*
	 * //Long:记录的是毫秒值 //Date:对long的包装 优点：格式好，缺点：计算时间略有复杂性 现在的时间是2020年4月31日
	 * 180天前是几号？ 现在的long System.currentTimeMillis()-180*24*60*60*1000 long-long
	 * >0 Date 2014年1月4日 14：21 Date 2014年1月4日 14：22
	 */
	private Long birthday;

	// 视图值：视图值是一种用于界面显示的变量值，该值不具体对应某个数据库字段，它服务于某个数据库字段
	// 当数据库中的某个字段值不便于直接显示时，为该字段添加视图值，用于显示对应的信息
	// 1.定义一个String类型的变量，变量名是无法合理显示的字段的字段名+View
	// 2.提供其get方法
	// 3.在其对应的变量的set方法中对这个View值进行初始化
	private String birthdayView;
	private String genderView;
	private String lastLoginTimeView;

	// 辅助值
	private String resAll;

	// 多对一
	private DepModel dm;
	// 对角色多对多
	private Set<RoleModel> roles;

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public String getResAll() {
		return resAll;
	}

	public void setResAll(String resAll) {
		this.resAll = resAll;
	}

	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}

	public String getGenderView() {
		return genderView;
	}

	public String getBirthdayView() {
		return birthdayView;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView = FormatUtil.formatDate(birthday);
	}

	public DepModel getDm() {
		return dm;
	}

	public void setDm(DepModel dm) {
		this.dm = dm;
	}

}
