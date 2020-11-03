package myPkg;

import jason.control.ExecutionControl;

public class MyExecutionControl extends ExecutionControl {
  protected void startNewCycle(){
    if (super.getCycleNumber() == 0){
      try {
        System.out.println("entrou");
        Thread.sleep(20000);
      } catch (Exception e) {}
    }
    super.startNewCycle();
  }
    // protected void allAgsFinished() {
    //     try {
    //       System.out.println("entrou");
    //       super.allAgsFinished();
    //       // Thread.sleep(20);
    //     } catch (Exception e) {}
    // }
}
