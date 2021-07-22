package ernadas_irankiai;

import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;

import tado.automobiliai.TopAuto;

public class ZurnalasAtaskaita {

	 protected Session em;	
		
	  public ZurnalasAtaskaita(  Session em  ) {
		  
		    this.em = em;
	  }
	  
	  public List<Zurnalas> zurnalas( String id_kliento, String id_irankio, String kada_isnuomota, String kada_grazinta, String bukle ) {
		  	
		  	String qw_zurnalas =
		  			
		  			"SELECT SQL_CALC_FOUND_ROWS " 
		  			  		+ 							"`klientai`.`id` AS `id_kliento` "
		  			  		+							"`irankiai`.`id` AS `id_irankio"
		  			  		+							"`zurnalas`.`kada_isnuomota` AS `kada_isnuomota`"
		  			  		+							"`zurnalas`.`kada_grazinta` AS `kada_grazinta`"
		  			  		+							"`zurnalas`.`bukle` AS `bukle`"
		  			  		+							"COUNT(`zurnalas`.`id`) AS `isnuomota_kartu`"
		  			  		+							"SUM(IF (`klientai`.`fizinis_juridinis`='fizinis', 1, 0)) AS `isnuomota_kartu_fiziniams`"
		  			  		+							"SUM(IF (`klientai`.`fizinis_juridinis`='juridinis', 1, 0)) AS `isnuomota_kartu_j`"
		  			  		+ "FROM"
		  			  		+		"`modelis`"
		  			  		+ "LEFT JOIN"
		  			  		+ 		"`irankiai` ON ("		
		  			  		+ 						"`irankiai`.`id`=`zurnalas`.`id_irankio`"
		  			  		+		" )"
			  			  	+ "LEFT JOIN"
		  			  		+ 		"`klientai` ON ("		
		  			  		+ 						"`klientai`.`id`=`zurnlas`.`id_kliento`"
		  			  		+		" )"
		  			  		
		  					+ " GRUOP BY"
		  					+		" `irankiai`.`id` "
		  					+ " ORDER BY"
		  					+		"`isnuomota_kartu` DESC"
		  			  		;
		  	System.out.println ( qw_zurnalas );
		    Query query = em.createNativeQuery ( qw_zurnalas );
		    return (List<Zurnalas>) query.getResultList();
	  }
}
