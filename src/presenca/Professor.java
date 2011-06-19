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
    private String formacao;
    private String anoConclusao;
    
    private Endereco endereco;
    
    public Professor(){
        endereco = new Endereco();
    }
    
    public Professor(Integer id){
        super(id);
        endereco = new Endereco("Professor",id);
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setAnoConclusao(String anoConclusao){
        this.anoConclusao = anoConclusao;
    }
    
    public String getAnoConclusao(){
        return anoConclusao;
    }
    
    public void setFormacao(String formacao){
        this.formacao = formacao;
    }
    
    public String getFormacao(){
        return formacao;
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
        attributes.add("formacao");
        attributes.add("anoConclusao");
        
        //relacionamentos
        attributes.add("presenca.Endereco");
        
        return attributes;
    }
    
    @Override
    protected boolean afterSave(boolean new_record){
        endereco.setClasseObjeto("Professor");
        endereco.setIdObjeto(id);
        return endereco.save(new_record);
    }
    
}
