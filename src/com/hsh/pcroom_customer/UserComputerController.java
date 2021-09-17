package com.hsh.pcroom_customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

// 고객의 컴퓨터에서
public class UserComputerController {
    static UserComputerService service = new UserComputerService();
    static int choiceSeatId = 0;

    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            choiceSeatId = 0;
            try{
                List<SeatVO> seats = showUsableSeats();

                choiceSeatId = Integer.parseInt(br.readLine());
                // return값이 1이면 원하는 1개 업데이트이므로
                if(service.updateSeat(choiceSeatId, "N") == 1){
                    if(seats.get(choiceSeatId-1).getIs_usable().equals("N")){
                        UserComputerView.display("이미 사용중인 좌석입니다. 다시 선택해 주세요.");
                    }
                    else{
                        UserComputerView.display("============" + choiceSeatId + "번 자리를 선택하였습니다." + "============");
                    }

                }
                else{
                    UserComputerView.display("좌석 선택에 실패하였습니다. 다시 선택해 주세요.");
                    continue;
                }


                while (true){
                    UserComputerView.display("==========숫자를 선택해 주세요===========");
                    UserComputerView.display(" 1.회원가입 / 2.로그인 / 3.시스템종료");
                    UserComputerView.display("====================================");
                    String selectNum = br.readLine();

                    switch (selectNum){
                        case "1":
                            UserComputerView.display("==========회원 정보를 입력해주세요===========\"");
                            signUp(); break;
                        case "2":
                            UserComputerView.display2("아이디 : ");
                            String id = br.readLine();

                            UserComputerView.display2("비밀번호 : ");
                            String pw = br.readLine();

                            CustomerVO customer = signIn(id, pw);

                            // null이 아니다 -> 로그인이 됐다 !
                            if(customer != null){
                                afterSignIn(customer);

                            }
                            break;

                        case "3":
                            exit(choiceSeatId);
                            return;
                        default:
                            UserComputerView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                            break;
                    }
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
            UserComputerView.display2("1. 아이디 : ");
            try{
                id = br.readLine();
                /* 중복 확인 해야됨 */
                break;

            }catch (IOException e){
                UserComputerView.display2("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        // 비밀번호 입력
        while (true) {
            UserComputerView.display2("2. 비밀번호 : ");
            try{
                password = br.readLine();
                /* 비밀번호 유효성 확인 해야됨 */
                break;

            }catch (IOException e){
                UserComputerView.display("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        // 이름 입력
        while (true) {
            UserComputerView.display2("3. 이름 : ");
            try{
                name = br.readLine();
                break;
            }catch (IOException e){
                UserComputerView.display("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }
        }

        // 핸드폰 번호 입력
        while (true) {
            UserComputerView.display2("4. 핸드폰번호(예 : 010-3333-3333) : ");
            try{
                phone = br.readLine();
                /* 중복 확인 해야됨 */
                break;
            }catch (IOException e){
                UserComputerView.display("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }

        }


        // 생년월일 입력
        while (true) {
            UserComputerView.display2("5. 생년월일(예 : 1997-12-31) : ");
            try {
                birthday = Date.valueOf(br.readLine());
                break;
            } catch (IOException | IllegalArgumentException e) {
                UserComputerView.display("잘못된 형식입니다. 확인 후 다시 입력해 주세요.");
            }
        }
        while (true){
            UserComputerView.display2("6. (선택)주소 : ");
            try{
                address = br.readLine();
                break;
            }catch (IOException e){
                UserComputerView.display("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
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
    private static CustomerVO signIn(String id,String pw){
        CustomerVO customer = service.selectByIdPassword(id, pw);
        if(customer != null){
            if(customer.getRemain_time() == 0){
                UserComputerView.display("남아있는 시간이 없습니다. 요금 충전 후, 다시 로그인 해주세요.");
            }
            else{
                UserComputerView.display("로그인에 성공하였습니다");
                return customer;
                //
            }
        }
        else{
            UserComputerView.display("로그인에 실패하였습니다. 확인 후 다시 로그인 해주세요.");
        }
        return null;
    }

    public static class Remain0ExitThread extends Thread{
        private long time;
        private CustomerVO updateCustomer;
        Remain0ExitThread(long time, CustomerVO updateCustomer){
            this.time = time;
            this.updateCustomer = updateCustomer;
        }

        @Override
        public void run() {
            try {
                sleep(time);
                updateCustomer.setRemain_time(0);
                UserComputerView.display("\n시간이 모두 소모되었습니다.");

                int result = service.updateCustomerRemainTime(updateCustomer);
                if(result == 1){
                    exit(choiceSeatId);
                }
                else{
                    UserComputerView.display("시스템 종료 오류가 발생하였습니다.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String millisToFormat(long time) {
        StringBuilder sb = new StringBuilder();
        int hour = (int) ((time / (1000 * 60 * 60)) % 24);
        int min = (int)((time / (1000 * 60 )) % 60);
        return sb.append("남은 시간은 '").append(hour).append("시간 ").append(min).append("분'").toString();

    }


    // 남아있는 시간이 있다면 로그인 가능
    private static void afterSignIn(CustomerVO customer){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH시간 mm분");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        UserComputerView.display("===============컴퓨터 실행중=================");

        // 방문 기록 저장
        long now_time = System.currentTimeMillis(); // 현재시간(초)
        VisitVO visit = new VisitVO();
        visit.setCustomer_id(customer.getId());
        visit.setSeat_id(choiceSeatId);
        visit.setVisit_date(new Date(now_time));
        visit.setExit_date(null);
        service.insertVisit(visit);

        long remain_time = customer.getRemain_time()*60000;
        long exit_time = remain_time + now_time; // 끝나는시간(밀리초)
        java.util.Date exit_date = new java.util.Date(exit_time); // 끝나는시간(date)

        Remain0ExitThread thread = new Remain0ExitThread(remain_time, customer);
        thread.start();

        CustomerVO updateCustomer = customer;
        while(true){
            UserComputerView.display2("1.남은시간확인 / 2.음식주문 / 3.시스템종료 : ");
            try{
                String answer = br.readLine();
                switch (answer){
                    case "1":
                        // 종료될시간 - 현재시간
                        UserComputerView.display(millisToFormat(remain_time - (System.currentTimeMillis()-now_time)));

                        break;
                    case "2":
                        break;
                    case "3":
                        // 갱신된 남은 시간을 디비에 저장
                        remain_time = (exit_time-System.currentTimeMillis())/60000;
                        updateCustomer.setRemain_time((int)remain_time);

                        int result = service.updateCustomerRemainTime(updateCustomer);
                        if(result == 1){
                            visit.setVisit_date(null);
                            visit.setExit_date(new Date(System.currentTimeMillis()));

                            service.insertVisit(visit);
                            exit(choiceSeatId);
                        }
                        else{
                            UserComputerView.display("시스템 종료 오류가 발생하였습니다.");
                        }
                        break;
                    default:
                        UserComputerView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        break;
                }
            } catch (IOException e) {
                UserComputerView.display("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }

        }



    }

    // 사용가능한 좌석 확인
    private static List<SeatVO> showUsableSeats(){
        List<SeatVO> seats = service.selectSeatAll();
        UserComputerView.display("==========자리를 선택해 주세요(좌석현황)===========");
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

            UserComputerView.display2(show);
        }
        UserComputerView.display("\n====================================");
        return seats;
    }

    private static void exit(int id){
        UserComputerView.display("시스템을 종료합니다. 안녕히 가세요(__)");
        service.updateSeat(id, "Y");
        System.exit(0);
    }


}
