package com.hsh.pcroom_customer.counter;

import com.hsh.pcroom_customer.CheckporderVO;
import com.hsh.pcroom_customer.SeatVO;
import com.hsh.pcroom_customer.VisitVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// 카운터에서 쓰는 프로그램.
// 좌석 확인, 주문확인, 방문기록 확인 기능

public class CounterController {
    static CounterService service = new CounterService();

    public static void main(String[] args) {
        CounterView.displayNotice("카운터 프로그램에 접속하였습니다!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){

            CounterView.display2("1.좌석현황 / 2.주문확인 / 3.방문기록확인 / 4.종료하기 : ");

            try {
                String ans_menu = br.readLine();
                String pattern = "^[1-5]*$";
                if(ans_menu.matches(pattern)){
                    switch (ans_menu){
                        case "1":
                            showSeats();
                            break;
                        case "2":
                            showOrders();
                            break;
                        case "3":
                            showVisits();
                            break;
                        case "4":
                            CounterView.displayNotice("시스템을 종료합니다. 안녕히 가세요(__)");
                            System.exit(0);
                        default:
                    }
                }
                else{
                    CounterView.displayNotice("잘못 입력하셨습니다. 다시 입력해 주세요 !");
                    continue;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public static void showSeats(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CounterView.display("\n[좌석현황]");
        CounterView.display("------------------------------------------");
        List<SeatVO> seats = service.selectSeatAll();
        CounterView.displayList(seats);
        CounterView.display("------------------------------------------\n");
        while(true){
            CounterView.display2("1.뒤로가기 / 2.새로고침 : ");
            try {
                String ans = br.readLine();
                if(ans.equals("1")){
                    return;
                }
                if(ans.equals("2")){
                    CounterView.display("\n[좌석현황]");
                    CounterView.display("------------------------------------------");
                    seats = service.selectSeatAll();
                    CounterView.displayList(seats);
                    CounterView.display("------------------------------------------\n");
                }
                else{
                    CounterView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                    continue;
                }

            } catch (IOException e) {
                CounterView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                e.printStackTrace();
            }
        }


    }


    public static void showOrders(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CounterView.display("\n[주문확인]");

        List<CheckporderVO> checkporders = service.selectCheckporderAllByStatus("N");

        int prev_id = 0;

        for(CheckporderVO c:checkporders){
            if(prev_id != c.getPorder_id()){
                if(prev_id!=0){
                    CounterView.display("\n===============================================");
                }
                CounterView.display("\n===============================================");
                CounterView.display("주문번호: "+c.getPorder_id() + "\n회원아이디: "+c.getCustomer_id()+
                        "\n자리번호: "+c.getSeat_id() +"\n주문날짜: "+c.getPayment_date()+"\n결제방법: "+c.getPayment_way()+
                        "\n요청사항: "+(c.getRequest()==null?"없음":c.getRequest()) + "\n총가격: "+c.getPrice_sum());
                CounterView.display("--------------------------------------------------");
            }
            CounterView.display(c.toString());
            prev_id = c.getPorder_id();
        }
        CounterView.display("===============================================\n");

        while(true){
            CounterView.display2("1.뒤로가기 / 2.새로고침 / 3.결제완료처리 : ");
            try {
                String ans = br.readLine();
                if(ans.equals("1")){
                    return;
                }
                else if(ans.equals("2")){
                    CounterView.display("\n[주문확인]");

                    checkporders = service.selectCheckporderAllByStatus("N");

                    prev_id = 0;

                    for(CheckporderVO c:checkporders){
                        if(prev_id != c.getPorder_id()){
                            if(prev_id!=0){
                                CounterView.display("\n===============================================");
                            }
                            CounterView.display("\n===============================================");
                            CounterView.display("주문번호: "+c.getPorder_id() + "\n회원아이디: "+c.getCustomer_id()+
                                    "\n자리번호: "+c.getSeat_id() +"\n주문날짜: "+c.getPayment_date()+"\n결제방법: "+c.getPayment_way()+
                                    "\n요청사항: "+(c.getRequest()==null?"없음":c.getRequest()) + "\n총가격: "+c.getPrice_sum());
                            CounterView.display("--------------------------------------------------");
                        }
                        CounterView.display(c.toString());
                        prev_id = c.getPorder_id();
                    }
                    CounterView.display("===============================================\n");
                }
                else if(ans.equals("3")){
                    CounterView.display2("결제완료처리할 주문번호를 선택해 주세요 : ");
                    String ans_ordernum = br.readLine();
                    String pattern = "^[0-9]*$";
                    CheckporderVO choicePorder = null;
                    if(ans_ordernum.matches(pattern)){
                        for(CheckporderVO checkporder:checkporders){
                            if(checkporder.getPorder_id()==Integer.parseInt(ans_ordernum)){
                                choicePorder = checkporder;
                                break;
                            }
                        }
                    }

                    else{
                        CounterView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        continue;
                    }

                    if(choicePorder == null){
                        CounterView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");

                    }
                    else{
                        if(service.updatePorderById(Integer.parseInt(ans_ordernum))==1){
                            CounterView.displayNotice("처리가 완료되었습니다.");
                        }
                        else{
                            CounterView.displayNotice("처리를 실패하였습니다. 다시 시도해 주세요.");
                        }
                    }
                }
                else{
                    CounterView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                }

            } catch (IOException e) {
                CounterView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                e.printStackTrace();
            }
        }


    }

    public static void showVisits() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CounterView.display("\n[방문기록확인]");
        CounterView.display("------------------------------------------");
        List<VisitVO> visits = service.selectVisitAll();
        CounterView.displayList(visits);
        CounterView.display("------------------------------------------\n");
        while (true) {
            CounterView.display2("1.뒤로가기 / 2.새로고침 : ");
            try {
                String ans = br.readLine();
                if (ans.equals("1")) {
                    return;
                } else if (ans.equals("2")) {
                    CounterView.display("\n[방문기록확인]");
                    CounterView.display("------------------------------------------");
                    visits = service.selectVisitAll();
                    CounterView.displayList(visits);
                    CounterView.display("------------------------------------------\n");
                } else {
                    CounterView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
