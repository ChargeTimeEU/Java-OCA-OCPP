package eu.chargetime.ocpp.utilities.test;

/*
 ubitricity.com - Java-OCA-OCPP

 MIT License

 Copyright (C) 2018 Evgeny Pakhomov <eugene.pakhomov@ubitricity.com>

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

import static org.junit.Assert.*;

import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.*;
import org.junit.Test;

/**
 * Unit test for {@link MoreObjects}.
 *
 * @author <a href=mailto:eugene.pakhomov@ubitricity.com>Eugene Pakhomov</a>
 */
public class MoreObjectsTest {

  private final TestObjectClass testObject = new TestObjectClass();
  private final ChildTestObjectClass childTestObject = new ChildTestObjectClass();

  @Test
  public void testEqualsWitSameObjects() {
    assertEquals(true, MoreObjects.equals(testObject, testObject));
  }

  @Test
  public void testEqualsWithNullObjectA() {
    assertEquals(false, MoreObjects.equals(null, testObject));
  }

  @Test
  public void testEqualsWithNullObjectB() {
    assertEquals(false, MoreObjects.equals(testObject, null));
  }

  @Test
  public void testEqualsWithNullObjectsAB() {
    assertEquals(true, MoreObjects.equals(null, null));
  }

  @Test
  public void testEqualsWithDifferentObjects() {
    assertEquals(false, MoreObjects.equals(testObject, new TestObjectClass()));
  }

  @Test
  public void testDeepEqualsWithSameObjects() {
    assertEquals(true, MoreObjects.deepEquals(testObject, testObject));
  }

  @Test
  public void testDeepEqualsWithNullObjectA() {
    assertEquals(false, MoreObjects.deepEquals(null, testObject));
  }

  @Test
  public void testDeepEqualsWithNullObjectB() {
    assertEquals(false, MoreObjects.deepEquals(testObject, null));
  }

  @Test
  public void testDeepEqualsWithNullObjectsAB() {
    assertEquals(true, MoreObjects.deepEquals(null, null));
  }

  @Test
  public void testDeepEqualsWithDifferentObjects() {
    assertEquals(false, MoreObjects.deepEquals(testObject, new TestObjectClass()));
  }

  @Test
  public void testHashCode() {
    assertEquals(testObject.hashCode(), MoreObjects.hashCode(testObject));
  }

  @Test
  public void testHashCodeWithNullObject() {
    assertEquals(0, MoreObjects.hashCode(null));
  }

  @Test
  public void testHash() {
    assertEquals(Arrays.hashCode(new Object[] {testObject}), MoreObjects.hash(testObject));
  }

  @Test
  public void testHashWithNullObjects() {
    assertEquals(961, MoreObjects.hash(null, null));
  }

  @Test
  public void testCloneObjectArray() {
    Object a = new Object();
    Object[] array = new Object[] {a};
    Object[] arrayClone = MoreObjects.clone(array);
    assertEquals(1, arrayClone.length);
    assertNotEquals(array, arrayClone);
    assertEquals(array[0], arrayClone[0]);
  }

  @Test
  public void testCloneByteArray() {
    byte[] array = new byte[1];
    array[0] = '1';
    byte[] arrayClone = MoreObjects.clone(array);
    assertEquals(1, arrayClone.length);
    assertNotEquals(array, arrayClone);
    assertEquals(array[0], arrayClone[0]);
  }

  @Test
  public void testCloneNull() {
    assertArrayEquals(null, MoreObjects.clone((Object[]) null));
  }

  @Test
  public void testToString() {
    assertEquals("TestObjectClass{value=test}", testObject.toString());
  }

  @Test
  public void testToStringSecure() {
    assertEquals(
        "TestObjectClassWithSecureField{secret=********}",
        (new TestObjectClassWithSecureField()).toString());
  }

  @Test
  public void testToStringWithObjectWithSuperclass() {
    assertEquals(
        "ChildTestObjectClass{TestObjectClass{value=test}, flag=true}", childTestObject.toString());
  }

  @Test
  public void testToStringWithClass() {
    assertEquals("TestObjectClass{value=test}", testObject.toStringWithClass());
  }

  @Test
  public void testToStringWithClassname() {
    assertEquals("TestObjectClass{value=test}", testObject.toStringWithClassName());
  }

  @Test
  public void testToStringWithNullArray() {
    assertEquals(
        "TestObjectClassWithNullArray{array=null}",
        (new TestObjectClassWithNullArray()).toString());
  }

  @Test
  public void testToStringWithShortArray() {
    assertEquals(
        "TestObjectClassWithShortArray{array=[49, 50]}",
        (new TestObjectClassWithShortArray()).toString());
  }

  @Test
  public void testToStringWithLongArray() {
    assertEquals(
        "TestObjectClassWithLongArray{array.length=40}",
        (new TestObjectClassWithLongArray()).toString());
  }

  @Test
  public void testToStringWithLongArrayFullOutput() {
    assertEquals(
        "TestObjectClassWithLongArray{array=[49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, "
            + "54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48]}",
        (new TestObjectClassWithLongArray()).toString(true));
  }

  @Test
  public void testToStringWithList() {
    assertEquals(
        "TestObjectClassWithList{list.size=1}", (new TestObjectClassWithList()).toString());
  }

  @Test
  public void testToStringWithListFullOutput() {
    assertEquals(
        "TestObjectClassWithList{list=[test]}", (new TestObjectClassWithList()).toString(true));
  }

  @Test
  public void testToStringWithSet() {
    assertEquals("TestObjectClassWithSet{set.size=1}", (new TestObjectClassWithSet()).toString());
  }

  @Test
  public void testToStringWithSetFullOutput() {
    assertEquals(
        "TestObjectClassWithSet{set=[test]}", (new TestObjectClassWithSet()).toString(true));
  }

  @Test
  public void testToStringWithMap() {
    assertEquals("TestObjectClassWithMap{map.size=1}", (new TestObjectClassWithMap()).toString());
  }

  @Test
  public void testToStringWithMapFullOutput() {
    assertEquals(
        "TestObjectClassWithMap{map={test=test}}", (new TestObjectClassWithMap()).toString(true));
  }

  @Test
  public void testToStringWithQueue() {
    assertEquals(
        "TestObjectClassWithQueue{queue.size=1}", (new TestObjectClassWithQueue()).toString());
  }

  @Test
  public void testToStringWithQueueFullOutput() {
    assertEquals(
        "TestObjectClassWithQueue{queue=[test]}", (new TestObjectClassWithQueue()).toString(true));
  }

  @Test
  public void testToStringHelperOmitNullValues() {
    assertEquals(
        "TestObjectClassWithNullArray{}",
        MoreObjects.toStringHelper(TestObjectClassWithNullArray.class, true)
            .omitNullValues()
            .toString());
  }

  @Test
  public void testToStringHelperWithPrimitiveTypes() {
    assertEquals(
        "TestPrimitiveTypeObjectClass{b=true, c=c, d=0.1, f=0.2, i=3, l=4}",
        (new TestPrimitiveTypesObjectClass().toString()));
  }

  @Test
  public void testToStringHelperWithPrimitiveArrays() {
    assertEquals(
        "TestPrimitiveArraysObjectClass{b=[true], c=[c], d=[0.1], f=[0.2], i=[3], l=[4]}",
        (new TestPrimitiveArraysObjectClass().toString()));
  }

  @Test
  public void testToStringHelperWithObjectArray() {
    TestObjectClass[] array =
        new TestObjectClass[] {new TestObjectClass("1"), new TestObjectClass("2")};
    assertEquals("{array.length=2}", MoreObjects.toStringHelper("").add("array", array).toString());
  }

  @Test
  public void testToStringHelperWitNullObjectArray() {
    assertEquals(
        "{array=null}",
        MoreObjects.toStringHelper("").add("array", (TestObjectClass[]) null).toString());
  }

  @Test
  public void testToStringHelperAddPrimitiveValues() {
    TestPrimitiveTypesObjectClass container = new TestPrimitiveTypesObjectClass();
    assertEquals(
        "{true, c, 0.1, 0.2, 3, 4}",
        MoreObjects.toStringHelper("")
            .addValue(container.b)
            .addValue(container.c)
            .addValue(container.d)
            .addValue(container.f)
            .addValue(container.i)
            .addValue(container.l)
            .toString());
  }

  private class TestObjectClassWithSecureField {
    private String secret = "secret";

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this).addSecure("secret", secret).toString();
    }
  }

  private class TestObjectClassWithNullArray {
    private byte[] array = null;

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this, false).add("array", array).toString();
    }
  }

  private abstract class TestObjectClassWithContainer {

    public abstract String toString(boolean withDetails);

    @Override
    public String toString() {
      return toString(false);
    }
  }

  private class TestObjectClassWithList extends TestObjectClassWithContainer {
    private List<String> list = new ArrayList<String>();

    {
      list.add("test");
    }

    @Override
    public String toString(boolean withDetails) {
      return MoreObjects.toStringHelper("TestObjectClassWithList", withDetails)
          .add("list", list)
          .toString();
    }
  }

  private class TestObjectClassWithSet extends TestObjectClassWithContainer {
    private Set<String> set = new HashSet<String>();

    {
      set.add("test");
    }

    @Override
    public String toString(boolean withDetails) {
      return MoreObjects.toStringHelper("TestObjectClassWithSet", withDetails)
          .add("set", set)
          .toString();
    }
  }

  private class TestObjectClassWithMap extends TestObjectClassWithContainer {
    private Map<String, String> map = new HashMap<String, String>();

    {
      map.put("test", "test");
    }

    @Override
    public String toString(boolean withDetails) {
      return MoreObjects.toStringHelper("TestObjectClassWithMap", withDetails)
          .add("map", map)
          .toString();
    }
  }

  private class TestObjectClassWithQueue extends TestObjectClassWithContainer {
    private Queue<String> queue = new ArrayDeque<String>();

    {
      queue.add("test");
    }

    @Override
    public String toString(boolean withDetails) {
      return MoreObjects.toStringHelper("TestObjectClassWithQueue", withDetails)
          .add("queue", queue)
          .toString();
    }
  }

  private class TestObjectClassWithShortArray {
    private byte[] array = "12".getBytes();

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("TestObjectClassWithShortArray")
          .add("array", array)
          .toString();
    }
  }

  private class TestObjectClassWithLongArray extends TestObjectClassWithContainer {
    private byte[] array = "1234567890123456789012345678901234567890".getBytes();

    @Override
    public String toString(boolean withDetails) {
      return MoreObjects.toStringHelper("TestObjectClassWithLongArray", withDetails)
          .add("array", array)
          .toString();
    }
  }

  private class TestObjectClass {
    private String value = "test";

    public TestObjectClass() {}

    public TestObjectClass(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("TestObjectClass").add("value", value).toString();
    }

    public String toStringWithClass() {
      return MoreObjects.toStringHelper(this.getClass()).add("value", value).toString();
    }

    public String toStringWithClassName() {
      return MoreObjects.toStringHelper("TestObjectClass").add("value", value).toString();
    }
  }

  private class ChildTestObjectClass extends TestObjectClass {
    private boolean flag = true;

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .addValue(super.toString())
          .add("flag", flag)
          .toString();
    }
  }

  private class TestPrimitiveTypesObjectClass {
    boolean b = true;
    char c = 'c';
    double d = 0.1;
    float f = 0.2f;
    int i = 3;
    long l = 4l;

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("TestPrimitiveTypeObjectClass")
          .add("b", b)
          .add("c", c)
          .add("d", d)
          .add("f", f)
          .add("i", i)
          .add("l", l)
          .toString();
    }
  }

  private class TestPrimitiveArraysObjectClass {
    boolean[] b = new boolean[] {true};
    char[] c = new char[] {'c'};
    double[] d = new double[] {0.1};
    float[] f = new float[] {0.2f};
    int[] i = new int[] {3};
    long[] l = new long[] {4l};

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("TestPrimitiveArraysObjectClass")
          .add("b", b)
          .add("c", c)
          .add("d", d)
          .add("f", f)
          .add("i", i)
          .add("l", l)
          .toString();
    }
  }
}
