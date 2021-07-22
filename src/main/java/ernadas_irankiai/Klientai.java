package ernadas_irankiai;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "klientai")
public class Klientai {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String pav_kliento;
	private String fizinis_juridinis;
	private String kontaktas;
	
	@ManyToOne
	@JoinColumn(name = "id_irankio",referencedColumnName="id",insertable=false, updatable=false)
	private Irankiai irankiai;  
	
	public Klientai() {
		
	}
	
	public Klientai(Integer id, String pav_kliento, String fizinis_juridinis, String kontaktas) {
		
		this.id = id;
		this.pav_kliento = pav_kliento;
		this.fizinis_juridinis = fizinis_juridinis;
		this.kontaktas = kontaktas;	
	}
	
	public Klientai(String id, String pav_kliento, String fizinis_juridinis, String kontaktas) {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPav_kliento() {
		return pav_kliento;
	}

	public void setPav_kliento(String pav_kliento) {
		this.pav_kliento = pav_kliento;
	}

	public String getFizinis_juridinis() {
		return fizinis_juridinis;
	}

	public void setFizinis_juridinis(String fizinis_juridinis) {
		this.fizinis_juridinis = fizinis_juridinis;
	}

	public String getKontaktas() {
		return kontaktas;
	}

	public void setKontaktas(String kontaktas) {
		this.kontaktas = kontaktas;
	}

	public Irankiai getIrankiai() {
		return irankiai;
	}

	public void setIrankiai(Irankiai irankiai) {
		this.irankiai = irankiai;
	}

	@Override
	public String toString() {
		return "Klientai [id=" + id + ", pav_kliento=" + pav_kliento + ", fizinis_juridinis=" + fizinis_juridinis
				+ ", kontaktas=" + kontaktas + ", irankiai=" + irankiai + "]";
	}
}
