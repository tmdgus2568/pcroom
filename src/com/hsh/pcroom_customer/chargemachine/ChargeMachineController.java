package com.hsh.pcroom_customer.chargemachine;

import com.hsh.pcroom_customer.CustomerVO;
import com.hsh.pcroom_customer.RateplanVO;
import com.hsh.pcroom_customer.counter.CounterView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// 요금 충전 기기
public class ChargeMachineController {
    static ChargeMachineService service = new ChargeMachineService();
    public static void main(String[] args) {
        ChargeMachineView.displayNotice("요금 충전 기기에 접속하였습니다!");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<RateplanVO> rateplans;
        while(true){
            ChargeMachineView.display2("1.회원 / 2.비회원 / 3.종료하기 : ");
            try{
                String role = br.readLine();
                switch (role){
                    case "1":
                        // 요금제 선택
                        rateplans = service.selectRateplanByRole("회원");
                        showRateplans(rateplans);
                        int choiceRateplanNum = Integer.parseInt(br.readLine());

                        if(choiceRateplanNum > rateplans.size()) throw new IOException();

                        // 아이디 선택
                        ChargeMachineView.display2("충전할 아이디 : ");
                        String customerId = br.readLine();
                        CustomerVO customer = null;
                        while ((customer = service.selectCustomerById(customerId))==null){
                            ChargeMachineView.displayNotice("유효하지 않은 아이디 입니다. 다시 입력해 주세요.");
                            ChargeMachineView.display2("충전할 아이디 : ");
                            customerId = br.readLine();
                        }
                        ChargeMachineView.displayNotice2("남은시간 : "+customer.getRemain_time() + "분");

                        RateplanVO choiceRateplan = rateplans.get(choiceRateplanNum-1);
                        ChargeMachineView.displayNotice2("선택한 요금제는 '"+choiceRateplan.getId()+
                                                    " ("+choiceRateplan.getPrice()+"원)'"+" 입니다.\n");
                        ChargeMachineView.display2("결제를 진행하시겠습니까? (Y/N) : ");
                        String answer = br.readLine();
                        if(answer.equals("Y") || answer.equals("y")){
                            int result = service.updateCustomerForBuyTime(customer.getId(),choiceRateplan.getApply_time(),choiceRateplan.getId());
                            if(result == 1){
                                ChargeMachineView.displayNotice("충전이 완료되었습니다.");
                            }
                            else{
                                ChargeMachineView.displayNotice("충전에 실패하였습니다.");
                            }
                        }
                        else if(answer.equals("N") || answer.equals("n")){
                            break;
                        }
                        else throw new IOException();

                        break;
                    case "2":
                        break;
                    case "3":
                        CounterView.displayNotice("시스템을 종료합니다. 안녕히 가세요(__)");
                        System.exit(0);
                    default:
                        ChargeMachineView.displayNotice("잘못 입력하셨습니다. 다시 선택해 주세요 !");
                        break;
                }
            }catch (IOException | IllegalArgumentException e){
                ChargeMachineView.displayNotice("입력오류가 발생했습니다. 확인 후 다시 입력해 주세요.");
            }

        }
    }
    public static void showRateplans(List<RateplanVO> rateplans){
        ChargeMachineView.display("================요금제를 선택해주세요=================");
        for(int i=1;i<=rateplans.size();i++){
            RateplanVO rateplan = rateplans.get(i-1);
            System.out.println(i+". "+rateplan.getId() +
                    " (" + rateplan.getPrice() + "원)");
        }
        ChargeMachineView.display("===============================================");
    }
}
