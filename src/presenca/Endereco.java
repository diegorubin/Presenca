/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;

/**
 *
 * @author drubin
 */
public class Endereco extends Model{
    private String logradouro;
    private String municipio;
    private String bairro;
    private String cep;
    private String numero;
    private String uf;
    private String classeObjeto;
    private Integer idObjeto;
    
    public Endereco(){
        
    }
    
    public Endereco(String classe, Integer id){
        super(classe,id);
        this.classeObjeto = classe;
        this.idObjeto = id;
    }
    
    public String getClasseObjeto(){
        return this.classeObjeto;
    }
    
    public Integer getIdObjeto(){
        return this.idObjeto;
    }
    
    public void setClasseObjeto(String classeObjeto){
        this.classeObjeto = classeObjeto;
    }
    
    public void setIdObjeto(Integer idObjeto){
        this.idObjeto = idObjeto;
    }
    
    public String getMunicipio(){
        return municipio;
    }
    
    public String getLogradouro(){
        return logradouro;
    }
    
    public String getBairro(){
        return bairro;
    }
    
    public String getCep(){
        return cep;
    }
    
    public String getNumero(){
        return numero;
    }
    
    public String getUf(){
        return uf;
    }
    
    public void setMunicipio(String municipio){
        this.municipio = municipio;
    }
    
    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }
    
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    
    public void setCep(String cep){
        this.cep = cep;
    }
    
    public void setNumero(String numero){
        this.numero = numero;
    }
    
    public void setUf(String uf){
        this.uf = uf;
    }
}
