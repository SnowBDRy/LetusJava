package section02;

/**
 * 数组工具类
 *
 * @author bdry
 * @since 2020/05/23
 * @version v1.0
 */
public class ArrayTool {
  // 创建对象的前提是有构造方法，没显式生命编译器会自动创建一个默认无参的构造方法
  // 若这个类全部都为静态方法，那么这个类就没有创建对象的必要，但想防止这个类去创建对象，应该如何做？
  // 将这个类的构造方法私有化，其他类无法访问，自然不能创建对象

  /** 构造方法，私有，不让其他类访问 */
  private ArrayTool() {}

  /**
   * 获取整数数组的最大值 （解题思路：数组中的元素两两比较，取得较大的数组下标，直至最后一个元素）
   *
   * @param array
   * @return maxArray[]
   */
  public static int getMaxElement(int[] array) {
    // 最大元素的下标
    int maxIndex = 0;
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[maxIndex]) {
        maxIndex = i;
      }
    }
    return array[maxIndex];
  }

  /**
   * 数组排序（解题思路：冒泡排序）
   *
   * @param array
   */
  public static void sort(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i] > array[j]) {
          swap(array, i, j);
        }
      }
    }
  }

  /**
   * 交换两个变量的值
   *
   * @param array
   * @param i
   * @param j
   */
  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * 获取数组中第一次对应值的下标
   *
   * @param array
   * @param value
   * @return
   */
  public static int getIndex(int[] array, int value) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == value) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 把数组变成字符串
   *
   * @param array
   * @return
   */
  public static String convertArrayToString(int[] array) {
    String result = "[ ";
    for (int i = 0; i < array.length; i++) {

      if (i != array.length - 1) {
        result = result + array[i] + ", ";
      } else {
        result = result + array[i] + " ]";
      }
    }
    return result;
  }
}
