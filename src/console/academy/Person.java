package console.academy;

public class Person{
	//멤버변수
	protected String name;
	protected int age;
	protected String address;
	protected String phoneNumber;
	protected String identificationNumber;
	protected String department;
	//기본생성자
	public Person() {}
	//인자 생성자
	public Person(Builder builder) {
		name = builder.name;
		age = builder.age;
		address = builder.address;
		phoneNumber = builder.phoneNumber;
		identificationNumber = builder.identificationNumber;
		department = builder.department;
	}
	public static class Builder{
		private String name;
		private int age;
		private String address;
		private String phoneNumber;
		private String identificationNumber;
		private String department;
		public Builder(String name, int age) {
			this.name = name;
			this.age = age;
		}
		public Builder address(String str) {
			address = str;
			return this;
		}
		public Builder phoneNumber(String str) {
			phoneNumber = str;
			return this;
		}
		public Builder identificationNumber(String str) {
			identificationNumber = str;
			return this;
		}
		public Builder department(String str) {
			department = str;
			return this;
		}
		public Person build() {
			return new Person(this);
		}
	}
	//멤버메소드
	public String get() {
		return String.format("이름:%s, 나이:%s, 주소:%s, 연락처:%s, 학번:%s, 학과:%s",
				name, age, address, phoneNumber, identificationNumber, department);
	}
	public void print() {
		System.out.println(get());
	}	
}
