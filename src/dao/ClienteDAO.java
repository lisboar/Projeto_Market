package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import model.Cliente;
import model.ClientePF;
import model.ClientePJ;

/**
 *
 * @author assparremberger
 */
public class ClienteDAO {
    
    public static void inserir(Cliente cliente){
        String cpf_cnpj = "";
        if( cliente.getTipo().equals( Cliente.PESSOA_FISICA )){
            ClientePF pf = (ClientePF) cliente;
            cpf_cnpj = pf.getCpf();
        }else{
            ClientePJ pj = (ClientePJ) cliente;
            cpf_cnpj = pj.getCnpj();
        }
        String query = "INSERT INTO clientes "
           + "( nome, email, cpf_cnpj, receberEmail, "
           + " tipo, codCidade) VALUES ( "
           + " '" + cliente.getNome()           + "' , "
           + " '" + cliente.getEmail()          + "' , "
           + " '" + cpf_cnpj                    + "' , "
           + "  " + cliente.isReceberEmail()    + " , "
           + " '" + cliente.getTipo()           + "' , "
           + "  " + cliente.getCidade().getId() + " ) ";
        Conexao.executar(query);
        
    }
    public static void editar(Cliente cliente){
        String cpf_cnpj = "";
        if( cliente.getTipo().equals( Cliente.PESSOA_FISICA )){
            ClientePF pf = (ClientePF) cliente;
            cpf_cnpj = pf.getCpf();
        }else{
            ClientePJ pj = (ClientePJ) cliente;
            cpf_cnpj = pj.getCnpj();
        }
        String query = "UPDATE clientes SET "
           + " nome =         '" + cliente.getNome()           + "' , "
           + " email =        '" + cliente.getEmail()          + "' , "
           + " cpf_cnpj =     '" + cpf_cnpj                    + "' , "
           + " receberEmail =  " + cliente.isReceberEmail()    + "  , "
           + " tipo =         '" + cliente.getTipo()           + "' , "
           + " codCidade =     " + cliente.getCidade().getId() + "    "
           + " WHERE id = " + cliente.getId();
        Conexao.executar(query);
        
    }
    
    public static void excluir(int idCliente){
        String query = "DELETE FROM clientes "
                     + " WHERE id = " + idCliente;
        Conexao.executar(query);
    }
    
    public static List<Cliente> getClientes(){
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT c.id, c.nome, c.email, "
                + " c.cpf_cnpj, c.tipo, c.receberEmail, "
                + " d.id, d.nome "
                + " FROM clientes c "
                + " INNER JOIN cidades d "
                + " ON c.codCidade = d.id ";
        ResultSet rs = Conexao.consultar( query );
        
        if( rs != null ){
            
            try {
                
                while( rs.next() ){
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 7 ) );
                    cid.setNome( rs.getString( 8 ) );
                    
                    Cliente cliente = new Cliente();
                    cliente.setCidade( cid );
                    cliente.setId( rs.getInt( 1 ) );
                    cliente.setNome( rs.getString( 2 ) );
                    cliente.setEmail( rs.getString( 3 ) );
                    if( rs.getInt( 6 ) == 1 )
                        cliente.setReceberEmail( true );
                    else
                        cliente.setReceberEmail( false );
                    
                    if( rs.getString( 5 ).equals(Cliente.PESSOA_FISICA)){
                        ClientePF pf = (ClientePF) cliente;
                        pf.setTipo( Cliente.PESSOA_FISICA );
                        pf.setCpf( rs.getString( 4 ) );
                        lista.add( pf );
                    }else{
                        ClientePJ pj = (ClientePJ) cliente;
                        pj.setTipo( Cliente.PESSOA_JURIDICA );
                        pj.setCnpj(rs.getString( 4 ) );
                        lista.add( pj );
                    }
                }
                
            } catch (Exception e) {
                
            }
            
        }
        
        
        return lista;
    }
    
    
}
