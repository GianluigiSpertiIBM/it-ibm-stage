package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "utente")
public class Utente {
	private String nome;
	private String cognome;
	private String sesso;
	private int eta;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@XmlTransient
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	@JsonIgnore
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
	
    public String toString() {
    	if (sesso == null)
    		return nome + " " + cognome + " " + eta;
    	if (eta == 0) 
    		return nome + " " + cognome + " " + sesso;
    	return nome + " " + cognome + " " + sesso + " " + eta;
	}
	
}
