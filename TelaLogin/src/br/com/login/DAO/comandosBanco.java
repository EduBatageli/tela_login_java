package br.com.login.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.login.factory.FactoryConnection;
import br.com.login.model.InfoLogin;
import br.com.login.model.ValidarSenha;

public class comandosBanco {

	public void cadastrar(InfoLogin credenciais) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ValidarSenha validarSenha = new ValidarSenha();

		String insert = "insert into cadastro_usuario(usuario, senha) values(?,?)";
		try {

			conn = FactoryConnection.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(insert);

			pstm.setString(1, credenciais.getUsuario());

			validarSenha.setSenha(credenciais.getSenha());

			if (validarSenha.validarSenha() == true) {
				JOptionPane.showMessageDialog(null, "A senha deve conter no mínimo: \n1 caracter especial "
						+ "\n8 dígitos \n1 letra maiúscula \n1 letra minúscula");
			} else {
				pstm.setString(2, credenciais.getSenha());
				JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
			}

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// finalizando o cursor
			try {
				if (pstm != null) {
					pstm.close();
				} else if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean autenticarUsuario(InfoLogin credenciais) {

		Connection conn = null;
		PreparedStatement pstm = null;
		String select = "SELECT usuario, senha FROM cadastro_usuario WHERE usuario = ?";

		try {

			conn = FactoryConnection.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(select);
			
			pstm.setString(1, credenciais.getUsuario());
			
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String senhaArmazenada = rs.getString("senha");
                    String nomeArmazenado = rs.getString("usuario");
                    
                    if(nomeArmazenado.equals(credenciais.getUsuario())) {
                        if (senhaArmazenada.equals(credenciais.getSenha())) {
                        	JOptionPane.showMessageDialog(null, "Logado com sucesso");
                            return true; // Credenciais válidas
                        }else {
                        	JOptionPane.showMessageDialog(null, "Senha incorreta");
                        }
                    }else {
                    	JOptionPane.showMessageDialog(null, "usuario nao identificado");
                    }
                    
                }
            }
			

			pstm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// finalizando o cursor
			try {
				if (pstm != null) {
					pstm.close();
				} else if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false; // Credenciais inválidas

	}
}
