
public class Pair {

	double pourcentage ; 
	String nomProp ;
	
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public String getNomProp() {
		return nomProp;
	}
	public void setNomProp(String nomProp) {
		this.nomProp = nomProp;
	}

	public Pair(double pourcentage, String nomProp) {
		super();
		this.pourcentage = pourcentage;
		this.nomProp = nomProp;
	} 
	
	public Pair() {
		super();
	} 
	
}
