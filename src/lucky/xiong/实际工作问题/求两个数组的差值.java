package lucky.xiong.实际工作问题;

/**
 * @author XiongJl
 * @date 2020/7/16 11:14
 */
public class 求两个数组的差值 {

    public static void main(String[] args) {
        
    }

    class Lot{
        private String lotId;
        private String eqpId;

        public Lot() {
        }

        public Lot(String lotId, String eqpId) {
            this.lotId = lotId;
            this.eqpId = eqpId;
        }

        public String getLotId() {
            return lotId;
        }

        public void setLotId(String lotId) {
            this.lotId = lotId;
        }

        public String getEqpId() {
            return eqpId;
        }

        public void setEqpId(String eqpId) {
            this.eqpId = eqpId;
        }


    }
}
