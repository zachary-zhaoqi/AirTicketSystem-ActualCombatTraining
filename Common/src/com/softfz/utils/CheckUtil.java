package com.softfz.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 信息检查
 * 
 * @author Administrator
 * 
 */
public class CheckUtil {
	/**
	 * 校验正确
	 */
	public static final int OK = 999;
	
	
	/**
	 * 年月不能超过 6位 如 201308
	 */
	private static final int YEAR_LONG =-501;
	/**
	 * 年月不能含有字母和其他字符
	 */
	private static final int YEAR_ERROR =-502;
	/**
	 * 月份要在1~12之间
	 */
	private static final int YEAR_FARMOT =-503;
	/**
	 * 登录ID不能为空
	 */
	public static final int ID_NOT_NULL = -200;
	/**
	 * 帐号要求数字和字母组合长度为8位
	 */
	public static final int ID_ERROR = -201;

	/**
	 * 密码不能为空
	 */
	public static final int PWD_NOT_NULL = -110;
	/**
	 * 密码格式错误，需长度3~16位的字母、数字和_的任意组合
	 */
	public static final int PWD_ERROR = -111;
	/**
	 * 两密码不相等
	 */
	public static final int TWOPWD_ERROR = -120;

	/**
	 * IP地址不能为空
	 */
	public static final int IP_NOT_NULL = -180;
	/**
	 * IP地址格式错误
	 */
	public static final int IP_ERROR = -181;
	/**
	 * IP地址的值太大，必须在0~255之间
	 */
	public static final int IP_NUM_TOO_LARGE = -182;
	/**
	 * IP地址的值太小，必须在0~255之间
	 */
	public static final int IP_NUM_TOO_SMALL = -183;

	/**
	 * 端口号不能为空
	 */
	public static final int PORT_NOT_NULL = -190;
	/**
	 * 端口号格式错误
	 */
	public static final int PORT_ERROR = -191;
	/**
	 * 端口号的值太小，必须在1024~65535之间
	 */
	public static final int PORT_NULL_TOO_SMALL = -192;
	/**
	 * 端口号的值太大，必须在1024~65535之间
	 */
	public static final int PORT_NULL_TOO_LARGE = -193;

	/**
	 * 用户名真实姓名不能为空
	 */
	public static final int CUSTNAME_NOT_NULL = -100;
	/**
	 * 真实姓名必须是2~10之间的中文
	 */
	public static final int CUSTNAME_ERROR = -101;
    /**
     * 电话号码为空
     */
	private static final int PHONE_NOT_NULL = -230;
	/**
	 * 电话号码不足
	 */
	private static final int PHONE_NOT_ENOUGH = -231;
	/**
	 * 电话号码错误
	 */
	private static final int PHONE_ERROR = -232;
	/**
	 * 身份证不能为空
	 */
	private static final int IDCARD_NOT_NULL = -241;
	/**
	 * 身份证不足
	 */
	private static final int IDCARD_NOT_ENOUGH = -242;
	/**
	 * 身份证号码错误
	 */
	private static final int IDCARD_ERROR = -243;

	/**
	 * 地址不能为空
	 */
	public static final int ADDRESS_NOT_NULL = -140;
	/**
	 * 地址不超过50位
	 */
	public static final int ADDRESS_TOO_LONG = -141;

	/**
	 * 邮箱不能不空
	 */
	public static final int EMAIL_NOT_NULL = -300;
	/**
	 * 邮箱错误
	 */
	public static final int EMAIL_ERROR = -301;
	/**
	 * 时间不能为空
	 */
	private static final int  TIME_NO_NULL =-401;
	/**
	 * 时间超过了5位
	 */
	private static final int TIME_LONG_ERRER=-402;
	/**
	 * 时间小时数要在0~59之间
	 */
	private static final int TIME_MI_OUT=-403;
	/**
	 *  时间分钟数要在0~59之间
	 */
	private static final int TIME_SS_OUT=-404;
	/**
	 *  小时与分钟之间少了“ ：”
	 */
	private static final int FATMOT_ERROR=-405;
	/**
	 * 时间不能包含字母和其他字符，除 “ ：”，且“：”是英文字符
	 */
	private static final int  TIME_TYPE_ERROR =-406;
	
	private static final int DATEDAY_NOT_NULL = -1;
	private static final int DATEDAY_ERROR = -2;
	
	//增加航班时用到的错误信息
	private static final int FLIGHTNO_NOT_NULL = 139;
	private static final int FLIGHTNO_ERROR = 591;
	private static final int FROMCITY_NOT_NULL = 912;
	private static final int TOCITY_NOT_NULL = 84;
	private static final int FLIGHTTYPE_NOT_NULL = 135;
	private static final int STOPCITY_NOT_NULL = 891;
	private static final int NUMBER_NOT_NULL1 = 23;
	private static final int NUMBER_ERROR1 = 404;
	private static final int NUMBER_NOT_NULL2 = 230;
	private static final int NUMBER_ERROR2 = 4040;
	
	//系统管理后台用到的错误信息
	//新增用户错误信息
	private static final int SYSUSERNAME_NOT_NULL = 330;
	private static final int SYSUSERNAME_ERROR = 331;
	
	//新增销售网点错误信息
	private static final int NETCODE_NOT_NULL = 260;
	private static final int NETCODE_ERROR = 261;
	private static final int NETNAME_NOT_NULL = 262;
	private static final int NETDIRECTOR_NOT_NULL = 263;
	
	//服务器“数据库连接”面板错误信息
	private static final int SID_NOT_NULL = 460;
	private static final int DBNAME_NOT_NULL = 461;
	private static final int DBPASSWORD_NOT_NULL = 462;
	
	
	/**
	 * 错误编号所对应的错误信息
	 */
	
	public static Map<Integer, String> ERRMsgMap;
	static {
		
		ERRMsgMap = new HashMap<Integer, String>();
		ERRMsgMap.put(OK, "OK");
		
		
		ERRMsgMap.put(YEAR_LONG,"年月要 6位 如 201308");
		ERRMsgMap.put(YEAR_ERROR," 年月不能含有字母和其他字符，且第一位不能是0");
		ERRMsgMap.put(YEAR_FARMOT,"月份要在1~12之间");
		
		ERRMsgMap.put(TIME_LONG_ERRER, "时间要5位，格式为 00:00");
		ERRMsgMap.put(TIME_NO_NULL, "不能为空");
		ERRMsgMap.put(TIME_MI_OUT, "小时数要在0~24之间");
		ERRMsgMap.put(TIME_SS_OUT, "分钟数要在0~59之间");
		ERRMsgMap.put(FATMOT_ERROR, " 小时与分钟之间少了“ ：”");
		ERRMsgMap.put(TIME_TYPE_ERROR, "不能包含字母和其他字符，除 “ ：”，且“：”是英文字符");

		ERRMsgMap.put(ID_NOT_NULL, "登录ID不能为空");
		ERRMsgMap.put(ID_ERROR, "帐号要求数字和字母组合长度为8位");

		ERRMsgMap.put(PWD_NOT_NULL, "密码不能为空");
		ERRMsgMap.put(PWD_ERROR, "密码格式错误，需数字和字母组合长度在6-12位之间");
		ERRMsgMap.put(TWOPWD_ERROR, "两次输入的密码不相同，请重新确认新密码");

		ERRMsgMap.put(IP_NOT_NULL, "IP地址不能为空");
		ERRMsgMap.put(IP_ERROR, "IP地址格式错误");
		ERRMsgMap.put(IP_NUM_TOO_LARGE, "IP地址的值太大，必须在0~255之间");
		ERRMsgMap.put(IP_NUM_TOO_SMALL, "IP地址的值太小，必须在0~255之间");

		ERRMsgMap.put(PORT_NOT_NULL, "端口号不能为空");
		ERRMsgMap.put(PORT_ERROR, "端口号格式错误");
		ERRMsgMap.put(PORT_NULL_TOO_SMALL, "端口号的值太小，必须在1024~65535之间");
		ERRMsgMap.put(PORT_NULL_TOO_LARGE, "端口号的值太大，必须在1024~65535之间");

		ERRMsgMap.put(CUSTNAME_NOT_NULL, "姓名不能为空");
		ERRMsgMap.put(CUSTNAME_ERROR, "姓名必须是2~10之间的中文");

		ERRMsgMap.put(PHONE_NOT_NULL, "电话不能为空");
		ERRMsgMap.put(PHONE_NOT_ENOUGH, "电话号码应为11位");
		ERRMsgMap.put(PHONE_ERROR, "电话号码格式错误，号码应为数字");

		ERRMsgMap.put(IDCARD_NOT_NULL, "身份证号码不能为空");
		ERRMsgMap.put(IDCARD_NOT_ENOUGH, "身份证号码应为18位");
		ERRMsgMap.put(IDCARD_ERROR, "身份证号码应全为数字或最后一位为X");

		ERRMsgMap.put(ADDRESS_NOT_NULL, "地址不能为空");
		ERRMsgMap.put(ADDRESS_TOO_LONG, "地址应为50位");
		ERRMsgMap.put(EMAIL_NOT_NULL, "邮箱不能为空");
		ERRMsgMap.put(EMAIL_ERROR, "邮箱格式错误,应为 :数字或字母@字母.com");
		
		ERRMsgMap.put(DATEDAY_NOT_NULL, "日期不能为空");
		ERRMsgMap.put(DATEDAY_ERROR, "日期格式错误，应该为yyyy-MM-dd");
		
		
		ERRMsgMap.put(FLIGHTNO_NOT_NULL, "航班编号不能为空");
		ERRMsgMap.put(FLIGHTNO_ERROR, "航班编号必须由字母或数字的组合");
		ERRMsgMap.put(FROMCITY_NOT_NULL, "出发城市不能为空");
		ERRMsgMap.put(TOCITY_NOT_NULL, "到达城市不能为空");
		
		ERRMsgMap.put(FLIGHTTYPE_NOT_NULL, "航班类型不能为空");
		ERRMsgMap.put(STOPCITY_NOT_NULL, "经停城市不能为空");
		ERRMsgMap.put(NUMBER_NOT_NULL1, "“票面价格、航班里程、座位数量”都为必填项");
		ERRMsgMap.put(NUMBER_ERROR1, "“票面价格、航班里程、座位数量”都由0~9数字组成，不带小数点");
		ERRMsgMap.put(NUMBER_NOT_NULL2, "“经停票面价格、经停起飞票价、经停里程数”都为必填项");
		ERRMsgMap.put(NUMBER_ERROR2, "“经停票面价格、经停起飞票价、经停里程数”都由0~9数字组成，不带小数点");
		
		ERRMsgMap.put(SYSUSERNAME_NOT_NULL, "管理员账号不能为空");
		ERRMsgMap.put(SYSUSERNAME_ERROR, "管理员账号由数字和字母组合，长度3-12位之间");
		
		ERRMsgMap.put(NETCODE_NOT_NULL, "销售网点编号不能为空");
		ERRMsgMap.put(NETCODE_ERROR, "销售网点编号由数字和字母组合，长度8位");
		ERRMsgMap.put(NETNAME_NOT_NULL, "销售网点名称不能为空");
		ERRMsgMap.put(NETDIRECTOR_NOT_NULL, "销售网点负责人不能为空");
		
		ERRMsgMap.put(SID_NOT_NULL, "SID不能为空");
		ERRMsgMap.put(DBNAME_NOT_NULL, "账号不能为空");
		ERRMsgMap.put(DBPASSWORD_NOT_NULL, " 密码不能为空");
		

	}

	/**
	 * 检查用户id 先判登录帐号是否为null或者长度为0，如果是返回ID_NOT_NULL。
	 * 再判读帐号的长度是否为8位，如果不符合返回ID_ERROR。 如果都符合返回OK。
	 * 
	 * @param id
	 *    用户id
	 * @return 返回校验信息
	 */
	public static int checkId(String id) {
		if (id == null || id.length() == 0) {
			return ID_NOT_NULL;
		} else if (!(id.matches("[a-zA-Z0-9]{8}"))) {
			return ID_ERROR;
		} else {
			return OK;
		}
	}

	/**
	 * 检查密码 先判断密码是否为null或者长度为0，如果是返回PWD_NOT_NULL。
	 * 再判断密码格式是否符合要求，数字和字母组合长度在6-12位之间，如果不符合返回PWD_ERROR。 如果都符合返回OK。
	 * 
	 * @param pwd
	 *            用户密码
	 * @return 返回校验信息
	 */
	public static int checkPwd(String pwd) {
		if (pwd == null || pwd.length() == 0) {
			return PWD_NOT_NULL;
		} else if (!(pwd.matches("[a-zA-Z0-9]{6,12}"))) {
			return PWD_ERROR;
		} else {
			return OK;
		}
	}

	/**
	 * 判断两密码是否相等，两密码比较返回0 时说明相等
	 * 
	 * @param pwd1
	 *            密码一
	 * @param pwd2
	 *            密码二
	 * @return 返回校验信息
	 */
	public static int checkTwoPwd(String pwd1, String pwd2) {
		if (pwd1.compareTo(pwd2) != 0) {
			return TWOPWD_ERROR;
		} else {
			return OK;
		}
	}

	/**
	 * 检查ip地址 先判断IP地址是否为null或者长度为0，如果是返回IP_NOT_NULL。
	 * 判断IP地址中的数值是否在0～255之间，如果不符合返回IP_NUM_TOO_SMALL、IP_NUM_TOO_LARGE。
	 * 判断是否符合IP格式，返回IP_ERROR。 如果都符合返回OK。
	 * 
	 * @param ip
	 *            ip地址
	 * @return 返回校验信息
	 */
	public static int checkIp(String ip) {
		if (ip == null || ip.length() == 0) {
			return IP_NOT_NULL;
		}
		if (!(ip.matches("([0-9]{1,3}\\.){3}[0-9]{1,3}"))) {
			return IP_ERROR;
		}
		String[] strs = ip.split("\\.");
		for (String string : strs) {
			if (Integer.parseInt(string) < 0) {
				return IP_NUM_TOO_SMALL;
			}
			if (Integer.parseInt(string) > 255) {
				return IP_NUM_TOO_LARGE;
			}
		}
		return OK;
	}

	/**
	 * 检查端口号 先判断端口号是否为null或者长度为0，如果是返回PORT_NOT_NULL。
	 * 再判断端口号的值是否在1024～65535之间，如果不符合返回PORT_NUM_TOO_SMALL、PORT_NUM_TOO_LARGE。
	 * 如果不符合其他要求，返回PORT_ERROR。 如果都符合返回OK。
	 * 
	 * @param portStr
	 *            端口号
	 * @return 返回校验信息
	 */
	public static int checkPort(String portStr) {
		if (portStr == null || portStr.length() == 0) {
			return PORT_NOT_NULL;
		} else if (!(portStr.matches("[0-9]{1,}"))) {
			return PORT_ERROR;
		} else if (Integer.parseInt(portStr) < 1024) {
			return PORT_NULL_TOO_SMALL;
		} else if (Integer.parseInt(portStr) > 65535) {
			return PORT_NULL_TOO_LARGE;
		} else {
			return OK;
		}

	}

	/**
	 * 检查用户名 先判断旅客姓名是否为null或者长度为0，如果是返回NO_NULL。 再判断custName的格式是否符合要求，长度在 2~10 之间
	 * (必需是中文)，如果否返回CUSTNAME_ERROR。 如果都符合返回OK。
	 * 
	 * @param userName
	 *            用户名
	 * @return 返回校验信息
	 */
	public static int checkName(String name) {
		if (name == null || name.length() == 0) {
			return CUSTNAME_NOT_NULL;
		} else if (!(name.matches("[\\u4e00-\\u9fa5]{2,10}"))) {
			return CUSTNAME_ERROR;
		} else {
			return OK;
		}
	}

	/**
	 * 检查旅客电话
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static int checkPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() == 0) {
			return PHONE_NOT_NULL;
		}
		if (phoneNumber.length() != 11) {
			return PHONE_NOT_ENOUGH;
		}
		for (int i = 0; i < phoneNumber.length(); i++) {
			if (Character.isDigit(phoneNumber.charAt(i)) != true) {
				return PHONE_ERROR;
			}
		}
		return OK;
	}

	/**
	 * 检查身份证
	 * 
	 * @param idcard
	 * @return
	 */
	public static int checkIdCard(String idcard) {
		if (idcard == null || idcard.length() == 0) {
			return IDCARD_NOT_NULL;
		}
		if (idcard.length() != 18) {
			return IDCARD_NOT_ENOUGH;
		}
		if (!idcard.matches("[0-9]{17}[X0-9]{1}")) {
			return IDCARD_ERROR;
		}
		return OK;
	}

	/**
	 * 检查地址 先判断网点地址是否为null或者长度为0，如果是返回ADDRESS_NOT_NULL。
	 * 再判断网点地址是否符合要求，不超过50个字符，如果不符合返回ADDRESS_TOO_LONG。 如果都符合返回OK。
	 * 
	 * @param addressStr
	 *            地址
	 * @return 返回校验信息
	 */
	public static int checkAddress(String addressStr) {
		if (addressStr == null || addressStr.length() == 0) {
			return ADDRESS_NOT_NULL;
		} else if (addressStr.length() > 50) {
			return ADDRESS_TOO_LONG;
		} else {
			return OK;
		}

	}
	
	/**
	 * 检查邮箱地址格式
	 * @param email
	 * @return
	 */
	public static int checkEmail(String email){
		if (email == null || email.length() == 0) {
			return EMAIL_NOT_NULL;
		}
		if (!email.matches("[a-zA-Z0-9]{1,}@[a-zA-Z_]{1,}\\.com")) {
			return EMAIL_ERROR;
		}
		return OK;
	}
	
    /**
     * 判断时间是否符合时间的格式和要求
     * @param time
     * @return
     */
	public static int checkDate(String time){
	
		if(time.trim() ==null ||time.length() == 0){
		      return TIME_NO_NULL;
		}
		if(time.trim().length() !=5){
			return TIME_LONG_ERRER;
		}
		if(!time.trim().matches("[0-9]{2}[:][0-9]{2}")){
			return TIME_TYPE_ERROR;
		}
		int mi =Integer.valueOf(time.trim().substring(0, 2));
	
		int ss =Integer.valueOf(time.trim().substring(3, 5));
		
		if(mi < 0  || mi > 24){
			return TIME_MI_OUT;
		}
		if(ss < 0  ||ss > 59){
			return TIME_SS_OUT;
		}
		if(!time.substring(2, 3).contains(":")){
			return FATMOT_ERROR;
		}
		return OK;	
	}
    public static void main(String[] args) {
    	System.out.println(ERRMsgMap.get(checkDate("09:24")));
	}
    
    
    
	public static int checkYearM(String date){
		if(date.trim().length()!=6){
			return YEAR_LONG;
		}
		if(!date.trim().trim().matches("[1-9]{1}[0-9]{5}")){
			return YEAR_ERROR;
		}
		if(Integer.valueOf(date.trim().substring(4,6)) < 1  || Integer.valueOf(date.trim().substring(4,6))>12){
			return YEAR_FARMOT;
		}
		return OK;	
	}
	
	
	public static int  CompareString(String str1,String str2)
	{
		String[] str3=str1.split(":");
		int hour1=Integer.parseInt(str3[0]);
		int minute1=Integer.parseInt(str3[1]);
		String[] str4=str2.split(":");
		int hour2=Integer.parseInt(str4[0]);
		int minute2=Integer.parseInt(str4[1]);
		if(hour1>hour2)
			return 1;
		if(hour1==hour2 && minute1>minute2)
			return 1;
		return -1;
				
	}	
	
	/**
	 * 核查输入的日期(精确到"天")是否符合要求
	 * @param name
	 * @return
	 */
	public static int checkDateDay(String date){
		if(date==null || date.length()==0){
			return DATEDAY_NOT_NULL;
		}
		if(date.length() != 10){
			return DATEDAY_ERROR;
		}
		int year = Integer.parseInt(date.trim().substring(0, 4));
		int month = Integer.parseInt(date.trim().substring(5, 7));
		int day = Integer.parseInt(date.trim().substring(8, 10));
		if(year<1900 || year>3000 || month<1 || month>12 || day<1 || day>31){
			return DATEDAY_ERROR;
		}
		return OK;	
	}
	
	
	/**
	 * 核查退票界面上的信息是否都有填写
	 * @param map
	 * @return
	 */
	public static String checkAllInputIsNull(HashMap<String, String> map){
		Set entrySet = map.entrySet();
		Iterator entryIterator = entrySet.iterator();
		while(entryIterator.hasNext()){
			Map.Entry entry = (Map.Entry)entryIterator.next();
			Object key = entry.getKey();
			Object values = entry.getValue();
			if(key.equals("")){
				StringBuffer errorInfo = new StringBuffer();
				return String.valueOf(errorInfo.append(values + "必须填写"));
			}
		}
		return "";
	}
	
	/**
	 * 从该类的错误集合MAP中取出错误代码对应的错误信息
	 * @param errorCode
	 * @return
	 */
	public static String getMapNoticeInfo(int errorCode){
		Set entrySet = ERRMsgMap.entrySet();
		Iterator entryIterator = entrySet.iterator();
		while(entryIterator.hasNext()){
			Map.Entry entry = (Map.Entry)entryIterator.next();
			Object key = entry.getKey();
			Object values = entry.getValue();
			if((Integer)key == errorCode){
				return (String)values;
			}
		}
		return null;
	}
	
	/**
	 * 核查航班编号
	 * @param flightno
	 * @return
	 */
	public static int checkFlightNo(String flightno){
		if(flightno.trim().equals("")){
			return FLIGHTNO_NOT_NULL;
		}
		if(!flightno.trim().matches("^[A-Za-z0-9]+$")){
			return FLIGHTNO_ERROR;
		}
		return OK;
	}
	/**
	 * 核查航班编号格式，允许空
	 * @param flightno
	 * @return
	 */
	public static int checkFlightNoExceptNull(String flightno){
		if(!flightno.trim().matches("^[A-Za-z0-9]+$")){
			return FLIGHTNO_ERROR;
		}
		return OK;
	}
	
	/**
	 * 核查销售网点编号格式，允许空
	 * @param netcode
	 * @return
	 */
	public static int checkNetcodeExceptNull(String netcode){
		if (!(netcode.matches("^[A-Za-z0-9]{8}"))) {
			return NETCODE_ERROR;
		}
		return OK;
	}
	
	/**
	 * 在添加航班信息时，核查出发城市、到达城市、航班机型
	 */
	public static int checkCityAndFlighttype(String fromcity, String tocity,
			String flighttype){
		if(fromcity.trim().equals("")){
			return FROMCITY_NOT_NULL;
		}else if(tocity.trim().equals("")){
			return TOCITY_NOT_NULL;
		}else if(flighttype.trim().equals("")){
			return FLIGHTTYPE_NOT_NULL;
		}
		return OK;
	}
	
	/**
	 * 在添加航班信息时，核查经停城市
	 * @param stopcity
	 * @return
	 */
	public static int checkStopCity(String stopcity){
		if(stopcity.trim().equals("")){
			return STOPCITY_NOT_NULL;
		}
		return OK;
	}
	
	/**
	 * 在添加航班时，核查数字的输入
	 */
	public static int checkIsNum(String num, int flag){
		if(num.trim().equals("")){
			switch(flag){
			case 1:
				return NUMBER_NOT_NULL1;
			case 2:
				return NUMBER_NOT_NULL2;
			}
		}
		if(!num.trim().matches("^[0-9]*$")){
			switch(flag){
			case 1:
				return NUMBER_ERROR1;
			case 2:
				return NUMBER_ERROR2;
			}
		}
		return OK;
	}
	/**
	 * 在添加航班时，核查数字的输入
	 * 核查“票面价格、航班里程、座位数量”、“经停票面价格、经停起飞票价、经停里程数”
	 */
	public static int checkIsNum(String val1, String val2, String val3, int flag){
		if(!(checkIsNum(val1,flag) == OK)){
			return checkIsNum(val1,flag); 
		}else if(!(checkIsNum(val2,flag) == OK)){
			return checkIsNum(val2,flag);
		}else if(!(checkIsNum(val3,flag) == OK)){
			return checkIsNum(val3,flag);
		}
		return OK;
	}
	
	/**
	 * 核查系统管理员账号
	 * @param name
	 * @return
	 */
	public static int checkSysUsername(String name) {
		if (name == null || name.length() == 0) {
			return SYSUSERNAME_NOT_NULL;
		} else if (!(name.matches("^[A-Za-z0-9]{3,12}"))) {
			return SYSUSERNAME_ERROR;
		} else {
			return OK;
		}
	}
	
	
	/**
	 * 核查网点账号
	 * @param name
	 * @return
	 */
	public static int checkNetCode(String name) {
		if (name == null || name.length() == 0) {
			return NETCODE_NOT_NULL;
		} else if (!(name.matches("^[A-Za-z0-9]{8}"))) {
			return NETCODE_ERROR;
		} else {
			return OK;
		}
	}
	
	/**
	 * 销售网点名称
	 * @param name
	 * @return
	 */
	public static int checkNetName(String name) {
		if (name == null || name.length() == 0) {
			return NETNAME_NOT_NULL;
		} else {
			return OK;
		}
	}
	
	/**
	 * 销售网点负责人
	 * @param name
	 * @return
	 */
	public static int checkDirector(String name) {
		if (name == null || name.length() == 0) {
			return NETDIRECTOR_NOT_NULL;
		} else {
			return OK;
		}
	}
	
	
	public static int checkSidUserPwd(String sid, String username, String password){
		if(sid==null || sid.length()==0){
			return SID_NOT_NULL;
		}else if(username==null || username.length()==0){
			return DBNAME_NOT_NULL;
		}else if(password==null || password.length()==0){
			return DBPASSWORD_NOT_NULL;
		}
		return OK;
	}
	
	
	
}
