package console.academy;

public class Teacher extends Person {
	//멤버변수
	protected String subject;
	//인자 생성자
	public Teacher(Builder person, String subject) {
		super(person);
		this.subject = subject;
	}
	public Teacher(TeacherBuilder teacherBuilder) {}
	@Override
	public String get() {
		return String.format("%s, 과목:%s", super.get(), subject);
	}
	@Override
	public void print() {		
		super.print();
	}
	public static class TeacherBuilder{
		private int age;
		private String address;
		private String phoneNumber;
		private String identificationNumber;
		private String department;
		private String subject;
		public TeacherBuilder(int age) {
			this.age = age;
		}
		public TeacherBuilder address(String str) {
			address = str;
			return this;
		}
		public TeacherBuilder phoneNumber(String str) {
			phoneNumber = str;
			return this;
		}
		public TeacherBuilder identificationNumber(String str) {
			identificationNumber = str;
			return this;
		}
		public TeacherBuilder department(String str) {
			department = str;
			return this;
		}
		public TeacherBuilder subject(String str) {
			subject = str;
			return this;
		}
		public Teacher build() {
			return new Teacher(this);
		}
	}
	public void reSetter(TeacherBuilder tb) {
		this.age = tb.age;
		this.address = tb.address;
		this.phoneNumber = tb.phoneNumber;
		this.identificationNumber = tb.identificationNumber;
		this.department = tb.department;
		this.subject = tb.subject;
	}
}