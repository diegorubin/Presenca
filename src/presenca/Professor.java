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
public class Professor extends Model{
    private String nome;
    private Endereco endereco;
    
    public Professor(){
    }
    
    public Professor(Integer id){
        super(id);
        endereco = new Endereco(this.getClass().toString(),id);
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public Endereco getEndereco(){
        return endereco;
    }
    
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    
    @Override
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("nome");
        
        return attributes;
    }
    
    @Override
    protected boolean afterSave(){
        endereco.setClasseObjeto(this.getClass().toString());
        endereco.setIdObjeto(id);
        if(!endereco.save())
            return false;
        
        return true;
    }
    
}
