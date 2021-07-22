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
@Table(name = "zurnalas")
public class Zurnalas {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String id_kliento;
	private String idIrankio;
	private String kada_isnuomota;
	private String kada_grazinta;
	private String bukle;

	@ManyToOne
	@JoinColumn(name = "idIrankio",referencedColumnName="id",insertable=false, updatable=false)
	private Irankiai irankiai;  
	
	public Zurnalas() {
		
	}
	
	public Zurnalas(Integer id, String id_kliento, String id_irankio, String kada_isnuomota, String kada_grazinta,  String bukle) {
		
		this.id = id;
		this.id_kliento = id_kliento;
		this.idIrankio = id_irankio;
		this.kada_isnuomota = kada_isnuomota;	
		this.kada_grazinta = kada_grazinta;
		this.bukle = bukle;
	}
	
	public Zurnalas(String id, String id_kliento, String id_irankio, String kada_isnuomota, String kada_grazinta,
			String bukle) {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getId_kliento() {
		return id_kliento;
	}

	public void setId_kliento(String id_kliento) {
		this.id_kliento = id_kliento;
	}

	public String getIdIrankio() {
		return idIrankio;
	}

	public void setIdIrankio(String id_irankio) {
		this.idIrankio = id_irankio;
	}

	public String getKada_isnuomota() {
		return kada_isnuomota;
	}

	public void setKada_isnuomota(String kada_isnuomota) {
		this.kada_isnuomota = kada_isnuomota;
	}

	public String getKada_grazinta() {
		return kada_grazinta;
	}

	public void setKada_grazinta(String kada_grazinta) {
		this.kada_grazinta = kada_grazinta;
	}

	public String getBukle() {
		return bukle;
	}

	public void setBukle(String bukle) {
		this.bukle = bukle;
	}

	public Irankiai getIrankiai() {
		return irankiai;
	}

	public void setIrankiai(Irankiai irankiai) {
		this.irankiai = irankiai;
	}

	@Override
	public String toString() {
		return "Zurnalas [id=" + id + ", id_kliento=" + id_kliento + ", idIrankio=" + idIrankio + ", kada_isnuomota="
				+ kada_isnuomota + ", kada_grazinta=" + kada_grazinta + ", bukle=" + bukle + ", irankiai=" + irankiai
				+ "]";
	}
}