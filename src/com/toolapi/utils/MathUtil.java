package com.toolapi.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.primitives.Ints;

/** 
* @author byxiaobai
* 类说明 
*/
public class MathUtil {
	private static final Random RANDOM=new Random();
	/**
	 * 得到两个数之间的数组(包括这两个数)
	 * 输入要求为正整数
	 * @param num1 较小数
	 * @param num2 较大数
	 * @return
	 */
	public static int[] getNumberToNumber(int num1,int num2) {
		int numbersNumber=num2-num1+1;//数字数量
		int[] nums=new int[numbersNumber];
		for(int i=0;i<numbersNumber;i++,num1++) {
			nums[i]=num1;
		}
		return nums;
	}
	/**
	 * 查看某个数字是否在范围之内
	 * @param number 要判断的数字
	 * @param num1 范围中较小的数字
	 * @param num2 范围中较大的数字
	 * @return
	 */
	public static boolean isInRange(int number,int num1,int num2) {
		if(number>=num1&&number<=num2)
			return true;
		return false;
	}
	/**
	 * 得到一个随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNumber(int min,int max) {
		int randNumber =RANDOM.nextInt(max - min + 1) + min; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
		return randNumber;
	}
	
	public static double getRandomNumber(double min,double max) {
		double randNumber =RANDOM.nextDouble()*(max - min + 1) + min; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
		return randNumber;
	}
	
	public static double getBiggestNumber(double...numbers) {
		double max=-1;
		for(double number:numbers) {
			if(number>max)
				max=number;
		}
		return max;
	}
	public static double getSmallestNumber(double...numbers) {
		double max=-1;
		for(double number:numbers) {
			if(number<max)
				max=number;
		}
		return max;
	}
	/**
	 * 删除掉大数组中包括的小数组的值
	 * @param bigNumbers
	 * @param smallNumbers
	 * @return
	 */
	public static int[] deleteNumbers(int[] bigNumbers,int[] smallNumbers) {
		if(smallNumbers==null)return bigNumbers;
		if(bigNumbers==null)return null;
		for(int number:smallNumbers) {
			bigNumbers=exceptNumbers(bigNumbers,number);
		}
		return bigNumbers;
	}
	
	/**
	 * 去除掉数组中的所有值为指定值的空间
	 * @param bigNumbers
	 * @return
	 */
	private static int[] exceptNumbers(int[] bigNumbers,int checkNumber) {
		int[] indexs=getNumberNotEqualIndexes(bigNumbers,checkNumber);
		int[] newNumbers=new int[indexs.length];//储存新数字的数组
		for(int i=0;i<indexs.length;i++) {
			newNumbers[i]=bigNumbers[indexs[i]];
		}
		return newNumbers;
	}
	/**
	 * 得到值不为提供值的所有索引
	 * @param numbers 数组
	 */
	private static int[] getNumberNotEqualIndexes(int[] numbers,int checkNumber) {
		List<Integer> indexList=new ArrayList<>();
		for(int i=0;i<numbers.length;i++) {
			int number=numbers[i];
			if(number!=checkNumber) {
				indexList.add(i);
			}
		}
		return Ints.toArray(indexList);
	}
	/**
	 * 得到一些不重复的随机数
	 * @param min 最小值
	 * @param max 最大值
	 * @param numberAmount 要得到的数量
	 * @return
	 */
	public static int[] getNoRepeatRandomNumbers(int min,int max,int numberAmount) {
        List<Integer> list = new ArrayList<Integer>();
        while(list.size() < numberAmount){
            int number = MathUtil.getRandomNumber(min, max);
            if(!list.contains(number)){
                list.add(number);
            }
        }
        return Ints.toArray(list);
	}
	public static double getBiggerNumber(double number1,double number2) {
		if(number1>number2) {
			return number1;
		}
		return number2;
	}
	public static int abs(int a) {
		if(a<0)return -a;
		return a;
	}
	
	
	
	
	
	
	
	
	
	
}