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
public class Pengembalian {
    private int idpengembalian;
    private Peminjaman peminjaman;
    private String status;
    
    public Pengembalian(){
        
    }
    
    public Pengembalian(Peminjaman peminjaman, String status){
        this.peminjaman=peminjaman;
        this.status=status;
    }

    public int getIdpengembalian() {
        return idpengembalian;
    }

    public void setIdpengembalian(int idpengembalian) {
        this.idpengembalian = idpengembalian;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Pengembalian getById(int id) {
        Pengembalian kembali = new Pengembalian();

        String query = "SELECT * FROM pengembalian b "
                + "LEFT JOIN peminjaman p ON b.idpeminjaman = p.idpeminjaman "
                + "WHERE b.idpengembalian = '" + id + "'";
        ResultSet rs = DBHelper.selectQuery(query);

        try {
            while (rs.next()) {
                    kembali = new Pengembalian();
                    Peminjaman pinjam = new Peminjaman();
                    kembali.setPeminjaman(pinjam);
                    
                    kembali.setIdpengembalian(rs.getInt("idpengembalian"));
                    kembali.setStatus(rs.getString("status"));
                    kembali.getPeminjaman().setIdpeminjaman(rs.getInt("idpeminjaman"));
                    kembali.getPeminjaman().setWaktupeminjaman(rs.getString("waktupeminjaman"));
                    kembali.getPeminjaman().setWaktupengembalian(rs.getString("waktupengembalian"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kembali;
    }

    public ArrayList<Pengembalian> getAll() {
        ArrayList<Pengembalian> Pengembalian = new ArrayList();
        String query = "SELECT * FROM pengembalian b "
                + "LEFT JOIN peminjaman p ON b.idpeminjaman = p.idpeminjaman ";

        ResultSet rs = DBHelper.selectQuery(query);

        try {
            while (rs.next()) {
                Pengembalian kembali = new Pengembalian();
                Peminjaman pinjam = new Peminjaman();
                kembali.setPeminjaman(pinjam);
                    
                    kembali.setIdpengembalian(rs.getInt("idpengembalian"));
                    kembali.setStatus(rs.getString("status"));
                    kembali.getPeminjaman().setIdpeminjaman(rs.getInt("idpeminjaman"));
                    kembali.getPeminjaman().setWaktupeminjaman(rs.getString("waktupeminjaman"));
                    kembali.getPeminjaman().setWaktupengembalian(rs.getString("waktupengembalian"));
                    
                Pengembalian.add(kembali);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Pengembalian;
    }
    
        public ArrayList<Pengembalian> search(String keyword){
        ArrayList<Pengembalian> ListPengembalian = new ArrayList();
        
                            String query = "SELECT * FROM pengembalian AS b "
                                            + "LEFT JOIN peminjaman AS p ON b.idpeminjaman = p.idpeminjaman "
                                            + "WHERE b.status LIKE '%" + keyword +"%'";
                            
        ResultSet rs = DBHelper.selectQuery(query);
        
        try{
                while(rs.next()){
                Pengembalian kembali = new Pengembalian();
                Peminjaman pinjam = new Peminjaman();
                kembali.setPeminjaman(pinjam);
                    
                    kembali.setIdpengembalian(rs.getInt("idpengembalian"));
                    kembali.setStatus(rs.getString("status"));
                    kembali.getPeminjaman().setIdpeminjaman(rs.getInt("idpeminjaman"));
                    kembali.getPeminjaman().setWaktupeminjaman(rs.getString("waktupeminjaman"));
                    kembali.getPeminjaman().setWaktupengembalian(rs.getString("waktupengembalian"));
                    
                ListPengembalian.add(kembali);
                    
                    
                }
        }
            catch (Exception e){
                    e.printStackTrace();
                    }
        
            return ListPengembalian;
    }
        public void save(){
        if (getById(idpengembalian).getIdpengembalian() == 0) {
            try {
                    String SQL = "INSERT INTO pengembalian (idpeminjaman, status) VALUES("
                            + "'" + this.getPeminjaman().getIdpeminjaman() + "',"
                            + "'" + this.status+ "'"
                            + ")";

             this.idpengembalian = DBHelper.insertQueryGetId(SQL);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            
            else{
            
            String SQL = "UPDATE pengembalian SET"
                            + "     idpeminjaman = '" + this.getPeminjaman().getIdpeminjaman() + "',"
                            + "     status = '" + this.status + "'";
            
                DBHelper.executeQuery(SQL);
            }
        }
        
        public void delete(){
            String SQL = "DELETE FROM pengembalian WHERE idpengembalian = '" + this.idpengembalian + "'";
            DBHelper.insertQueryGetId(SQL);
        }
}
