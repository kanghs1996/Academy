package console.academy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcademyCollectionFileLogic {
	/* 멤버변수 */
	private Scanner in = new Scanner(System.in);
	private TreeMap<Character,List<Person>> personMap;
	private List<Person> person;
	public AcademyCollectionFileLogic() {
		personMap = new TreeMap<>();
		loadData();
	}
	/*멤버메소드 */
	/* 화면 출력관련 메소드들 */
	/* 메인메뉴 출력 메소드 */
	public void printMainMenu() {
		System.out.println("주소록 메뉴");
		System.out.println("〓〓〓〓〓〓");
		System.out.println(String.format("%-12s%s", "1.주소록 입력", "5.주소록 검색"));
		System.out.println(String.format("%-12s%s", "2.주소록 출력", "6.주속록 저장"));
		System.out.println(String.format("%-12s%s", "3.주소록 수정", "9.프로그램 종료"));
		System.out.print(String.format("%-12s%n%s","4.주소록 삭제", "메뉴를 입력하세요: "));		
	}
	/* 선택한 메뉴 출력 하는 메소드 */
	public void getSubMenu(int menuNum) {
		switch (menuNum) {
		case 1:
			while(true) {
				printInputSubMenu();
				int subMenuNum = selectMenu();
				if(subMenuNum == 3) break;
				switch (subMenuNum) {
					case 1:				
					case 2: setPerson(subMenuNum); break;					
					default:
						System.out.println("메뉴에 없는 번호입니다.");
				}
			}
			break;			
		case 2:
			printPerson();
			break;
		case 3:
			//updatePerson();
			while(true) {
				printUpdateSubMenu();
				int subMenuNum = selectMenu();
				if(subMenuNum == 3) break;
				switch (subMenuNum) {
					case 1:	updatePerson(); break;
					case 2: updatePersonIdentificationNumber(); break;					
					default:
						System.out.println("메뉴에 없는 번호입니다.");
				}
			}
			break;
		case 4:
			//deletePerson();
			while(true) {
				printDeleteSubMenu();
				int subMenuNum = selectMenu();
				if(subMenuNum == 3) break;
				switch (subMenuNum) {
					case 1:	deletePerson(); break;
					case 2: deletePersonIdentificationNumber(); break;					
					default:
						System.out.println("메뉴에 없는 번호입니다.");
				}
			}
			break;
		case 5:
			//findPerson();
			while(true) {
				printFindSubMenu();
				int subMenuNum = selectMenu();
				if(subMenuNum == 3) break;
				switch (subMenuNum) {
					case 1:	findPerson(); break;
					case 2: findPersonIdentificationNumber(); break;					
					default:
						System.out.println("메뉴에 없는 번호입니다.");
				}
			}
			break;
		case 6:
			savePerson();
			break;
		case 9:
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);
		default:
			System.out.println("메뉴에 없는 번호입니다.");
		}
	}
	/* 주소록 입력의 서브메뉴 출력 */
	private void printInputSubMenu() {
		System.out.println("주소록 메뉴");
		System.out.println("〓〓〓〓〓〓");
		System.out.print(String.format("%s%n%s%n%s%n%s","1.학생 정보 입력","2.교사 정보 입력",
				"3.메인메뉴로 돌아가기", "메뉴를 입력하세요: "));
	}
	/* 주소록 검색의 서브메뉴 출력 */
	private void printFindSubMenu() {
		System.out.println("주소록 메뉴");
		System.out.println("〓〓〓〓〓〓");
		System.out.print(String.format("%s%n%s%n%s%n%s","1.이름으로 검색","2.학번으로 검색",
				"3.메인메뉴로 돌아가기", "메뉴를 입력하세요: "));
	}
	/* 주소록 삭제의 서브메뉴 출력 */
	private void printDeleteSubMenu() {
		System.out.println("주소록 메뉴");
		System.out.println("〓〓〓〓〓〓");
		System.out.print(String.format("%s%n%s%n%s%n%s","1.이름으로 삭제","2.학번으로 삭제",
				"3.메인메뉴로 돌아가기", "메뉴를 입력하세요: "));
	}
	/* 주소록 수정의 서브메뉴 출력 */
	private void printUpdateSubMenu() {
		System.out.println("주소록 메뉴");
		System.out.println("〓〓〓〓〓〓");
		System.out.print(String.format("%s%n%s%n%s%n%s","1.이름으로 수정","2.학번으로 수정",				
				"3.메인메뉴로 돌아가기", "메뉴를 입력하세요: "));
	}
	/* 주소록 출력 메소드 */
	private void printPerson() {
		if(personMap.isEmpty()) {
			System.out.println("데이터가 없습니다.");
			return;
		}
		Set<Character> keys = personMap.keySet();
		for(Character key:keys) {
			System.out.printf("[%c로 시작하는 명단]%n",key);
			List<Person> vals=personMap.get(key);	
			sort(vals);
			for(Person val:vals)
				System.out.println(val.get());
		}
	}
	/* List<person> 오름차순 정렬 */
	private void sort(List<Person> vals) {
		Collections.sort(vals, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.name.compareTo(o2.name);
			}
		});
	}
	/* 주소록 이름 검색 메소드 */
	private void findPerson() {
		System.out.print("검색할 이름을 입력하세요: ");
		String keyName;
		boolean found = false;
		char jaeum;
		while(true) {
			keyName = in.nextLine().trim();
			jaeum = getJaeum(keyName);
			if(jaeum == '0') System.out.println("한글만 입력해주세요.");
			else break;
		}		
		List<Person> vals=personMap.get(jaeum);			
		for(Person val:vals)
			if(val.name.equals(keyName)) {
				System.out.println(val.get());
				found = true;
				//return;
			}
		if(!found)
			System.out.println("검색된 사람이 없습니다.");
	}
	/* 주소록 학번 검색 메소드 */
	private void findPersonIdentificationNumber() {
		System.out.print("검색할 학번을 입력하세요: ");
		String keyNum = in.nextLine().trim();
		boolean found = false;
		Set<Character> keys = personMap.keySet();
		for(Character key : keys) {
			List<Person> vals=personMap.get(key);			
			for(Person val:vals)
				if(val.identificationNumber.equals(keyNum)) {
					System.out.println(val.get());
					found = true;
					//return;
				}
		}
		if(!found)
			System.out.println("검색된 학번이 없습니다.");
	}
	/* 사용자 입력관련 메소드들 */
	/* 메인메뉴에 대한 사용자 입력 얻어오는 메소드 */
	public int selectMenu() {
		try {
			String selectMenu = in.nextLine().trim();
			int menuNum = Integer.parseInt(selectMenu);
			return menuNum;
		}catch (NumberFormatException e) { System.out.println("숫자만 입력해주세요."); }
		return 0;
	}
	/* 주소록 입력받기 */
	private void setPerson(int subMenuNum) {
		System.out.print("이름을입력하세요: ");
		String name;
		char jaeum;
		while(true) {
			name = in.nextLine().trim();
			jaeum = getJaeum(name);
			if(jaeum == '0') System.out.println("한글만 입력해주세요");
			else break;
		}		
		int age;
		while(true) {
			try {
				System.out.print("나이를입력하세요: ");
				age = Integer.parseInt(in.nextLine().trim());				
				break;
			}catch (NumberFormatException e) { System.out.println("숫자만 입력해주세요."); }
		}
		System.out.print("주소를입력하세요: ");
		String address = in.nextLine().trim();
		System.out.print("연락처를입력하세요: ");
		String phoneNumber = in.nextLine().trim();
		System.out.print("학번을 입력하세요: ");
		String identificationNumber = in.nextLine().trim();
		System.out.print("학과를 입력하세요: ");
		String department = in.nextLine().trim();
		Person.Builder builder = new Person.Builder(name,age).
				address(address).phoneNumber(phoneNumber).
				identificationNumber(identificationNumber).
				department(department);
		switch (subMenuNum) {
			case 1:
				System.out.print("학년을 입력하세요: ");
				String grader = in.nextLine().trim();				
				Student st = new Student(builder, grader);
				if(personMap.containsKey(jaeum)) {
					person = personMap.get(jaeum);
				}
				else {
					person = new Vector<>();
				}
				person.add(st);
				personMap.put(jaeum, person);
				return;
			case 2:
				System.out.print("과목을입력하세요: ");
				String subject = in.nextLine().trim();
				Teacher te = new Teacher(builder, subject);
				if(personMap.containsKey(jaeum)) {
					person = personMap.get(jaeum);
				}
				else {
					person = new Vector<>();										
				}
				person.add(te);
				personMap.put(jaeum, person);
		}
	}
	/* personMap 키값 세팅에 관련된 메소드 */
	public static char getJaeum(String name) {
		char[] jaeum = name.trim().toCharArray();
		char[] startChar= {'가','나','다','라','마','바','사','아','자','차','카','타','파','하'};
		char[] endChar= {'낗','닣','띻','맇','밓','삫','앃','잏','찧','칳','킿','팋','핗','힣'};
		char[] returnJaeum= {'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i=0;i < startChar.length;i++)
			if(jaeum[0] >=startChar[i] &&  jaeum[0] <= endChar[i])
				return returnJaeum[i];
		return '0';//자음이 아닌 경우
	}
	/* 이름으로 저장된 person 값 수정 메소드 */
	private void updatePerson() {
		System.out.print("수정할 이름을 입력하세요: ");
		String keyName;
		char jaeum;
		boolean found = false;
		while(true) {
			keyName = in.nextLine().trim();
			jaeum = getJaeum(keyName);
			if(jaeum == '0') System.out.println("한글만 입력해주세요");
			else break;
		}		
		List<Person> vals=personMap.get(jaeum);			
		for(Person val:vals) {
			int count = 0;
			for(int i=0; i<vals.size(); i++)
				if(vals.get(i).name.equals(keyName)) count++;
			if(count >= 2) {
				System.out.println("동일한 데이터값(이름)을 갖는 데이터가 " + (count-1) + "개 있어 수정이 불가합니다.");
				System.out.println("수정을 원하시는 데이터의 학번을 알아내 학번으로 삭제해주세요.");
				return;
			}
			if(val.name.equals(keyName)) {
				int age;
				while(true) {
					try {
						System.out.print("나이를입력하세요: ");
						age = Integer.parseInt(in.nextLine().trim());				
						break;
					}catch (NumberFormatException e) { System.out.println("숫자만 입력해주세요."); }
				}
				System.out.print("주소를입력하세요: ");
				String address = in.nextLine().trim();
				System.out.print("연락처를입력하세요: ");
				String phoneNumber = in.nextLine().trim();
				System.out.print("학번을 입력하세요: ");
				String identificationNumber = in.nextLine().trim();
				System.out.print("학과를 입력하세요: ");
				String department = in.nextLine().trim();					
				if(val instanceof Student) {
					System.out.print("학년을 입력하세요: ");
					String grader = in.nextLine().trim();
					Student.StudentBuilder sb = new Student.StudentBuilder(age).
							address(address).phoneNumber(phoneNumber).
							identificationNumber(identificationNumber).
							department(department).grader(grader);
					((Student) val).reSetter(sb);	
					found = true;
				}
				else if(val instanceof Teacher){
					System.out.print("과목을입력하세요: ");
					String subject = in.nextLine().trim();
					Teacher.TeacherBuilder sb = new Teacher.TeacherBuilder(age).
							address(address).phoneNumber(phoneNumber).
							identificationNumber(identificationNumber).
							department(department).subject(subject);
					((Teacher) val).reSetter(sb);
					found = true;
				}
			}
		}
		if(!found) System.out.println("검색된 사람이 없습니다.");
		else System.out.println("수정 되었습니다.");
	}
	/* 학번으로 저장된 person 값 수정 메소드 */
	private void updatePersonIdentificationNumber() {
		System.out.println("이름을 수정하거나 동명이인 구별해 수정을 위한 메소드");
		System.out.print("수정할 학번을 입력하세요: ");
		String keyNum = in.nextLine().trim();		
		boolean found = false;
		boolean isStudent = false;
		Set<Character> keys = personMap.keySet();
		for(Character key : keys) {
			List<Person> vals = personMap.get(key);			
			for(Person val:vals) 				
				if(keyNum.equals(val.identificationNumber)) {
					if(val instanceof Student) isStudent = true;
					found = true;
					char jaeum = getJaeum(val.name);					
					vals.remove(val);
					if(vals.size() == 0) personMap.remove(jaeum);		
					break;
				}			
			if(found) break;
		}
		if(found) {
			System.out.print("이름을입력하세요: ");
			String name;
			char jaeum;
			while(true) {
				name = in.nextLine().trim();
				jaeum = getJaeum(name);
				if(jaeum == '0') System.out.println("한글만 입력해주세요");
				else break;
			}		
			int age;
			while(true) {
				try {
					System.out.print("나이를입력하세요: ");
					age = Integer.parseInt(in.nextLine().trim());				
					break;
				}catch (NumberFormatException e) { System.out.println("숫자만 입력해주세요."); }
			}
			System.out.print("주소를입력하세요: ");
			String address = in.nextLine().trim();
			System.out.print("연락처를입력하세요: ");
			String phoneNumber = in.nextLine().trim();			
			System.out.print("학과를 입력하세요: ");
			String department = in.nextLine().trim();
			Person.Builder builder = new Person.Builder(name,age).
					address(address).phoneNumber(phoneNumber).
					identificationNumber(keyNum).
					department(department);
			if(isStudent) {
				System.out.print("학년을 입력하세요: ");
				String grader = in.nextLine().trim();				
				Student st = new Student(builder, grader);
				if(personMap.containsKey(jaeum)) {
					person = personMap.get(jaeum);
				}
				else {
					person = new Vector<>();
				}
				person.add(st);
				personMap.put(jaeum, person);					
			}else {
				System.out.print("과목을입력하세요: ");
				String subject = in.nextLine().trim();
				Teacher te = new Teacher(builder, subject);
				if(personMap.containsKey(jaeum)) {
					person = personMap.get(jaeum);
				}
				else {
					person = new Vector<>();										
				}
				person.add(te);
				personMap.put(jaeum, person);
			}	
		}
		if(!found) System.out.println("검색된 사람이 없습니다.");
		else System.out.println("수정 되었습니다.");
	}
	/* 이름으로 저장된 person값 삭제 */
	private void deletePerson() {
		System.out.print("삭제할 이름을 입력하세요: ");
		String keyName;
		char jaeum;
		while(true) {
			keyName = in.nextLine().trim();
			jaeum = getJaeum(keyName);
			if(jaeum == '0') System.out.println("한글만 입력해주세요.");
			else break;
		}		
		List<Person> vals=personMap.get(jaeum);			
		for(Person val:vals) {
			int count = 0;
			for(int i=0; i<vals.size(); i++)
				if(vals.get(i).name.equals(keyName)) count++;
			if(count >= 2) {
				System.out.println("동일한 데이터값(이름)을 갖는 데이터가 " + (count-1) + "개 있어 삭제가 불가합니다.");
				System.out.println("삭제를 원하시는 이름의 학번을 알아내 학번으로 삭제해주세요.");
				return;
			}
			if(keyName.equals(val.name)) {
				System.out.println(val.name + "의 정보가 삭제되었습니다.");
				vals.remove(val);
				if(vals.size() == 0) personMap.remove(jaeum);	
				return;
			}
		}
		System.out.println("검색된 사람이 없습니다.");
	}
	/* 학번으로 저장된 person값 삭제 */
	private void deletePersonIdentificationNumber() {
		System.out.print("삭제할 학번을 입력하세요: ");
		String keyNum = in.nextLine().trim();
		Set<Character> keys = personMap.keySet();
		for(Character key: keys) {		
			List<Person> vals=personMap.get(key);			
			for(Person val:vals) {
				if(keyNum.equals(val.identificationNumber)) {
					System.out.println(val.name + "의 정보가 삭제되었습니다.");
					char jaeum = getJaeum(val.name);
					vals.remove(val);
					if(vals.size() == 0) personMap.remove(jaeum);	
					return;
				}
			}
		}
		System.out.println("검색된 사람이 없습니다.");
	}
	/* 저장된 값을 파일로 저장 */
	private void savePerson() {		
		if(personMap.isEmpty()) {
			System.out.println("저장할 데이터가 없습니다.");
			return;
		}
		String path = System.getProperty("user.home");
		File mkdir = new File(path + "/Academy");
		if(!mkdir.exists()) mkdir.mkdirs();
		PrintWriter out = null;
		try {
			out = new PrintWriter(new OutputStreamWriter
					(new FileOutputStream(path + "/Academy/AcademyFileData.txt"), "UTF-8"), true);
			Set<Character> keys = personMap.keySet();
			for(Character key:keys) {
				List<Person> vals=personMap.get(key);			
				for(Person val:vals) {
					out.println(val.get());					
				}
			}
			System.out.println("저장 성공");
		} catch (IOException e) {
			System.out.println("파일 저장 오류: " + e.getMessage());
		}finally {
			if(out != null) { out.close();}
		}		
	}
	/* 저장된 파일이 있으면 불러오기 */
	private void loadData() {
		BufferedReader br = null;
		String path = System.getProperty("user.home");
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path + "/Academy/AcademyFileData.txt"), "UTF-8"));
			String data;
			char jaeum;
			String regex="\\W{2}:(\\W{3,5}),\\s"
					+ "\\W{2}:(\\d{2,3}),\\s"
					+ "\\W{2}:(.+),\\s"
					+ "\\W{3}:(.+),\\s"
					+ "\\W{2}:(.+),\\s"
					+ "\\W{2}:(.+),\\s"
					+ "(\\W{2}):(.+)";
			while((data = br.readLine()) != null) {
				Pattern pattern= Pattern.compile(regex);
				Matcher matcher= pattern.matcher(data);		
				if(matcher.matches()) {
					jaeum = getJaeum(matcher.group(1));
					Person.Builder builder = new Person.Builder(matcher.group(1), Integer.parseInt(matcher.group(2))).
							address(matcher.group(3)).phoneNumber(matcher.group(4)).
							identificationNumber(matcher.group(5)).
							department(matcher.group(6));
					if(matcher.group(7).equals("학년")) {
							Student st = new Student(builder, matcher.group(8));
							if(personMap.containsKey(jaeum)) {
								person = personMap.get(jaeum);
							}
							else {
								person = new Vector<>();
							}
							person.add(st);
							personMap.put(jaeum, person);
					}else {
							Teacher te = new Teacher(builder, matcher.group(8));
							if(personMap.containsKey(jaeum)) {
								person = personMap.get(jaeum);
							}
							else{
								person = new Vector<>();										
							}
							person.add(te);
							personMap.put(jaeum, person);
					}
				}
			}
		}catch(IOException e) { /* System.out.println("파일 로드 오류: " + e.getMessage()); */}
		finally {
			try { if(br !=null) br.close(); }
			catch (IOException e) { System.out.println("스트림 오류" + e.getMessage());}
		}
	}
}
