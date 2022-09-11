package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for CustomArray App.
 */
public class CustomArrayTests
{
    public CustomArray<Integer> array;

    @Before
    public void beforeEachTest() {
        array = new CustomArray<>();
    }

    @Test
    public void sizeTest() {

        Assert.assertEquals(0, array.size());
        for (int i = 1; i < 5; ++i) {
            array.add(i);
            Assert.assertEquals(i, array.size());
        }
    }

    @Test
    public void isEmptyTest() {
        Assert.assertEquals(0, array.size());
        array.add(1);
        array.remove(0);
        Assert.assertEquals(0, array.size());
    }

    @Test
    public void addTest() {

        for (int i = 1; i < 5; ++i) {
            array.add(i);
            Assert.assertEquals(i, array.size());
        }
    }

    @Test
    public void addAllTest() {

        array.addAll(new Integer[]{1, 2, 3, 4, 5, 6});
        Assert.assertEquals(6, array.size());

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(array.toArray()));
    }

    @Test
    public void addAnotherCollectionTest() {
        array.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(6, array.size());

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(array.toArray()));
    }

    @Test
    public void addAllByIndexTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals(4, array.size());

        array.addAll(0, new Integer[]{-1, -2, -3});
        Assert.assertEquals(Arrays.asList(-1, -2, -3, 1, 2, 3, 4), Arrays.asList(array.toArray()));

        array.addAll(6, new Integer[]{5, 6, 7});
        Assert.assertEquals(Arrays.asList(-1, -2, -3, 1, 2, 3, 5, 6, 7, 4), Arrays.asList(array.toArray()));
    }

    @Test
    public void getTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));

        Assert.assertEquals(Integer.valueOf(1), array.get(0));
        Assert.assertEquals(Integer.valueOf(4), array.get(3));
        Assert.assertEquals(Integer.valueOf(3), array.get(2));
    }

    @Test
    public void setTest() {


        array.addAll(Arrays.asList(1, 2, 3, 4));

        array.set(0, 100);
        Assert.assertEquals(Arrays.asList(100, 2, 3, 4), Arrays.asList(array.toArray()));

        array.set(3, 400);
        Assert.assertEquals(Arrays.asList(100, 2, 3, 400), Arrays.asList(array.toArray()));
    }

    @Test
    public void removeByIndexTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));

        array.remove(1);
        Assert.assertEquals(Arrays.asList(1, 3, 4), Arrays.asList(array.toArray()));

        array.remove(2);
        Assert.assertEquals(Arrays.asList(1, 3), Arrays.asList(array.toArray()));

        array.remove(0);
        Assert.assertEquals(Arrays.asList(3), Arrays.asList(array.toArray()));

        array.remove(0);
        Assert.assertTrue(array.isEmpty());
    }

    @Test
    public void removeByValueTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));

        array.remove(Integer.valueOf(1));
        Assert.assertEquals(Arrays.asList(2, 3, 4), Arrays.asList(array.toArray()));

        array.remove(Integer.valueOf(2));
        Assert.assertEquals(Arrays.asList(3, 4), Arrays.asList(array.toArray()));

        array.remove(Integer.valueOf(3));
        Assert.assertEquals(Arrays.asList(4), Arrays.asList(array.toArray()));

        array.remove(Integer.valueOf(4));
        Assert.assertTrue(array.isEmpty());
    }

    @Test
    public void containsTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));

        Assert.assertTrue(array.contains(1));
        Assert.assertTrue(array.contains(2));
        Assert.assertTrue(array.contains(3));
        Assert.assertTrue(array.contains(4));
        Assert.assertFalse(array.contains(5));
    }

    @Test
    public void indexOfTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));

        Assert.assertEquals(0, array.indexOf(1));
        Assert.assertEquals(1, array.indexOf(2));
        Assert.assertEquals(2, array.indexOf(3));
        Assert.assertEquals(3, array.indexOf(4));
        Assert.assertEquals(-1, array.indexOf(5));
    }

    @Test
    public void reverseTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), Arrays.asList(array.toArray()));

        array.reverse();
        Assert.assertEquals(Arrays.asList(4, 3, 2, 1), Arrays.asList(array.toArray()));

        array.remove(0);

        array.reverse();
        Assert.assertEquals(Arrays.asList(1, 2, 3), Arrays.asList(array.toArray()));
    }

    @Test
    public void toStringTest() {

        array.addAll(Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals("[ 1 2 3 4 ]", array.toString());
    }

    @Test
    public void toArrayTest() {
        array.addAll(Arrays.asList(1, 3, 4));
        Assert.assertEquals(Arrays.asList(1, 3, 4), Arrays.asList(array.toArray()));
    }
}
