package model;

import java.util.ArrayList;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UDao udao = new UDao();
		// User table 생성! 이건 초기에 한번만해주고 주석처리해주세염!
//		udao.createUser();
		
		String uname1 = "dahye";
		String uname2 = "성준";
		//User 입력 만약에 이미 존재하는 이름이면 0 아니면 1
		int u1 = udao.insert_record(uname1);
		if(u1==0) {
			System.out.println("이미 존재하는 이름입니다. 기존에 존재하는 User정보를 불러옵니다.");
			
		}else {
			System.out.println("존재하지 않는 이름 입니다. 새로운 User 정보를 생성합니다..");
		}
		User user1 = udao.findUser(uname1);
		int u2 = udao.insert_record(uname2);
		if(u2==0) {
			System.out.println("이미 존재하는 이름입니다. 기존에 존재하는 User정보를 불러옵니다.");
			
		}else {
			System.out.println("존재하지 않는 이름 입니다. 새로운 User 정보를 생성합니다..");
		}
		User user2 = udao.findUser(uname2);
		
		
		// 이겼는지 졌는지 정보 update
		udao.updateResult(user2.id, "win");
		udao.updateResult(user1.id, "lose");
		
		// 전체 리스트 출
		ArrayList<User> dtos = udao.list();
		dtos.forEach((user) -> {

			System.out.println("이름 : "+user.name);
			System.out.println("총 게임수 : "+user.total);
			System.out.println("lose : "+user.lose);
			System.out.println("win : "+user.win);
			if(user.total!=0) {
				System.out.println("승률 : "+(double) user.win/ (double) user.total * 100.0 + "%");
			}
			
		});
	}

}
