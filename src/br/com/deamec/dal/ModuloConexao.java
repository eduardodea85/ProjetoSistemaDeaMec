/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.deamec.dal;
//O * significa que o java.sql, vair trazer tudo que existe no pacote baixado na biblioteca

import java.sql.*;

/**
 *
 * @author danie
 */
public class ModuloConexao {

    // comando ou método para estabelecer uma conexão com o banco
    public static Connection conector() {
        // connection é um framework, um conjuto de funcionalidades que vem do 
        //pacote java.sql e conector é o nome do método
        //Sempre que cria um método sem a palavra void, é um método que tem que 
        //ter um retorno.
        //comando, variavel para trazer informações do banco.
        java.sql.Connection conexao = null;
        //Usando o pacote java.sql e o framework Connection eu crio o variavel 
        //chamada conexao e atribuo nulo. não existe nenhuma conexão no momento.
        //Atenção, a variavel se chama conexao, mas o método se chama conector
        //Agora carregar o driver correspondente ao tipo de banco de dados. 
        //A linha abaixo chama o driver que importou na bibliteca.
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando informações referente ao banco. (String, cria variavel)
        String url = "jdbc:mysql://108.179.252.168:3306/deamec16_java";
        //String url = "jdbc:mysql://localhost:3306/deajava";
        // cria a url,"jdbc:mysql://" não muda. após isso é o caminho do banco.
        String user = "deamec16_java";
        //String user = "root";
        String password = "@Dea080985";
        //String password = "";
        //""
        //Shift + alt + F (identação do código)
        // foi criado o driver, caminho, usuario e senha do banco.
        //Agora estabelenco a conexão com o banco de dados.
        try {
            Class.forName(driver);
            //executa o arquivo do driver.
            conexao = DriverManager.getConnection(url, user, password);
            //variavel conexao criada e gerenciamento da conexão utulizando o 
            //caminho, usuario e senha
            return conexao;
            //método criado é um metodo que requer um retorno. se der tudo certo
            //obtenha a conexao e retorne logando.
        } catch (Exception e) {
            //System.out.println(e);
            // "e" mostra qual é a falha na autenticação para o desenvolvedor
            return null;
            //caso aja erro e não retorna.
        }

    }

    private static void forname(String driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
