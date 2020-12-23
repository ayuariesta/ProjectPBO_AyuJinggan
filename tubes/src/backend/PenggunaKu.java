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
public class PenggunaKu extends Aktor {
    private int idku;
    private Pengguna pengguna = new Pengguna();
    private String nama, alamat, telepon;
    
    public PenggunaKu(){
        
    }
    
    public PenggunaKu(Pengguna pengguna, String nama, String alamat, String telepon){
        this.pengguna=pengguna;
        this.nama=nama;
        this.alamat=alamat;
        this.telepon=telepon;
        
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public int getIdku() {
        return idku;
    }

    public void setIdku(int idku) {
        this.idku = idku;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PenggunaKu getById(int id){
        PenggunaKu pg = new PenggunaKu();
        
        String query = "SELECT "
                +"pb.idku AS idku, "
                +"pb.nama AS nama, "
                +"pb.alamat AS alamat, "
                +"pb.telepon AS telepon, "
                +"p.idpengguna AS idpengguna, "
                +"p.jenis AS jenis "
                +"FROM penggunaku pb "
                +"LEFT JOIN pengguna p ON pb.idpengguna = p.idpengguna "
                +"WHERE pb.idku = '" +id+ "'";
        ResultSet rs = DBHelper.selectQuery(query);
        
        try {
            while(rs.next()){
                pg = new PenggunaKu();
                pg.setIdku(rs.getInt("idku"));
                pg.getPengguna().setIdpengguna(rs.getInt("idpengguna"));
                pg.getPengguna().setJenis(rs.getString("jenis"));
                pg.setNama(rs.getString("nama"));
                pg.setAlamat(rs.getString("alamat"));
                pg.setTelepon(rs.getString("telepon"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pg;
    }
    
    public ArrayList<PenggunaKu> getAll(){
        ArrayList<PenggunaKu> ListPenggunaku = new ArrayList();
        String query = "SELECT pb.idku AS idku, pb.nama AS nama, pb.alamat AS alamat, pb.telepon AS telepon, p.idpengguna AS idpengguna, p.jenis AS jenis "
                + "FROM penggunaku AS pb LEFT JOIN pengguna AS p ON pb.idpengguna = p.idpengguna";
        ResultSet rs = DBHelper.selectQuery(query);
        
        try {
            while(rs.next()){
                PenggunaKu pg = new PenggunaKu();
                pg.setIdku(rs.getInt("idku"));
                pg.getPengguna().setIdpengguna(rs.getInt("idpengguna"));
                pg.getPengguna().setJenis(rs.getString("jenis"));
                pg.setNama(rs.getString("nama"));
                pg.setAlamat(rs.getString("alamat"));
                pg.setTelepon(rs.getString("telepon"));
                
                ListPenggunaku.add(pg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        
        return ListPenggunaku;
    }
    
     public ArrayList<PenggunaKu> search(String keyword){
        ArrayList<PenggunaKu> ListPenggunaku = new ArrayList();
        
        
        String query = "SELECT pb.idku AS idku,"
                + "pb.nama AS nama, "
                + "pb.alamat AS alamat, "
                + "pb.telepon AS telepon, "
                + "p.idpengguna AS idpengguna, "
                + "p.jenis AS jenis "
                + "FROM penggunaku AS pb LEFT JOIN "
                + "pengguna AS p ON pb.idpengguna = p.idpengguna "
                + "WHERE pb.nama LIKE '%" + keyword + "%'"
                + "OR pb.alamat LIKE '%" + keyword + "%'"
                + "OR pb.telepon LIKE '%" + keyword + "%'";
        
        ResultSet rs = DBHelper.selectQuery(query);

        try {
            while(rs.next()){
                PenggunaKu pg = new PenggunaKu();
                pg.setIdku(rs.getInt("idku"));
                pg.getPengguna().setIdpengguna(rs.getInt("idpengguna"));
                pg.getPengguna().setJenis(rs.getString("jenis"));
                pg.setNama(rs.getString("nama"));
                pg.setAlamat(rs.getString("alamat"));
                pg.setTelepon(rs.getString("telepon"));
                
                ListPenggunaku.add(pg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ListPenggunaku;
    }
    @Override
        
     public void save(){
         if(getById(idku).getIdku() == 0){
             String SQL = "INSERT INTO penggunaku (idpengguna, nama, alamat, telepon) VALUES("
                    + "'" + this.getPengguna().getIdpengguna() + "',"
                    + "'" + this.nama + "',"
                    + "'" + this.alamat + "',"
                     + "'" + this.telepon + "'"
                    + ")";

             this.idku = DBHelper.insertQueryGetId(SQL);
         } else {
             String SQL = "UPDATE penggunaku SET "
                     +"idpengguna = '" + this.getPengguna().getIdpengguna() + "',"
                     +"nama = '" + this.nama + "',"
                     +"alamat = '" + this.alamat + "',"
                     +"telepon = '" + this.telepon + "'"
                     +"WHERE idku = '" + this.idku + "'";
            DBHelper.executeQuery(SQL);
         }
     }

    @Override
              public void delete(){
         String SQL = "DELETE FROM penggunaku WHERE idku = '" + this.idku + "'";
         DBHelper.executeQuery(SQL);
     }

    
    
    
}
