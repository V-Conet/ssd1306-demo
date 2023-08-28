package top.vcox.joled;

import de.pi3g.pi.oled.Font;
import de.pi3g.pi.oled.OLEDDisplay;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println(".96 OLED Display - Running...");
        try{
            OLEDDisplay o = new OLEDDisplay();

            OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            //##################### Max length for 5x8
            while(true){
                //Current OS
                o.drawStringCentered("Debian 12 - Raspberry".toUpperCase(),Font.FONT_4X5,0,true);

                //Current Time & Date
                LocalDate ld = LocalDate.now();
                LocalTime ldt = LocalTime.now();
                o.drawString("Date: "+ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Font.FONT_5X8,0,6,true);
                o.drawString("Time: " + ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss")),Font.FONT_5X8,0,15,true);


                //System Info
                //draw bar of sys info
                //x from 1 to 63,y from 23 to 63
                o.drawStringCentered("System Info",Font.FONT_5X8,24,true);
                for (int x = 1; x <= 127; x++) {
                    o.setPixel(x, 23, true); // 顶边
                    o.setPixel(x, 63, true);   // 底边
                }
                for (int y = 23; y <= 63; y++) {
                    o.setPixel(1, y, true); // 左边
                    o.setPixel(127, y, true);   // 右边
                }
                //get sysinfo and write
                double sysAvg = osBean.getSystemLoadAverage();
                o.drawString("SYS LOAD: "+ String.format("%.2f", sysAvg) + "%",Font.FONT_4X5,4,33,true);



                o.update();//write to buffer
                o.clear();//clear num display err
            }

//            Thread.sleep(3000);







        }catch(Exception e){
            e.getStackTrace();
        }
    }
}