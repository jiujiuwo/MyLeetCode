import java.util.*;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (tmp.containsKey(nums1[i])) {
                tmp.put(nums1[i], tmp.get(nums1[i]) + 1);
            } else {
                tmp.put(nums1[i], 1);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (tmp.containsKey(nums2[i]) && tmp.get(nums2[i]) > 0) {
                resultList.add(nums2[i]);
                tmp.put(nums2[i], tmp.get(nums2[i]) - 1);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}