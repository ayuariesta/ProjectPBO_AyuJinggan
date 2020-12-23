package backend;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author dewiokta
 */
public class Sepeda{
    private int idsepeda;
    private Kategori kategori = new Kategori();
    private String nama, keterangan;

    public Sepeda() {
    }

    public Sepeda(Kategori kategori, String nama, String keterangan) {
        this.kategori = kategori;
        this.nama=nama;
        this.keterangan=keterangan;
    }

    public int getIdsepeda() {
        return idsepeda;
    }

    public void setIdsepeda(int idsepeda) {
        this.idsepeda = idsepeda;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Sepeda getById(int id){
        Sepeda spd = new Sepeda();
        
        String query = "SELECT "
                +"s.idsepeda AS idsepeda, "
                +"s.nama AS nama, "
                +"s.keterangan AS keterangan, "
                +"k.idkategori AS idkategori, "
                +"k.jenis AS jenis, "
                +"k.keterangan AS keterangan "
                +"FROM sepeda s "
                +"LEFT JOIN kategori k ON s.idkategori = k.idkategori "
                +"WHERE s.idsepeda = '" +id+ "'";
        ResultSet rs = DBHelper.selectQuery(query);
        
        try {
            while(rs.next()){
                spd = new Sepeda();
                spd.setIdsepeda(rs.getInt("idsepeda"));
                spd.getKategori().setIdkategori(rs.getInt("idkategori"));
                spd.getKategori().setJenis(rs.getString("jenis"));
                spd.getKategori().setKeterangan(rs.getString("keterangan"));
                spd.setNama(rs.getString("nama"));
                spd.setKeterangan(rs.getString("keterangan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return spd;
    }
    
    public ArrayList<Sepeda> getAll(){
        ArrayList<Sepeda> ListSepeda = new ArrayList();
        String query = "SELECT s.idsepeda AS idsepeda, s.nama AS nama, s.keterangan AS keterangan, k.idkategori AS idkategori, k.jenis AS jenis, k.keterangan AS keterangan "
                + "FROM sepeda AS s LEFT JOIN kategori AS k ON s.idkategori = k.idkategori";
        ResultSet rs = DBHelper.selectQuery(query);
        
        
        try {
            while(rs.next()){
                Sepeda spd = new Sepeda();
                spd.setIdsepeda(rs.getInt("idsepeda"));
                spd.getKategori().setIdkategori(rs.getInt("idkategori"));
                spd.getKategori().setJenis(rs.getString("jenis"));
                spd.getKategori().setKeterangan(rs.getString("keterangan"));
                spd.setNama(rs.getString("nama"));
                spd.setKeterangan(rs.getString("keterangan"));
                
                ListSepeda.add(spd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        
        return ListSepeda;
    }
    
     public ArrayList<Sepeda> search(String keyword){
        ArrayList<Sepeda> ListSepeda = new ArrayList();
        
        
        String query = "SELECT s.idsepeda AS idsepeda, "
                + "s.nama AS nama, "
                + "s.keterangan AS keterangan, "
                + "k.idkategori AS idkategori, "
                + "k.jenis AS jenis, "
                + "k.keterangan AS keterangan "
                + "FROM sepeda AS s LEFT JOIN "
                + "kategori AS k ON s.idkategori = k.idkategori "
                + "WHERE s.nama LIKE '%" + keyword + "%'"
                +"OR s.keterangan LIKE '%" + keyword + "%'";
        
        ResultSet rs = DBHelper.selectQuery(query);

        try {
            while(rs.next()){
                Sepeda spd = new Sepeda();
                spd.setIdsepeda(rs.getInt("idsepeda"));
                spd.getKategori().setIdkategori(rs.getInt("idkategori"));
                spd.getKategori().setJenis(rs.getString("jenis"));
                spd.getKategori().setKeterangan(rs.getString("keterangan"));
                spd.setNama(rs.getString("nama"));
                spd.setKeterangan(rs.getString("keterangan"));
                
                ListSepeda.add(spd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ListSepeda;
    }
     
     public void save(){
         if(getById(idsepeda).getIdsepeda() == 0){
             String SQL = "INSERT INTO sepeda (idkategori, nama, keterangan) VALUES("
                    + "'" + this.getKategori().getIdkategori() + "',"
                    + "'" + this.nama + "',"
                    + "'" + this.keterangan + "'"
                    + ")";

             this.idsepeda = DBHelper.insertQueryGetId(SQL);
         } else {
             String SQL = "UPDATE sepeda SET "
                     +"idkategori = '" + this.getKategori().getIdkategori() + "',"
                     +"nama = '" + this.nama + "',"
                     +"keterangan = '" + this.keterangan + "'"
                     +"WHERE idsepeda = '" + this.idsepeda + "'";
            DBHelper.executeQuery(SQL);
         }
     }
          public void delete(){
         String SQL = "DELETE FROM sepeda WHERE idsepeda = '" + this.idsepeda + "'";
         DBHelper.executeQuery(SQL);
     }
}
     