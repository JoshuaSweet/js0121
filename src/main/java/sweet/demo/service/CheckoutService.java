package sweet.demo.service;

import static org.springframework.web.util.HtmlUtils.htmlUnescape;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import sweet.demo.form.CheckoutForm;
import sweet.demo.model.RentalAgreement;
import sweet.demo.model.Tool;
import sweet.demo.repository.RentalAgreementRepository;
import sweet.demo.repository.ToolRepository;

/**
 * Provides checkout service operations like taking pre-validated checkout
 * form, creating rental agreement from valid form data, and persisting
 * agreement data.
 * Provides layer between controller and persistence.
 * @author Sweet
 *
 */
@Service
public class CheckoutService {

	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private RentalAgreementRepository rentalAgreementRepository;
	
	/**
	 * Find all tools.
	 * @return
	 */
	public List<Tool> findAllTools() {
		
		return (List<Tool>) toolRepository.findAll();
		
	}
	
	/**
	 * Find all, update model, and return template.
	 * @param model
	 * @return
	 */
	public String findAllToolsCheckoutTemplate( Model model ) {

		List<Tool> tools = (List<Tool>) toolRepository.findAll();
		model.addAttribute( "tools", tools );
		return "checkout.html";	
		
	}
	
	/**
	 * Find by tool code.
	 * @param toolCode
	 * @return
	 */
	public Optional<Tool> findToolsByToolCode( String toolCode ) {
		
		Optional<Tool> tool = (Optional<Tool>) toolRepository.findByToolCode( toolCode );
		return tool;
		
	}
		
	/**
	 * Find rental agreements for a particular tool code.
	 * @param toolCode
	 * @return
	 */
	public List<RentalAgreement> findRentalAgreementsByToolCode( String toolCode ) {
		
		List<RentalAgreement> rentalAgreements = (List<RentalAgreement>) rentalAgreementRepository.findByToolCode( toolCode );
		return rentalAgreements;
		
	}
	
	/**
	 * Find all rental agreements.
	 * @return
	 */
	public List<RentalAgreement> findAllRentalAgreements() {
		
		List<RentalAgreement> rentalAgreements = (List<RentalAgreement>) rentalAgreementRepository.findAll();
		return rentalAgreements;
		
	}

	/**
	 * Find tool by uuid.
	 * @param toolId
	 * @return Tool or null
	 */
	public Optional<Tool> findById( String toolId ) {
		
		UUID toolUUID = UUID.fromString( toolId );
		Optional<Tool> tool = (Optional<Tool>) toolRepository.findById( toolUUID );
		return tool;
		
	}
	
	/**
	 * Assumes binding result has already been generated from evaluating validation configuration.
	 * When returning this error, the page will have the form data available, but the tools option set will need to be provided.
	 * @param model
	 * @return template checkout.html
	 */
	public String respondWithValidationErrors( Model model ) {
		
		List<Tool> tools = (List<Tool>) toolRepository.findAll();
		model.addAttribute( "tools", tools );
		return "checkout.html";
		
	}
	
	/**
	 * Assumes validation of checkout form data has already been accomplished and passed.
	 * Checks that tool specified in checkout exists since form validation does not check existence.
	 * Creates agreement record and stores in database.
	 * Logs agreement data to console.
	 * @param checkoutForm acquire data directly from form which already passed validation
	 * @param model required for adding data to template
	 * @return template rentalAgreement.html
	 */
	public String respondWithNewRentalAgreement( CheckoutForm checkoutForm, Model model ) {
		
		String toolCode = checkoutForm.getToolCode();
		Optional<Tool> optionalTool = (Optional<Tool>) toolRepository.findByToolCode( toolCode );
		
		if ( optionalTool.isEmpty() ) {
			
			String header = htmlUnescape( "Error: Tool Code does not exist." );
			String message = htmlUnescape( "The provided tool code of \"" + toolCode + "\" does not exist in the system. Please contact a system administrator." );			
			model.addAttribute( "errorHeader", header );
			model.addAttribute( "errorMessage", message );
			
			return "error.html";
			
		} else {
			
			Tool tool = optionalTool.get();
			RentalAgreement rentalAgreement = new RentalAgreement( tool, checkoutForm );
			
			try {
				
				/**
				 * DEMO NOTE:
				 * We are persisting the rental agreement here. It is conceivable the business would want 
				 * to analyze rental data over time to determine trends or even something simple like
				 * discontinuing tool that are costing more to maintain than how much revenue they 
				 * generate through renting.  I don't have enough requirements to do this properly for
				 * the demo of course, but maybe good to be a little forward looking.  Would really only
				 * want to persist agreements that were sale related and not just inquiry.
				 */
				rentalAgreementRepository.save( rentalAgreement );
				
			} catch ( Exception e ) {				
				
				String header = htmlUnescape( "Error: Could not create rental agreement." );//DEMO NOTE: nothing really to unescape here but this does indicate that anything special does need to be escaped should this message change.
				String message = htmlUnescape( "Please contact system administrator." );
				model.addAttribute( "errorHeader", header );
				model.addAttribute( "errorMessage", message );
				return "error.html";
				
			}
						
			model.addAttribute( "toolCode" , rentalAgreement.getToolCode() );
			model.addAttribute( "toolType" , rentalAgreement.getType().getName() );
			model.addAttribute( "toolBrand" , rentalAgreement.getBrand().getName() );
			model.addAttribute( "rentalDays" , rentalAgreement.getRentalDays() );
			model.addAttribute( "checkoutDate" , rentalAgreement.getCheckoutDateFormattedString() );
			model.addAttribute( "dueDate" , rentalAgreement.getDueDateFormattedString() );
			model.addAttribute( "dailyRentalCharge" , rentalAgreement.getDailyRentalChargeString() );
			model.addAttribute( "chargeDays" , rentalAgreement.getChargeDays() );
			model.addAttribute( "preDiscountCharge" , rentalAgreement.getPreDiscountChargeString() );
			model.addAttribute( "discountPercent" , rentalAgreement.getDiscountPercentString() );
			model.addAttribute( "discountAmount" , rentalAgreement.getDiscountAmountString() );
			model.addAttribute( "finalCharge" , rentalAgreement.getFinalChargeString() );
			rentalAgreement.printToConsole();
			return "rentalAgreement.html";
			
		}
		
	}
	
}
