package com.app.a20221207_tula_nycschools.model;

public class SchoolModel {
    private String school_name, phone_number, overview_paragraph, school_email, website,
            num_of_sat_test_takers, sat_critical_reading_avg_score, sat_math_avg_score,
            sat_writing_avg_score, dbn, attendance_rate, school_sports, total_students,
            city, language_classes, location, latitude, longitude;

    public String getLocation() {
        return location;
    }
    public String getDbn() {
        return dbn;
    }
    public String getSchool_name() {
        return school_name;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getOverview_paragraph() {
        return overview_paragraph;
    }
    public String getSchool_email() {
        return school_email;
    }
    public String getWebsite() {
        return website;
    }
    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }
    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }
    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }
    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }
    public String getAttendance_rate() {
        return attendance_rate;
    }
    public String getSchool_sports() {
        return school_sports;
    }
    public String getTotal_students() {
        return total_students;
    }
    public String getCity() {
        return city;
    }
    public String getLanguage_classes() {
        if (language_classes==null){
            return "N/A";
        }
        return language_classes ;
    }
    public void setNum_of_sat_test_takers(String num_of_sat_test_takers) {
        this.num_of_sat_test_takers = num_of_sat_test_takers;
    }
    public void setSat_critical_reading_avg_score(String sat_critical_reading_avg_score) {
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
    }
    public void setSat_math_avg_score(String sat_math_avg_score) {
        this.sat_math_avg_score = sat_math_avg_score;
    }
    public void setSat_writing_avg_score(String sat_writing_avg_score) {
        this.sat_writing_avg_score = sat_writing_avg_score;
    }

    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
}
