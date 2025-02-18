package service;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import dto.Member;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* 왜 Service, Dao 인터페이스를 만들어서 구현했을까?
 * - 인터페이스를 상속 받아 구현하면
 *   모든 자식 클래스가 똑같은 기능을 가지게되어
 *   비슷하게 생김!
 *
 *  -> 언제든지 서로 다른 자식 클래스로 대체 가능!!
 *    ==> 유지보수성 증가
 */

// MemberService를 상속 받아 구현
public class MemberServiceImpl implements MemberService{

    // dao 객체 부모 참조 변수 선언
    private MemberDAO dao = null;
    private String[] gradeArr = {"일반", "골드", "다이아"};


    // 기본 생성자
    // - MemberServiceImpl 객체 생성 시
    //   MemberDAOImpl 객체도 생성
    public MemberServiceImpl() 
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        dao = new MemberDAOImpl();
    }

    
    //********************************
    // 추가, 수정 삭제 기능이 수행되면
    // 무조건 dao.saveFile() 수행!
    //********************************

    // 회원 추가
    @Override
    public boolean addMember(String name, String phone) throws IOException {
  		try {
  			dao = new MemberDAOImpl();
  		} catch (Exception e) {

  			System.out.println("*** 프로그램 실행 중 오류 발생 ***");
  			e.printStackTrace();
  			System.exit(0); // 프로그램 종료
  		}
  		Member member = new Member();
  		member.setName(name);
  		member.setPhone(phone);
  		member.setAmount(0);
  		member.setGrade(0);
  		
  		dao.addMember(member);
       
  		return false;
    }


    // DAO에서 조회한 memberList를 그대로 반환
    @Override
    public List<Member> getMemberList() {
        
    	// dao에서 반환 받은 memberList를 그대로 view로 리턴
    	return dao.getMemberList();
    }


    // 이름 검색
    @Override
    public List<Member> selectName(String searchName) {
    	
    	List<Member> memberList = getMemberList().stream()
          .filter(member -> member.getName().contains(searchName))
          .collect(Collectors.toList());
    	
    	memberList.stream().forEach(member -> {
  			System.out.println(member);
  		});
    	
        return memberList;
    }


    // 금액 누적
    @Override
    public String updateAmount(Member target, int acc) throws IOException {
    	
    	int sum = target.getAmount() + acc;
      
    	System.out.printf("%d -> %d\n", target.getAmount(), sum);
    	
    	target.setAmount(sum);
    	
    	if(sum < 100000) {
    		target.setGrade(0);
    		System.out.println("현재 일반 등급입니.\n");
    	}
    	if(sum >= 100000 && sum <1000000) {
    		target.setGrade(1);
    		System.out.println("현재 골드 등급입니다\n");
    	}
    	if(sum >= 1000000) {
    		target.setGrade(2);
    		System.out.println("현재 다이아 등급입니다\n");
    	}
    	
    	dao.saveFile();
    	
    	return null; // 결과 문자열 반환
        
        //ex)
        // 2000 -> 100000
        // * 골드 * 등급으로 변경 되셨습니다
    }


    //회원 정보(전화번호) 수정
    @Override
    public String updateMember(Member target, String phone) throws IOException {

    	System.out.printf("%s님의 전화번호가 변경되었습니다\n", target.getName());
    	System.out.printf("%s -> %s\n", target.getPhone(), phone);
    	target.setPhone(phone);
    	
    	dao.saveFile();
    	
        return null; // 결과 문자열 반환
        
        // ex)
        // 홍길동님의 전화번호가 변경 되었습니다
        // 01012341234 -> 01044445555
    }


    // 회원 탈퇴
    @Override
    public String deleteMember(Member target) throws IOException {
    	
    	System.out.printf("%s 회원이 탈퇴 처리 되었습니다\n", target.getName());
    	getMemberList().remove(target);
    	dao.saveFile();

      return null; // 결과 문자열 반환
        // ex)
        // "홍길동 회원이 탈퇴 처리 되었습니다"
    }

}