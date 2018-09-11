package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import tools.Tool;

public class DatabaseDao {
	public static String drv="com.mysql.jdbc.Driver";//鏁版嵁搴撶被鍨�
	public static String url="jdbc:mysql://localhost:3306/news";//鏁版嵁搴撶綉鍧�
	public static String usr="root";//鐢ㄦ埛鍚�
	public static String pwd="123456";//瀵嗙爜
	
	Connection connect = null;
	Statement stmt=null;
	ResultSet rs = null;
	
	boolean defaultCommit;

	public DatabaseDao()  throws Exception{
		Class.forName(drv);
		this.url = url+"?useUnicode=true&characterEncoding=utf-8";
		connect = DriverManager.getConnection(url, usr, pwd);
		stmt = connect.createStatement();			
	}

	public void  query(String sql) throws SQLException{
		rs = stmt.executeQuery(sql);
	}
	
	public Integer update(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}

	public boolean next() throws SQLException{//rs鐨勪笅涓�鏉¤褰曟槸鍚﹀瓨鍦�
		return rs.next();
	}

	public String getString(String field) throws SQLException{//鑾峰彇瀛楃涓茬被鍨嬪瓧娈电殑鍊硷紝瀛楁鍊间负null鍨嬬殑锛屾寜鐓х┖瀛楃涓插鐞�
		return rs.getString(field);
	}
	
	public Integer getInt(String field) throws SQLException{//鑾峰彇鏁存暟绫诲瀷瀛楁鐨勫��
		return rs.getInt(field);
	}
	
	public Timestamp getTimestamp(String field) throws SQLException{//鑾峰彇鏁存暟绫诲瀷瀛楁鐨勫��
		return rs.getTimestamp(field);
	}
	
	public LocalDateTime getLocalDateTime(String field) throws SQLException{//鑾峰彇鏃ユ湡鏃堕棿绫诲瀷瀛楁鐨勫��
		return rs.getTimestamp(field).toLocalDateTime();
	}
	
	public Float getFloat(String field) throws SQLException{//鑾峰彇瀹炴暟绫诲瀷瀛楁鐨勫��
		return rs.getFloat(field);
	}

	public ArrayList<String> FieldsList(String tableName) throws SQLException{// 鑾峰彇琛ㄧ殑瀛楁鍚嶇О锛屽苟淇濆瓨鍒版暟缁勪腑
		ArrayList<String> fieldList = new ArrayList<String>();
		String sql = "select * from " + tableName + " limit 1";// limit 1琛ㄧず鏌ヨ缁撴灉鍙寘鍚竴鏉¤褰�
		query(sql);
		ResultSetMetaData fields = rs.getMetaData();//ResultSetMetaData璁板綍浜嗚〃鐨勫厓鏁版嵁锛屽瀛楁鍚嶇О锛屽瓧娈电被鍨嬬瓑
		
		for (int i = 1; i < fields.getColumnCount() + 1; i++) {//getColumnCount锛堬級鑾峰彇瀛楁鏁版嵁
			fieldList.add(fields.getColumnName(i));//getColumnName(i)鑾峰彇瀛楁鍚嶇О
		}
		return fieldList;
	}
	
	public Integer getCount(String sql) throws SQLException{//鏌ヨ绗﹀悎鏉′欢鐨勮褰曠殑鏁扮洰
		query(sql);
		while (next()) {
			return this.getRs().getInt("count1");
		}
		return 0;
	}	

	//鏍规嵁琛ㄣ�佸瓧娈碉紝鑾峰彇璇ュ瓧娈典笂鎵�鏈夐潪閲嶅鍊�
	public ArrayList<String> getStringFieldValueByTableAndField(String tableName,
			String fieldName) throws SQLException{
		ArrayList<String> FieldValueList = new ArrayList<String>();
		query("select distinct " + fieldName + " from " + tableName);
		
		while (next()) {
			FieldValueList.add(getString(fieldName));
		}

		return FieldValueList;
	}
	
	//淇敼鏌愪釜琛紝鏌愭潯璁板綍锛坕d锛夌殑鏌愪釜瀛楃鍨嬪瓧娈电殑鍊�
	public Integer updateAStringFieldById(String tableName,Integer id,
		String fieldName,String fieldValue)throws SQLException{
		String sql="update "+tableName+" set "+fieldName+"='"+fieldValue+"' where "+
				tableName.toLowerCase()+"Id="+id.toString();
		return update(sql);
	}
	
	
	public void setAutoCommit(boolean f) throws SQLException{
		connect.setAutoCommit(f);
	}
	
	public void commit() throws SQLException{
		connect.commit();
	}
	
	public boolean hasId(String tableName, Integer id) throws SQLException{
		tableName=tableName.toLowerCase();
		String sql="select * from "+tableName+" where "+tableName+"Id="+id.toString();
		query(sql);
		while(next()){
			return true;
		}
		return false;
	}
	
	public void getById(String tableName, Integer id) throws SQLException{
		tableName=tableName.toLowerCase();
		String sql="select * from "+tableName+" where "+tableName+"Id="+id.toString();
		query(sql);
	}
	
	//鍒犻櫎澶氫釜鐢ㄦ埛
	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException{//鏌ヨ澶辫触杩斿洖-1
		if(ids!=null && ids.length()>0){
			String sql = "delete from "+tableName+" where "+tableName.toLowerCase()+"Id in ("+ids+")";
			return databaseDao.update(sql);
		}else
			return -1;
	}		

	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	
	
}
