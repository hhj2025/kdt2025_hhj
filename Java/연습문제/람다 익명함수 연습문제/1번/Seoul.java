package ch15;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Seoul {
    public static void main(String[] args) {
        String filePath = "D:/seoul_people.csv";
        ArrayList<District> people = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), "EUC-KR"))) {

            String line;
            boolean first = true;

            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                if (first) {
                    first = false;
                    continue;
                }

                String gucode = st.nextToken().trim();
                String gu = st.nextToken().trim();
                int yearmonth = Integer.parseInt(st.nextToken().trim());
                int totalpeople = Integer.parseInt(st.nextToken().trim());
                int sedesu = Integer.parseInt(st.nextToken().trim());
                double sededang = Double.parseDouble(st.nextToken().trim());
                int male = Integer.parseInt(st.nextToken().trim());
                int female = Integer.parseInt(st.nextToken().trim());

                people.add(new District(gucode, gu, yearmonth, totalpeople, sedesu, sededang, male, female));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        District maxmale = Collections.max(people, Comparator.comparing(District :: getmale));
        District minmale = Collections.min(people, Comparator.comparing(District :: getmale));

        District maxfemale = Collections.max(people, Comparator.comparing(District :: getfemale));
        District minfemale = Collections.min(people, Comparator.comparing(District :: getfemale));

        District maxsededang = Collections.max(people, Comparator.comparing(District :: getsededag));
        District minsededang = Collections.min(people, Comparator.comparing(District :: getsededag));

        double totalpeople = 0;
        int i = 0;

        for(District d : people){
            totalpeople += d.get_totalpeople();
            i++;

        }

        double avgpeople = (double)(totalpeople/i);

        System.out.println("남자 인구가 가장 많은 구: " + maxmale.getgucode() + " (" + maxmale.getmale() + ")");
        System.out.println("남자 인구가 가장 적은 구: " + minmale.getgucode() + " (" + minmale.getmale() + ")");
        System.out.println("여자 인구가 가장 많은 구: " + maxfemale.getgucode() + " (" + maxfemale.getfemale() + ")");
        System.out.println("여자 인구가 가장 적은 구: " + minfemale.getgucode() + " (" + minfemale.getfemale() + ")");
        System.out.println("세대당 인구가 가장 많은 구: " + maxsededang.getgucode() + " (" + maxsededang.getsededag() + ")");
        System.out.println("세대당 인구가 가장 적은 구: " + minsededang.getgucode() + " (" + minsededang.getsededag() + ")");
        System.out.println("구의 평균 인구수: " + String.format("%.2f",avgpeople));
    }
}



