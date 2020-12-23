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
public class Kategori {
 
 private int idkategori;
 private String jenis;
 private String keterangan;
 
    public Kategori(){
        
    }

    public Kategori(String jenis, String keterangan){
        this.jenis=jenis;
        this.keterangan=keterangan;
    }

    public int getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(int idkategori) {
        this.idkategori = idkategori;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    
    public Kategori getById(int id){
        Kategori kat = new Kategori();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori " 
                                            + " WHERE idkategori = '" + id + "'");
        
        try{
                while(rs.next()){
                    kat = new Kategori();
                    kat.setIdkategori(rs.getInt("idkategori"));
                    kat.setJenis(rs.getString("jenis"));
                    kat.setKeterangan(rs.getString("keterangan"));
                }
        }
            catch (Exception e){
                    e.printStackTrace();
                    }
        
            return kat;
    }
    
    public ArrayList<Kategori> getAll(){
        ArrayList<Kategori> ListKategori = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori");
        
                
        try{
                while(rs.next()){
                    Kategori kat = new Kategori();
                    kat.setIdkategori(rs.getInt("idkategori"));
                    kat.setJenis(rs.getString("jenis"));
                    kat.setKeterangan(rs.getString("keterangan"));
                    
                    ListKategori.add(kat);
                }
        }
            catch (Exception e){
                
                    e.printStackTrace();
                    
                    }
        
            return ListKategori;
    }
    
        public ArrayList<Kategori> search(String keyword){
        ArrayList<Kategori> ListKategori = new ArrayList();
        
        String sql = "SELECT * FROM kategori WHERE " 
                + "    jenis LIKE '%" + keyword + "%' " 
                + "    OR keterangan LIKE '%" + keyword + "%' ";
        
        ResultSet rs = DBHelper.selectQuery(sql);
        
                
        try{
                while(rs.next()){
                    Kategori kat = new Kategori();
                    kat.setIdkategori(rs.getInt("idkategori"));
                    kat.setJenis(rs.getString("jenis"));
                    kat.setKeterangan(rs.getString("keterangan"));
                    
                    ListKategori.add(kat);
                }
        }
            catch (Exception e){
                
                    e.printStackTrace();
                    
                    }
        
            return ListKategori;
    }
        public void save(){
            if(getById(idkategori).getIdkategori() == 0){
                String SQL = "INSERT INTO kategori (jenis, keterangan) VALUES("
                            + "     '" + this.jenis + "', "
                            + "     '" + this.keterangan + "' "
                            + "     )";
                this.idkategori = DBHelper.insertQueryGetId(SQL);
                
            }
            
            else{
                String SQL = "UPDATE kategori SET "
                            + "     jenis = '" + this.jenis + "', "
                            + "     keterangan = '" + this.keterangan + "' "
                            + "     WHERE idkategori = '" + this.idkategori + "'";
                DBHelper.executeQuery(SQL);
            }
        }
        
        public void delete(){
            String SQL = "DELETE FROM kategori WHERE idkategori = '" + this.idkategori + "'";
            DBHelper.insertQueryGetId(SQL);
        }
        
            public String toString(){
            return jenis;
        }

}

