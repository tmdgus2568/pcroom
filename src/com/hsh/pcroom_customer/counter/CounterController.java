package com.hsh.pcroom_customer.counter;

import com.hsh.pcroom_customer.PorderVO;
import com.hsh.pcroom_customer.SeatVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// 카운터에서 쓰는 프로그램.
// 좌석 확인, 주문확인, 회원 정보 수정, 방문기록 확인 기능

public class CounterController {
    static CounterService service = new CounterService();

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            CounterView.display("=============메뉴를 선택해 주세요============");
            CounterView.display2("1.좌석현황 / 2.주문확인 / 3.회원정보수정 / 4.방문기록확인 / 5.종료하기 : ");

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
                        case "4":
                        case "5":
                            CounterView.display("시스템을 종료합니다. 안녕히 가세요(__)");
                            System.exit(0);
                        default:
                    }
                }
                else{
                    CounterView.display("잘못 입력하셨습니다. 다시 입력해 주세요 !");
                    continue;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public static void showSeats(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CounterView.display("=================좌석현황=================");
        CounterView.display("좌석번호\t\t사용가능여부\t\t아이디");
        CounterView.display("------------------------------------------");
        List<SeatVO> seats = service.selectSeatAll();
        CounterView.displayList(seats);
        while(true){
            CounterView.display2("1.뒤로가기 / 2.새로고침 : ");
            try {
                String ans = br.readLine();
                if(ans.equals("1")){
                    return;
                }
                if(ans.equals("2")){
                    CounterView.display("=================좌석현황=================");
                    CounterView.display("좌석번호\t\t사용가능여부\t\t아이디");
                    CounterView.display("----------------------------");
                    seats = service.selectSeatAll();
                    CounterView.displayList(seats);
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
        CounterView.display("=======================주문확인=======================");
        CounterView.display("주문번호\t아이디\t음식이름\t결제방식\t요청사항\t주문날짜\t자리번호");
        CounterView.display("----------------------------------------------------");
        List<PorderVO> porders = service.selectPorderAllByStatus("N");
        CounterView.displayList(porders);

        while(true){
            CounterView.display2("1.뒤로가기 / 2.새로고침 / 3.결제완료처리 : ");
            try {
                String ans = br.readLine();
                if(ans.equals("1")){
                    return;
                }
                else if(ans.equals("2")){
                    CounterView.display("=======================주문확인=========================");
                    CounterView.display("주문번호\t아이디\t음식이름\t결제방식\t요청사항\t주문날짜\t자리번호");
                    CounterView.display("----------------------------------------------------");

                    porders = service.selectPorderAllByStatus("N");
                    CounterView.displayList(porders);
                }
                else if(ans.equals("3")){
                    CounterView.display2("결제완료처리할 주문번호를 선택해 주세요 : ");
                    String ans_ordernum = br.readLine();
                    String pattern = "^[0-9]*$";
                    PorderVO choicePorder = null;
                    if(ans_ordernum.matches(pattern)){
                        for(PorderVO porder:porders){
                            if(porder.getId()==Integer.parseInt(ans_ordernum)){
                                choicePorder = porder;
                                break;
                            }
                        }
                    }
                    else{
                        CounterView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        continue;
                    }

                    if(choicePorder == null){
                        CounterView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");

                    }
                    else{
                        if(service.deletePorderById(Integer.parseInt(ans_ordernum))){
                            CounterView.display("처리가 완료되었습니다.");
                        }
                        else{
                            CounterView.display("처리를 실패하였습니다. 다시 시도해 주세요.");
                        }
                    }
                }
                else{
                    CounterView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                }

            } catch (IOException e) {
                CounterView.display("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                e.printStackTrace();
            }
        }


    }
}
