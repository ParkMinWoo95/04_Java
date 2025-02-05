package section03.run;

import section03.dto.Calculator;
import section03.dto.PMWCalculator;
import section03.dto.PMWCalculator2;

public class InterfaceRun {
	public static void main(String[] args) {
		
		// 인터페이스를 부모 타입 참조 변수로 하여 Calculator 구현체 생성하기
		// (업캐스팅 + 동적바인딩)
		
		// Calculator calc = new PMWCalculator();
		Calculator calc = new PMWCalculator2();
		
		System.out.println(calc.plus(1234, 5678));
		System.out.println(calc.minus(5678, 1234));
		System.out.println(calc.multi(33333, 44444));
		System.out.println(calc.div(1111, 33));
		System.out.println(calc.mod(1111, 33));
		System.out.println(calc.pow(2, 8));
		System.out.println(calc.areaOfCircle(10));
	}
}
