package com.springmvc.myutil.jexcelsql;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @author ly
 * @Email 805830547@qq.com
 *
 */
public class CityService {

    public static List<String> getStringList(String colName, String condition) {
        List<String> list = new ArrayList<String>();
        try {
            DBhepler db = new DBhepler();
            String sql = "select distinct " + colName + " from city where " + condition;
            ResultSet rs = db.Search(sql, null);
            while (rs.next()) {
                list.add(rs.getString(colName));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询stu表中所有的数据
     *
     * @return
     */
    public static List<CityEntity> getAllByDb() {
        List<CityEntity> list = new ArrayList<CityEntity>();
        try {
            DBhepler db = new DBhepler();
            String sql = "select * from city";
            ResultSet rs = db.Search(sql, null);
            while (rs.next()) {
                String cityId = rs.getString("city_id");
                String cityNameZh = rs.getString("city_name_zh");
                String cityNameEn = rs.getString("city_name_en");
                String country = rs.getString("country");
                String province = rs.getString("province");

                list.add(new CityEntity(cityId, cityNameZh, cityNameEn, country, province));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询指定目录中电子表格中所有的数据
     *
     * @param file
     *            文件完整路径
     * @return
     */
    public static List<CityEntity> getAllByExcel(String file) {
        List<CityEntity> list = new ArrayList<CityEntity>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet("city");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行

            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    // 默认最左边编号也算一列
                    // 所以这里得j++
                    String cityId = rs.getCell(j++, i).getContents();
                    String cityNameZh = rs.getCell(j++, i).getContents();
                    String cityNameEn = rs.getCell(j++, i).getContents();
                    String country = rs.getCell(j++, i).getContents();
                    String province = rs.getCell(j++, i).getContents();

                    System.out.println("cityId:" + cityId + " cityNameEn:" + cityNameEn + " country:" + country
                            + " province:" + province);
                    list.add(new CityEntity(cityId, cityNameZh, cityNameEn, country, province));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 通过Id判断是否存在
     *
     * @param id
     * @return
     */
    public static boolean isExist(String cityId) {
        try {
            DBhepler db = new DBhepler();
            ResultSet rs = db.Search("select * from city where city_id=?", new String[] { cityId });
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    // public static void main(String[] args) {
    // /*
    // * List<StuEntity> all=getAllByDb(); for (StuEntity stuEntity : all) {
    // * System.out.println(stuEntity.toString()); }
    // */
    //
    // System.out.println(isExist(""));
    //
    // }

}