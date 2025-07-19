package basics;

public class Student {
	private int id;
	private String name;
	private int age;
	private boolean isPassed;
	private Marks marks;
	
	
	
	
	
	public Student(int id, String name, int age, boolean isPassed, Marks marks) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.isPassed = isPassed;
		this.marks = marks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isPassed() {
		return isPassed;
	}
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	public Marks getMarks() {
		return marks;
	}
	public void setMarks(Marks marks) {
		this.marks = marks;
	}
	
	
	
}
