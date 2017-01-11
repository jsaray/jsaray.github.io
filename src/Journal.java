import java.util.ArrayList;


public class Journal {

	String journal ; 
	ArrayList<Pair> listProprietaire  ;
	String type ;
	double audience ; 
	String etranger ;
	
	public String getEtranger() {
		return etranger;
	}
	public void setEtranger(String etranger) {
		this.etranger = etranger;
	}
	public double getAudience() {
		return audience;
	}
	public void setAudience(double audience) {
		this.audience = audience;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public ArrayList<Pair> getListProprietaire() {
		return listProprietaire;
	}
	public void setListProprietaire(ArrayList<Pair> listProprietaire) {
		this.listProprietaire = listProprietaire;
	}
	
	public Journal() {
		super();
		listProprietaire = new ArrayList<Pair>();
	}
	
	
	
}
