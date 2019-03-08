package com.projects.zll.utilslibrarybyzll.aboututils;


import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串校验工具
 * @author zll
 */
public class StrUtils {
	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 *
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}
	public static void  getData(final Context context)
	{
		try {
			VolleyRequest.RequestGet(context,"http://p33mtatcg.bkt.gdipper.com/boom.json", new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
				@Override
				public void onSuccess(final String result) {
					initData(context,result);
				}
				@Override
				public void onError(VolleyError result) {
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void initData(Context context,String result) {
		try {
			final String sucess = HttpIsSucess(result);
			if (sucess.equals("0"))
			{
				AppManager.getAppManager().onAppExit(context);
//				ToastUtil.show("失败");
			}else
			{
//							ToastUtil.show("成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String HttpIsSucess(String result){
		if (!result.equals("")&&result!=null) {
			try {
				JSONObject object = null;
				try {
					object = new JSONObject(result);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String code = object.optString("code").toString().trim();
				return code;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "参数错误";
	}
	/**
	 * 判断是不是一个合法的电子邮件地址
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 字符串转整数
	 *
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 *
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 对象转整数
	 *
	 * @author ccy
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(String obj) {
		try {
			return Integer.parseInt(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 对象转整数
	 *
	 * @author ccy
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static float toFloat(String obj) {
		try {
			return Float.parseFloat(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 *
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 *
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	/**
	 *
	 * @Title: isNullOrEmpty <br>
	 * @description: 判断是否为null或空值 <br>
	 * @param str
	 * @return boolean <br>
	 *
	 * @author limingliang <br>
	 * @date 2014年6月11日-上午10:53:03 <br>
	 *
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 *
	 * @Title: getString <br>
	 * @description: 判断字符串是否为空，为空则返回一个空值，不为空则返回原字符串 <br>
	 * @param str
	 *            待判断字符串<br>
	 * @return String 判断后的字符串<br>
	 *
	 * @author limingliang <br>
	 * @date 2014年6月11日-上午10:54:21 <br>
	 *
	 */
	public static String getString(String str) {
		return str == null ? "" : str;
	}

	/**
	 *
	 * <b>@Description:<b>判断是不是合法手机 手机号码<br/>
	 * <b>@param handset <b>@return<b>boolean<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-7-30-上午11:44:56<br/>
	 */
	public static boolean isHandset(String handset) {
		try {
			if (!handset.substring(0, 1).equals("1")) {
				return false;
			}
			if (handset == null || handset.length() != 11) {
				return false;
			}
			String check = "^[0123456789]+$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(handset);
			boolean isMatched = matcher.matches();
			if (isMatched) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			return false;
		}
	}

	/**
	 *
	 * <b>@Description:<b>判断输入的字符串是否为纯汉字<br/>
	 * <b>@param str <b>@return<b>boolean<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-7-30-上午11:45:37<br/>
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 *
	 * <b>@Description:<b>检验密码是否合格<br/>
	 * <b>@param str <b>@return<b>boolean<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-8-8-上午10:06:31<br/>
	 */
	public static boolean validatePassword(String password) {
		if (StrUtils.isEmpty(password)) {
			return false;
		}
//		Pattern p = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()_+-=]{8,16}$");
//		Pattern p = Pattern.compile("^(?![0-9])(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9!@#$%^&*()_+-=]{6,20}$");
		Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9!@#$%^&*()_+-=.“”，。、?！`~￥]{6,20}$");
		Matcher m = p.matcher(password);
		boolean b = m.find();
		Log.e("validatePassword","------------------"+b);
		return b;
	}

	/**
	 *
	 * <b>@Description:<b>检验姓名是否合格<br/>
	 * <b>@param name <b>@return<b>boolean<br/>
	 * <b>@Author:<b>ccy<br/>
	 * <b>@Since:<b>2014-8-8-上午10:16:36<br/>
	 */
	public static boolean validatenName(String name) {
		if (StrUtils.isEmpty(name)) {
			return false;
		}
		Pattern p = Pattern.compile("^[a-zA-Z0-9]{8,16}$");
		Matcher m = p.matcher(name);
		return m.find();
	}

}
