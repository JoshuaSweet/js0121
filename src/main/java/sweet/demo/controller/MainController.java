package sweet.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sweet.demo.form.CheckoutForm;
import sweet.demo.model.RentalAgreement;
import sweet.demo.model.Tool;
import sweet.demo.service.CheckoutService;

@Controller
@RequestMapping(path="/")
public class MainController {
		
	private static final Logger log = LogManager.getLogger( MainController.class );
	
	@Autowired
	private CheckoutService checkoutService;

	/**
	 * Gets the form used by the client to attempt checkout.
	 * @param model
	 * @param checkoutForm
	 * @return template for checkout
	 */
	@GetMapping("/checkout")
	public String index( Model model, CheckoutForm checkoutForm ) {
		
		return checkoutService.findAllToolsCheckoutTemplate( model );		
		
	}
	
	/**
	 * Receives and processes checkout form from client.  If validation fails, validation 
	 * failures are returned to the client and displayed by the applicable field.
	 * If form validation succeeds, then form data is passed to checkout service to complete
	 * checkout which includes creating and persisting the rental agreement.
	 * The rental agreement is then sent to and displayed client side.
	 * @param checkoutForm
	 * @param bindingResult
	 * @param model
	 * @return rental agreement template if checkout form data valid, checkout template with error otherwise
	 */
	@PostMapping("/checkout")
	public String getRentalAgreement( @Valid CheckoutForm checkoutForm, BindingResult bindingResult, Model model ) {
		
		if ( bindingResult.hasErrors() ) {
						
			return checkoutService.respondWithValidationErrors( model );
			
		} else {
			
			return checkoutService.respondWithNewRentalAgreement( checkoutForm, model );
						
		}
		
	}
	
	/**
	 * DEMO NOTE:
	 * To expedite building the demo, I decided to use templates, but there is no reason why
	 * we wouldn't want a REST api which would be could be useful in decoupling client and
	 * server side tech to facilitate independent uplifts/upgrades on either end.  If you are 
	 * running the app, the end points below will return json with nested data as applicable.
	 * Keep in mind, this demo uses an in mem db for simplicity so new data is wiped on shutdown. 
	 */
	
	@GetMapping("/tools/all")
	@ResponseBody
	public List<Tool> getAllTools() {		
		
		List<Tool> tools = (List<Tool>) checkoutService.findAllTools();
		return tools;
		
	}
	
	@GetMapping(path="/tools/{toolId}")
	@ResponseBody
	public Optional<Tool> getToolById(@PathVariable("toolId") String toolId, HttpServletRequest request, HttpSession session ) {
		
		Optional<Tool> tool = checkoutService.findById( toolId );
		return tool;
	    
	}
	
	@GetMapping(path="/toolscoded/{toolCode}")
	@ResponseBody
	public Optional<Tool> getToolByToolCode(@PathVariable("toolCode") String toolCode, HttpServletRequest request, HttpSession session ) {

		Optional<Tool> tool = (Optional<Tool>) checkoutService.findToolsByToolCode( toolCode );
		return tool;
	    
	}
	
	@GetMapping(path="/rentalagreementscoded/{toolCode}")
	@ResponseBody
	public List<RentalAgreement> getRentalAgreementsByToolCode(@PathVariable("toolCode") String toolCode, HttpServletRequest request, HttpSession session ) {

		List<RentalAgreement> rentalAgreement = (List<RentalAgreement>) checkoutService.findRentalAgreementsByToolCode( toolCode );
		return rentalAgreement;
	    
	}
	
	@GetMapping(path="/rentalagreements/all")
	@ResponseBody
	public List<RentalAgreement> getAllRentalAgreements() {

		List<RentalAgreement> rentalAgreement = (List<RentalAgreement>) checkoutService.findAllRentalAgreements();
		return rentalAgreement;
	    
	}

}