package section01.dto;

public class Child extends Parent{
	
	private String car;
	
	public Child() {
		super();
	}
	
	// 매개 변수 있는 생성자
	public Child(String lastName, int money, String car) {
		super(lastName, money); // 부모 매개변수 있는 생성자 호출
		this.car = car;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " / Child [car = " + car + "]";
	}
}
