package presenca;

import java.sql.*;
import java.util.ArrayList;
import java.lang.reflect.Method;

/*
 * Eu sei que esta lógica não era para estar aqui
 * Se der tempo eu penso em algo melhor, mas por enquanto ...
 */
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public abstract class Model {
    protected ArrayList<String> Attributes = new ArrayList<String>();
    protected Integer id;

    static Connection con;
    public Model(){

    }
    
    public Model(Integer id){
        loadModel("id = " + id);
    }
    
    public Model(String classe, Integer id){
        loadModel("classe_objeto = " + classe + " AND id_objeto = " + id);
    }
    
    public Model(String conditions){
        loadModel(conditions);
    }
    
    private void loadModel(String conditions){
        Statement statement;
        ResultSet result;
        String query = "SELECT * FROM " + 
                       this.getClass().getSimpleName() + 
                       " WHERE " + conditions + ";";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(query);
            result.first();

            this.id = result.getInt("id");
            for(String attribute: getAttributes()){
                try{
                    Method setMethod = this.getClass().getMethod("set" + Utils.titlelize(attribute), String.class);
                    setMethod.invoke(this,result.getString(attribute));
                }catch(NoSuchMethodException e){
                    System.err.println("Campo '" + attribute + "' não existe para '" + this.getClass().getSimpleName() +"' ");
                }catch(Exception e){
                    System.err.println("O método '" + attribute + "' não é publico");
                }
            }
            
        }catch(SQLException e){
            System.out.println("Query: " + query);
            System.out.println("Problema na consulta");
        }
        Fechar();

    }
    
    public void toTable(JTable table, String conditions){
       
        Statement statement;
        ResultSet result;

        DefaultTableModel mdlModel = (DefaultTableModel)table.getModel();
        
        int i;
        for(i = mdlModel.getRowCount() - 1; i >= 0; i-- ){
            mdlModel.removeRow(i);
        }
        
        String query = "SELECT * FROM " + 
                       this.getClass().getSimpleName();
        if(!conditions.isEmpty()){
            query += " WHERE " + conditions;
        }
        
        query += ";";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(query);
            
            Object contents[] = new Object[getAttributes().size()+1];
            
            while(result.next()){
                i=0;
                contents[i++] = result.getString("id");
                for(String attribute: getAttributes()){
                    try{
                        contents[i++] = result.getString(attribute);
                    }catch(Exception e){
                        e.printStackTrace();
                        System.err.println("O método '" + attribute + "' não é publico");
                    }
                }
                mdlModel.addRow(contents);
            }
            
        }catch(SQLException e){
            System.out.println("Query: " + query);
            System.out.println("Problema na consulta");
        }
        Fechar();
        
    }

    public boolean save(){
        return save(false);
    }
    
    public boolean save(boolean new_record){
        Statement statement;

        String query ="";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

            if(new_record){
                String values = "";
                String fields = "";
                
                for(String attribute: this.getAttributes()){
                    fields += attribute + ",";
                    try{
                        Method getMethod = this.getClass().getMethod("get" + Utils.titlelize(attribute));
                        values += "'" + (String)getMethod.invoke(this) + "',";
                    }catch(NoSuchMethodException e){
                        System.err.println("Campo '" + attribute + "' não existe para '" + this.getClass().getSimpleName() +"' ");
                    }catch(Exception e){
                        System.err.println("O método '" + attribute + "' não é publico");
                    }
                    
                }
                fields = "(" + Utils.chop(fields) + ")";
                values = Utils.chop(values);
                query = "INSERT INTO " + this.getClass().getSimpleName() + " " + fields +" VALUES ("+ values +");";

                statement.execute(query);
                id = statement.executeQuery("select id from " + this.getClass().getSimpleName() + " ORDER BY id desc").getInt("id");
            }else{
                String update = "";
                for(String attribute: this.getAttributes()){
                    try{
                        Method getMethod = this.getClass().getMethod("get" + Utils.titlelize(attribute));
                        update += attribute + " = '" + (String)getMethod.invoke(this) + "',";
                    }catch(NoSuchMethodException e){
                        System.err.println("Campo '" + attribute + "' não existe para '" + this.getClass().getSimpleName() +"' ");
                    }catch(Exception e){
                        System.err.println("O método '" + attribute + "' não é publico");
                    }
                    
                }
                query = "UPDATE " + 
                        this.getClass().getSimpleName() + 
                        " SET " + 
                        Utils.chop(update) + 
                        "WHERE id = " + id +";";
                statement.executeUpdate(query);
            }
            
        }catch(SQLException e){
            System.out.println("SQL: " + query);
            System.out.println("Problema ao salvar professor");
            Fechar();
            return false;
        }
        Fechar();
        return true;
    }

    public boolean destroy(){
        Statement statement;

        String query = "";

        try{
            Conecta();
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_READ_ONLY);

            query = "DELETE FROM " + this.getClass().getSimpleName() + " WHERE id = " + id.toString() + ";";
            statement.executeUpdate(query);
            

        }catch(SQLException e){
            System.out.println("SQL: " + query);
            System.out.println("Problema na query delete");
            Fechar();
            return false;
        }
        Fechar();
        return true;
    }
    
    protected ArrayList<String> getAttributes(){
        ArrayList<String> attributes = new ArrayList<String>();
        
        return attributes;
    }
    
    protected boolean afterSave(){
        return true;
    }

    private void Conecta(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Presenca","root","root");
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("Não foi possivel conectar com o banco");
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado");
        }
    }

    private void Fechar(){
        try{
            con.close();
        }catch(SQLException ex){
            System.out.println("Erro ao encerrar o banco");
        }
    }
}