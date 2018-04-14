package com.linchisin.Examination;

import java.util.Arrays;
import java.util.Scanner;

public class Practice {




    private static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }
    public static void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, 0, len - 1);
    }


	public static void bubbleSort(int [] array){
		int size=array.length;
		for(int i=0;i<size-1;i++){
			for(int j=0;j<size-1-i;j++){
				int k = j+1;
				if(array[j]>array[k]){
				    array[j]=array[i]^array[j];
				    array[i]=array[j]^array[i];
				    array[j]=array[i]^array[j];
				}
			}
		}
	}

	public static void insertSort(int [] array){
	    int size=array.length;
        for (int i = 1; i <size ; i++) {
            for (int j = i; j >=1 ; j--) {
                if(array[j]<array[j-1]){
                    array[j]=array[j-1]+array[j];
                    array[j-1]=array[j]-array[j-1];
                    array[j]=array[j]-array[j-1];
                }
            }
        }
    }

    public static int getMinIndex(int [] array){
	    int minIndex=0;
        for (int i = 1; i <array.length ; i++) {
            if (array[minIndex]>array[i]) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static int getMin(int [] array){
	    int minIndex=getMinIndex(array);
	    return array[minIndex];
    }


    public static void selectSort(int [] array){
        for (int i = 0; i <array.length-1 ; i++) {
            int minIndex=i;
            for (int j = i+1; j <array.length ; j++) {
                if(array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            if(i!=minIndex) {
                array[i] = array[i] + array[minIndex];
                array[minIndex] = array[i] - array[minIndex];
                array[i] = array[i] - array[minIndex];
            }
        }
    }

    //堆调整
    public static void heapify(int [] array, int root,int length){
	    int left=2*root+1;
	    int right=2*root+2;
	    if(left<length) {
            if (array[root] < array[left]) {
                int temp = array[root];
                array[root] = array[left];
                array[left] = temp;
            }
        }
        if(right<length){
            if(array[root]<array[right]){
                int temp=array[root];
                array[root]=array[right];
                array[right]=temp;
            }
	    }
	    if(left<length)
	        heapify(array,left,length);
        if(right<array.length)
            heapify(array,right,length);


    }
    public static void heapSort(int [] array){

	    //bulid a heap
        for (int i = array.length/2-1; i >=0 ; i--) {
            heapify(array,i,array.length);
        }

        //catch a heap
        for (int i = array.length-1; i >=0 ; i--) {
            int temp=array[0];
            array[0]=array[i];
            array[i]=temp;
            heapify(array,0,i);
        }
    }

    public static void quickSort(int [] array, int left,int right){
	    int pivot=right+1;

	    if(right<left||left<0||left>=array.length||right<0||right>=array.length){
	        return;
        }
        int i=left;
	    int j=right;
	    while(i<=j) {
            while (array[i] <=array[pivot]&&i<pivot) {
                i++;
            }
            while (array[j] >=array[pivot] && j >i) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }else if(i>=j){
                int temp=array[pivot];
                array[pivot]=array[i];
                array[i]=temp;
            }
        }
        quickSort(array,left,i-2);
	    quickSort(array,i+1,right);

    }

    private static void quickSortIA(int[]array,int p,int r){
        if(p<r) {
            int q=partition(array,p,r);
            quickSortIA(array,p,q-1);
            quickSortIA(array,q+1,r);
        }
    }



    private static int partition(int [] array, int p,int r){

        int pivot=array[r];
        int i=p-1;
        for (int j = p; j <r ; j++) {
            if(array[j]<=pivot){
                i+=1;
                int temp=array[j];
                array[j]=array[i];
                array[i]=temp;
            }
        }
        int temp=array[r];
        array[r]=array[i+1];
        array[i+1]=temp;
        return i+1;
    }




    //二分查找，若数在升序数组中，返回下标，否则返回它按照数组元素大小排序后的下标

    public static int queryIndiceFromNums(int num,int[]array,int left, int right){
	    int indice=left+(right-left)/2;
	    if(left==right){
	        if(num==array[indice]){
	            return indice;
            }else if(num>array[indice]){
	            return indice+1;
            }else{
	            return indice-1;
            }
        }
	    if (num==array[indice])
                return indice;
	    else if ( num>array[indice])
                return queryIndiceFromNums(num,array,indice+1,right);
	    else
                return queryIndiceFromNums(num,array,0,indice-1);
    }


	public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入原始数组的长度");
        int arrayLength=scanner.nextInt();
        int [] array= new int[arrayLength];
        for (int i = 0; i <arrayLength ; i++) {
            array[i]=scanner.nextInt();
        }
        System.out.println("原始数组为："+Arrays.toString(array));
        System.out.println();

        quickSortIA(array,0,arrayLength-1);
//        heapSort(array);

        for (int i = 0; i <array.length; i++) {
            System.out.print(array[i]+" ");
        }

        System.out.println("输入要查询的数：");
        int queryNum=scanner.nextInt();
        System.out.println("该数的序号为："+queryIndiceFromNums(queryNum,array,0,arrayLength-1));



        System.out.println();
        System.out.println(Arrays.toString(array));




	}

	

}
