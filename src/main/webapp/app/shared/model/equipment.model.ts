export interface IEquipment {
  id?: number;
  code?: string;
  name?: string;
  equipmentTypeID?: number;
  status?: number;
  description?: string;
  qrcode?: string;
}

export class Equipment implements IEquipment {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public equipmentTypeID?: number,
    public status?: number,
    public description?: string,
    public qrcode?: string
  ) {}
}
