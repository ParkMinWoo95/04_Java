package section03.dto;

/**	Calculator 인터페이스 구현체
 * - 모든 인터페이스 구현체는 같은 형태를 띄고 있다
 */

public class PMWCalculator implements Calculator{


	@Override
	public int plus(int a, int b) {
		return checkNum(a+b);
	}

	@Override
	public int minus(int a, int b) {
		return checkNum(a-b);
	}

	@Override
	public int multi(int a, int b) {
		return checkNum(a*b);
	}

	@Override
	public double div(int a, int b) {
		return checkNum(a/b);
	}

	@Override
	public int mod(int a, int b) {
		return checkNum(a%b);
	}

	@Override
	public int pow(int a, int x) {
		int result = 1;
		
		for(int i=0; i<x; i++) {
			result *= a;
		}
		return checkNum(result);
	}

	@Override
	public double areaOfCircle(double r) {
		double check = PI*r*r;
		double num;
		
		if(check > MAX_NUM) {
			num = MAX_NUM;
		} else if(check < MIN_NUM) {
			num = MIN_NUM;
		} else {
			num = check;
		}
		return num;
	}

}
