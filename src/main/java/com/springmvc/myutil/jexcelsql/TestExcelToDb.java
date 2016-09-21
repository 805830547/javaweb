package com.springmvc.myutil.jexcelsql;

import java.util.List;

/**
 * @author Javen
 * @Email zyw205@gmail.com
 *
 */
public class TestExcelToDb {
    public static void main(String[] args) {
        // 得到表格中所有的数据
        List<CityEntity> listExcel = CityService.getAllByExcel("d://ss.xls");
        /*
         * //得到数据库表中所有的数据 List<StuEntity> listDb=StuService.getAllByDb();
         */

        DBhepler db = new DBhepler();

        for (CityEntity cityEntity : listExcel) {
            String cityId = cityEntity.getCityId();
            if (!CityService.isExist(cityId)) {
                // 不存在就添加
                String sql = "insert into city (city_id,city_name_zh,city_name_en,country,province) values(?,?,?,?,?)";
                String[] str = new String[] { cityEntity.getCityId(), cityEntity.getCityNameZh(),
                        cityEntity.getCityNameEn(), cityEntity.getCountry(), cityEntity.getProvince() };
                db.AddU(sql, str);
            } else {
                // 存在就更新
                String sql = "update city set city_name_zh=?,city_name_en=?,country=?,province=? where city_id=?";
                String[] str = new String[] { cityEntity.getCityNameZh(), cityEntity.getCityNameEn(),
                        cityEntity.getCountry(), cityEntity.getProvince(), cityEntity.getCityId() };
                db.AddU(sql, str);
            }
        }
    }
}