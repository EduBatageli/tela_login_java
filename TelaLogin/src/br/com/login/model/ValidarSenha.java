package br.com.login.model;

import javax.swing.JOptionPane;

public class ValidarSenha {

	String senha;
	String caracteresEspeciais = "!@#$%^&*()-_=+[{]};:'\",<.>/?";
	boolean temErro = false;
	
	
	public boolean validarSenha() {
		boolean temCaractereEspecial = false;
		boolean temMaiuscula = false;
		boolean temMinuscula = false;
		boolean temDigito = false;

		for (char c : caracteresEspeciais.toCharArray()) {
			if (senha.contains(String.valueOf(c))) {
				temCaractereEspecial = true;
				break;
			}
		}

		for (char c : senha.toCharArray()) {
			if (Character.isUpperCase(c)) {
				temMaiuscula = true;
			} else if (Character.isLowerCase(c)) {
				temMinuscula = true;
			} else if (Character.isDigit(c)) {
				temDigito = true;
			}
		}

		if (senha.length() >= 8 && temCaractereEspecial && temMaiuscula && temMinuscula && temDigito) {
			JOptionPane.showMessageDialog(null, "Senha validada com sucesso");
			temErro = false;
		} else {
			//JOptionPane.showMessageDialog(null, "A senha deve conter no mínimo: \n1 caracter especial "
					//+ "\n8 dígitos \n1 letra maiúscula \n1 letra minúscula");
			temErro = true;
		}
		
		return temErro;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
