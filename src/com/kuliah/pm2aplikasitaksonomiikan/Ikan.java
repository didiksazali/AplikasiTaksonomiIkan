package com.kuliah.pm2aplikasitaksonomiikan;

public class Ikan {

	// variables
    int _kode;
    String _filum;
    String _kelas;
    String _bangsa;
    String _keluarga;
    String _marga;
    String _jenis;

    // Konstruktor
    public Ikan(){
         
    }
    // constructor
    public Ikan(int id, String filum, String kelas, String bangsa, String keluarga, String marga, String jenis){
        this._kode= id;
        this._filum = filum;
        this._kelas = kelas;
        this._bangsa = bangsa;
        this._keluarga=keluarga;
        this._marga=marga;
        this._jenis=jenis;
    }
     
    // constructor
    public Ikan(String filum, String kelas, String bangsa, String keluarga, String marga, String jenis){
        this._filum = filum;
        this._kelas = kelas;
        this._bangsa = bangsa;
        this._keluarga=keluarga;
        this._marga=marga;
        this._jenis=jenis;
    }
    // getting ID
    public int getID(){
        return this._kode;
    }
     
    // setting id
    public void setID(int kode){
        this._kode = kode;
    }
     
    // getting filum
    public String getFilum(){
        return this._filum;
    }
     
    // setting filum
    public void setFilum(String filum){
        this._filum = filum;
    }
     
    // getting kelas
    public String getKelas(){
        return this._kelas;
    }
     
    // setting kelas
    public void setKelas(String kelas){
        this._kelas = kelas;
    }
    // getting kelas
    public String getBangsa(){

    	return this._bangsa;
    }
     
    // setting kelas
    public void setBangsa(String bangsa){
        this._kelas = bangsa;
    }
    
    // getting kelas
    public String getKeluarga(){
        return this._keluarga;
    }
     
    // setting kelas
    public void setKeluarga(String keluarga){
        this._keluarga = keluarga;
    }
    
    // getting marga
    public String getMarga(){
        return this._marga;
    }
     
    // setting marga
    public void setMarga(String marga){
        this._marga = marga;
    }
    
    // getting Jenis
    public String getJenis(){
        return this._jenis;
    }
     
    // setting Jenis
    public void setJenis(String jenis){
        this._jenis = jenis;
    }
}

