package com.kvlt.nlp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author KVLT
 * 2018-03-08 15:02
 */
public class Demo {

    public static void main(String[] args) {
        String str = "AFG-阿富汗AHO-荷属安的列斯ALB-阿尔巴尼亚ALG-阿尔及利亚AND-安道尔ANG-安哥拉ANT-安提瓜和巴布达ARG-阿根廷ARM-亚美尼亚ARU-阿鲁巴ASA-美属萨摩亚AUS-澳大利亚AUT-奥地利AZE-阿塞拜疆BAH-巴哈马BAN-孟加拉国BAR-巴巴多斯BDI-布隆迪BEL-比利时BEN-贝宁BER-百慕大BHU-不丹BIH-波黑BIZ-伯利兹BLR-白俄罗斯BOL-玻利维亚BOT-博茨瓦纳BRA-巴西BRN-巴林BRU-文莱BUL-保加利亚BUR-布基纳法索CAF-中非CAM-柬埔寨CAN-加拿大CAY-开曼群岛CGO-刚果(布)CHA-乍得CHI-智利CHN-中国CIV-科特迪瓦CMR-喀麦隆COD-刚果（金）COK-库克群岛COL-哥伦比亚COM-科摩罗CPV-佛得角CRC-哥斯达黎加CRO-克罗地亚CUB-古巴CYP-塞浦路斯CZE-捷克DEN-丹麦DJI-吉布提DMA-多米尼加DOM-多米尼加共和国ECU-厄瓜多尔EGY-埃及ERI-厄立特里亚ESA-萨尔瓦多ESP-西班牙EST-爱沙尼亚ETH-埃塞俄比亚FIJ-斐济FIN-芬兰FRA-法国FSM-密克罗尼西亚联邦GAB-加蓬GAM-冈比亚GBR-英国GBS-几内亚比绍GEO-格鲁吉亚GEQ-赤道几内亚GER-德国GHA-加纳GRE-希腊GRN-格拉纳达GUA-危地马拉GUI-几内亚GUM-关岛GUY-圭亚那HAI-海地HKG-中国香港"
                + "HON-洪都拉斯HUN-匈牙利INA-印度尼西亚IND-印度IRI-伊朗IRL-爱尔兰IRQ-伊拉克ISL-冰岛ISR-以色列ISV-美属维尔京群岛ITA-意大利IVB-英属维尔京群岛JAM-牙买加JOR-约旦JPN-日本KAZ-哈萨克斯坦KEN-肯尼亚KGZ-吉尔吉斯斯坦KIR-基里巴斯KOR-韩国KSA-沙特KUW-科威特LAO-老挝LAT-拉脱维亚LBA-利比亚LBR-利比里亚LCA-圣卢西亚LES-莱索托LIB-黎巴嫩LIE-列支敦士登LTU-立陶宛LUX-卢森堡MAD-马达加斯加MAR-摩洛哥MAS-马来西亚MAW-马拉维MDA-摩尔多瓦MDV-马尔代夫MEX-墨西哥MGL-蒙古MHL-马绍尔群岛MKD-马其顿MLI-马里MLT-马耳他MNE-黑山MON-摩纳哥MOZ-莫桑比克MRI-毛里求斯MTN-毛里塔尼亚MYA-缅甸NAM-纳米比亚NCA-尼加拉瓜NED-荷兰NEP-尼泊尔NGR-尼日利亚NIG-尼日尔NOR-挪威NRU-瑙鲁NZL-新西兰OMA-阿曼PAK-巴基斯坦PAN-巴拿马PAR-巴拉圭PER-秘鲁PHI-菲律宾PLE-巴勒斯坦PLW-帕劳PNG-巴布亚新几内亚POL-波兰POR-葡萄牙PRK-朝鲜PUR-波多黎各QAT-卡塔尔ROU-罗马尼亚RSA-南非RUS-俄罗斯RWA-卢旺达SAM-萨摩亚SEN-塞内加尔SEY-塞舌尔SIN-新加坡SKN-圣基茨和尼维斯SLE-塞拉利昂SLO-斯洛文尼亚SMR-圣马力诺SOL-所罗门群岛SOM-索马里SRB-塞尔维亚SRI-斯里兰卡STP-圣多美和普林西比SUD-苏丹SUI-瑞士SUR-苏里南SVK-斯洛伐克SWE-瑞典SWZ-斯威士兰SYR-叙利亚TAN-坦桑尼亚TGA-汤加THA-泰国TJK-塔吉克斯坦TKM-土库曼斯坦TLS-东帝汶TOG-多哥TPE-中华台北TRI-特立尼达和多巴哥"
                + "TUN-突尼斯TUR-土耳其TUV-图瓦卢UAE-阿联酋UGA-乌干达UKR-乌克兰URU-乌拉圭USA -美国UZB-乌兹别克斯坦VAN-瓦努阿图 VEN-委内瑞拉VIE-越南VIN-圣文森特和格林纳丁斯YEM-也门ZAM-赞比亚ZIM-津巴布韦";

        /*String string = "041偷功.mp3";
        System.out.println(judgeChina(string, 5));*/

       /* String str1 = "中华    人   民共   和国hello Word";
        System.out.println(getChineseOrEnglishOrNumber("CHINESE", str1));*/

        char[] charArray = str.toCharArray();

        List<Integer> lastHanziIdxSet = Lists.newArrayList();
        int idxS = 0;
        for (int i = 0; i < charArray.length; i++) {
            int idxE = 0;
            if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {  // 中文字符
                if (idxS == 0) {
                    idxS = i;
                } else if (i - idxS == 1) {
                    idxS = i;
                }
            } else if (i - idxS != 1) {
                if (idxS != 0) {
                    lastHanziIdxSet.add(idxS);
//                    System.out.print(idxS + "\t");
                }
                idxS = 0;
            }
        }

        Set<String> mapSet = Sets.newLinkedHashSet();

        int len = lastHanziIdxSet.size();
        for (int i = 0; i < charArray.length; i++) {
            int start = 0;

            int end = 0;
            int count = 0;
            char[] ssst = null;
            while (count < len) {
                ssst = new char[80];

                end = lastHanziIdxSet.get(count) + 1;
                System.arraycopy(charArray, start, ssst, 0, end - start);
//                System.out.println(ssst);

                mapSet.add(String.valueOf(ssst));

                start = lastHanziIdxSet.get(count) + 1;
                count++;
            }
        }

        Map<String, String> map = Maps.newHashMap();
        for (String s : mapSet) {
            String[] arr = s.split("-");
            if (arr.length == 2) {
                map.put(arr[1], arr[0]);
                System.out.println(arr[0]);
            }
        }


        /*System.out.println(str);
        for (int i = 0; i< charArray.length; i++) {
            boolean flag = true;
            for (Integer idx : lastHanziIdxSet) {
                if (i == idx) {
                    System.out.print("^");
                    flag = false;
                    continue;
                }
            }
            if (flag)
                System.out.print(charArray[i]);
        }*/

//        String sss = "[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}";
    }

}
