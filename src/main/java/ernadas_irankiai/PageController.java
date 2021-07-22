package ernadas_irankiai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ernadas_irankiai.IrankiaiRepository;
import ernadas_irankiai.Irankiai;
import ernadas_irankiai.Klientai;
import ernadas_irankiai.KlientaiRepository;
import ernadas_irankiai.Zurnalas;
import ernadas_irankiai.ZurnalasRepository;
import ernadas_irankiai.ZurnalasAtaskaita;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PageController {
	
	@Autowired
	private IrankiaiRepository irankiai_repository;
	
	@Autowired
	private KlientaiRepository klientai_repository;
	
	@Autowired
	private ZurnalasRepository zurnalas_repository;
	
	@Autowired 
	EntityManagerFactory factory;	
	
	public SessionFactory sessionFactory() {

		
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return factory.unwrap(SessionFactory.class);
}
	
	public String vardas_failo = "data.txt";
	
	@RequestMapping(path="/irankiai")
	public String irankiai(
			
		@RequestParam(name="id", required=false, defaultValue="0") String id,
		@RequestParam(name="pavadinimas", required=false, defaultValue="-") String pavadinimas,
		@RequestParam(name="irankio_tipas", required=false, defaultValue="-") String irankio_tipas,
		@RequestParam(name="inventoriaus_nr", required=false, defaultValue="-") String inventoriaus_nr,
		@RequestParam(name="isigyjimo_data", required=false, defaultValue="-") String isigyjimo_data,
		@RequestParam(name="isigyjimo_kaina", required=false, defaultValue="-") String isigyjimo_kaina,
		@RequestParam(name="nuomos_kaina", required=false, defaultValue="-") String nuomos_kaina,
		@RequestParam(name="send", required=false, defaultValue="-") String send,
		Model model
		) throws IOException {
		
		System.out.println(pavadinimas);
		
		String url_tpl = "irankiai";
	
		System.out.println(send);
		
		if ( ( send != null ) && send.equals ("siųsti") ) {
			rasomIFailaIrankiai(id, pavadinimas, irankio_tipas, inventoriaus_nr, isigyjimo_data, isigyjimo_kaina, nuomos_kaina );
			Irankiai irankiai = new Irankiai ( 
			
					( id.equals("0") ? null : Integer.parseInt (id) )
					, pavadinimas
					, irankio_tipas
					, inventoriaus_nr
					, isigyjimo_data
					, Double.parseDouble(isigyjimo_kaina)
					, Double.parseDouble(nuomos_kaina)
					);
			System.out.println(irankiai.toString());
			irankiai = irankiai_repository.save(irankiai);
			url_tpl = "redirect:zurnalas?id_irankio=" + irankiai.getId() /* + "&markes_pavadinimas=" + marke.getMarkes_pavadinimas() + "&markes_metai_nuo=" + marke.getAtidarymo_metai()*/;   //? get parametrai ieskomi po klaustuko surasomi parametrai kurie imami is get
		}
		Iterable<Irankiai> irankiai_visi = irankiai_repository.findAll();
		
		model.addAttribute ( "irankiai_visi", irankiai_visi );
		
		return url_tpl;
	}
	
	private void rasomIFailaIrankiai (
			String id,
			String pavadinimas,
			String irankio_tipas,
			String inventoriaus_nr,
			String isigyjimo_data,
			String isigyjimo_kaina,
			String nuomos_kaina
		
		) throws IOException { 
		
		System.out.println("irasinejam");
		File data_file = new File("data.txt");
		data_file =  createNewFile();
		FileWriter fw = new FileWriter("data.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(id);
		bw.newLine();
		bw.write(pavadinimas);
		bw.newLine();
		bw.write(irankio_tipas);
		bw.newLine();
		bw.write(inventoriaus_nr);
		bw.newLine();
		bw.write(isigyjimo_data);
		bw.newLine();
		bw.write(isigyjimo_kaina);
		bw.newLine();
		bw.write(nuomos_kaina);
		bw.newLine();
		bw.close();
	}	
	
	@RequestMapping(path="/klientai" , method={ RequestMethod.GET, RequestMethod.POST })
	public String klientai(
			
		@RequestParam(name="id", required=false, defaultValue="0") String id,
		@RequestParam(name="pav_kliento", required=false, defaultValue="-") String pav_kliento,
		@RequestParam(name="fizinis_juridinis", required=false, defaultValue="-") String fizinis_juridinis,
		@RequestParam(name="kontaktas", required=false, defaultValue="-") String kontaktas,
		@RequestParam(name="send", required=false, defaultValue="-") String send,
		Model model 
		) throws IOException {
		
		String url_tpl = "klientai";
		System.out.println(id);
		if ( ( send != null ) && send.equals ("siųsti") ) {
			rasomIFailaKlientai(id, pav_kliento, fizinis_juridinis, kontaktas );
			Klientai klientai = new Klientai ( 
			
					( id.equals("0") ? null : Integer.parseInt (id) )
					, pav_kliento
					, fizinis_juridinis
					, kontaktas
					);
			System.out.println(klientai.toString());
			klientai_repository.save(klientai);
			
		}
		model.addAttribute("klientas", klientai_repository.findById(Integer.parseInt (id)));
		return "klientai";	
	}
	
	private void rasomIFailaKlientai (
			String id,
			String pav_kliento,
			String fizinis_juridinis,
			String kontaktas
			
		) throws IOException { 
		

		System.out.println("irasinejam");
		File data_file = new File("data.txt");
		data_file =  createNewFile();
		FileWriter fw = new FileWriter("data.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(pav_kliento);
		bw.newLine();
		bw.write(fizinis_juridinis);
		bw.newLine();
		bw.write(kontaktas);
		bw.newLine();
	}
	
	@RequestMapping(path="/zurnalas" , method={ RequestMethod.GET, RequestMethod.POST })
	public String zurnalas(
			
		@RequestParam(name="id", required=false, defaultValue="0") String id,
		@RequestParam(name="id_kliento", required=false, defaultValue="-") String id_kliento,
		@RequestParam(name="id_irankio", required=false, defaultValue="-") String id_irankio,
		@RequestParam(name="kada_isnuomota", required=false, defaultValue="-") String kada_isnuomota,
		@RequestParam(name="kada_grazinta", required=false, defaultValue="-") String kada_grazinta,
		@RequestParam(name="bukle", required=false, defaultValue="-") String bukle,
		@RequestParam(name="isnuomoti", required=false, defaultValue="-") String isnuomoti,
		@RequestParam(name="grazinti", required=false, defaultValue="-") String grazinti,
		Model model 
		) throws IOException {
		
		String url_tpl = "zurnalas";
		System.out.println(id);
		if ( ( send != null ) && send.equals ("siųsti") ) {
			rasomIFailaZurnalas(id, id_kliento, id_irankio, kada_isnuomota, kada_grazinta, bukle );
			Zurnalas zurnalas = new Zurnalas ( 
			
					( id.equals("0") ? null : Integer.parseInt (id) )
					, id_kliento
					, id_irankio
					, kada_isnuomota
					, kada_grazinta
					, bukle
					);
			System.out.println(zurnalas.toString());
			zurnalas_repository.save(zurnalas);
			
		}
		model.addAttribute("irasas", zurnalas_repository.findById(Integer.parseInt (id)));
		return "zurnalas";	
	}
	
	private void rasomIFailaZurnalas (
			String id,
			String id_kliento,
			String id_irankio,
			String kada_isnuomota,
			String kada_grazinta,
			String bukle
			
		) throws IOException { 
		

		System.out.println("irasinejam");
		File data_file = new File("data.txt");
		data_file =  createNewFile();
		FileWriter fw = new FileWriter("data.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(id_kliento);
		bw.newLine();
		bw.write(id_irankio);
		bw.newLine();
		bw.write(kada_isnuomota);
		bw.newLine();
		bw.write(kada_grazinta);
		bw.newLine();
		bw.write(bukle);
		bw.newLine();
	}
	
	private File createNewFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(path="Nuomojam")
	public String skaitomFaila(Model model) throws IOException {
		
		String data_line ="";
		ArrayList<String> vieta = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader( new FileReader( vardas_failo) ); 
				data_line = br.readLine();
				
				int eilute = 0;
				
				while (( data_line = br.readLine() ) != null) {
					
					String eilute_eilute = data_line;
					
					vieta.add(eilute_eilute);
					System.out.println(eilute_eilute);
					eilute++;
				}
				
		} catch ( Exception e ) {
				e.printStackTrace();
		}
		model.addAttribute("vieta", vieta);
		model.addAttribute("nuskaityta_eilute", data_line);
	
		return "nuomojam";
	}
	@RequestMapping(path="/zurnalo-irasai" , method={ RequestMethod.GET, RequestMethod.POST })
	public String zurnaloIrasai(
		
		@RequestParam(name="pavadinimas", required=false, defaultValue="-") String pavadinimas,
		@RequestParam(name="kliento_pav", required=false, defaultValue="-") String kliento_pav,
		@RequestParam(name="id_kliento", required=false, defaultValue="-") String id_kliento,
		@RequestParam(name="id_irankio", required=false, defaultValue="-") String id_irankio, 
		@RequestParam(name="bukle", required=false, defaultValue="-") String bukle, 
		@RequestParam(name="fiziniai_juridiniai", required=false, defaultValue="-") String fizinis_juridinis, 
		@RequestParam(name="ieskoti", required=false, defaultValue="-") String ieskoti,
		Model model
		) throws IOException {
		System.out.println(pavadinimas + " " + kliento_pav + " " + fizinis_juridinis + " "+ bukle + " " + ieskoti);
		if ( ( ieskoti != null ) && ieskoti.equals ("ieskoti") ) {
			
			if(pavadinimas.equals("-") && kliento_pav.equals("-") && bukle.equals("-") && fizinis_juridinis.equals("-")){
				
				model.addAttribute("klientai", klientai_repository.findAll());
				
			/*} else { 
				
				if (!kliento_pav.equals("-")) {
					
					model.addAttribute("klientas", klientai_repository.findByKliento_pav(Integer.parseInt(kliento_pav)));
					
				} else {
			
					model.addAttribute("klientas", klientai_repository.findByFizinis_juridinis(Integer.parseInt(fizinis_juridinis)));
				
				}
			} 
		*/
			Iterable<Klientai> klientai_visi;
			
		} else {
			
			model.addAttribute("klientas", klientai_repository.findAll());
			
		}
		return "klientai_visi";		
	}
	
	@GetMapping(path="/zurnalas")
	public String ZurnalasIsnuomotiGrazinti(
				@RequestParam(name="id", required=false, defaultValue="0") String id,
				@RequestParam(name="id_kliento", required=false, defaultValue="-") String id_kliento,
				@RequestParam(name="id_irankio", required=false, defaultValue="-") String id_irankio,
				@RequestParam(name="kada_isnuomota", required=false, defaultValue="-") String kada_isnuomota,
				@RequestParam(name="kada_grazinta", required=false, defaultValue="-") String kada_grazinta,
				@RequestParam(name="bukle", required=false, defaultValue="-") String bukle,
				@RequestParam(name="isnuomoti", required=false, defaultValue="-") String isnuomoti,
				@RequestParam(name="grazinti", required=false, defaultValue="-") String grazinti,
			, Model model
	) throws IOException {
		
		
	}
}
}