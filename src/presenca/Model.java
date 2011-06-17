package presenca;

import java.sql.*;
import java.util.ArrayList;
import java.lang.reflect.Method;

/**
 *
 * @author diego
 */
public abstract class Model {
    protected ArrayList<String> Attributes = new ArrayList<String>();
    private Integer id;

    static Connection con;
    public Model(){

    }
    
    public Model(Integer id){
        Statement statement;
        ResultSet result;
        String query = "SELECT * FROM " + 
                       this.getClass().getSimpleName() + 
                       " WHERE id = " + id + ";";

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
    
    public ArrayList<Model> all(String conditions){
        ArrayList<Model> contents = new ArrayList<Model>();
        
        Statement statement;
        ResultSet result;
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
            
            while(result.next()){
                Class model = this.getClass();
                Object obj = model.newInstance();
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
            }
            
        }catch(SQLException e){
            System.out.println("Query: " + query);
            System.out.println("Problema na consulta");
        }catch(InstantiationException e){
            System.out.println("Não possível criar instancia do modelo");
            e.printStackTrace();
        }catch(IllegalAccessException e){
            System.out.println("Não foi possível acessar construtor do modelo");
            e.printStackTrace();
        }
        Fechar();
        
        return contents;
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