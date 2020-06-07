package section03;

/**
 * 项目经理
 *
 * @author bdry
 */
public class Manager extends Employee {
  /** 奖金 */
  private double bonus;

  Manager(String name, String id, double pay, double bonus) {
    super(name, id, pay);
    this.bonus = bonus;
  }

  /** 工作内容 */
  @Override
  public void work() {
    System.out.println("我的工作是管理");
  }
}
