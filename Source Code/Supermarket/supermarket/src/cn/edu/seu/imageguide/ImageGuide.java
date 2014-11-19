package cn.edu.seu.imageguide;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import oracle.sql.BLOB;


/**
 * ImageGuide entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="IMAGE_GUIDE"
    ,schema="SYSTEM"
)

public class ImageGuide  implements java.io.Serializable {


    // Fields    

     private String notes;
     private String imagename;
     private String num;
     private byte[] imagestream;
     private String remark;


    // Constructors

    /** default constructor */
    public ImageGuide() {
    }

	/** minimal constructor */
    public ImageGuide(String notes) {
        this.notes = notes;
    }
    
    /** full constructor */
    public ImageGuide(String notes, String imagename, String num, byte[] imagestream, String remark) {
        this.notes = notes;
        this.imagename = imagename;
        this.num = num;
        this.imagestream = imagestream;
        this.remark = remark;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="NOTES", unique=true, nullable=false, length=16)

    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @Column(name="IMAGENAME", length=50)

    public String getImagename() {
        return this.imagename;
    }
    
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
    
    @Column(name="NUM", length=10)

    public String getNum() {
        return this.num;
    }
    
    public void setNum(String num) {
        this.num = num;
    }
    
    @Column(name="IMAGESTREAM",columnDefinition="BLOB")

    public byte[] getImagestream() {
        return this.imagestream;
    }
    
    public void setImagestream(byte[] imagestream) {
        this.imagestream = imagestream;
    }
    
    @Column(name="REMARK", length=50)

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}