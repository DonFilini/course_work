package com.example.hs;
import jakarta.persistence.*;

@Entity
@Table(name = "h_system")
public class Hs {
    private Long id;
    private String type;
    private String location;
    private Long funding;
    private Long residents_registered;
    private Long residents_per_month;
    private Long average_score;


    protected Hs() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getFunding() {
        return funding;
    }

    public void setFunding(Long funding) {
        this.funding = funding;
    }

    public Long getResidents_registered() {
        return residents_registered;
    }

    public void setResidents_registered(Long residents_registered) {
        this.residents_registered = residents_registered;
    }

    public Long getResidents_per_month() {
        return residents_per_month;
    }

    public void setResidents_per_month(Long residents_per_month) {
        this.residents_per_month = residents_per_month;
    }

    public Long getAverage_score() {
        return average_score;
    }

    public void setAverage_score(Long average_score) {
        this.average_score = average_score;
    }

    @Override
    public String toString() {
        return "performance [id=" + id + ", type=" + type + ", funding=" + funding + ", residents_registered=" + residents_registered +
                ", residents_per_month=" + residents_per_month + ", average_score=" +average_score + "]";
    }
}







