
package projects;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;


public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();

	//@formatter:off
	private List<String> operations = List.of(
			"1) Add a project" 
			);
	//@formatter:on
	
	
	// method that processes the menu
	
	public static void main(String[] args) {
		new ProjectsApp().processUserSelections();
	}

	/// proccessUsersSelections method, this displays menu selections, get selection from user and acts
	// now building on this adding case 1, page 10 homework
	
	private void processUserSelections() {
		boolean done = false;
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch(selection) {
				case -1:
					done = exitMenu();
					break;
					
				case 1:
					createProject();
					break;
				
				default:
					System.out.println("\n" + selection + "is not a valid selection. Try agian.");
				break;
				
				}
			}
			catch(Exception e) {
				System.out.println("\nERROR: " + e + " Try agian.");
				
			}
		}
	
	}
	
// method for createProject step 3, part 2 homework	
	private void createProject() {
		 String projectName = getStringInput("Enter the project name");
		    BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		    BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		    Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		    String notes = getStringInput("Enter the project notes");

		    Project project = new Project();

		    project.setProjectName(projectName);
		    project.setEstimatedHours(estimatedHours);
		    project.setActualHours(actualHours);
		    project.setDifficulty(difficulty);
		    project.setNotes(notes);

		    Project dbProject = projectService.addProject(project);
		    System.out.println("You have successfully created project: " + dbProject);

		
		
	}

	
	// Big decimal Method
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)){
	return null;

		}
		try	{
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}



	}
	
// Called when user wants to Exit
	
	private boolean exitMenu() {
		System.out.println("Exiting the Menu.");
		return true;
	}

	// step 5 create method getUserSelction 5a)call to the method printOperations 5b) method call to getInput(will return user's selection)
	// 5c) return statement to check if null or it returns input 

	private int getUserSelection() {
		printOprerations();
		
		Integer input = getIntInput("Enter a menu Selection");
		
		return Objects.isNull(input) ? -1 : input;
	}
	
	
	
	// step 6, creating method printOperations, use \n to print on separate lines, I guess this take no parameters and returns nothing it just prints
	// 6b) print all available menu selections, indent each line 2 spaces, I tried the "Lambda expression", I think I need to do a "for each method", or I can do an enhanced for loop, need to ask Mentor or rewatch vids or check solutions
	// I used an enhanced for loop I couldnt get Lambda to work
	
	private void printOprerations() {
	System.out.println("\nThese are available selections. Press the Enter Key to quit:");
	for(String line : operations){
		System.out.println("  " + line);
	}
	}
	
	// step 7 writing a method that returns an integer value, "getInput", accepts input from user and converts to integer, its called by getUserSelection
	// 7a assigning local variable name "input"
	// 7b test value in the variable input
	// 7c try/catch 2 test the value returned can be converted to an integer, catch should except a parameter or type numberformatexception
	
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number. try agian");
			}
	
	}
	
// step 8 create getStringInput (this REALLY prints the prompt and gets the input from user) this will be called on by other input methods
// keep cursor on same line just print not print Ln	

	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	}
	
// the instructions say at this point I should have no compile error, BUT I see LOT a RED on mine	
		
	
	
// step 9 adding code to process user selection, using a switch statement to process the selection, go back to processUserSelections method	
	
	
// step 10 is to run/test, talk about this when you make the video, ask michael whats up with my error	
	
}


	

