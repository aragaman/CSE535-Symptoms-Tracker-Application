package com.example.DrSmart;

import android.os.Parcel;
import android.os.Parcelable;

public class DBModel implements Parcelable {
    Integer HEART_RATE;
    Integer RESPIRATORY_RATE;
    Integer RATE_NAUSEA;
    Integer RATE_HEADACHE;
    Integer RATE_DIARRHEA;
    Integer RATE_SORE_THROAT;
    Integer RATE_FEVER;
    Integer RATE_MUSCLE_PAIN;
    Integer RATE_SMELL_TASTE;
    Integer RATE_COUGH;
    Integer RATE_SHORTNESS_BREATH;
    Integer RATE_TIRED;
    String USER_NAME;

    protected DBModel(Parcel in) {
        if (in.readByte() == 0) {
            HEART_RATE = null;
        } else {
            HEART_RATE = in.readInt();
        }
        if (in.readByte() == 0) {
            RESPIRATORY_RATE = null;
        } else {
            RESPIRATORY_RATE = in.readInt();
        }

        if (in.readByte() == 0) {
            RATE_NAUSEA = null;
        } else {
            RATE_NAUSEA = in.readInt();
        }

        if (in.readByte() == 0) {
            RATE_HEADACHE = null;
        } else {
            RATE_HEADACHE = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_DIARRHEA = null;
        } else {
            RATE_DIARRHEA = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_SORE_THROAT = null;
        } else {
            RATE_SORE_THROAT = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_FEVER = null;
        } else {
            RATE_FEVER = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_MUSCLE_PAIN = null;
        } else {
            RATE_MUSCLE_PAIN = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_SMELL_TASTE = null;
        } else {
            RATE_SMELL_TASTE = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_COUGH = null;
        } else {
            RATE_COUGH = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_SHORTNESS_BREATH = null;
        } else {
            RATE_SHORTNESS_BREATH = in.readInt();
        }
        if (in.readByte() == 0) {
            RATE_TIRED = null;
        } else {
            RATE_TIRED = in.readInt();
        }
        USER_NAME = in.readString();
    }

    public static final Creator<DBModel> CREATOR = new Creator<DBModel>() {
        @Override
        public DBModel createFromParcel(Parcel in) {
            return new DBModel(in);
        }

        @Override
        public DBModel[] newArray(int size) {
            return new DBModel[size];
        }
    };

    @Override
    public String toString() {
        return "Readings{" +
                "HEART_RATE=" + HEART_RATE +
                ", RESPIRATORY_RATE=" + RESPIRATORY_RATE +
                ", NAUSEA=" + RATE_NAUSEA +
                ", HEADACHE=" + RATE_HEADACHE +
                ", DIARRHEA=" + RATE_DIARRHEA +
                ", SORE_THROAT=" + RATE_SORE_THROAT +
                ", FEVER=" + RATE_FEVER +
                ", MUSCLE_ACHE=" + RATE_MUSCLE_PAIN +
                ", LOSS_SMELL_TASTE=" + RATE_SMELL_TASTE +
                ", COUGH=" + RATE_COUGH +
                ", SHORTNESS_BREATH=" + RATE_SHORTNESS_BREATH +
                ", FEELING_TIRED=" + RATE_TIRED +
                ", USER_NAME='" + USER_NAME + '\'' +
                '}';
    }

    public DBModel() {
        this.HEART_RATE = 0;
        this.RESPIRATORY_RATE = 0;
        this.RATE_NAUSEA = 0;
        this.RATE_HEADACHE = 0;
        this.RATE_DIARRHEA = 0;
        this.RATE_SORE_THROAT = 0;
        this.RATE_FEVER = 0;
        this.RATE_MUSCLE_PAIN = 0;
        this.RATE_SMELL_TASTE = 0;
        this.RATE_COUGH = 0;
        this.RATE_SHORTNESS_BREATH = 0;
        this.RATE_TIRED = 0;
        this.USER_NAME = "";
    }

    public DBModel(Integer HEART_RATE, Integer RESPIRATORY_RATE, Integer RATE_NAUSEA, Integer RATE_HEADACHE, Integer RATE_DIARRHEA, Integer RATE_SORE_THROAT, Integer RATE_FEVER, Integer RATE_MUSCLE_PAIN, Integer RATE_SMELL_TASTE, Integer RATE_COUGH, Integer RATE_SHORTNESS_BREATH, Integer RATE_TIRED, String USER_NAME) {
        this.HEART_RATE = HEART_RATE;
        this.RESPIRATORY_RATE = RESPIRATORY_RATE;
        this.RATE_NAUSEA = RATE_NAUSEA;
        this.RATE_HEADACHE = RATE_HEADACHE;
        this.RATE_DIARRHEA = RATE_DIARRHEA;
        this.RATE_SORE_THROAT = RATE_SORE_THROAT;
        this.RATE_FEVER = RATE_FEVER;
        this.RATE_MUSCLE_PAIN = RATE_MUSCLE_PAIN;
        this.RATE_SMELL_TASTE = RATE_SMELL_TASTE;
        this.RATE_COUGH = RATE_COUGH;
        this.RATE_SHORTNESS_BREATH = RATE_SHORTNESS_BREATH;
        this.RATE_TIRED = RATE_TIRED;
        this.USER_NAME = USER_NAME;
    }

    public Integer getHEART_RATE() {
        return HEART_RATE;
    }

    public void setHEART_RATE(Integer HEART_RATE) {
        this.HEART_RATE = HEART_RATE;
    }

    public Integer getRESPIRATORY_RATE() {
        return RESPIRATORY_RATE;
    }

    public void setRESPIRATORY_RATE(Integer RESPIRATORY_RATE) {
        this.RESPIRATORY_RATE = RESPIRATORY_RATE;
    }

    public Integer getRATE_HEADACHE() {
        return RATE_HEADACHE;
    }

    public void setRATE_HEADACHE(Integer RATE_HEADACHE) {
        this.RATE_HEADACHE = RATE_HEADACHE;
    }

    public Integer getRATE_DIARRHEA() {
        return RATE_DIARRHEA;
    }

    public void setRATE_DIARRHEA(Integer RATE_DIARRHEA) {
        this.RATE_DIARRHEA = RATE_DIARRHEA;
    }

    public Integer getRATE_SORE_THROAT() {
        return RATE_SORE_THROAT;
    }

    public void setRATE_SORE_THROAT(Integer RATE_SORE_THROAT) {
        this.RATE_SORE_THROAT = RATE_SORE_THROAT;
    }

    public Integer getRATE_FEVER() {
        return RATE_FEVER;
    }

    public void setRATE_FEVER(Integer RATE_FEVER) {
        this.RATE_FEVER = RATE_FEVER;
    }

    public Integer getRATE_MUSCLE_PAIN() {
        return RATE_MUSCLE_PAIN;
    }

    public void setRATE_MUSCLE_PAIN(Integer RATE_MUSCLE_PAIN) {
        this.RATE_MUSCLE_PAIN = RATE_MUSCLE_PAIN;
    }

    public Integer getRATE_SMELL_TASTE() {
        return RATE_SMELL_TASTE;
    }

    public void setRATE_SMELL_TASTE(Integer RATE_SMELL_TASTE) {
        this.RATE_SMELL_TASTE = RATE_SMELL_TASTE;
    }

    public Integer getRATE_COUGH() {
        return RATE_COUGH;
    }

    public void setRATE_COUGH(Integer RATE_COUGH) {
        this.RATE_COUGH = RATE_COUGH;
    }

    public Integer getRATE_SHORTNESS_BREATH() {
        return RATE_SHORTNESS_BREATH;
    }

    public void setRATE_SHORTNESS_BREATH(Integer RATE_SHORTNESS_BREATH) {
        this.RATE_SHORTNESS_BREATH = RATE_SHORTNESS_BREATH;
    }

    public Integer getRATE_TIRED() {
        return RATE_TIRED;
    }

    public void setRATE_TIRED(Integer RATE_TIRED) {
        this.RATE_TIRED = RATE_TIRED;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public Integer getRATE_NAUSEA() {
        return RATE_NAUSEA;
    }

    public void setRATE_NAUSEA(Integer RATE_NAUSEA) {
        this.RATE_NAUSEA = RATE_NAUSEA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (HEART_RATE == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(HEART_RATE);
        }

        if (RESPIRATORY_RATE == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RESPIRATORY_RATE);
        }
        if (RATE_NAUSEA == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_NAUSEA);
        }
        if (RATE_HEADACHE == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_HEADACHE);
        }
        if (RATE_DIARRHEA == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_DIARRHEA);
        }
        if (RATE_SORE_THROAT == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_SORE_THROAT);
        }
        if (RATE_FEVER == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_FEVER);
        }
        if (RATE_MUSCLE_PAIN == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_MUSCLE_PAIN);
        }
        if (RATE_SMELL_TASTE == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_SMELL_TASTE);
        }
        if (RATE_COUGH == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_COUGH);
        }
        if (RATE_SHORTNESS_BREATH == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_SHORTNESS_BREATH);
        }
        if (RATE_TIRED == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(RATE_TIRED);
        }
        dest.writeString(USER_NAME);
    }
}
