package ch15;

public class District implements Comparable<District> {
    private String gucode, gu;
    private int yearmonth, totalpeople, sedesu, male, female;
    private double sededang;

    public District(String gucode, String gu, int yearmonth, int totalpeople, int sedesu, double sededang, int male, int female){
        this.gucode = gucode;
        this.gu = gu;
        this.yearmonth = yearmonth;
        this.totalpeople = totalpeople;
        this.sedesu = sedesu;
        this.sededang = sededang;
        this. male = male;
        this.female = female;
    }

    public String getgucode(){
        return gucode;
    }
    
    public String getgu(){
        return gu;
    }

    public int getyearmonth(){
        return yearmonth;
    }

    public int get_totalpeople(){
        return totalpeople;
    }

    public int getsedesu(){
        return sedesu;
    }

    public double getsededag(){
        return sededang;
    }

    public int getmale(){
        return male;
    }

    public int getfemale(){
        return female;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %f %d %d", gucode, gu, yearmonth, totalpeople, sedesu, sededang, male, female);
    }

    @Override
    public int compareTo(District d) {
        // TODO Auto-generated method stub
        return 0;
    }
}
