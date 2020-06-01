package section02;

/**
 * 数组工具类使用
 *
 * @author bdry
 */
public class Demo01 {
  public static void main(String[] args) {
    int[] arr = {4, 8, 2, 9, 72, 6};

    ArrayTool.sort(arr);
    System.out.println(ArrayTool.convertArrayToString(arr));
  }
}
