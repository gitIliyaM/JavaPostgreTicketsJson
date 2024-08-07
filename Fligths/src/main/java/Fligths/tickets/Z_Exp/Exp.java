package Fligths.tickets.Z_Exp;

public class Exp {
     /*String a = "01:12";
        String b = "10:25";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date aa = format.parse(a);
            Date bb = format.parse(b);
        System.out.println(bb.compareTo(aa));*/

        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = simpleDateFormat.parse("2022-12-06");
        Date date2 = simpleDateFormat.parse("2022-13-06");
        System.out.println(date2.compareTo(date1));*/

        /*String firstTime = "17:00";
        String secondTime = "16:09";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            long a = format.parse(firstTime).getTime();
            long b = format.parse(secondTime).getTime();
            long diff = a - b;
            System.out.println("Difference is " + (diff / (60 * 1000)) + " minutes.");
        } catch (ParseException e) {
            //Parsing error
        }*/
    //ReadJson fromJson = new ReadJson();

        /*Map<Integer, Integer> hashSet = new HashMap<>();
        hashSet.put(0, 9);
        hashSet.put(1, 10);
        hashSet.put(2, 8);
        hashSet.put(3, 12);
        hashSet.put(4, 7);
        hashSet.put(5, 15);

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hashSet.entrySet());
        list.sort(Map.Entry.comparingByValue());
        System.out.println(list);

        Date date = new SimpleDateFormat("uuuu M, dd").parse("2017.9.11");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");

        LocalDate localDate1 = LocalDate.parse("2023.5.9", dateFormatter);
        LocalDate localDate2 = LocalDate.parse("2024.6.18", dateFormatter);

        LocalDateTime localDateTime1 = LocalDateTime.of(localDate1,LocalTime.of(10,20));
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate2,LocalTime.of(20,20));

        Period p = Period.between(localDateTime1.toLocalDate(),localDateTime2.toLocalDate());
        Duration duration = Duration.between(localDateTime1.toLocalTime(),localDateTime2.toLocalTime());

        System.out.println(p.getDays());

        System.out.println(localDate1);

        System.out.println(localDate1.format(dateFormatter));

        System.out.println("--------------------------");*/

    /*if(compareDates(departureDate,arrivalDate)){
                // проверка по времени
                stringList.add(getStringDurationTime(durationTime, zeroNoZero));
            }   // проверка по дате
            else{
                Integer hour = getIntHour(durationTime);

            }*/

    /*boolean compareDates(String departureDate, String arrivalDate){
        if(departureDate.compareTo(arrivalDate) == 0){
            return true;
        }
        return false;
    }*/

    /*
     String departureDate = jo.getJsonObject("Владивосток").getString("departure_date");
     String arrivalDate = jo.getJsonObject("Тель-Авив").getString("arrival_date");
     */
}
