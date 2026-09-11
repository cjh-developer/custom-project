package org.project.custom.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    // date
    public static final String PATTERN_YMD           = "yyyy-MM-dd";
    public static final String PATTERN_YMD_COMPACT   = "yyyyMMdd";
    public static final String PATTERN_DATETIME      = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATETIME_MS   = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_ISO_OFFSET    = "yyyy-MM-dd'T'HH:mm:ssXXX";

    private static final DateTimeFormatter FMT_YMD         = DateTimeFormatter.ofPattern(PATTERN_YMD);
    private static final DateTimeFormatter FMT_YMD_COMPACT = DateTimeFormatter.ofPattern(PATTERN_YMD_COMPACT);
    private static final DateTimeFormatter FMT_DATETIME    = DateTimeFormatter.ofPattern(PATTERN_DATETIME);
    private static final DateTimeFormatter FMT_DATETIME_MS = DateTimeFormatter.ofPattern(PATTERN_DATETIME_MS);
    private static final DateTimeFormatter FMT_ISO_OFFSET  = DateTimeFormatter.ofPattern(PATTERN_ISO_OFFSET);

    /* ================= 시간 단위 상수 ================= */

    public static final long SECOND_MILLIS = 1_000L;
    public static final long MINUTE_MILLIS = 60L * SECOND_MILLIS;
    public static final long HOUR_MILLIS   = 60L * MINUTE_MILLIS;
    public static final long DAY_MILLIS    = 24L * HOUR_MILLIS;

    /** 시각 공급 주체 */
    private static volatile Clock clock = Clock.systemDefaultZone();

    private DateUtil() {
        throw new AssertionError("DateUtil not Accept Instance");
    }

    public static void setClock(Clock newClock) {
        clock = (newClock != null) ? newClock : Clock.systemDefaultZone();
    }

    public static void resetClock(){
        clock = Clock.systemDefaultZone();
    }

    public static ZoneId zone(){
        return clock.getZone();
    }

    /**
     * 현재 시각
     * @return
     */
    public static long currentTime(){
        return clock.millis();
    }

    /**
     * 현재 시각(초)
     * @return
     */
    public static long currentSecond(){
        return clock.millis() / SECOND_MILLIS;
    }

    /**
     * 현재 시각 + offset(밀리초)
     * @param offsetMills
     * @return
     */
    public static long currentTime(long offsetMills){
        return clock.millis() + offsetMills;
    }

    /**
     * 현재 시각 문자열(밀리초 숫자)
     * @return
     */
    public static String currentTimeString(){
        return String.valueOf(currentTime());
    }

    /**
     * 현재 시각 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String currentDateTimeString(){
        return format(currentTime());
    }

    /**
     * 현재 시각 "yyyy-MM-dd"
     * @return
     */
    public static String currentYmd(){
        return formatYmd(currentTime());
    }

    /**
     * 밀리초 -> 초
     * @param mills
     * @return
     */
    public static long toSeconds( long mills){
        return Math.floorDiv(mills, SECOND_MILLIS);
    }

    /**
     * 초 -> 밀리초
     * @param seconds
     * @return
     */
    public static long toMills( long seconds){
        return Math.multiplyExact(seconds, SECOND_MILLIS);
    }

    public static long plusSeconds(long millis, long seconds) {
        return millis + seconds * SECOND_MILLIS;
    }

    public static long plusMinutes(long millis, long minutes) {
        return millis + minutes * MINUTE_MILLIS;
    }

    public static long plusHours  (long millis, long hours)   {
        return millis + hours   * HOUR_MILLIS;
    }

    public static long plusDays   (long millis, long days)    {
        return millis + days    * DAY_MILLIS;
    }

    /** 지금부터 minutes 분 뒤(밀리초). 토큰 만료 시각 계산 등에 쓴다. */
    public static long futureMinutes(long minutes) {
        return plusMinutes(currentTime(), minutes);
    }

    /** 지금부터 days 일 뒤(밀리초) */
    public static long futureDays(long days) { return plusDays(currentTime(), days); }

    /* ================= 비교 ================= */

    /** baseMillis 로부터 지금까지 흐른 시간(밀리초) */
    public static long elapsed(long baseMillis) {
        return currentTime() - baseMillis;
    }

    /**
     * baseMills + ttlMills가 이미 지났는 가
     * base가 null이라면 "기준 시각이 없다" = 만료로 확인 ( 비밀번호 변경 이력이 없는 계정 등 )
     * @param baseMillis
     * @param ttlMillis
     * @return
     */
    public static boolean isExpired( Long baseMillis, long ttlMillis){
        if( baseMillis == null ){
            return true;
        }
        return currentTime() > baseMillis + ttlMillis;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * @param millis
     * @return
     */
    public static String format( long millis){
        return format(millis, FMT_DATETIME);
    }

    /**
     * null 이면 null을 그대로 반환
     * @param millis
     * @return
     */
    public static String format( Long millis){
        return (millis == null) ? null : format(millis.longValue());
    }

    /**
     * yyyy-MM-dd
     * @param millis
     * @return
     */
    public static String formatYmd( long millis){
        return format(millis, FMT_YMD);
    }

    public static String formatYmd(Long millis) {
        return (millis == null) ? null : formatYmd(millis.longValue());
    }

    /** "yyyyMMdd" */
    public static String formatYmdCompact(long millis) {
        return format(millis, FMT_YMD_COMPACT);
    }

    /** "yyyy-MM-dd HH:mm:ss.SSS" — 로그용 */
    public static String formatWithMillis(long millis) {
        return format(millis, FMT_DATETIME_MS);
    }

    /** "yyyy-MM-dd'T'HH:mm:ss+09:00" — 외부 연동용 */
    public static String formatIso(long millis) {
        return format(millis, FMT_ISO_OFFSET);
    }

    /** 임의 패턴. 상수로 등록된 패턴이면 위 전용 메서드를 쓰는 편이 빠르다. */
    public static String format(long millis, String pattern) {
        return format(millis, DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(long millis, DateTimeFormatter formatter) {
        return formatter.format(toZonedDateTime(millis));
    }

    public static String format(long millis, DateTimeFormatter formatter, ZoneId zoneId) {
        return formatter.format(Instant.ofEpochMilli(millis).atZone(zoneId));
    }

    /**
     * yyyy-MM-dd HH:mm:ss -> 밀리초
     * @param text
     * @return
     */
    public static long parse(String text) {
        return parse(text, FMT_DATETIME);
    }

    /**
     * yyyy-MM-dd -> 그 날 00:00:00의 밀리초
     * @param text
     * @return
     */
    public static long parseYmd(String text) {
        return LocalDate.parse(text, FMT_YMD).atStartOfDay(zone()).toInstant().toEpochMilli();
    }

    public static long parse(String text, String pattern) {
        return parse(text, DateTimeFormatter.ofPattern(pattern));
    }

    public static long parse(String text, DateTimeFormatter formatter) {
        return LocalDateTime.parse(text, formatter).atZone(zone()).toInstant().toEpochMilli();
    }

    /**
     * 파싱 실패 시 예외 대신 null 처리
     * @param text
     * @param formatter
     * @return
     */
    public static Long parseOrNull( String text, DateTimeFormatter formatter){
        if( StringCheck.isEmpty(text) || text.isBlank()){
            return null;
        }

        try{
            return parse(text, formatter);
        }catch (DateTimeParseException e){
            return null;
        }
    }

    /**
     * 변환
     * @param millis
     * @return
     */
    public static ZonedDateTime toZonedDateTime(long millis){
        return Instant.ofEpochMilli(millis).atZone(zone());
    }

    public static LocalDateTime toLocalDateTime(long millis){
        return toZonedDateTime(millis).toLocalDateTime();
    }

    public static LocalDate toLocalDate(long millis){
        return toZonedDateTime(millis).toLocalDate();
    }

    public static long toEpochMilli( LocalDateTime dateTime){
        return dateTime.atZone(zone()).toInstant().toEpochMilli();
    }

    public static long toEpochMilli(LocalDate date){
        return date.atStartOfDay(zone()).toInstant().toEpochMilli();
    }


    /**
     * 해당 시각이 속한 날의 00:00:00.000
     * @param millis
     * @return
     */
    public static long startOfDay( long millis){
        return toZonedDateTime(millis).toLocalDate().atStartOfDay(zone()).toInstant().toEpochMilli();
    }

    /**
     * 해당 시각이 속한 날의 다음 날 00:00:00.000
     * @param millis
     * @return
     */
    public static long startOfNextDay( long millis){
        return startOfDay(millis) + DAY_MILLIS;
    }

    /**
     * 오늘 00:00:00.000
     * @return
     */
    public static long todayStart(){
        return startOfDay(currentTime());
    }

    public static String[] getYMD( long millis){
        ZonedDateTime zonedDateTime = toZonedDateTime(millis);
        return new String[]{
                String.valueOf(zonedDateTime.getYear()),
                String.valueOf(zonedDateTime.getMonthValue()),
                String.valueOf(zonedDateTime.getDayOfMonth()),
                String.valueOf(zonedDateTime.getHour()),
        };
    }


}
