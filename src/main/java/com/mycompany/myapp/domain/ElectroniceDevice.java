package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.Loaisanpham;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A ElectroniceDevice.
 */
@Entity
@Table(name = "electronice_device")
public class ElectroniceDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_sanpham")
    private String tenSanpham;

    @Column(name = "gia_ban")
    private Long giaBan;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_sanpham")
    private Loaisanpham loaiSanpham;

    @Column(name = "soluong")
    private Long soluong;

    @Column(name = "sanphammoi")
    private Boolean sanphammoi;

    @Column(name = "over_view")
    private String overView;

    @Column(name = "dung_luong")
    private String dungLuong;

    @Column(name = "ma_hoa")
    private String maHoa;

    @Column(name = "toc_do")
    private String tocDo;

    @Column(name = "m_tbf")
    private String mTBF;

    @Column(name = "n_and_flash")
    private String nANDFlash;

    @Column(name = "he_dieuhanhhotro")
    private String heDieuhanhhotro;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ElectroniceDevice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSanpham() {
        return this.tenSanpham;
    }

    public ElectroniceDevice tenSanpham(String tenSanpham) {
        this.setTenSanpham(tenSanpham);
        return this;
    }

    public void setTenSanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
    }

    public Long getGiaBan() {
        return this.giaBan;
    }

    public ElectroniceDevice giaBan(Long giaBan) {
        this.setGiaBan(giaBan);
        return this;
    }

    public void setGiaBan(Long giaBan) {
        this.giaBan = giaBan;
    }

    public Loaisanpham getLoaiSanpham() {
        return this.loaiSanpham;
    }

    public ElectroniceDevice loaiSanpham(Loaisanpham loaiSanpham) {
        this.setLoaiSanpham(loaiSanpham);
        return this;
    }

    public void setLoaiSanpham(Loaisanpham loaiSanpham) {
        this.loaiSanpham = loaiSanpham;
    }

    public Long getSoluong() {
        return this.soluong;
    }

    public ElectroniceDevice soluong(Long soluong) {
        this.setSoluong(soluong);
        return this;
    }

    public void setSoluong(Long soluong) {
        this.soluong = soluong;
    }

    public Boolean getSanphammoi() {
        return this.sanphammoi;
    }

    public ElectroniceDevice sanphammoi(Boolean sanphammoi) {
        this.setSanphammoi(sanphammoi);
        return this;
    }

    public void setSanphammoi(Boolean sanphammoi) {
        this.sanphammoi = sanphammoi;
    }

    public String getOverView() {
        return this.overView;
    }

    public ElectroniceDevice overView(String overView) {
        this.setOverView(overView);
        return this;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getDungLuong() {
        return this.dungLuong;
    }

    public ElectroniceDevice dungLuong(String dungLuong) {
        this.setDungLuong(dungLuong);
        return this;
    }

    public void setDungLuong(String dungLuong) {
        this.dungLuong = dungLuong;
    }

    public String getMaHoa() {
        return this.maHoa;
    }

    public ElectroniceDevice maHoa(String maHoa) {
        this.setMaHoa(maHoa);
        return this;
    }

    public void setMaHoa(String maHoa) {
        this.maHoa = maHoa;
    }

    public String getTocDo() {
        return this.tocDo;
    }

    public ElectroniceDevice tocDo(String tocDo) {
        this.setTocDo(tocDo);
        return this;
    }

    public void setTocDo(String tocDo) {
        this.tocDo = tocDo;
    }

    public String getmTBF() {
        return this.mTBF;
    }

    public ElectroniceDevice mTBF(String mTBF) {
        this.setmTBF(mTBF);
        return this;
    }

    public void setmTBF(String mTBF) {
        this.mTBF = mTBF;
    }

    public String getnANDFlash() {
        return this.nANDFlash;
    }

    public ElectroniceDevice nANDFlash(String nANDFlash) {
        this.setnANDFlash(nANDFlash);
        return this;
    }

    public void setnANDFlash(String nANDFlash) {
        this.nANDFlash = nANDFlash;
    }

    public String getHeDieuhanhhotro() {
        return this.heDieuhanhhotro;
    }

    public ElectroniceDevice heDieuhanhhotro(String heDieuhanhhotro) {
        this.setHeDieuhanhhotro(heDieuhanhhotro);
        return this;
    }

    public void setHeDieuhanhhotro(String heDieuhanhhotro) {
        this.heDieuhanhhotro = heDieuhanhhotro;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElectroniceDevice)) {
            return false;
        }
        return id != null && id.equals(((ElectroniceDevice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElectroniceDevice{" +
            "id=" + getId() +
            ", tenSanpham='" + getTenSanpham() + "'" +
            ", giaBan=" + getGiaBan() +
            ", loaiSanpham='" + getLoaiSanpham() + "'" +
            ", soluong=" + getSoluong() +
            ", sanphammoi='" + getSanphammoi() + "'" +
            ", overView='" + getOverView() + "'" +
            ", dungLuong='" + getDungLuong() + "'" +
            ", maHoa='" + getMaHoa() + "'" +
            ", tocDo='" + getTocDo() + "'" +
            ", mTBF='" + getmTBF() + "'" +
            ", nANDFlash='" + getnANDFlash() + "'" +
            ", heDieuhanhhotro='" + getHeDieuhanhhotro() + "'" +
            "}";
    }
}
