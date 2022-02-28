package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Otp;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "otp_tbl")
@Data
@NoArgsConstructor
public class OtpTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "phone_number")
    private long phoneNumber;
    private long otp;

    public OtpTable(long id,long phoneNumber, long otp) {
        this.id = id;
        this.phoneNumber=phoneNumber;
        this.otp = otp;
    }

    public Otp toOtp() {
        return new Otp(this.id,this.phoneNumber, this.otp);
    }
}
