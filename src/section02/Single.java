package section02;

/**
 * 单例类（饿汉式）
 *
 * @author bdry
 */
public class Single {
  /** 2.的解决策略：静态化创建的对象，使其变为共有，且只执行一次 */
  private static Single single = new Single();

  /** 1.的解决策略：私有化构造方法，使其无法使用new来创建对象 */
  private Single() {}

  /**
   * 3.的解决策略：a.由于不能创建对象，方法应该静态化 b.除本方法，不能有其他方式访问对象，故其他成员私有化（不能使用Single.single这种方式访问）
   *
   * @return Single
   */
  public static Single getInstance() {
    return single;
  }

  public void printMessage() {
    System.out.println("我是全局变量哈。");
  }
}
