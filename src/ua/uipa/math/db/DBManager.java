package ua.uipa.math.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.uipa.math.db.entity.ConfItem;
import ua.uipa.math.db.entity.Department;
import ua.uipa.math.db.entity.GuidanceItem;
import ua.uipa.math.db.entity.Image;
import ua.uipa.math.db.entity.NewsItem;
import ua.uipa.math.db.entity.Science;
import ua.uipa.math.exception.DBException;

public final class DBManager {
	
	private static final String SQL_NEWS_ITEMS = "select * from news";
	private static final String SQL_NEWS_ITEMS_NUMBERED = "select * from news order by news_date desc limit ? offset ?";
	private static final String SQL_NEWS_ITEMS_BY_ID = "select * from news where id=?";
	private static final String SQL_NEWS_IMAGE_BY_ID = "select news_image.id, news_image.name, image.path from news_image join image where news_id=? and image.id=image_id";
	private static final String SQL_SIMPLE_NEWS_IMAGE_BY_ID = "select news_image.id, news_image.name, image.path from news_image join image where news_id=? and image.id=image_id limit 1";
	private static final String SQL_STAFF_ITEMS = "select * from department order by rank desc;";
	private static final String SQL_EMPLOYEE_ITEM = "select * from department where id=?";
	private static final String SQL_STAFF_IMAGE_BY_ID = "select department.id, department.name_en as 'name', image.path from department join image where department.id=? and department.photo=image.id";
	private static final String SQL_CONF_ITEMS = "select * from conf";
	private static final String SQL_CONF_ITEMS_NUMBERED = "select * from conf order by conf_date desc limit ? offset ?";
	private static final String SQL_CONF_ITEMS_BY_ID = "select * from conf where id=?";
	private static final String SQL_CONF_IMAGE_BY_ID = "select conf_image.id, conf_image.name, image.path from conf_image join image where conf_id=? and image.id=image_id";
	private static final String SQL_SIMPLE_CONF_IMAGE_BY_ID = "select conf_image.id, conf_image.name, image.path from conf_image join image where conf_id=? and image.id=image_id limit 1";
	private static final String SQL_GUIDANCE_ITEMS = "select * from guidance";
	private static final String SQL_GUIDANCE_IMAGE_BY_ID = "select guidance_image.id, guidance_image.name, image.path from guidance_image join image where guidance_id=? and image.id=image_id";
	private static final String SQL_SCIENCE_ITEMS = "select science.id, science.title_ua, science.title_ru, science.title_en from science join type where type.name=? and type_id=type.id and visible=1"; 
	private static final String SQL_SCIENCE_IMAGES = "select science_image.id, science_image.name, image.path from science_image join type join image where type.name=? and type_id=type.id and image_id=image.id";
	private static final String SQL_CENTER_ITEMS = "select center_works.id, science.title_ua from center_works join science join type where type.name=? and type_id=type.id and work_id=science.id;";
	private static final String SQL_ENTRANT_INSERT = "insert into students (name, phone, email) values (?, ?, ?)";
	private static final String SQL_WORK_ITEMS = "select science.id, science.title_ua, science.title_ru, science.title_en from science join works where employee_id=? and science.id=works.work_id";
	private static final String SQL_STUDENTS_DATA = "select * from temp where id=1";
	private static final String SQL_ENTRANT_DATA = "select * from temp where id=2";
	
	private static DBManager instance;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://server:3306/db_name?useUnicode=true&characterEncoding=utf8";
	private static final String USER = "some_username";
	private static final String PASS = "some_password";
	//private DataSource ds;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null)
			instance = new DBManager();
		return instance;
	}

	private DBManager() throws DBException {
		
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(JDBC_DRIVER);
			//System.out.println("Connecting to DB...");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			//System.out.println("Connect!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public List<NewsItem> getNewsItems(HttpServletRequest request) throws DBException {
		List<NewsItem> newsItemsList = new ArrayList<NewsItem>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_NEWS_ITEMS);
			while (rs.next()) {
				newsItemsList.add(extractNewsItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return newsItemsList;
	}
	
	public List<GuidanceItem> getGuidanceListItems(HttpServletRequest request) throws DBException {
		List<GuidanceItem> items = new ArrayList<GuidanceItem>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GUIDANCE_ITEMS);
			while (rs.next()) {
				items.add(extractGuidanceItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return items;
	}

	public List<NewsItem> getNewsItems(int total, int start, HttpServletRequest request) throws DBException {
		List<NewsItem> newsItemsList = new ArrayList<NewsItem>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_NEWS_ITEMS_NUMBERED);
			st.setInt(1, total);
			st.setInt(2, start);
			rs = st.executeQuery();
			while (rs.next()) {
				newsItemsList.add(extractNewsItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return newsItemsList;
	}

	public NewsItem getNewsById(int id, HttpServletRequest request) throws DBException {
		NewsItem newsItem = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_NEWS_ITEMS_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				newsItem = extractNewsItem(rs, request);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return newsItem;
	}
	
	public List<ConfItem> getConfItems(HttpServletRequest request) throws DBException {
		List<ConfItem> items = new ArrayList<ConfItem>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_CONF_ITEMS);
			while (rs.next()) {
				items.add(extractConfItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return items;
	}
	
	public String getStudentData(HttpServletRequest request) throws DBException {
		String data = new String();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_STUDENTS_DATA);
			while (rs.next()) {
				data = extractTemplateData(rs, request);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return data;
	}
	
	public String getEntrantData(HttpServletRequest request) throws DBException {
		String data = new String();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_ENTRANT_DATA);
			while (rs.next()) {
				data = extractTemplateData(rs, request);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return data;
	}

	public List<ConfItem> getConfItems(int total, int start, HttpServletRequest request) throws DBException {
		List<ConfItem> items = new ArrayList<ConfItem>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_CONF_ITEMS_NUMBERED);
			st.setInt(1, total);
			st.setInt(2, start);
			rs = st.executeQuery();
			while (rs.next()) {
				items.add(extractConfItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return items;
	}

	public ConfItem getConfById(int id, HttpServletRequest request) throws DBException {
		ConfItem item = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_CONF_ITEMS_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				item = extractConfItem(rs, request);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return item;
	}

	public List<Department> getStaffItems(HttpServletRequest request) throws DBException {
		List<Department> staff = new ArrayList<Department>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_STAFF_ITEMS);
			while (rs.next()) {
				staff.add(extractStaffItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return staff;
	}
	
	public Department getEmployeeById(int id, HttpServletRequest request) throws DBException {
		Department item = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_EMPLOYEE_ITEM);
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				item = extractStaffItem(rs, request);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return item;
	}
	
	public List<Science> getScienceItemsByType(String type, HttpServletRequest request) throws DBException {
		List<Science> items = new ArrayList<Science>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_SCIENCE_ITEMS);
			st.setString(1, type);
			rs = st.executeQuery();
			while (rs.next()) {
				items.add(extractScienceItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return items;
	}
	
	public List<Science> getCenterItemsByType(String type, HttpServletRequest request) throws DBException {
		List<Science> items = new ArrayList<Science>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_CENTER_ITEMS);
			st.setString(1, type);
			rs = st.executeQuery();
			while (rs.next()) {
				items.add(extractScienceItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return items;
	}
	
	public List<Science> getWorksById(Long id, HttpServletRequest request) throws DBException {
		List<Science> items = new ArrayList<Science>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_WORK_ITEMS);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				items.add(extractScienceItem(rs, request));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return items;
	}
	
	public List<Image> getNewsImageById(Long id) throws DBException {
		List<Image> ImageList = new ArrayList<Image>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_NEWS_IMAGE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				ImageList.add(extractImageItem(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return ImageList;
	}
	
	public List<Image> getGuidanceImageById(Long id) throws DBException {
		List<Image> ImageList = new ArrayList<Image>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_GUIDANCE_IMAGE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				ImageList.add(extractImageItem(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return ImageList;
	}
	
	public List<Image> getConfImageById(Long id) throws DBException {
		List<Image> ImageList = new ArrayList<Image>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_CONF_IMAGE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				ImageList.add(extractImageItem(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return ImageList;
	}
	
	public List<Image> getScienceImageByType(String type) throws DBException {
		List<Image> ImageList = new ArrayList<Image>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_SCIENCE_IMAGES);
			st.setString(1, type);
			rs = st.executeQuery();
			while (rs.next()) {
				ImageList.add(extractImageItem(rs));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return ImageList;
	}

	public Image getSimpleNewsImageById(Long id) throws DBException {
		Image image = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_SIMPLE_NEWS_IMAGE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				image = extractImageItem(rs);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return image;
	}
	
	public Image getStaffImageById(Long id) throws DBException {
		Image image = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_STAFF_IMAGE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				image = extractImageItem(rs);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return image;
	}
	
	public Image getSimpleConfImageById(Long id) throws DBException {
		Image image = new Image();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_SIMPLE_CONF_IMAGE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				image = extractImageItem(rs);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		return image;
	}
	
	public void saveEntrantInfo(String name, String phone, String email) {
		PreparedStatement st = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.prepareStatement(SQL_ENTRANT_INSERT);
			st.setString(1, name);
			st.setString(2, phone);
			st.setString(3, email);
			st.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(st);
			close(con);
		}
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	private NewsItem extractNewsItem(ResultSet rs, HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		NewsItem bean = new NewsItem();
		bean.setId(rs.getLong(Field.NEWS_ITEM_ID));
		bean.setDate(rs.getLong(Field.NEWS_ITEM_DATE));
		switch (lang) {
		case "ru":
			bean.setTitle(rs.getString(Field.NEWS_ITEM_TITLE_RU));
			bean.setText(rs.getString(Field.NEWS_ITEM_TEXT_RU));
			break;
		case "en":
			bean.setTitle(rs.getString(Field.NEWS_ITEM_TITLE_EN));
			bean.setText(rs.getString(Field.NEWS_ITEM_TEXT_EN));
			break;
		default:
			bean.setTitle(rs.getString(Field.NEWS_ITEM_TITLE_UA));
			bean.setText(rs.getString(Field.NEWS_ITEM_TEXT_UA));
		}
		return bean;
	}
	
	
	private ConfItem extractConfItem(ResultSet rs, HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		ConfItem bean = new ConfItem();
		bean.setId(rs.getLong(Field.ENTITY_ID));
		bean.setDate(rs.getLong(Field.CONF_ITEM_DATE));
		switch (lang) {
		case "ru":
			bean.setTitle(rs.getString(Field.CONF_ITEM_TITLE_RU));
			bean.setText(rs.getString(Field.CONF_ITEM_TEXT_RU));
			break;
		case "en":
			bean.setTitle(rs.getString(Field.CONF_ITEM_TITLE_EN));
			bean.setText(rs.getString(Field.CONF_ITEM_TEXT_EN));
			break;
		default:
			bean.setTitle(rs.getString(Field.CONF_ITEM_TITLE_UA));
			bean.setText(rs.getString(Field.CONF_ITEM_TEXT_UA));
		}
		return bean;
	}

	private Department extractStaffItem(ResultSet rs, HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		Department bean = new Department();
		bean.setId(rs.getLong(Field.ENTITY_ID));
		switch (lang) {
		case "ru":
			bean.setName(rs.getString(Field.STAFF_ITEM_NAME_RU));
			bean.setPhd(rs.getString(Field.STAFF_ITEM_PHD_RU));
			bean.setInfo(rs.getString(Field.STAFF_ITEM_INFO_RU));
			bean.setEducation(rs.getString(Field.STAFF_ITEM_EDUCATION_RU));
			bean.setEmail(rs.getString(Field.STAFF_ITEM_EMAIL));
			break;
		case "en":
			bean.setName(rs.getString(Field.STAFF_ITEM_NAME_EN));
			bean.setPhd(rs.getString(Field.STAFF_ITEM_PHD_EN));
			bean.setInfo(rs.getString(Field.STAFF_ITEM_INFO_EN));
			bean.setEducation(rs.getString(Field.STAFF_ITEM_EDUCATION_EN));
			bean.setEmail(rs.getString(Field.STAFF_ITEM_EMAIL));
			break;
		default:
			bean.setName(rs.getString(Field.STAFF_ITEM_NAME_UA));
			bean.setPhd(rs.getString(Field.STAFF_ITEM_PHD_UA));
			bean.setInfo(rs.getString(Field.STAFF_ITEM_INFO_UA));
			bean.setEducation(rs.getString(Field.STAFF_ITEM_EDUCATION_UA));
			bean.setEmail(rs.getString(Field.STAFF_ITEM_EMAIL));
		}
		return bean;
	}

	private String extractTemplateData(ResultSet rs, HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String bean = new String();
		switch (lang) {
		case "ru":
			bean = rs.getString(Field.TEMPLATE_DATA_RU);
			break;
		case "en":
			bean = rs.getString(Field.TEMPLATE_DATA_EN);
			break;
		default:
			bean = rs.getString(Field.TEMPLATE_DATA_UA);
		}
		return bean;
	}
	
	private Science extractScienceItem(ResultSet rs, HttpServletRequest request) throws SQLException {
		Science bean = new Science();
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		bean.setId(rs.getLong(Field.ENTITY_ID));
		switch (lang) {
		case "ru":
			bean.setTitle(rs.getString(Field.SCIENCE_TITLE_RU));
			break;
		case "en":
			bean.setTitle(rs.getString(Field.SCIENCE_TITLE_EN));
			break;
		default:
			bean.setTitle(rs.getString(Field.SCIENCE_TITLE_UA));
		}
		return bean;
	}
	
	private GuidanceItem extractGuidanceItem(ResultSet rs, HttpServletRequest request) throws SQLException {
		GuidanceItem bean = new GuidanceItem();
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		bean.setId(rs.getLong(Field.ENTITY_ID));
		switch (lang) {
		case "ru":
			bean.setTitle(rs.getString(Field.GUIDANCE_TITLE_RU));
			bean.setText(rs.getString(Field.GUIDANCE_TEXT_RU));
			break;
		case "en":
			bean.setTitle(rs.getString(Field.GUIDANCE_TITLE_EN));
			bean.setText(rs.getString(Field.GUIDANCE_TEXT_EN));
			break;
		default:
			bean.setTitle(rs.getString(Field.GUIDANCE_TITLE_UA));
			bean.setText(rs.getString(Field.GUIDANCE_TEXT_UA));
		}
		return bean;
	}
	
	private Image extractImageItem(ResultSet rs) throws SQLException {
		Image bean = new Image();
		bean.setId(rs.getLong(Field.IMAGE_ITEM_ID));
		bean.setName(rs.getString(Field.IMAGE_ITEM_NAME));
		bean.setPath(rs.getString(Field.IMAGE_ITEM_PATH));
		return bean;
	}
}
