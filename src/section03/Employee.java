package section03;

/**
 * 雇员父类
 *
 * @author bdry
 */
public abstract class Employee {
  /** 姓名 */
  private String name;
  /** 工号 */
  private String id;
  /** 薪水 */
  private double pay;

  Employee(String name, String id, double pay) {
    this.name = name;
    this.id = id;
    this.pay = pay;
  }
  /** 工作内容 */
  public abstract void work();
}
