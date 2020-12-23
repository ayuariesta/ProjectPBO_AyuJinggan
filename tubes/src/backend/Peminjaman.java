/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author user
 */
public class Peminjaman {
    private int idpeminjaman;
    private Sepeda sepeda;
    private PenggunaKu penggunaku;
    private Karyawan karyawan;
    private String waktupeminjaman, waktupengembalian;
    private int jumlahsepeda, hari, harga, total;
    public Peminjaman(){
        
    }

    public Peminjaman(Sepeda sepeda,PenggunaKu penggunaku,Karyawan karyawan, String waktupeminjaman, String waktupengembalian, int jumlahsepeda, int hari, int harga, int total){
        this.sepeda=sepeda;
        this.penggunaku=penggunaku;
        this.karyawan=karyawan;
        this.waktupeminjaman=waktupeminjaman;
        this.waktupengembalian=waktupengembalian;
        this.jumlahsepeda=jumlahsepeda;
        this.hari=hari;
        this.harga=harga;
        this.total=total;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }
    
    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }
    
    public PenggunaKu getPenggunaku() {
        return penggunaku;
    }

    public void setPenggunaku(PenggunaKu penggunaku) {
        this.penggunaku = penggunaku;
    }

    public Sepeda getSepeda() {
        return sepeda;
    }

    public void setSepeda(Sepeda sepeda) {
        this.sepeda = sepeda;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public String getWaktupeminjaman() {
        return waktupeminjaman;
    }

    public void setWaktupeminjaman(String waktupeminjaman) {
        this.waktupeminjaman = waktupeminjaman;
    }

    public String getWaktupengembalian() {
        return waktupengembalian;
    }

    public void setWaktupengembalian(String waktupengembalian) {
        this.waktupengembalian = waktupengembalian;
    }

    public int getJumlahsepeda() {
        return jumlahsepeda;
    }

    public void setJumlahsepeda(int jumlahsepeda) {
        this.jumlahsepeda = jumlahsepeda;
    }

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
    public Peminjaman getById(int id) {
        Peminjaman pinjam = new Peminjaman();

        String query = "SELECT * FROM peminjaman p "
                + "LEFT JOIN sepeda s ON p.idsepeda = s.idsepeda "
                + "LEFT JOIN penggunaku pb ON pb.idku = p.idku "
                + "LEFT JOIN karyawan k ON k.idkaryawan = p.idkaryawan "
                + "WHERE p.idpeminjaman = '" + id + "'";
        ResultSet rs = DBHelper.selectQuery(query);

        try {
            while (rs.next()) {
                    pinjam = new Peminjaman();
                    Sepeda spd = new Sepeda();
                    PenggunaKu pg = new PenggunaKu();
                    Karyawan kry = new Karyawan();
                    pinjam.setSepeda(spd);
                    pinjam.setPenggunaku(pg);
                    pinjam.setKaryawan(kry);
                    
                    pinjam.setIdpeminjaman(rs.getInt("idpeminjaman"));
                    pinjam.setWaktupeminjaman(rs.getString("waktupeminjaman"));
                    pinjam.setWaktupengembalian(rs.getString("waktupengembalian"));
                    pinjam.setJumlahsepeda(rs.getInt("jumlahsepeda"));
                    pinjam.setHari(rs.getInt("hari"));
                    pinjam.setHarga(rs.getInt("harga"));
                    pinjam.setTotal(rs.getInt("total"));
                    pinjam.getSepeda().setIdsepeda(rs.getInt("idsepeda"));
                    pinjam.getSepeda().setNama(rs.getString("nama"));
                    pinjam.getSepeda().setKeterangan(rs.getString("keterangan"));
                    pinjam.getPenggunaku().setIdku(rs.getInt("idku"));
                    pinjam.getPenggunaku().setNama(rs.getString("nama"));
                    pinjam.getPenggunaku().setAlamat(rs.getString("alamat"));
                    pinjam.getPenggunaku().setTelepon(rs.getString("telepon"));
                    pinjam.getKaryawan().setIdkaryawan(rs.getInt("idkaryawan"));
                    pinjam.getKaryawan().setNama(rs.getString("nama"));
                    pinjam.getKaryawan().setJeniskelamin(rs.getString("jeniskelamin"));
                    pinjam.getKaryawan().setAlamat(rs.getString("alamat"));
                    pinjam.getKaryawan().setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pinjam;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> Peminjaman = new ArrayList();
        String query = "SELECT * FROM peminjaman p "
                + "LEFT JOIN sepeda s ON p.idsepeda = s.idsepeda "
                + "LEFT JOIN penggunaku pb ON pb.idku = p.idku "
                + "LEFT JOIN karyawan k ON k.idkaryawan = p.idkaryawan ";

        ResultSet rs = DBHelper.selectQuery(query);

        try {
            while (rs.next()) {
                Peminjaman pinjam = new Peminjaman();
                    Sepeda spd = new Sepeda();
                    PenggunaKu pg = new PenggunaKu();
                    Karyawan kry = new Karyawan();
                    pinjam.setSepeda(spd);
                    pinjam.setPenggunaku(pg);
                    pinjam.setKaryawan(kry);
                    
                    pinjam.setIdpeminjaman(rs.getInt("idpeminjaman"));
                    pinjam.setWaktupeminjaman(rs.getString("waktupeminjaman"));
                    pinjam.setWaktupengembalian(rs.getString("waktupengembalian"));
                    pinjam.setJumlahsepeda(rs.getInt("jumlahsepeda"));
                    pinjam.setHari(rs.getInt("hari"));
                    pinjam.setHarga(rs.getInt("harga"));
                    pinjam.setTotal(rs.getInt("total"));
                    pinjam.getSepeda().setIdsepeda(rs.getInt("idsepeda"));
                    pinjam.getSepeda().setNama(rs.getString("nama"));
                    pinjam.getSepeda().setKeterangan(rs.getString("keterangan"));
                    pinjam.getPenggunaku().setIdku(rs.getInt("idku"));
                    pinjam.getPenggunaku().setNama(rs.getString("nama"));
                    pinjam.getPenggunaku().setAlamat(rs.getString("alamat"));
                    pinjam.getPenggunaku().setTelepon(rs.getString("telepon"));
                    pinjam.getKaryawan().setIdkaryawan(rs.getInt("idkaryawan"));
                    pinjam.getKaryawan().setNama(rs.getString("nama"));
                    pinjam.getKaryawan().setJeniskelamin(rs.getString("jeniskelamin"));
                    pinjam.getKaryawan().setAlamat(rs.getString("alamat"));
                    pinjam.getKaryawan().setTelepon(rs.getString("telepon"));
                    
                Peminjaman.add(pinjam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Peminjaman;
    }
    
        public ArrayList<Peminjaman> search(String keyword){
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList();
        
                            String query = "SELECT * FROM peminjaman p " 
                                            + "LEFT JOIN sepeda s ON p.idsepeda = s.idsepeda "
                                            + "LEFT JOIN penggunaku pb ON pb.idku = p.idku"
                                            + "LEFT JOIN karyawan k ON k.idkaryawan = p.idkaryawan"
                                            + "WHERE p.waktupeminjaman LIKE '%" +keyword +"%'"
                                            + "OR p.waktupengembalian LIKE '%" +keyword +"%'";
        ResultSet rs = DBHelper.selectQuery(query);
        
        try{
                while(rs.next()){
                    Peminjaman pinjam = new Peminjaman();
                    Sepeda spd = new Sepeda();
                    PenggunaKu pg = new PenggunaKu();
                    Karyawan kry = new Karyawan();
                    pinjam.setSepeda(spd);
                    pinjam.setPenggunaku(pg);
                    pinjam.setKaryawan(kry);
                    
                    pinjam.setIdpeminjaman(rs.getInt("idpeminjaman"));
                    pinjam.setWaktupeminjaman(rs.getString("waktupeminjaman"));
                    pinjam.setWaktupengembalian(rs.getString("waktupengembalian"));
                    pinjam.setJumlahsepeda(rs.getInt("jumlahsepeda"));
                    pinjam.setHari(rs.getInt("hari"));
                    pinjam.setHarga(rs.getInt("harga"));
                    pinjam.setTotal(rs.getInt("total"));
                    pinjam.getSepeda().setIdsepeda(rs.getInt("idsepeda"));
                    pinjam.getSepeda().setNama(rs.getString("nama"));
                    pinjam.getSepeda().setKeterangan(rs.getString("keterangan"));
                    pinjam.getPenggunaku().setIdku(rs.getInt("idku"));
                    pinjam.getPenggunaku().setNama(rs.getString("nama"));
                    pinjam.getPenggunaku().setAlamat(rs.getString("alamat"));
                    pinjam.getPenggunaku().setTelepon(rs.getString("telepon"));
                    pinjam.getKaryawan().setIdkaryawan(rs.getInt("idkaryawan"));
                    pinjam.getKaryawan().setNama(rs.getString("nama"));
                    pinjam.getKaryawan().setJeniskelamin(rs.getString("jeniskelamin"));
                    pinjam.getKaryawan().setAlamat(rs.getString("alamat"));
                    pinjam.getKaryawan().setTelepon(rs.getString("telepon"));
                    
                    ListPeminjaman.add(pinjam);
                    
                    
                }
        }
            catch (Exception e){
                    e.printStackTrace();
                    }
        
            return ListPeminjaman;
    }
        public void save(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        if (getById(idpeminjaman).getIdpeminjaman() == 0) {
            try {
                java.util.Date waktupeminjaman = format.parse(this.waktupeminjaman);
                java.sql.Date sqlwaktupeminjaman= new java.sql.Date(waktupeminjaman.getTime());
                
                java.util.Date waktupengembalian = format.parse(this.waktupengembalian);
                java.sql.Date sqlwaktupengembalian = new java.sql.Date(waktupengembalian.getTime());

                    String SQL = "INSERT INTO peminjaman (idsepeda, idku, idkaryawan,waktupeminjaman, waktupengembalian, jumlahsepeda, hari, harga, total) VALUES("
                            + "'" + this.getSepeda().getIdsepeda() + "',"
                            + "'" + this.getPenggunaku().getIdku() + "',"
                            + "'" + this.getKaryawan().getIdkaryawan() + "',"
                            + "'" + sqlwaktupeminjaman + "',"
                            +"'" + sqlwaktupengembalian + "',"
                            + "'" + this.jumlahsepeda + "',"
                            + "'" + this.hari + "',"
                            + "'" + this.harga + "',"
                            + "'" + this.total + "' "
                            + ")";

             this.idpeminjaman = DBHelper.insertQueryGetId(SQL);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            
            else{
                String SQL = "UPDATE peminjaman SET "
                            + "     idsepeda = '" + this.getSepeda().getIdsepeda()+ "', "
                            + "     idpengguna = '" + this.getPenggunaku().getIdku()+ "', "
                            + "     idkaryawan = '" + this.getKaryawan().getIdkaryawan() + "', "
                            + "     waktupeminjaman = '" + this.waktupeminjaman+ "', "
                            + "     waktupengembalian = '" + this.waktupengembalian + "', "
                            + "     jumlahsepeda = '" + this.jumlahsepeda + "', "
                            + "     hari = '" + this.hari + "', "
                            + "     harga = '" + this.harga + "', "
                            + "     total = '" + this.total + "', ";
                DBHelper.executeQuery(SQL);
            }
        }
        
        public void delete(){
            String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.insertQueryGetId(SQL);
        }
}