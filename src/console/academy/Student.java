package console.academy;

public class Student extends Person {	
	protected String grader;	
	//인자 생성자
	public Student(Builder person, String grader) {
		super(person);
		this.grader = grader;
	}
	public Student(StudentBuilder studentBuilder) { }
	@Override
	public String get() {		
		return String.format("%s, 학년:%s", super.get(), grader);
	}
	@Override
	public void print() {		
		super.print();
	}	
	public void setGrader(String grader) {
		this.grader = grader;
	}
	public static class StudentBuilder{
		private int age;
		private String address;
		private String phoneNumber;
		private String identificationNumber;
		private String department;
		private String grader;
		public StudentBuilder(int age) {
			this.age = age;
		}
		public StudentBuilder address(String str) {
			address = str;
			return this;
		}
		public StudentBuilder phoneNumber(String str) {
			phoneNumber = str;
			return this;
		}
		public StudentBuilder identificationNumber(String str) {
			identificationNumber = str;
			return this;
		}
		public StudentBuilder department(String str) {
			department = str;
			return this;
		}
		public StudentBuilder grader(String str) {
			grader = str;
			return this;
		}
		public Student build() {
			return new Student(this);
		}
	}
	public void reSetter(StudentBuilder sb) {
		this.age = sb.age;
		this.address = sb.address;
		this.phoneNumber = sb.phoneNumber;
		this.identificationNumber = sb.identificationNumber;
		this.department = sb.department;
		this.grader = sb.grader;
	}
	
}