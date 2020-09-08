package com.tuan.dictionary.util;

import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String ACCOUNT_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*$";
	private static final String NAME_PATTERN = "^[A-Za-z0-9- ]+$";
	private static final String TOKEN_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String PASSWORD_CHARACTERS = "abcdefghkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
	public static final String DEFAULT_TIMEZONE = "GMT+8:00";
	public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
	public static final String PHONE_NUMBER = "[0-9\\+]{1,15}$";
	private static final String TOKEN_PATTERN = "^[A-Za-z0-9]+$";
	private static final String DEFAULT_TEMPLATE_ITEM_CSV_FILE = "TemplateItemCSVdata.json";

	private static Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
	private static Pattern namePattern = Pattern.compile(NAME_PATTERN);
	private static Pattern accountPattern = Pattern.compile(ACCOUNT_PATTERN);
	private static Pattern phoneNumber = Pattern.compile(PHONE_NUMBER);
	private static Pattern tokenPattern = Pattern.compile(TOKEN_PATTERN);
	private static SecureRandom random = new SecureRandom();
	
	public static String generateToken(int size) {
		return generateToken(TOKEN_CHARACTERS, size);
	}

	public static String generatePassword(int size) {
		return generateToken(PASSWORD_CHARACTERS, size);
	}

	public static String generateToken(String tokenSpace, int size) {
		StringBuilder builder = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			builder.append(tokenSpace.charAt(random.nextInt(tokenSpace.length())));
		}
		return builder.toString();
	}
	
	public static boolean isValidEmail(String email) {
		return emailPattern.matcher(email).matches();
	}

	public static boolean isValidName(String name) {
		return namePattern.matcher(name).matches();
	}

	public static boolean isValidPhoneNumber(String phone) {
		return phoneNumber.matcher(phone).matches();
	}

	public static boolean isValidToken(String token) {
		return tokenPattern.matcher(token).matches();
	}

	public static boolean isValidAccount(String accountId) {
		return accountPattern.matcher(accountId).matches();
	}

	public static String generateHash(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] mdbytes = md.digest(data);
			// convert the byte to hex format method 2
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				String hex = Integer.toHexString(0xff & mdbytes[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String generateFileNameToUpload(String fileId) {
		long now = new Date().getTime();
		if (fileId.compareTo("null") == 0 || fileId.compareTo("0") == 0) {
			return now + "-" + new Random(now).nextLong();
		}
		return fileId + "-" + now + "-" + new Random(now).nextLong();
	}

	public static void writeCsvString(Writer writer, String value) throws IOException {
		if (value.contains(",")) {
			value = "\"" + value + "\"";
		}
		writer.write(value);
	}

	public static byte luhnChecksum(String value) {
		int factor = 2;
		int sum = 0;
		for (int i = value.length() - 1; i >= 0; i--) {
			int d = value.charAt(i) - 0x30;
			d = d * factor;
			if (d > 9) {
				d -= 9;
			}
			factor = 3 - factor;
			sum += d;
		}
		return (byte) ((sum * 9) % 10);
	}

	public static Date getStartOfDate() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		return now.getTime();
	}

	public static Date getStartOfDate(Date date) {
		return getStartOfDate(date, TimeZone.getTimeZone("GMT+8:00"));
	}

	public static Date getStartOfDate(Date date, TimeZone timeZone) {
		Calendar calendar = Calendar.getInstance(timeZone);
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static int random(int limit) {
		return random.nextInt(limit);
	}

	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}

	public static boolean isValidDate(String format, String date) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		try {
			f.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String formatCent(Long value) {
		return value != null ? String.format("$%1$d.%2$02d", value / 100, value % 100) : "$0.00";
	}

	public static Date getEndOfCurrentYear(TimeZone timeZone) {
		Calendar cal = Calendar.getInstance(timeZone);
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static String formatDateFromPattern(String pattern, TimeZone timezone, Date date) {
		SimpleDateFormat ftm = new SimpleDateFormat(pattern);
		ftm.setTimeZone(timezone);
		return ftm.format(date);
	}

	public static String formatDateFromPattern(String pattern, TimeZone timezone, long mili) {
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTimeInMillis(mili);
		return formatDateFromPattern(pattern, timezone, cal.getTime());
	}

	public static Date parseFromPattern(String pattern, TimeZone timezone, String value) throws ParseException {
		SimpleDateFormat ftm = new SimpleDateFormat(pattern);
		ftm.setTimeZone(timezone);
		return ftm.parse(value);
	}

	public static Date getEndOfDate(Date date, TimeZone timezone) {
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static String trim(String original, int maxLength) {
		if (original != null) {
			original = original.trim();
			if (original.length() > maxLength) {
				original = original.substring(0, maxLength);
			}
			if ("".equals(original)) {
				original = null;
			}
		}
		return original;
	}

	public static String getCurrentDate(String pattern, TimeZone timeZone) {
		Calendar calendar = Calendar.getInstance(timeZone);
		Date currentDate = calendar.getTime();
		return formatDateFromPattern(pattern, timeZone, currentDate);
	}

	public static <T> List<T> parseJsonList(String jsonData, Class<T> classType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<T> list = objectMapper.readValue(jsonData,
				objectMapper.getTypeFactory().constructCollectionType(List.class, classType));
		return list;
	}

	public static <T> List<T> getTemplateItemCSVData(Class<T> classType) {
		try {
			Path path = Paths.get(Utils.class.getClassLoader().getResource(DEFAULT_TEMPLATE_ITEM_CSV_FILE).toURI());
			Stream<String> lines = Files.lines(path);
			String jsonData = lines.collect(Collectors.joining("\n"));
			lines.close();
			return parseJsonList(jsonData, classType);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
