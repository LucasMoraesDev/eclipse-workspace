package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Cliente;
import factories.ConnectionFactory;

public class ClienteRepository {

	public void create(Cliente cliente) throws Exception {

		// abrindo conexão com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// escrevendo um comando em linguagem SQL para inserir um cliente no banco de
		// dados
		PreparedStatement statement = connection
				.prepareStatement("insert into cliente(nome, email, telefone, cpf) values(?,?,?,?)");
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getTelefone());
		statement.setString(4, cliente.getCpf());
		statement.execute();

		// fechando a conexão
		connection.close();
	}

	// método para receber e atualizar um cliente no banco de dados
	public void update(Cliente cliente) throws Exception {

		// abrindo conexão com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// escrevendo um comando em linguagem SQL para atualizar um cliente no banco de
		// dados
		PreparedStatement statement = connection
				.prepareStatement("update cliente set nome=?, email=?, telefone=?, cpf=? where idcliente=?");
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getTelefone());
		statement.setString(4, cliente.getCpf());
		statement.setInt(5, cliente.getIdCliente());
		statement.execute();

		// fechando a conexão
		connection.close();
	}

	// método para receber e excluir um cliente no banco de dados
	public void delete(Cliente cliente) throws Exception {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("delete from cliente where idcliente=?");
		statement.setInt(1, cliente.getIdCliente());
		statement.execute();
		
		connection.close();
	}
	
	public List<Cliente> findAll() throws Exception {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from cliente");
		ResultSet resultSet = statement.executeQuery();
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		while(resultSet.next()) {
			Cliente cliente = new Cliente();
			
			cliente.setIdCliente(resultSet.getInt("idcliente"));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setCpf(resultSet.getString("cpf"));
			
			lista.add(cliente);
			
		}
		connection.close();
		return lista;
	}

}
