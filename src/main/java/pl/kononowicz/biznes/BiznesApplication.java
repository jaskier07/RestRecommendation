package pl.kononowicz.biznes;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages={"pl.kononowicz.biznes.app.IProductCommentDAO", "pl.kononowicz.biznes.app.ProductCommentDAO", "pl.kononowicz.biznes.app.ProductComment", "pl.kononowicz.biznes.app.ProductCommentDAO", 
       // "pl.kononowicz.biznes.app.ProductCommentRepostitory", "pl.kononowicz.biznes.app.ProductCommentRowMapper", "pl.kononowicz.biznes.app.ProductCommentService", "pl.kononowicz.biznes.app.MainController"})
public class BiznesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiznesApplication.class, args);
	}
}

/*



(scanBasePackages={
"pl.kononowicz.biznes.MainController", "pl.kononowicz.biznes.Author"})
*/