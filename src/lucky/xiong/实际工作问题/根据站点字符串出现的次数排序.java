package lucky.xiong.实际工作问题;

import java.util.*;

/**
 * @author XiongJl
 * @date 2020/6/19 15:32
 */
public class 根据站点字符串出现的次数排序 {

    public static void main(String[] args) {
        TrackOutRes r1 = new TrackOutRes("l1","s1","e1");
        TrackOutRes r2 = new TrackOutRes("l2","s2","e1");
        TrackOutRes r3 = new TrackOutRes("l3","s1","e1");
        TrackOutRes r4 = new TrackOutRes("l4","s2","e1");
        TrackOutRes r5 = new TrackOutRes("l5","s3","e1");
        TrackOutRes r8 = new TrackOutRes("l5","s3","e1");
        TrackOutRes r9 = new TrackOutRes("l5","s3","e1");
        TrackOutRes r10 = new TrackOutRes("l5","s3","e1");
        TrackOutRes r11 = new TrackOutRes("l5","s3","e1");
        TrackOutRes r12 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r13 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r14 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r15 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r16 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r17 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r18 = new TrackOutRes("l4","s10","e1");
        TrackOutRes r19 = new TrackOutRes("l6","s12","e1");
        TrackOutRes r6 = new TrackOutRes("l6","s4","e1");
        TrackOutRes r7 = new TrackOutRes("l7","s1","e1");

        List<TrackOutRes> res = new ArrayList<>();
        res.add(r7);
        res.add(r6);
        res.add(r5);
        res.add(r4);
        res.add(r3);
        res.add(r2);
        res.add(r1); res.add(r8); res.add(r9); res.add(r10);res.add(r11);
        res.add(r12); res.add(r13); res.add(r14); res.add(r15);res.add(r16);
        res.add(r17); res.add(r18); res.add(r19);
        res.forEach(System.out::println);
        List<TrackOutRes> res2 = new ArrayList<>();
        res2.add(r7);
        res2.add(r6);
        res2.add(r5);
        res2.add(r4);
        res2.add(r3);
        res2.add(r2);
        res2.add(r1); res2.add(r8); res2.add(r9); res2.add(r10);res2.add(r11);
        res2.add(r12); res2.add(r13); res2.add(r14); res2.add(r15);res2.add(r16);
        res2.add(r17); res2.add(r18); res2.add(r19);
//        res2.forEach(System.out::println);
        trackOutResComparator(res);
        trackOutResComparatorV2(res2);
        // 排序后
        System.out.println("排序后");
        res.forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        res2.forEach(System.out::println);
    }


    /**
     * 出站的数据返回结果类
     */
    private static class TrackOutRes{
        private String lotId;
        private String stationId;
        private String eqpgroupId;

        public String getLotId() {
            return lotId;
        }

        public void setLotId(String lotId) {
            this.lotId = lotId;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getEqpgroupId() {
            return eqpgroupId;
        }

        public void setEqpgroupId(String eqpgroupId) {
            this.eqpgroupId = eqpgroupId;
        }

        public TrackOutRes(String lotId, String stationId, String eqpgroupId) {
            this.lotId = lotId;
            this.stationId = stationId;
            this.eqpgroupId = eqpgroupId;
        }

        @Override
        public String toString() {
            return "TrackOutRes{" +
                    "lotId='" + lotId + '\'' +
                    ", stationId='" + stationId + '\'' +
                    ", eqpgroupId='" + eqpgroupId + '\'' +
                    '}';
        }

        public TrackOutRes() {
        }
    }


    static void trackOutResComparator(List<TrackOutRes> results){
        long startTime = System.currentTimeMillis();
        if (results.size()<=2){
            return;
        }
        // 获取所有已有站点
        Set<String> stations = new HashSet<>();
        results.forEach(r1 -> stations.add(r1.getStationId()));
        // 创建map，保存每个站点对应的实体类list .  0.75 的hash
        Map<String,List> map = new HashMap<>((int)Math.rint(stations.size()/0.75));
        Iterator<String> it = stations.iterator();
        // 循环遍历已有的站点，并添加对应的实体类数组
        while (it.hasNext()) {
            String stationId = it.next();
            List<TrackOutRes> list = new ArrayList<>();
            Iterator<TrackOutRes> listIt = results.iterator();
            while(listIt.hasNext()){
                TrackOutRes trackOutRes = listIt.next();
                if (stationId.equals(trackOutRes.getStationId())){
                    list.add(trackOutRes);
                    // 从原list中移除当前元素
                    listIt.remove();
                }
            }
            map.put(stationId,list);
        }
        // 迭代map ， 使用stationId出现的次数排序
        List<Map.Entry<String,List>> sortList = new ArrayList<>(map.entrySet());
        sortList.sort((Comparator.comparingInt(o -> o.getValue().size())));
        results.clear();
        sortList.forEach(e -> results.addAll(e.getValue()));
        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("第一种程序运行时间： " + (endTime - startTime) + "ms");
    }

    /**
     * 尝试优化
     * @param results
     */
    static void trackOutResComparatorV2(List<TrackOutRes> results){
        long startTime = System.currentTimeMillis();
        if (results.size()<=2){
            long endTime = System.currentTimeMillis(); // 获取结束时间
            System.out.println("第二种程序运行时间： " + (endTime - startTime) + "ms");
            return;
        }

        Map<String,List> map = new HashMap<>();
        for (TrackOutRes res:results){
            String stationId = res.getStationId();
            List v ;
            v = ((v = map.get(stationId)) != null) || map.containsKey(stationId) ? v: new ArrayList<>();
            v.add(res);
            map.put(stationId,v);
        }
        // 迭代map ， 使用stationId出现的次数排序
        List<Map.Entry<String,List>> sortList = new ArrayList<>(map.entrySet());
        sortList.sort((Comparator.comparingInt(o -> o.getValue().size())));
        results.clear();
        sortList.forEach(e -> results.addAll(e.getValue()));
        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("第二种程序运行时间： " + (endTime - startTime) + "ms");
        Optional.empty() ;
    }

}
