package ernadas_irankiai;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import ernadas_irankiai.Klientai;

@Entity
@Table(name = "irankiai")
public class Irankiai {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String pavadinimas;
	private String irankio_tipas;
	private String inventoriaus_nr;
	private String isigyjimo_data;
	private Double isigyjimo_kaina;
	private Double nuomos_kaina;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idIrankiai",referencedColumnName="id",insertable=false, updatable=false)
	private List<Klientai> Klientai;  
	
	@Transient
	private String klientu_pavadinimai;
	
	public Irankiai() {
		
	}

	public Irankiai ( Integer id, String pavadinimas, String irankio_tipas, String inventoriaus_nr, 
			String isigyjimo_data, Double isigyjimo_kaina, Double nuomos_kaina) {
	
		super();
		this.id = id;
		this.pavadinimas = pavadinimas;
		this.irankio_tipas = irankio_tipas;
		this.inventoriaus_nr = inventoriaus_nr;
		this.isigyjimo_data = isigyjimo_data;
		this.isigyjimo_kaina = isigyjimo_kaina;
		this.nuomos_kaina = nuomos_kaina;
		
	}
	
	public Irankiai ( String id, String pavadinimas, String irankio_tipas, String inventoriaus_nr, 
			String isigyjimo_data, String isigyjimo_kaina, String nuomos_kaina) {
	
	}
	public String klientuPavadinimai() {
		klientu_pavadinimai = "";
		String comma = "";
		for(Klientai klientai1:Klientai) {
			klientu_pavadinimai += comma+ klientai1.getPav_kliento();
			comma = "\n";
		}
		return klientu_pavadinimai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPavadinimas() {
		return pavadinimas;
	}

	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}

	public String getIrankio_tipas() {
		return irankio_tipas;
	}

	public void setIrankio_tipas(String irankio_tipas) {
		this.irankio_tipas = irankio_tipas;
	}

	public String getInventoriaus_nr() {
		return inventoriaus_nr;
	}

	public void setInventoriaus_nr(String inventoriaus_nr) {
		this.inventoriaus_nr = inventoriaus_nr;
	}

	public String getIsigyjimo_data() {
		return isigyjimo_data;
	}

	public void setIsigyjimo_data(String isigyjimo_data) {
		this.isigyjimo_data = isigyjimo_data;
	}

	public Double getIsigyjimo_kaina() {
		return isigyjimo_kaina;
	}

	public void setIsigyjimo_kaina(Double isigyjimo_kaina) {
		this.isigyjimo_kaina = isigyjimo_kaina;
	}

	public Double getNuomos_kaina() {
		return nuomos_kaina;
	}

	public void setNuomos_kaina(Double nuomos_kaina) {
		this.nuomos_kaina = nuomos_kaina;
	}

	public List<Klientai> getKlientai() {
		return Klientai;
	}

	public void setKlientai(List<Klientai> klientai) {
		Klientai = klientai;
	}

	public String getKlientu_pavadinimai() {
		return klientu_pavadinimai;
	}

	public void setKlientu_pavadinimai(String klientu_pavadinimai) {
		this.klientu_pavadinimai = klientu_pavadinimai;
	}

	@Override
	public String toString() {
		return "Irankiai [id=" + id + ", pavadinimas=" + pavadinimas + ", irankio_tipas=" + irankio_tipas
				+ ", inventoriaus_nr=" + inventoriaus_nr + ", isigyjimo_data=" + isigyjimo_data + ", isigyjimo_kaina="
				+ isigyjimo_kaina + ", nuomos_kaina=" + nuomos_kaina + ", Klientai=" + Klientai
				+ ", klientu_pavadinimai=" + klientu_pavadinimai + "]";
	}
}
