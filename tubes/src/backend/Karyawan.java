/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author user
 */
public class Karyawan {
 
 private int idkaryawan;
 private String nama;
 private String jeniskelamin;
 private String alamat;
 private String telepon;
 
    public Karyawan(){
        
    }

    public Karyawan(String nama, String jeniskelamin, String alamat, String telepon){
        this.nama=nama;
        this.jeniskelamin=jeniskelamin;
        this.alamat=alamat;
        this.telepon=telepon;
    }

    public int getIdkaryawan() {
        return idkaryawan;
    }

    public void setIdkaryawan(int idkaryawan) {
        this.idkaryawan = idkaryawan;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    
    
    
    public Karyawan getById(int id){
        Karyawan kry = new Karyawan();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM karyawan " 
                                            + " WHERE idkaryawan = '" + id + "'");
        
        try{
                while(rs.next()){
                    kry = new Karyawan();
                    kry.setIdkaryawan(rs.getInt("idkaryawan"));
                    kry.setNama(rs.getString("nama"));
                    kry.setJeniskelamin(rs.getString("jeniskelamin"));
                    kry.setAlamat(rs.getString("alamat"));
                    kry.setTelepon(rs.getString("telepon"));
                }
        }
            catch (Exception e){
                    e.printStackTrace();
                    }
        
            return kry;
    }
    
    public ArrayList<Karyawan> getAll(){
        ArrayList<Karyawan> ListKaryawan = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM karyawan");
        
                
        try{
                while(rs.next()){
                    Karyawan kry = new Karyawan();
                    kry.setIdkaryawan(rs.getInt("idkaryawan"));
                    kry.setNama(rs.getString("nama"));
                    kry.setJeniskelamin(rs.getString("jeniskelamin"));
                    kry.setAlamat(rs.getString("alamat"));
                    kry.setTelepon(rs.getString("telepon"));
                    
                    ListKaryawan.add(kry);
                }
        }
            catch (Exception e){
                
                    e.printStackTrace();
                    
                    }
        
            return ListKaryawan;
    }
    
        public ArrayList<Karyawan> search(String keyword){
        ArrayList<Karyawan> ListKaryawan = new ArrayList();
        
        String sql = "SELECT * FROM karyawan WHERE " 
                + "    nama LIKE '%" + keyword + "%' " 
                + "    OR jeniskelamin LIKE '%" + keyword + "%' "
                + "    OR alamat LIKE '%" + keyword + "%' "
                + "    OR telepon LIKE '%" + keyword + "%' ";
        
        ResultSet rs = DBHelper.selectQuery(sql);
        
                
        try{
                while(rs.next()){
                    Karyawan kry = new Karyawan();
                    kry.setIdkaryawan(rs.getInt("idkaryawan"));
                    kry.setNama(rs.getString("nama"));
                    kry.setJeniskelamin(rs.getString("jeniskelamin"));
                    kry.setAlamat(rs.getString("alamat"));
                    kry.setTelepon(rs.getString("telepon"));
                    
                    ListKaryawan.add(kry);
                }
        }
            catch (Exception e){
                
                    e.printStackTrace();
                    
                    }
        
            return ListKaryawan;
    }
        public void save(){
            if(getById(idkaryawan).getIdkaryawan() == 0){
                String SQL = "INSERT INTO karyawan (nama,jeniskelamin , alamat,telepon) VALUES("
                            + "     '" + this.nama + "', "
                            + "     '" + this.jeniskelamin + "', "
                            + "     '" + this.alamat + "', "
                            + "     '" + this.telepon + "' "
                            + "     )";
                this.idkaryawan = DBHelper.insertQueryGetId(SQL);
                
            }
            
            else{
                String SQL = "UPDATE karyawan SET "
                            + "     nama = '" + this.nama + "', "
                            + "     jeniskelamin = '" + this.jeniskelamin + "', "   
                            + "     alamat = '" + this.alamat+ "', "
                            + "     telepon = '" + this.telepon + "' "
                            + "     WHERE idkaryawan = '" + this.idkaryawan + "'";
                DBHelper.executeQuery(SQL);
            }
        }
        
        public void delete(){
            String SQL = "DELETE FROM karyawan WHERE idkaryawan = '" + this.idkaryawan + "'";
            DBHelper.insertQueryGetId(SQL);
        }


}

