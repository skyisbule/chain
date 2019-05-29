package com.github.skyisbule.chain.domain;

import java.io.Serializable;

/**
 * db_stu_info
 * @author 
 */
public class StuInfo implements Serializable {
    private Integer sid;

    private String stuName;

    private String school;

    private String timeRange;

    private String idNum;

    private String md5;

    private String exangeHash;

    public String getAll(){
        return stuName + " " +school + " " +timeRange + " " + idNum;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getExangeHash() {
        return exangeHash;
    }

    public void setExangeHash(String exangeHash) {
        this.exangeHash = exangeHash;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StuInfo other = (StuInfo) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getStuName() == null ? other.getStuName() == null : this.getStuName().equals(other.getStuName()))
            && (this.getSchool() == null ? other.getSchool() == null : this.getSchool().equals(other.getSchool()))
            && (this.getTimeRange() == null ? other.getTimeRange() == null : this.getTimeRange().equals(other.getTimeRange()))
            && (this.getIdNum() == null ? other.getIdNum() == null : this.getIdNum().equals(other.getIdNum()))
            && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
            && (this.getExangeHash() == null ? other.getExangeHash() == null : this.getExangeHash().equals(other.getExangeHash()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getStuName() == null) ? 0 : getStuName().hashCode());
        result = prime * result + ((getSchool() == null) ? 0 : getSchool().hashCode());
        result = prime * result + ((getTimeRange() == null) ? 0 : getTimeRange().hashCode());
        result = prime * result + ((getIdNum() == null) ? 0 : getIdNum().hashCode());
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
        result = prime * result + ((getExangeHash() == null) ? 0 : getExangeHash().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", stuName=").append(stuName);
        sb.append(", school=").append(school);
        sb.append(", timeRange=").append(timeRange);
        sb.append(", idNum=").append(idNum);
        sb.append(", md5=").append(md5);
        sb.append(", exangeHash=").append(exangeHash);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}