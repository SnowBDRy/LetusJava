package section03;

/**
 * 程序员
 *
 * @author bdry
 */
public class Programmer extends Employee {

  Programmer(String name, String id, double pay) {
    super(name, id, pay);
  }

  /** 工作内容 */
  @Override
  public void work() {
    System.out.println("我的工作是写代码");
  }
}
