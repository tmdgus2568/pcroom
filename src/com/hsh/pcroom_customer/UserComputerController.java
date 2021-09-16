package com.hsh.pcroom_customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

// 고객의 컴퓨터에서
public class UserComputerController {
    static UserComputerService service = new UserComputerService();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("==========자리를 선택해 주세요===========");
            System.out.println(" 1.회원가입 / 2.로그인");
            System.out.println("====================================");

            System.out.println("==========숫자를 선택해 주세요===========");
            System.out.println(" 1.회원가입 / 2.로그인 / 3.시스템종료");
            System.out.println("====================================");
            String selectNum = br.readLine();

            switch (selectNum){
                case "1":
                    System.out.println("==========회원 정보를 입력해주세요===========\"");
                    signUp(); break;
                case "2":
                    System.out.print("아이디 : ");
                    String id = br.readLine();

                    System.out.print("비밀번호 : ");
                    String pw = br.readLine();

                    signIn(id, pw); break;

                case "3":
                    System.out.println("시스템을 종료합니다. 안녕히 가세요(__)");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                    break;
            }

        }
    }

    private static CustomerVO signUpForm(){
        String id;
        String name;
        String password;
        String address;
        String phone;
        Date birthday;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 아이디 입력
        while (true) {
            System.out.print("1. 아이디 : ");
            try{
                id = br.readLine();
                /* 중복 확인 해야됨 */
                break;

            }catch (IOException e){
                System.out.println("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        // 비밀번호 입력
        while (true) {
            System.out.print("2. 비밀번호 : ");
            try{
                password = br.readLine();
                /* 비밀번호 유효성 확인 해야됨 */
                break;

            }catch (IOException e){
                System.out.println("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        // 이름 입력
        while (true) {
            System.out.print("3. 이름 : ");
            try{
                name = br.readLine();
                break;
            }catch (IOException e){
                System.out.println("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        // 핸드폰 번호 입력
        while (true) {
            System.out.print("4. 핸드폰번호(예 : 010-3333-3333) : ");
            try{
                phone = br.readLine();
                /* 중복 확인 해야됨 */
                break;
            }catch (IOException e){
                System.out.println("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }

        }


        // 생년월일 입력
        while (true) {
            System.out.print("5. 생년월일(예 : 1997-12-31) : ");
            try {
                birthday = Date.valueOf(br.readLine());
                break;
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("잘못된 형식입니다. 확인 후 다시 입력해 주세요.");
            }
        }
        while (true){
            System.out.print("6. (선택)주소 : ");
            try{
                address = br.readLine();
                break;
            }catch (IOException e){
                System.out.println("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        return new CustomerVO(id, password, phone, name, birthday, address);
    }

    // 회원가입
    private static void signUp(){
        CustomerVO newCustomer = signUpForm();

        // insert의 경우 무조건 false가 나오므로
        // false여야 성공한 것이다.
        if(!service.insertCustomer(newCustomer)){
            UserComputerView.display("회원가입이 완료되었습니다.");
        }
        else{
            UserComputerView.display("회원가입에 실패하였습니다. 다시 시도해 주세요.");
        }
    }

    // 로그인
    private static void signIn(String id,String pw){
        CustomerVO customer = service.selectByIdPassword(id, pw);
        if(customer != null){
            if(customer.getRemain_time() == 0){
                UserComputerView.display("남아있는 시간이 없습니다. 요금 충전 후, 다시 로그인 해주세요.");
            }
            else{
                UserComputerView.display("로그인에 성공하였습니다");
            }
        }
        else{
            UserComputerView.display("로그인에 실패하였습니다. 확인 후 다시 로그인 해주세요.");
        }
    }

    // 남아있는 시간이 있다면 로그인 가능
    private static void afterSignIn(CustomerVO customer){

    }

}
