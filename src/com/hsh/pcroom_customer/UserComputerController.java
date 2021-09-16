package com.hsh.pcroom_customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// 고객의 컴퓨터에서
public class UserComputerController {
    static UserComputerService service = new UserComputerService();
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            int choiceSeatId = 0;
            try{
                while (true){
                    UserComputerView.display("==========자리를 선택해 주세요(좌석현황)===========");
                    List<SeatVO> seats = showUsableSeats();
                    UserComputerView.display("\n====================================");
                    choiceSeatId = Integer.parseInt(br.readLine());
                    // return값이 1이면 원하는 1개 업데이트이므로
                    if(service.updateSeat(choiceSeatId, "N") == 1){
                        if(seats.get(choiceSeatId-1).getIs_usable().equals("N")){
                            UserComputerView.display("이미 사용중인 좌석입니다. 다시 선택해 주세요.");
                        }
                        else{
                            UserComputerView.display("============" + choiceSeatId + "번 자리를 선택하였습니다." + "============");
                            break;
                        }

                    }
                    else{
                        UserComputerView.display("좌석 선택에 실패하였습니다. 다시 선택해 주세요.");
                    }
                }


                UserComputerView.display("==========숫자를 선택해 주세요===========");
                UserComputerView.display(" 1.회원가입 / 2.로그인 / 3.시스템종료");
                UserComputerView.display("====================================");
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
                        exit(choiceSeatId);
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        break;
                }
            }catch (IOException | IllegalArgumentException e){
                UserComputerView.display("잘못된 입력 형식입니다. 확인 후 다시 입력해 주세요.");
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
                //
            }
        }
        else{
            UserComputerView.display("로그인에 실패하였습니다. 확인 후 다시 로그인 해주세요.");
        }
    }

    // 남아있는 시간이 있다면 로그인 가능
    private static void afterSignIn(CustomerVO customer){

        // 컴퓨터 실행중 ....
        // 현재 시간에서 남아있는 시간을 더한 후,
        // 그 시간이 되면 강제 종료

    }

    // 사용가능한 좌석 확인
    private static List<SeatVO> showUsableSeats(){
        List<SeatVO> seats = service.selectSeatAll();
        for(int i=0;i<seats.size();i++){
            String show = "";
            SeatVO seat = seats.get(i);
            // 사용가능한 자리는 좌석 번호(id)를 보여줌
            if(seat.getIs_usable().equals("Y")){
                show = String.valueOf(seat.getId());
            }
            // 사용불가능한 자리는 X로 보여줌
            else{
                show = "X";
            }
            show += "\t";
            if((i+1)%4 == 0) show += "\n";

            System.out.print(show);
        }
        return seats;
    }

    private static void exit(int id){
        System.out.println("시스템을 종료합니다. 안녕히 가세요(__)");
        service.updateSeat(id, "Y");
    }


}
