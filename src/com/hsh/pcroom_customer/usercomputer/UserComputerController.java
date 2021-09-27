package com.hsh.pcroom_customer.usercomputer;

import com.hsh.pcroom_customer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// 고객의 컴퓨터에서
public class UserComputerController {
    static UserComputerService service = new UserComputerService();
    static int choiceSeatId = 0;
    static List<PorderdetailVO> cart = new ArrayList<>();

    public static void main(String[] args){
        UserComputerView.displayNotice("컴퓨터에 접속하였습니다!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            choiceSeatId = 0;
            try{
                List<SeatVO> seats = showUsableSeats();

                choiceSeatId = Integer.parseInt(br.readLine());

                SeatVO choiceSeat = service.selectSeatById(choiceSeatId);
                // return값이 1이면 원하는 1개 업데이트이므로
                if(choiceSeat != null && choiceSeat.getIs_usable().equals("Y")){
                    UserComputerView.displayNotice( choiceSeatId + "번 자리를 선택하였습니다.");
                    service.updateSeat(choiceSeatId,"N",null);

                }
                else{
                    UserComputerView.displayNotice("이미 사용중인 좌석입니다. 다시 선택해 주세요.");
                    continue;
                }


                while (true){
                    UserComputerView.display2("1.회원가입 / 2.로그인 / 3.시스템종료 : ");
                    String selectNum = br.readLine();

                    switch (selectNum){
                        case "1":
                            UserComputerView.display("============회원 정보를 입력해주세요===========\"");
                            signUp(); break;
                        case "2":
                            UserComputerView.display2("아이디 : ");
                            String id = br.readLine();

                            UserComputerView.display2("비밀번호 : ");
                            String pw = br.readLine();

                            CustomerVO customer = signIn(id, pw);

                            // null이 아니다 -> 로그인이 됐다 !
                            if(customer != null){
                                service.updateSeat(choiceSeatId,"N",customer.getId());
                                afterSignIn(customer);

                            }
                            break;

                        case "3":
                            exit(choiceSeatId);
                            return;
                        default:
                            UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                            break;
                    }
                }

            }catch (IOException | IllegalArgumentException e){
                UserComputerView.displayNotice("잘못된 입력 형식입니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
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
                if(service.selectCustomerById(id) != null){
                    UserComputerView.displayNotice("사용불가능한 아이디입니다. 다시 입력해 주세요.");
                    continue;
                }

                else UserComputerView.displayNotice("사용가능한 아이디입니다 !");

                break;

            }catch (IOException e){
                UserComputerView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }
        }

        // 비밀번호 입력
        while (true) {
            UserComputerView.display2("2. 비밀번호(영문 또는 숫자만 사용가능) : ");
            try{
                password = br.readLine();
                /* 비밀번호 유효성 확인 해야됨 */
                String pattern = "^[a-zA-Z0-9]*$";
                if(!password.matches(pattern) || password.equals("")){
                    UserComputerView.displayNotice("비밀번호 형식이 잘못되었습니다. 다시 입력해 주세요.");
                    continue;
                }
                break;

            }catch (IOException e){
                UserComputerView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }
        }

        // 이름 입력
        while (true) {
            UserComputerView.display2("3. 이름 : ");
            try{
                name = br.readLine();
                break;
            }catch (IOException e){
                UserComputerView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }
        }

        // 핸드폰 번호 입력
        while (true) {
            UserComputerView.display2("4. 핸드폰번호(예 : 010-3333-3333) : ");
            try{
                phone = br.readLine();
                /* 중복 확인 해야됨 */
                String pattern = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$";
                if(!phone.matches(pattern) || phone.equals("")){
                    UserComputerView.display("핸드폰 번호 형식이 잘못되었습니다. 다시 입력해 주세요.");
                    continue;
                }

                break;
            }catch (IOException e){
                UserComputerView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }

        }


        // 생년월일 입력
        while (true) {
            UserComputerView.display2("5. 생년월일(예 : 1997-12-31) : ");
            try {
                String birthday_str = br.readLine();
                String pattern = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";
                if(!birthday_str.matches(pattern) || birthday_str.equals("")){
                    UserComputerView.displayNotice("생년월일 형식이 잘못되었습니다. 다시 입력해 주세요.");
                    continue;
                }
                birthday = Date.valueOf(birthday_str);
                break;
            } catch (IOException | IllegalArgumentException e) {
                UserComputerView.displayNotice("잘못된 형식입니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }
        }
        while (true){
            UserComputerView.display2("6. (선택)주소 : ");
            try{
                address = br.readLine();
                break;
            }catch (IOException e){
                UserComputerView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }
        }

        return new CustomerVO(id, password, phone, name, birthday, address);
    }

    // 회원가입
    private static void signUp(){
        CustomerVO newCustomer = signUpForm();

        if(service.insertCustomer(newCustomer)==1){
            UserComputerView.displayNotice("회원가입이 완료되었습니다.");
        }
        else{
            UserComputerView.displayNotice("회원가입에 실패하였습니다. 다시 시도해 주세요.");
        }
    }

    // 로그인
    private static CustomerVO signIn(String id,String pw){
        CustomerVO customer = service.selectByIdPassword(id, pw);
        if(customer != null){
            if(customer.getRemain_time() == 0){
                UserComputerView.displayNotice("남아있는 시간이 없습니다. 요금 충전 후, 다시 로그인 해주세요.");
            }
            else{
                UserComputerView.displayNotice("로그인에 성공하였습니다");
                return customer;
                //
            }
        }
        else{
            UserComputerView.displayNotice("로그인에 실패하였습니다. 확인 후 다시 로그인 해주세요.");
        }
        return null;
    }

    public static class Remain0ExitThread extends Thread{
        private long time;
        private CustomerVO updateCustomer;
        private VisitVO visit;
        Remain0ExitThread(long time, CustomerVO updateCustomer, VisitVO visit){
            this.time = time;
            this.updateCustomer = updateCustomer;
            this.visit = visit;
        }

        @Override
        public void run() {
            try {
                sleep(time);
                updateCustomer.setRemain_time(0);
                UserComputerView.displayNotice("\n시간이 모두 소모되었습니다.");

                int result = service.updateCustomerRemainTime(updateCustomer);
                if(result == 1){

                    visit.setVisit_date(null);
                    visit.setExit_date(new Date(System.currentTimeMillis()));

                    service.insertVisit(visit);
                    exit(choiceSeatId);
                }
                else{
                    UserComputerView.displayNotice("시스템 종료 오류가 발생하였습니다.");
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

        Remain0ExitThread thread = new Remain0ExitThread(remain_time, customer,visit);
        thread.start();

        CustomerVO updateCustomer = customer;
        cart.clear();
        while(true){
            UserComputerView.display2("1.남은시간확인 / 2.음식주문 / 3.시스템종료 : ");
            try{
                String answer = br.readLine();
                switch (answer){
                    case "1":
                        // 종료될시간 - 현재시간
                        UserComputerView.displayNotice(millisToFormat
                                (remain_time - (System.currentTimeMillis()-now_time)));

                        break;
                    case "2":
                        orderMenu(customer.getId());
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
                            UserComputerView.displayNotice("시스템 종료 오류가 발생하였습니다.");
                        }
                        break;
                    default:
                        UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        break;
                }
            } catch (IOException e) {
                UserComputerView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
                e.printStackTrace();
            }

        }



    }

    private static void orderMenu(String customer_id) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            UserComputerView.display2("1.음료 / 2.라면 / 3.식사류 / 4.세트메뉴 / 5.장바구니 확인 및 구매 / 6.주문취소 : ");
            try {
                String ans = br.readLine();

                switch (ans){
                    case "1":
                        addCartByKinds("음료");
                        break;
                    case "2":
                        addCartByKinds("라면");
                        break;
                    case "3":
                        addCartByKinds("식사류");
                        break;
                    case "4":
                        addCartByKinds("세트메뉴");
                        break;
                    case "5":
                        UserComputerView.display("==============장바구니 확인==============");
                        UserComputerView.displayList(cart);
                        int cart_sum = 0;
                        for(PorderdetailVO p:cart){
                            cart_sum += p.getPrice() * p.getNum();
                        }
                        UserComputerView.display("===============총 합계 : "+cart_sum+"원==============");
                        UserComputerView.display2("정말 구입하시겠습니까?(구입 : Y) : ");
                        String ans_buy = br.readLine();
                        if(ans_buy.equals("Y") || ans_buy.equals("y")){
                            String ans_way = "";
                            while(true){
                                UserComputerView.display2("결제방법을 선택해 주세요(카드/현금) : ");
                                ans_way = br.readLine();
                                if(ans_way.equals("카드") || ans_way.equals("현금")) break;
                                else UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                            }

                            UserComputerView.display2("요청사항을 입력해 주세요 : ");
                            String ans_req = br.readLine();
                            PorderVO porder = new PorderVO();
                            porder.setPayment_way(ans_way);
                            porder.setCustomer_id(customer_id);
                            porder.setRequest(ans_req);
                            porder.setSeat_id(choiceSeatId);
                            porder.setPrice_sum(cart_sum);

                            int result = service.insertPorder(porder, cart);
                            if(result == cart.size()) UserComputerView.displayNotice("주문이 성공적으로 완료되었습니다!");
                            else UserComputerView.displayNotice("주문에 실패하였습니다..");
                            cart.clear();
                            return;
                        }
                        else{
                            UserComputerView.displayNotice("주문을 취소하였습니다.");
                        }

                        break;

                    case "6":
                        cart.clear();
                        return;
                    default:
                        UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        break;

                }


            } catch (IOException e) {
                UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                e.printStackTrace();
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
        UserComputerView.display("\n===========================================");
        return seats;
    }

    private static void exit(int id){
        UserComputerView.displayNotice("시스템을 종료합니다. 안녕히 가세요(__)");
        service.updateSeat(id, "Y", null);
        System.exit(0);
    }

    // kinds에 따라 상품을 보여주고
    // 장바구니에 추가하게끔 하는 함수
    private static void addCartByKinds(String kinds){
        List<ProductVO> products = service.selectProductByKinds(kinds);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        if(products != null){
            while(true){
                UserComputerView.display("================="+kinds+" 상품목록==================");
                UserComputerView.displayList(products);
                UserComputerView.display("===============================================");
                UserComputerView.display2("상품의 번호를 입력하시면 장바구니에 추가됩니다 (뒤로가기 : Q) : ");

                try {
                    String ans = br.readLine();
                    if(ans.equals("Q") || ans.equals("q")){
                        return;
                    }
                    int ans_int = Integer.parseInt(ans);
                    if(ans_int >= 1 && ans_int <= products.size()){
                        PorderdetailVO porderdetail = new PorderdetailVO();

                        ProductVO choiceProduct = products.get(ans_int-1);

                        porderdetail.setProduct_id(choiceProduct.getId());
                        porderdetail.setPrice(choiceProduct.getPrice());
                        porderdetail.setName(choiceProduct.getName());

                        UserComputerView.display2(choiceProduct.getName() +
                                "(" + choiceProduct.getPrice() + "원)을 장바구니에 추가하시겠습니까?(추가 : Y) : ");
                        String ans_add = br.readLine();
                        if(ans_add.equals("Y") || ans_add.equals("y")) {
                            while (true){
                                String pattern = "^[0-9]*$";
                                UserComputerView.display2("수량을 입력해 주세요 : ");
                                String product_num = br.readLine();
                                if(product_num.matches(pattern)){
                                    porderdetail.setNum(Integer.parseInt(product_num));
                                    break;
                                }
                                UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 입력해 주세요.");

                            }

                            cart.add(porderdetail);
                            UserComputerView.displayNotice("장바구니에 추가되었습니다 !");
                        }

                    }
                    else{
                        UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                    }
                } catch (IOException | NumberFormatException e) {
                    UserComputerView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                    e.printStackTrace();
                }
            }


        }
    }


}
