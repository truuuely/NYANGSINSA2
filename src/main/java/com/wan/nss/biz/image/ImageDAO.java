package com.wan.nss.biz.image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("ImageDAO")
public class ImageDAO {

   @Autowired
   private JdbcTemplate jdbcTemplate;
   // 이미지 추가
   private final String SQL_INSERT = "INSERT INTO IMAGE (I_NM, TYPE_NO, TARGET_NO) VALUES(?, ?, ?)";
   // 이미지 번호
   private final String SQL_SELECTONE = "SELECT * FROM IMAGE WHERE I_NO = ?";
   // 이미지 전체 보기
   private final String SQL_SELECTALL = "SELECT * FROM IMAGE ORDER BY I_NO ASC";
   // 이미지 수정
   private final String SQL_UPDATE = "UPDATE IMAGE SET I_NM = ?, TYPE_NO = ? WHERE I_NO=?";
   // 이미지 삭제
   private final String SQL_DELETE = "DELETE FROM IMAGE WHERE I_NO=?";

   public boolean insert(ImageVO ivo) {
      jdbcTemplate.update(SQL_INSERT, ivo.getImageName(), ivo.getTargetNum(), ivo.getTypeNum());
      return true;
   }

   public boolean update(ImageVO ivo) {
      jdbcTemplate.update(SQL_UPDATE, ivo.getImageName(), ivo.getTypeNum(), ivo.getImageNum());
      return true;
   }

   public boolean delete(ImageVO ivo) {
      jdbcTemplate.update(SQL_DELETE, ivo.getImageNum());
      return true;
   }

   public ArrayList<ImageVO> selectAll(ImageVO ivo) {
      return (ArrayList<ImageVO>) jdbcTemplate.query(SQL_SELECTALL, new ImageRowMapper());
   }

   public ImageVO selectOne(ImageVO ivo) {
      Object[] args = { ivo.getImageNum() };
      return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ImageRowMapper());
   }

   class ImageRowMapper implements RowMapper<ImageVO> {

      @Override
      public ImageVO mapRow(ResultSet rs, int rowNum) throws SQLException {
         ImageVO ivo = new ImageVO();
         ivo.setImageNum(rs.getInt("I_NO"));
         ivo.setImageName(rs.getString("I_NM"));
         ivo.setTargetNum(rs.getInt("TARGET_NO"));
         ivo.setTypeNum(rs.getInt("TYPE_NO"));

         return ivo;
      }
   }
}