package myth;

public class PrinterAtomic {

  static class Printer {

    private volatile static boolean flag = true;

  }
}
