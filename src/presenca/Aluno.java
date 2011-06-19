/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Aluno extends Model {
    private String nome;
    private String anoIngresso;
    
    private Endereco endereco;
    
    public Aluno(){
        endereco = new Endereco();
    }
    
    public Aluno(Integer id){
        super(id);
        endereco = new Endereco("Aluno",id);
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setAnoIngresso(String anoIngresso){
        this.anoIngresso = anoIngresso;
    }
    
    public String getAnoIngresso(){
        return this.anoIngresso;
    }
    
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    
    public Endereco getEndereco(){
        return endereco;
    }
    
    @Override
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("nome");
        attributes.add("anoIngresso");
        
        //relacionamentos
        attributes.add("presenca.Endereco");
        
        return attributes;
    }
    
    @Override
    protected boolean afterSave(boolean new_record){
        endereco.setClasseObjeto("Aluno");
        endereco.setIdObjeto(id);
        return endereco.save(new_record);
    }
    
}
