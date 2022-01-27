package console.academy;

public class AcademyApp {
	
	public static void main(String[] args) {
		AcademyCollectionFileLogic logic = new AcademyCollectionFileLogic();
		while(true) {
			logic.printMainMenu();
			int menuNum = logic.selectMenu();
			logic.getSubMenu(menuNum);
		}
	}
}
