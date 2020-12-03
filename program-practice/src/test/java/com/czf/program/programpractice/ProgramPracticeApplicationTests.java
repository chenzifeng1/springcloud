package com.czf.program.programpractice;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProgramPracticeApplicationTests {

    @Test
    void contextLoads() {

        String json = " {\n" +
                "  \"tankId\":1,\n" +
                "  \"alarm_type\":2\n" +
                "}";

       GsonTest gt = new  Gson().fromJson(json,GsonTest.class);
        System.out.println(gt.getAlarmType());
    }

    public class GsonTest{
        int tankId;
        int alarmType;

        public GsonTest(int tankId, int alarm_type) {
            this.tankId = tankId;
            this.alarmType = alarm_type;
        }

        public GsonTest() {
        }

        public int getTankId() {
            return tankId;
        }

        public void setTankId(int tankId) {
            this.tankId = tankId;
        }

        public int getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(int alarmType) {
            this.alarmType = alarmType;
        }
    }
}
