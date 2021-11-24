import { Loaisanpham } from 'app/shared/model/enumerations/loaisanpham.model';

export interface IElectroniceDevice {
  id?: number;
  tenSanpham?: string | null;
  giaBan?: number | null;
  loaiSanpham?: Loaisanpham | null;
  soluong?: number | null;
  sanphammoi?: boolean | null;
  overView?: string | null;
  dungLuong?: string | null;
  maHoa?: string | null;
  tocDo?: string | null;
  mTBF?: string | null;
  nANDFlash?: string | null;
  heDieuhanhhotro?: string | null;
}

export const defaultValue: Readonly<IElectroniceDevice> = {
  sanphammoi: false,
};
