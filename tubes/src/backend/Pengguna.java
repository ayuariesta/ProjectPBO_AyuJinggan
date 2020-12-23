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
public class Pengguna extends Aktor {
 private int idpengguna;
 private String jenis;
 
    public Pengguna(){
        
    }

    public Pengguna(String jenis){
        this.jenis=jenis;
    }

    public int getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(int idpengguna) {
        this.idpengguna = idpengguna;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
    
    
    public Pengguna getById(int id){
        Pengguna pgn = new Pengguna();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pengguna " 
                                            + " WHERE idpengguna = '" + id + "'");
        
        try{
                while(rs.next()){
                    pgn = new Pengguna();
                    pgn.setIdpengguna(rs.getInt("idpengguna"));
                    pgn.setJenis(rs.getString("jenis"));
                }
        }
            catch (Exception e){
                    e.printStackTrace();
                    }
        
            return pgn;
    }
    
    public ArrayList<Pengguna> getAll(){
        ArrayList<Pengguna> ListPengguna = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pengguna");
        
                
        try{
                while(rs.next()){
                    Pengguna pgn = new Pengguna();
                    pgn.setIdpengguna(rs.getInt("idpengguna"));
                    pgn.setJenis(rs.getString("jenis"));
                    
                    ListPengguna.add(pgn);
                }
        }
            catch (Exception e){
                
                    e.printStackTrace();
                    
                    }
        
            return ListPengguna;
    }
    
        public ArrayList<Pengguna> search(String keyword){
        ArrayList<Pengguna> ListPengguna = new ArrayList();
        
        String sql = "SELECT * FROM pengguna WHERE " 
                + "   jenis LIKE '%" + keyword + "%' " ;
        
        ResultSet rs = DBHelper.selectQuery(sql);
        
                
        try{
                while(rs.next()){
                    Pengguna pgn = new Pengguna();
                    pgn.setIdpengguna(rs.getInt("idpengguna"));
                    pgn.setJenis(rs.getString("jenis"));
                    
                    ListPengguna.add(pgn);
                }
        }
            catch (Exception e){
                
                    e.printStackTrace();
                    
                    }
        
            return ListPengguna;
    }

    @Override
            public void save(){
            if(getById(idpengguna).getIdpengguna() == 0){
                String SQL = "INSERT INTO pengguna (jenis) VALUES("
                            + "     '" + this.jenis + "' "
                            + "     )";
                this.idpengguna = DBHelper.insertQueryGetId(SQL);
                
            }
            
            else{
                String SQL = "UPDATE pengguna SET "
                            + "     jenis = '" + this.jenis + "' "
                            + "     WHERE idpengguna = '" + this.idpengguna + "'";
                DBHelper.executeQuery(SQL);
            }
        }

    @Override
            
        public void delete(){
            String SQL = "DELETE FROM pengguna WHERE idpengguna = '" + this.idpengguna + "'";
            DBHelper.insertQueryGetId(SQL);
        }
        
    public String toString(){
            return jenis;
        }
}
